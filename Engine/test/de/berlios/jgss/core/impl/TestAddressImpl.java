package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Address;

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
public class TestAddressImpl extends TestCase {
	
	 String street = "allée louis aragon";
     long postalCode = 93160;
     String city = "Noisy le grand";
     Address addr1 = new AddressImpl(street,postalCode,city);
 	 
 	 String street2 = "rue des plantes";
     long postalCode2 = 57000;
     String city2 = "Metz";
     Address addr2 = new AddressImpl(street2,postalCode2,city2);
     
     AbstractDataModel tmpModel=null;
 	 
    public void testPojoClass() {

        Address address = new AddressImpl(street, postalCode, city);

        // test getter
        assertEquals(address.getStreet(),street);
        assertEquals(address.getCity(),city);
        assertEquals((long)address.getPostalCode(),postalCode);

        // test setter
        address.setStreet(street2);
        assertEquals(address.getStreet(),street2);
        address.setCity(city2);
        assertEquals(address.getCity(),city2);
        address.setPostalCode(postalCode2);
        assertEquals((long)address.getPostalCode(),postalCode2);
    }
    
    public void testAddAddress() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
		try {
			ModelManager.getInstance().beginTransaction();
			ModelManager.getInstance().save(addr1);
			ModelManager.getInstance().commit();

			tmpModel=new AbstractDataModel(AddressImpl.class,"From AddressImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Address addr = (Address)it.next();
				if(addr.getCity().equals(addr1.getCity())&& 
				   addr.getPostalCode().equals(addr1.getPostalCode())&&
				   addr.getStreet().equals(addr1.getStreet())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(addr);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			fail();

		} catch (RemoteException e) {e.printStackTrace();}		       
    }
    
    
   public void testRemoveAddress() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(addr2);
		ModelManager.getInstance().commit();
		try {
			
			
			tmpModel=new AbstractDataModel(AddressImpl.class,"From AddressImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Address addr = (Address)it.next();
				if(addr.getCity().equals(addr2.getCity())) {
					 
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(addr);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			
		} catch (RemoteException e) {e.printStackTrace();}
		assertFalse(true);
    }
   
}
