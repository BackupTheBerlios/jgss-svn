package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Address;
import de.berlios.jgss.core.People;

/**
 * 
 * @author rlataix
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TestPeopleImpl extends TestCase {

    public void testPojoClass() {
        String mobilePhone = "0612345678";
        String firstName = "prenom";
        String email = "mail";
        String homePhone = "012345678";
        String workPhone = "018765432";
        String name = "name";
        Address address = new AddressImpl();

        People people = new PeopleImpl(mobilePhone, firstName, email,
                homePhone, workPhone, name, address);

        // getter
        assertEquals(mobilePhone, people.getMobilePhone());
        assertEquals(firstName, people.getFirstName());
        assertEquals(email, people.getEmail());
        assertEquals(homePhone, people.getHomePhone());
        assertEquals(workPhone, people.getWorkPhone());
        assertEquals(name, people.getName());
        assertSame(address, people.getAddress());

        // setter
        String mobilePhone2 = "068765432";
        String firstName2 = "prenom2";
        String email2 = "mail2";
        String homePhone2 = "018765432";
        String workPhone2 = "012345678";
        String name2 = "name2";
        Address address2 = new AddressImpl();

        people.setMobilePhone(mobilePhone2);
        assertEquals(mobilePhone2, people.getMobilePhone());
        people.setFirstName(firstName2);
        assertEquals(firstName2, people.getFirstName());
        people.setEmail(email2);
        assertEquals(email2, people.getEmail());
        people.setHomePhone(homePhone2);
        assertEquals(homePhone2, people.getHomePhone());
        people.setWorkPhone(workPhone2);
        assertEquals(workPhone2, people.getWorkPhone());
        people.setName(name2);
        assertEquals(name2, people.getName());
        people.setAddress(address2);
        assertSame(address2, people.getAddress());
    }
    
    public void testAddPeople() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			
			People people = new PeopleImpl("0101010101", "sfre","dsq@dsq.fr", "0102030405","0145216859", "SEB",  null);
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(people);
			ModelManager.getInstance().commit();

			AbstractDataModel tmpModel=new AbstractDataModel(PeopleImpl.class,"From PeopleImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				People peo = (People)it.next();
				if(peo.getName().equals(people.getName())) {
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
    
    
    public void testRemoveMatter() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		People people = new PeopleImpl("0101010101", "sfre","dsq@dsq.fr", "0102030405", "0501020306","SEV", null);
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(people);
		ModelManager.getInstance().commit();
		try {
			
			
			AbstractDataModel tmpModel=new AbstractDataModel(PeopleImpl.class,"From PeopleImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				People peo = (People)it.next();
				if(peo.getName().equals(people.getName())) {
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
