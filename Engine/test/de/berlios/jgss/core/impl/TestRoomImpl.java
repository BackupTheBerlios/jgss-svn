package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Building;
import de.berlios.jgss.core.HColor;
import de.berlios.jgss.core.Room;
import de.berlios.jgss.core.RoomType;



public class TestRoomImpl extends TestCase { 

    public void testPojoClass(){
    	
    	//test getter
    	String name="titi";
    	Set rooms=new TreeSet();
    	Integer capacity=12;
    	Integer stage=2;
    	RoomType roomType = new RoomTypeImpl();
    	Building building = new BuildingImpl();
    	Set roomsGroup = new TreeSet();
    	HColor color = new HColor();
    	Set courses = new TreeSet();
    	
    	
    	RoomImpl r = new RoomImpl(name,rooms,capacity,stage,roomType,building,roomsGroup,color,courses);
    	assertEquals(r.getName(),name);
    	assertSame(r.getRooms(),rooms);
    	assertEquals(r.getCapacity(),capacity);
    	assertEquals(r.getStage(),stage);
    	assertSame(r.getRoomType(),roomType);
    	assertSame(r.getBuilding(),building);
    	assertSame(r.getRoomsGroups(),roomsGroup);
    	assertEquals(r.getColor(),color);
    	assertSame(r.getCourses(),courses);
    	
    	
    	//test setter
    	String name2="titi";
    	Set rooms2=new TreeSet();
    	Integer capacity2=12;
    	Integer stage2=2;
    	RoomType roomType2 = new RoomTypeImpl();
    	Building building2 = new BuildingImpl();
    	Set roomsGroup2 = new TreeSet();
    	HColor color2 = new HColor();
    	Set courses2 = new TreeSet();
    	
    	r.setName(name2);
    	r.setRooms(rooms2);
    	r.setCapacity(capacity2);
    	r.setStage(stage2);
    	r.setRoomType(roomType2);
    	r.setBuilding(building2);
    	r.setRoomsGroups(roomsGroup2);
    	r.setColor(color2);
    	r.setCourses(courses2);
    	
    	
    	
    	assertEquals(r.getName(),name2);
    	assertSame(r.getRooms(),rooms2);
    	assertEquals(r.getCapacity(),capacity2);
    	assertEquals(r.getStage(),stage2);
    	assertSame(r.getRoomType(),roomType2);
    	assertSame(r.getBuilding(),building2);
    	assertSame(r.getRoomsGroups(),roomsGroup2);
    	assertEquals(r.getColor(),color2);
    	assertSame(r.getCourses(),courses2);
        
    }
    
    
    public void testAddRoom() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			
			Room room = new RoomImpl();
			room.setName("room group");
			room.setColor(null);
			room.setRooms(null);
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(room);
			ModelManager.getInstance().commit();

			AbstractDataModel tmpModel=new AbstractDataModel(RoomImpl.class,"From RoomImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Room r = (Room)it.next();
				if(r.getName().equals(room.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(r);
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
    
    
    public void testRemoveRoom() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		Room r = new RoomImpl();
		r.setName("room 6");
		r.setColor(null);
		r.setRooms(null);
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(r);
		ModelManager.getInstance().commit();
		try {
			
			
			AbstractDataModel tmpModel=new AbstractDataModel(RoomImpl.class,"From RoomImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Room peo = (Room)it.next();
				if(peo.getName().equals(r.getName())) {
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
