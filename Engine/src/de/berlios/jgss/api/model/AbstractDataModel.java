/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.api.model;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.event.EntityEvent;
import de.berlios.jgss.core.rmi.datamodels.RemoteDataModel;


/**
 * AbstractDataModel
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
 * site: http://hangtime.dnsalias.net:8080<br>
 * bugs: http://hangtime.free.fr/mantis<br>
 * <br>
 */
public class AbstractDataModel extends UnicastRemoteObject implements DataModel,Remote{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private final List<DataListener> listeners = new ArrayList<DataListener>();
	private final RemoteDataModel model;
    private final List<Entity> entitiesList=new ArrayList<Entity>(); 
	private final Map<Serializable,Entity> entities = new HashMap<Serializable,Entity>();
	public AbstractDataModel(Class clas,String query) throws RemoteException{
		model = ModelManager.getInstance().getModel(clas,query);
		model.addListener(this);
		List<Entity> lightEntities = model.getLightDatas();
        for(Entity e:lightEntities){
            Entity ent =ModelManager.getInstance().getObject(e.getClas(),e.getId(),e.getVersion());
			entities.put(e.getId(),ent);
            entitiesList.add(ent);
		}
	}
	public void addDataListener(DataListener l) {
		listeners.add(l);
	}
	
	public void removeDataListener(DataListener l) {
		listeners.remove(l);
	}
	private void fireDataChanged(EntityEvent e){
        for(DataListener l:listeners){
			l.dataChanged(e);
		}
	}
	public void remoteDataChanged(EntityEvent e) {
        if((e==null)||(e.getEntity()==null))return;
		switch(e.getType()){
			case EntityEvent.ENTITY_INSERTED:{Entity ent =ModelManager.getInstance().getObject(e.getEntity().getClass(),e.getEntity().getId(),e.getEntity().getVersion());
                                            entities.put(e.getEntity().getId(),ent);
                                            entitiesList.add(ent);
											break;}
			case EntityEvent.ENTITY_DELETED:{entities.remove(e.getEntity().getId());
                                            Entity ent =ModelManager.getInstance().getObject(e.getEntity().getClass(),e.getEntity().getId(),e.getEntity().getVersion());
                                            entitiesList.remove(ent);
											break;}
			case EntityEvent.ENTITY_UPDATED:{Entity ent =ModelManager.getInstance().getObject(e.getEntity().getClass(),e.getEntity().getId(),e.getEntity().getVersion());
                                            entities.remove(e.getEntity().getId());
											entities.put(e.getEntity().getId(),ent);
                                            entitiesList.remove(ent);
                                            entitiesList.add(ent);
											break;}
		}
		fireDataChanged(e);
	}
	public List<Entity> getList(){
        
		return entitiesList;
	}
}
