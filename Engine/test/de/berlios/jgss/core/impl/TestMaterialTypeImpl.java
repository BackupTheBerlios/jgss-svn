package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.MaterialType;
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

public class TestMaterialTypeImpl extends TestCase{

    public void testPojoClass(){
        String name="name";
        
        MaterialType materialType=new MaterialTypeImpl(name);
        
        //getter
        assertEquals(materialType.getName(),name);
        
        String name2="name2";
        
        //setter
        materialType.setName(name2);
        assertEquals(materialType.getName(),name2);
    } 
    
    public void testAddTypeMaterial() {
    	
		System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {

			MaterialType mat1 = new MaterialTypeImpl("material type 1");
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(mat1);
			ModelManager.getInstance().commit();

			AbstractDataModel tmpModel2 = new AbstractDataModel(MaterialTypeImpl.class,"From MaterialTypeImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				MaterialType mat = (MaterialType)it.next();
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
    
    
 
	 public void testRemoveTypeMaterial() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
		
    	
    	try {
    		
    	MaterialTypeImpl matType2 = new MaterialTypeImpl("type material 2");
    	
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(matType2);
		ModelManager.getInstance().commit();
		
			
			
			AbstractDataModel tmpModel2=new AbstractDataModel(MaterialTypeImpl.class,"From MaterialTypeImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				MaterialType mat= (MaterialType)it.next();
				if(mat.getName().equals(matType2.getName()))
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
