/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.api.model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.rmi.datamodels.RemoteDataModel;
import de.berlios.jgss.core.rmi.datamodels.RemoteModelManager;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.pref.APIPreferences;


/**
 * ModelManager
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
public class ModelManager {
	private final Map<Class,Map<Serializable,Entity>> objects = new HashMap<Class,Map<Serializable,Entity>>();
	private final Map<String,RemoteDataModel> remoteModels = new HashMap<String,RemoteDataModel>();
	private final RemoteModelManager remoteManager;
	private static final ModelManager manager=new ModelManager();
    
    
	public static ModelManager getInstance(){
		return manager;
	}
	private ModelManager(){
		RemoteModelManager tmp=null;
		try {
            String host=APIPreferences.getInstance().getServerHost();
			int port=APIPreferences.getInstance().getServerPort();
            if((host==null)||(port<0))
                    throw new RuntimeException("Problème de configuration ...");
			tmp=(RemoteModelManager) Naming.lookup("//"+host+":"+port+"/"+RemoteModelManager.REGISTRY_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		remoteManager=tmp;
	}
	public RemoteDataModel getModel(Class clas,String query){
		RemoteDataModel rModel=(RemoteDataModel) remoteModels.get(query);
		if(rModel==null){
			rModel= remoteManager.getModel(clas,query);
			remoteModels.put(query,rModel);
		}
		return rModel;
	}
	public Entity getObject(Class clas,Serializable id,Long version){
		synchronized(objects){
			Map<Serializable,Entity> objectsForClass = objects.get(clas);
			Entity e = null;
			if(objectsForClass==null){
				objectsForClass=new HashMap<Serializable,Entity>();
				objects.put(clas,objectsForClass);
			}else{
				e=(Entity) objectsForClass.get(id);	
			}
			if((e==null)||(version.longValue()<0)||(e.getVersion().compareTo(version)<0)){
				e=load(clas,id);
				objectsForClass.put(id,e);
			}
			return e;
		}
	}
	private Entity load(Class clas,Serializable id){
		try {
            return remoteManager.load(clas,id);
        } catch (JGSSException e) {
            e.printStackTrace();
        }
        return null;
	}
    private String currentSessionId;
    public void beginTransaction(){
        try {
            currentSessionId=remoteManager.beginTransaction();
        } catch (JGSSException e) {
            e.printStackTrace();
        }
    }
    public void commit(){
        try {
            remoteManager.commit(currentSessionId);
        } catch (JGSSException e) {
            e.printStackTrace();
        }
    }
    public void rollback(){
        try {
            remoteManager.rollback(currentSessionId);
        } catch (JGSSException e) {
            e.printStackTrace();
        }
    }
    public Entity save(Entity e){
        try {
            return remoteManager.save(e,currentSessionId);
        } catch (JGSSException e1) {
            e1.printStackTrace();
        }
        return null;
    }
    public Entity update(Entity e){
        try {
            return remoteManager.update(e,currentSessionId);
        } catch (JGSSException e1) {
            e1.printStackTrace();
        }
        return null;
    }
    public void delete(Entity e){
        try {
            remoteManager.delete(e,currentSessionId);
        } catch (JGSSException e1) {
            e1.printStackTrace();
        }
    }
}
