package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.HColor;
import de.berlios.jgss.core.Material;
import de.berlios.jgss.core.MaterialType;
import de.berlios.jgss.core.People;
import de.berlios.jgss.core.Room;


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

public class TestMaterialImpl extends TestCase{
    public void testPojoClass(){
        String name="name";
        People owner=new PeopleImpl();
        MaterialType typeMat=new MaterialTypeImpl();
        Room room=new RoomImpl();
        HColor color=new HColor();
        
        Material material=new MaterialImpl(name,owner,typeMat,room,color);
        
        //Getter
        assertEquals(name,material.getName());
        assertSame(owner,material.getOwner());
        assertSame(typeMat,material.getTypeMat());
        assertSame(room,material.getRoom());
        assertSame(color,material.getColor());
        
        String name2="name2";
        People owner2=new PeopleImpl();
        MaterialType typeMat2=new MaterialTypeImpl();
        Room room2=new RoomImpl();
        HColor color2=new HColor();
        
        //setter
        material.setName(name2);
        assertEquals(name2,material.getName());
        material.setOwner(owner2);
        assertSame(owner2,material.getOwner());
        material.setTypeMat(typeMat2);
        assertSame(typeMat2,material.getTypeMat());
        material.setRoom(room2);
        assertSame(room2,material.getRoom());
        material.setColor(color2);
        assertSame(color2,material.getColor());
    }
    
    public void testAddMaterial() {
    	
		System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			AbstractDataModel tmpModel2 = new AbstractDataModel(MaterialImpl.class,"From MaterialImpl");
			Material mat1 = new MaterialImpl("material 1",null,null,null,null);
			
			
			
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(mat1);
			ModelManager.getInstance().commit();

			tmpModel2 = new AbstractDataModel(MaterialImpl.class,"From MaterialImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				Material mat = (Material)it.next();
				if(mat.getName().equals(mat1.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(mat);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			assertFalse(true);
    	
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
 
	 public void testRemoveMaterial() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
		
    	
    	try {
    		
    	AbstractDataModel tmpModel2 = new AbstractDataModel(MaterialImpl.class,"From MaterialImpl");
    	MaterialImpl mat2 = new MaterialImpl("material 2",null,null,null,null);
    	
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(mat2);
		ModelManager.getInstance().commit();
		
			
			
			tmpModel2=new AbstractDataModel(MaterialImpl.class,"From MaterialImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				Material mat= (Material)it.next();
				if(mat.getName().equals(mat2.getName()))
					 { 				 
						ModelManager.getInstance().beginTransaction();
						ModelManager.getInstance().delete(mat);
						ModelManager.getInstance().commit();
						assertTrue(true);
						return;
				}
			}
			
		} catch (RemoteException e) {e.printStackTrace();}
		assertFalse(true);
    }
    
}
