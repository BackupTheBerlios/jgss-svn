/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.core.rmi.datamodels.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.rmi.datamodels.RemoteDataModel;
import de.berlios.jgss.core.rmi.datamodels.RemoteModelManager;
import de.berlios.jgss.core.session.SessionManager;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Session;
import de.berlios.jgss.engine.session.Transaction;

/**
 * RemoteModelManagerImpl
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
public class RemoteModelManagerImpl implements RemoteModelManager {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private static final RemoteModelManagerImpl rManager=new RemoteModelManagerImpl();
	
	public static RemoteModelManager getInstance(){
		return rManager;
	}
	

	private final Map<String,RemoteDataModel> remoteModels = new HashMap<String,RemoteDataModel>();
	
	private RemoteModelManagerImpl(){
		
	}
	public RemoteDataModel getModel(Class clas,String query){
		RemoteDataModel rModel=remoteModels.get(query);
		if(rModel==null){
				rModel= new RemoteDataModelImpl(clas,query);
				try {
					UnicastRemoteObject.exportObject(rModel,Registry.REGISTRY_PORT);
					remoteModels.put(query,rModel);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
		}
		return rModel;
	}
	public Entity load(Class clas, Serializable id) throws JGSSException {
		Session s=SessionManager.getSession();
		return s.load(clas,id);
	}
    public Entity save(Entity e,String sessionId) throws JGSSException {
		Session s=sessions.get(sessionId);
        if(s==null){
            s=SessionManager.getSession();
            Transaction tx = s.beginTransaction();
            try{
                s.save(e);
                tx.commit();    
            }catch(JGSSException ex){
                tx.rollback();
                throw ex;
            }finally{
                s.close();       
            }
        }else{
            s.save(e);
        }
        
        return e;
    }

    public Entity update(Entity e,String sessionId) throws JGSSException {
		Session s=sessions.get(sessionId);
        
        if(s==null){
            s=SessionManager.getSession();
			Transaction tx = s.beginTransaction();
            try{
                s.update(e);
                tx.commit();    
            }catch(JGSSException ex){
                tx.rollback();
                throw ex;
            }finally{
                s.close();       
            }
        }else{
            s.update(e);
        }
        
        return e;
    }

    public void delete(Entity e,String sessionId) throws JGSSException {
        Session s=sessions.get(sessionId);
        if(s==null){
            s=SessionManager.getSession();
			Transaction tx = s.beginTransaction();
            try{
                s.delete(e);
                tx.commit();    
            }catch(JGSSException ex){
                tx.rollback();
                throw ex;
            }finally{
                s.close();       
            }
        }else{
            s.delete(e);
        }
        
    }
    private Map<String,Session> sessions=new HashMap<String,Session>();
    private Map<String,Transaction> transactions=new HashMap<String,Transaction>();
    
	public String beginTransaction() throws JGSSException {
		Session s=SessionManager.getSession();
        sessions.put(s.toString(),s);
        transactions.put(s.toString(),s.beginTransaction());
        return s.toString();
    }
    
    public void commit(String sessionId) throws JGSSException {
		Session s=sessions.remove(sessionId);
        if(s==null)return;
		Transaction t=transactions.remove(sessionId);
            if(t!=null)
                t.commit();
        s.close();
    }
    
    public void rollback(String sessionId) throws JGSSException {
		Session s=sessions.remove(sessionId);
        if(s==null)return;
		Transaction t=transactions.remove(sessionId);
            if(t!=null)
                t.rollback();
        s.close();
    }
}
