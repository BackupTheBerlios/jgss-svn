/*
 * Créé le 19 janv. 2005
 */
package de.berlios.jgss.core.transaction;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.event.EntityEvent;
import de.berlios.jgss.core.event.impl.EntityEventImpl;
import de.berlios.jgss.core.event.impl.EntityEventManagerImpl;

import net.sf.hibernate.FlushMode;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.TransactionException;
import net.sf.hibernate.engine.SessionImplementor;
/**
 * JDBCTransaction
 * <br>
 * @author Grégory Cuellar ( HangTime - Development and Technical Director )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Université Marne-la-Vallée<br>
 * 	Cité Descartes<br>
 *  5, bd Descartes<br>
 *  Champs-sur-Marne<br>
 *  77454 MARNE-LA-VALLEE CEDEX 2<br>
 *  FRANCE<br>
 * <br>
 * e-mail: gcuellar@etudiant.univ-mlv.fr<br>
 * site: http://hangtime.dnsalias.net:8080
 * bugs: http://hangtime.free.fr/mantis
 * <br>
 */
public class JDBCTransaction implements Transaction {

	private SessionImplementor session;
	private boolean toggleAutoCommit;
	private boolean rolledBack;
	private boolean committed;
	private boolean begun;
	private boolean commitFailed;
	
	private static final Log log = LogFactory.getLog(JDBCTransaction.class);
	
	public JDBCTransaction(SessionImplementor session) throws HibernateException {
		this.session = session;
	}
	
	public void begin() throws HibernateException {
		
		log.debug("begin");
		
		try {
			toggleAutoCommit = session.connection().getAutoCommit();
			if ( log.isDebugEnabled() ) log.debug("current autocommit status:" + toggleAutoCommit);
			if (toggleAutoCommit) {
				log.debug("disabling autocommit");
				session.connection().setAutoCommit(false);
			}
		}
		catch (SQLException e) {
			log.error("Begin failed", e);
			throw new TransactionException("Begin failed with SQL exception: ", e);
		}
		
		begun = true;
	}
	
	public void commit() throws HibernateException {
		
		if (!begun) throw new TransactionException("Transaction not successfully started");
		try {
			EntityEventManagerImpl.getManager().addToWaiting(getWaitingObjects());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		log.debug("commit");
		
		if ( session.getFlushMode()!=FlushMode.NEVER ) session.flush();
		try {
			
			session.connection().commit();
			committed = true;
			session.afterTransactionCompletion(true);
			EntityEventManagerImpl.getManager().sendAllWaiting();
		}
		catch (SQLException e) {
			log.error("Commit failed", e);
			session.afterTransactionCompletion(false);
			commitFailed = true;
			throw new TransactionException("Commit failed with SQL exception: ", e);
		}
		finally {
			EntityEventManagerImpl.getManager().clearWaiting();
			toggleAutoCommit();
		}
	}
	
	public void rollback() throws HibernateException {
		
		if (!begun) throw new TransactionException("Transaction not successfully started");
		
		log.debug("rollback");
		
		if (!commitFailed) {
			try {
				session.connection().rollback();
				rolledBack = true;
                EntityEventManagerImpl.getManager().clearWaiting();
			}
			catch (SQLException e) {
				log.error("Rollback failed", e);
				throw new TransactionException("Rollback failed with SQL exception: ", e);
			}
			finally {
				EntityEventManagerImpl.getManager().clearWaiting();
				session.afterTransactionCompletion(false);
				toggleAutoCommit();
			}
		}
	}
	
	private void toggleAutoCommit() {
		try {
			if (toggleAutoCommit) {
				log.debug("re-enabling autocommit");
				session.connection().setAutoCommit(true);
			}
		}
		catch (Exception sqle) {
			log.error("Could not toggle autocommit", sqle);
			//swallow it (the transaction _was_ successful or successfully rolled back)
		}
	}
	
	public boolean wasRolledBack() {
		return rolledBack;
	}
	public boolean wasCommitted() {
		return committed;
	}

	private EntityEvent[] getWaitingObjects() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchMethodException{
		int size =0;
		Field fieldInsertions=session.getClass().getDeclaredField("insertions");
		Field fieldDeletions=session.getClass().getDeclaredField("deletions");
		Field fieldUpdates=session.getClass().getDeclaredField("updates");
		
		fieldInsertions.setAccessible(true);
		fieldDeletions.setAccessible(true);
		fieldUpdates.setAccessible(true);
		
		List modif[] = {(List)fieldInsertions.get(session),(List)fieldDeletions.get(session),(List)fieldUpdates.get(session)};
		size = modif[0].size()+modif[1].size()+modif[2].size();
		EntityEvent[] ret = new EntityEvent[size];
		int index=0;
		for(int i=0;i<3;i++){
			
			for(Object tmp : modif[i]){
				Field fTmp = tmp.getClass().getSuperclass().getDeclaredField("instance");
				fTmp.setAccessible(true);
				Entity tmpEntity =(Entity)fTmp.get(tmp);
				ret[index]=new EntityEventImpl(i,tmpEntity);
				++index;
			}
		}
		return ret;
		
	}
}
