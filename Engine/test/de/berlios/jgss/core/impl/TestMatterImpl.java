package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Matter;


/**
 * 
 * @author rlataix
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestMatterImpl extends TestCase{
	
	
	AbstractDataModel tmpModel=null;
	Matter matter1 = new MatterImpl("matiere 1");
	Matter matter2 = new MatterImpl("matiere 2");

    public void testPojoClass(){
        String name="name";

        Matter matter=new MatterImpl(name);
        
        //getter
        assertEquals(matter.getName(),name);
        
        String name2="name2";
        
        //setter
        matter.setName(name2);
        assertEquals(matter.getName(),name2);
    }
    
   public void testAddMatter() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(matter1);
			ModelManager.getInstance().commit();

			tmpModel=new AbstractDataModel(MatterImpl.class,"From MatterImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Matter mat = (Matter)it.next();
				if(mat.getName().equals(matter1.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(mat);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			fail();

		} catch (RemoteException e) {e.printStackTrace();}		       
    }
    
    
    public void testRemoveMatter() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(matter2);
		ModelManager.getInstance().commit();
		try {
			
			
			tmpModel=new AbstractDataModel(MatterImpl.class,"From MatterImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Matter mat = (Matter)it.next();
				if(mat.getName().equals(matter2.getName())) {
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
