package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.HColor;
import de.berlios.jgss.core.RoomGroup;

/**
 * 
 * @author rlataix
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestRoomGroupImpl extends TestCase{

    public void testPojoClass(){
        int entityType=1;
        String name="name";
        Set rooms=new HashSet();
        HColor color=new HColor();
        Set courses=new HashSet();
        
        RoomGroup roomGroup=new RoomGroupImpl(entityType,name,rooms,color,courses);
        
        //getter
        assertEquals(entityType,roomGroup.getEntityType());
        assertEquals(name,roomGroup.getName());
        assertEquals(rooms,roomGroup.getRooms());
        assertSame(color,roomGroup.getColor());
        assertEquals(courses,((RoomGroupImpl)roomGroup).getCourses());
        
        
        String name2="name2";
        Set rooms2=new HashSet();
        HColor color2=new HColor();
        Set courses2=new HashSet();
        
        roomGroup.setName(name2);
        assertEquals(name2,roomGroup.getName());
        roomGroup.setRooms(rooms2);
        assertEquals(rooms2,roomGroup.getRooms());
        roomGroup.setColor(color2);
        assertSame(color2,roomGroup.getColor());
        ((RoomGroupImpl)roomGroup).setCourses(courses2);
        assertEquals(courses2,((RoomGroupImpl)roomGroup).getCourses());
    }
    
    public void testAddRoomGroup() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			
			RoomGroup rGroup = new RoomGroupImpl();
			rGroup.setName("room group");
			rGroup.setColor(null);
			rGroup.setRooms(null);
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(rGroup);
			ModelManager.getInstance().commit();

			AbstractDataModel tmpModel=new AbstractDataModel(RoomGroupImpl.class,"From RoomGroupImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				RoomGroup rg = (RoomGroup)it.next();
				if(rg.getName().equals(rGroup.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(rg);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
		} 
		catch (RemoteException e) {e.printStackTrace();
		assertFalse(true);
		}		       
		assertFalse(true);
    }
    
    
    public void testRemoveRoomGroup() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		RoomGroup rg = new RoomGroupImpl();
		rg.setName("room group");
		rg.setColor(null);
		rg.setRooms(null);
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(rg);
		ModelManager.getInstance().commit();
		try {
			
			
			AbstractDataModel tmpModel=new AbstractDataModel(RoomGroupImpl.class,"From RoomGroupImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				RoomGroup peo = (RoomGroup)it.next();
				if(peo.getName().equals(rg.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(peo);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			
		} catch (RemoteException e) {e.printStackTrace();
		assertFalse(true);
		}
		
    }
   
    
}
