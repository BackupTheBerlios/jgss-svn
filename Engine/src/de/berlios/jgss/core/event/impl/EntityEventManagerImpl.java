/*
 * Créé le 19 janv. 2005
 */
package de.berlios.jgss.core.event.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.event.EntityEvent;
import de.berlios.jgss.core.event.EntityEventListener;
import de.berlios.jgss.core.event.EntityEventManager;


/**
 * EntityEventManagerImpl
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
public class EntityEventManagerImpl  implements EntityEventManager {
	private static final int MAX_TRY = 3;
	private static EntityEventManager manager;
	public static EntityEventManager getManager(){
		if(manager==null)
			manager=new EntityEventManagerImpl();
			
		return manager;
	}
	private final ConcurrentHashMap<Entity,EntityEvent> waiting=new ConcurrentHashMap<Entity,EntityEvent>();
	private final Map<Class,List<EntityEventListener>> listenerList =new HashMap<Class,List<EntityEventListener>>();
	private final ExecutorService pool;
	private EntityEventManagerImpl(){
		pool=Executors.newCachedThreadPool();
	}
	
	public void addToWaiting(EntityEvent[] e){
		if(e!=null)
			for(int i =0 ; i<e.length;i++){
			addToWaiting(e[i]);
		}
	}
	public void addToWaiting(EntityEvent e){
		if(e!=null)
			waiting.putIfAbsent(e.getEntity(),e);
	}
	public void postFlush(Iterator entities) {
		for(;entities.hasNext();){
			Entity entity=(Entity)entities.next();
			waiting.putIfAbsent(entity,new EntityEventImpl(EntityEvent.ENTITY_UPDATED,entity));
		}
	}
	public void sendAllWaiting(){
		for(EntityEvent e:waiting.values()){
			fireEntityChanged(e);
		}
		waiting.clear();
	}
	public void clearWaiting(){
		waiting.clear();
	}
	private void fireEntityChanged(final EntityEvent event) {
		List<EntityEventListener> lists=listenerList.get(event.getEntity().getClass());
		if(lists!=null)
			for(final EntityEventListener listener:lists){
				Runnable task = new Runnable ()
				{
					public void run ()
					{
						int iTry = 0;
						for ( ; iTry < MAX_TRY; iTry ++)
						{
							try
							{
								listener.entityChanged (event);
								break;
							}
							catch (Exception e){}
                            
						}
						
						// Unregister this listener
						if (iTry >= MAX_TRY)
						{
							System.out.println ("Unregister the client listener");
							removeEntityEventListener (event.getEntity().getClass(), listener);
						}
					}
				};
				pool.execute (task);
			}
	}
	
	public void addEntityEventListener(Class clas,EntityEventListener listener) {
		List<EntityEventListener> al=listenerList.get(clas);
		if(al==null){
			al =new ArrayList<EntityEventListener>();
			listenerList.put(clas,al);
		}
		al.add(listener);
	}

	public void removeEntityEventListener(Class clas,EntityEventListener listener) {
		List al=listenerList.get(clas);
		if(al!=null){
			al.remove(listener);
		}
	}

	

}
