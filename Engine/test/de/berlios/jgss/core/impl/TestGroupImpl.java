package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Group;
import de.berlios.jgss.core.HColor;

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

public class TestGroupImpl extends TestCase {
   
	
	 String name = "name";
     String email = "email";
     Group parent = new GroupImpl();
     Set childs = new HashSet();
     Set vacations = new HashSet();
     HColor color = new HColor();
     Integer size = new Integer(1);
	
     public void testPojoClass() {
       

        Group group = new GroupImpl(name, email, parent, childs, vacations,
                color, size);

        // getter
        assertEquals(name, group.getName());
        assertEquals(email, group.getEmail());
        assertSame(parent, group.getParent());
        assertEquals(childs, group.getChilds());
        assertEquals(vacations, group.getVacations());
        assertSame(color, group.getColor());
        assertEquals(size, group.getSize());

        String name2 = "name2";
        String email2 = "email2";
        Group parent2 = new GroupImpl();
        Set childs2 = new HashSet();
        Set vacations2 = new HashSet();
        HColor color2 = new HColor();
        Integer size2 = new Integer(2);

        // setter
        group.setName(name2);
        assertEquals(name2, group.getName());
        
        group.setEmail(email2);
        assertEquals(email2, group.getEmail());
        
        group.setParent(parent2);
        assertSame(parent2, group.getParent());
        
        group.setChilds(childs2);
        assertEquals(childs2, group.getChilds());
        
        group.setVacations(vacations2);
        assertEquals(vacations2, group.getVacations());
        
        group.setColor(color2);
        assertSame(color2, group.getColor());
        
        group.setSize(size2);
        assertEquals(size2, group.getSize());
    }
	
	public void testAddGroup() {
    	
		System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			AbstractDataModel tmpModel2 = new AbstractDataModel(GroupImpl.class,"From GroupImpl");
			Group grp1 = new GroupImpl("groupe toto",(Group)tmpModel2.getList().get(0),new TreeSet());
			
			
			
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(grp1);
			ModelManager.getInstance().commit();

			tmpModel2 = new AbstractDataModel(GroupImpl.class,"From GroupImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				Group grp = (Group)it.next();
				if(grp.getName().equals(grp1.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(grp);
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
    
    
 
	 public void testRemoveGroup() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
		
    	
    	try {
    		
    	AbstractDataModel tmpModel2 = new AbstractDataModel(GroupImpl.class,"From GroupImpl");
    	GroupImpl grp2 = new GroupImpl("groupe tata",(Group)tmpModel2.getList().get(0),new TreeSet());
    	
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(grp2);
		ModelManager.getInstance().commit();
		
			
			
			tmpModel2=new AbstractDataModel(GroupImpl.class,"From GroupImpl");
			for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
				Group grp= (Group)it.next();
				if(grp.getName().equals(grp2.getName())&&
						grp.getParent().equals(grp2.getParent())&&
						grp.getChilds().equals(grp2.getChilds()))
					 { 				 
						ModelManager.getInstance().beginTransaction();
						ModelManager.getInstance().delete(grp);
						ModelManager.getInstance().commit();
						assertTrue(true);
						return;
				}
			}
			
		} catch (RemoteException e) {e.printStackTrace();}
		assertFalse(true);
    }
    
}
