/*
 * Créé le 19 janv. 2005
 */
package de.berlios.jgss.core.transaction;

import java.util.Properties;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.engine.SessionImplementor;
import net.sf.hibernate.transaction.TransactionFactory;

/**
 * JDBCTransactionFactory
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
public class JDBCTransactionFactory implements TransactionFactory {


	public Transaction beginTransaction(SessionImplementor session) throws HibernateException {
		JDBCTransaction tx = new JDBCTransaction(session);
		tx.begin();
		return tx;
	}
	public void configure(Properties props) throws HibernateException {}

}
