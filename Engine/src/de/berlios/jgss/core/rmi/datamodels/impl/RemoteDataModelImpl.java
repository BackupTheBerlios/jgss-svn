/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.core.rmi.datamodels.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.berlios.jgss.api.model.DataModel;
import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.event.EntityEvent;
import de.berlios.jgss.core.event.impl.EntityEventManagerImpl;
import de.berlios.jgss.core.impl.EntityImpl;
import de.berlios.jgss.core.rmi.datamodels.RemoteDataModel;
import de.berlios.jgss.core.session.SessionManager;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Session;

/**
 * RemoteDataModelImpl
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
public class RemoteDataModelImpl implements RemoteDataModel{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAX_TRY = 3;
	private final ExecutorService pool;
	private final String query;
    private List<Entity> oldDatas;
	protected RemoteDataModelImpl(Class clas,String _query){
		query=_query;
		pool=Executors.newCachedThreadPool();
        EntityEventManagerImpl.getManager().addEntityEventListener(clas,this);
        oldDatas=getDatas();
	}

	private final List<DataModel> listeners = new ArrayList<DataModel>();
	public void addListener(DataModel m) {
		listeners.add(m);
	}

	
	public void removeListener(DataModel m) {
		listeners.remove(m);
	}

	private List<Entity> getDatas() {
		Session s=null;
		try {
			s=SessionManager.getSession();
			List<Entity> ret;
			
			ret = s.find(query);
		} catch (JGSSException e) {
			e.printStackTrace();
		}finally{
			try {
				if(s!=null)s.close();
			} catch (JGSSException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public List<Entity> getLightDatas() {
		List<Entity> datas = getDatas();
		List<Entity> ret =new ArrayList<Entity>(); 
		if(datas!=null){
			for(Entity entity:datas){
				Entity newOne = new EntityImpl(entity.getId(),entity.getVersion(),entity.getClass(),Entity.TYPE_DEFAULT);
				ret.add(newOne);
			}
		}
		return ret;
	}
	public void entityChanged(EntityEvent e){
        boolean isUsed=false;
        if(e.getType()==EntityEvent.ENTITY_DELETED){
            if(oldDatas!=null){
                if(oldDatas.contains(e.getEntity())){
                    isUsed=true;
                }else{
                    for(Entity entity:oldDatas){
                        if(entity.use(e.getEntity())){
                            isUsed=true;
                            break;
                        }
                    }
                }
            }
            
        }else{
    		List<Entity> newList = getDatas();
    		
    		if(newList!=null){
    			if(newList.contains(e.getEntity())){
    				isUsed=true;
    			}else{
    				for(Entity entity:newList){
    					if(entity.use(e.getEntity())){
    						isUsed=true;
    						break;
    					}
    				}
    			}
    		}
        }
        oldDatas=getDatas();
		if(isUsed)
			fireRemoteDataChanged(e);
	}

	private void fireRemoteDataChanged(final EntityEvent event){
		for(final DataModel listener:listeners){
			Runnable task = new Runnable ()
			{
				public void run ()
				{
					int iTry = 0;
					for ( ; iTry < MAX_TRY; iTry ++)
					{
						try
						{
							listener.remoteDataChanged(event);
							break;
						}
						catch (Exception e){}
					}
					
					// Unregister this listener
					if (iTry >= MAX_TRY)
					{
						System.out.println ("Unregister the client listener");
						removeListener (listener);
					}
				}
			};
			pool.execute (task);
		}
	}
	public void unreferenced() {
		try {
			UnicastRemoteObject.unexportObject(this,true);
			System.gc();
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
	}

}
