package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Address;
import de.berlios.jgss.core.Building;
import de.berlios.jgss.core.impl.AddressImpl;
import de.berlios.jgss.core.impl.BuildingImpl;

import junit.framework.TestCase;


/**
* <br>
* @author Sébastien Guinchard ( HangTime )<br>
* UFR Ingenieurs 2000<br>
* <br>
* Université Marne-la-Vallée<br>
* 	Cité Descartes<br>
*  5, bd Descartes<br>
*  Champs-sur-Marne<br>
*  77454 MARNE-LA-VALLEE CEDEX 2<br>
*  FRANCE<br>
* <br>
* e-mail: sguincha@etudiant.univ-mlv.fr<br>
* site: http://hangtime.dnsalias.net:8080
* bugs: http://hangtime.free.fr/mantis
* <br>
*/


public class TestBuildingImpl extends TestCase {
	
    String name1 = "build 1";
    String name2 = "build 2";
    
    
    
    public void testPojoClass(){
        String name="buildingName";
        Address address=new AddressImpl();
        
        Building building=new BuildingImpl(name,address);
        
        assertEquals(name,building.getName());
        assertSame(address,building.getAddress());
    }
    
	public void testAddBuilding() {
    	
		System.setProperty("java.rmi.server.ignoreStubClasses","true"); 
    	
		try {
			AbstractDataModel tmpModel2 = new AbstractDataModel(AddressImpl.class,"From AddressImpl");
			BuildingImpl build1 = new BuildingImpl(name1,(Address)tmpModel2.getList().get(0));
			
			
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(build1);
			ModelManager.getInstance().commit();

			tmpModel2=new AbstractDataModel(BuildingImpl.class,"From BuildingImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				Building build = (Building)it.next();
				if(build.getName().equals(build1.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(build);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			fail();
    	
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
 
	 public void testRemoveBuilding() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
		
    	
    	try {
    		
    	AbstractDataModel tmpModel2 = new AbstractDataModel(AddressImpl.class,"From AddressImpl");
    	BuildingImpl build2 = new BuildingImpl(name2,(Address)tmpModel2.getList().get(0));
    	
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(build2);
		ModelManager.getInstance().commit();
			
			tmpModel2=new AbstractDataModel(BuildingImpl.class,"From BuildingImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				Building build= (Building)it.next();
				if(build.getName().equals(build2.getName())) { 				 
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(build);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			
		} catch (RemoteException e) {e.printStackTrace();}
		assertFalse(true);
    }
      
}
