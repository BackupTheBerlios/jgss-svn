package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Role;


public class TestRoleImpl extends TestCase{

    public void testPojoClass(){
    	String name = "admin_role";

    	Role role = new RoleImpl(name,true,true,true,true,true,true);
    	assertTrue(role.getName().equals(name));
    	assertTrue(role.isAdmin() == true);
    	assertTrue(role.isEditedt() == true);
    	assertTrue(role.isEditressource() == true);
    	assertTrue(role.isViewedt() == true);
    	assertTrue(role.isViewressource() == true);
    	assertTrue(role.isViewstat() == true);
        
    }
    
    public void testAddRole() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			
			Role role = new RoleImpl("role1",true,true,true,true,true,true);
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(role);
			ModelManager.getInstance().commit();

			AbstractDataModel tmpModel=new AbstractDataModel(RoleImpl.class,"From RoleImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Role peo = (Role)it.next();
				if(peo.getName().equals(role.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(peo);
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
    
    
    public void testRemoveRole() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		Role role = new RoleImpl("role 3",true,true,true,true,true,true);
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(role);
		ModelManager.getInstance().commit();
		try {
			
			
			AbstractDataModel tmpModel=new AbstractDataModel(RoleImpl.class,"From RoleImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Role peo = (Role)it.next();
				if(peo.getName().equals(role.getName())) {
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
