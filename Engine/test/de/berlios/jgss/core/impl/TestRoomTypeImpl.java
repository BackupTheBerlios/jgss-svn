package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.RoomType;
import de.berlios.jgss.core.impl.RoomTypeImpl;

import junit.framework.TestCase;

/**
 * 
 * TestRoomTypeImpl<br>
 * <br>
 * @author Remy Lataix ( HangTime - WebMaster )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Universite Marne-la-Vallee<br>
 *  Cite Descartes<br>
 *  5, bd Descartes<br>
 *  Champs-sur-Marne<br>
 *  77454 MARNE-LA-VALLEE CEDEX 2<br>
 *  FRANCE<br>
 * <br>
 * e-mail: rlataix@etudiant.univ-mlv.fr<br>
 * site: http://hangtime.dnsalias.net:8080<br>
 * bugs: http://hangtime.free.fr/mantis<br>
 * <br>
 */
public class TestRoomTypeImpl extends TestCase{

    public void testPojoClass(){
    	
    	String name="roomTypeName1";
    	
    	//test getter
    	RoomTypeImpl room = new RoomTypeImpl(name);
    	assertEquals(room.getName(),name);
    	
    	//test setter
    	String name2 = "roomTypeName2";
    	room.setName(name2);
    	assertEquals(room.getName(),name2);
        
    }
    

    /**
     * this method test the add of RoomType in the database
     * 
     */
    public void testAddRoomType() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "roomTypeName1";

            RoomType roomType1 = new RoomTypeImpl(name);

            // 1rst add
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(roomType1);
            ModelManager.getInstance().commit();

            // 2nd add to see if there are doublons
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(roomType1);
            ModelManager.getInstance().commit();

            AbstractDataModel tmpModel = new AbstractDataModel(
                    RoomTypeImpl.class, "From RoomTypeImpl");

            int find = 0;
            for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
                RoomType tmp = (RoomType) it.next();

                if (roomType1.getName().equals(tmp.getName())) {
                    ++find;
                }
            }
            System.out.println("find " + find);
            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method test the update of RoomType in the database
     * 
     */
    
    public void testUpdateRoomType() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "roomTypeName1";
            
            RoomType roomType1 = null;

            for (Iterator it = new AbstractDataModel(RoomTypeImpl.class,
                    "From RoomTypeImpl").getList().iterator(); it.hasNext();) {
                roomType1 = (RoomType) it.next();

                if (name.equals(roomType1.getName())) {
                    break;
                }
            }

            if (roomType1 == null)
                assertNull(roomType1);

            roomType1.setName("newroomTypeName1");

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().update(roomType1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(RoomTypeImpl.class,
                    "From RoomTypeImpl").getList().iterator(); it.hasNext();) {
                RoomType tmp = (RoomType) it.next();

                if (roomType1.getName().equals(tmp.getName())) {
                    ++find;
                }
            }
            System.out.println("find " + find);
            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method remove a RoomType in the database
     * 
     */
    public void testRemoveRoomType() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "newroomTypeName1";

            RoomType roomType1 = null;

            for (Iterator it = new AbstractDataModel(RoomTypeImpl.class,
                    "From RoomTypeImpl").getList().iterator(); it.hasNext();) {
                RoomType tmp = (RoomType) it.next();

                if (name.equals(tmp.getName())) {
                    roomType1 = tmp;
                    break;
                }
            }

            if (roomType1 == null)
                assertNull(roomType1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().delete(roomType1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(RoomTypeImpl.class,
                    "From RoomTypeImpl").getList().iterator(); it.hasNext();) {
                RoomType tmp = (RoomType) it.next();

                if (name.equals(tmp.getName())) {
                    ++find;
                }
            }
            System.out.println("find " + find);
            assertTrue(find == 0);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
