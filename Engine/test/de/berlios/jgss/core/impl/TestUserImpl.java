package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Address;
import de.berlios.jgss.core.Role;
import de.berlios.jgss.core.User;
import de.berlios.jgss.core.impl.AddressImpl;
import de.berlios.jgss.core.impl.RoleImpl;
import de.berlios.jgss.core.impl.UserImpl;
import de.berlios.jgss.core.impl.VacationImpl;

import junit.framework.TestCase;
/**
 * 
 * TestUserImpl<br>
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
public class TestUserImpl extends TestCase {
    /**
     * this method test the getter and the setter of the UserImpl class
     *
     */
    public void testPojoClass() {

        String mobilePhone = "0112121212";
        String workPhone = "0236541254";
        String homePhone = "0147852365";
        String name = "toto";
        String firstName = "tutu";
        String email = "sdsqdf@dsqf.fr";
        Address address = new AddressImpl();
        String ldapName = "toto";
        String password = "45613";
        Role role = new RoleImpl();

        User user = new UserImpl(mobilePhone, firstName, email, homePhone,
                workPhone, name, address, ldapName, role, password);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getHomePhone(), homePhone);
        assertEquals(user.getLdapName(), ldapName);
        assertEquals(user.getMobilePhone(), mobilePhone);
        assertEquals(user.getName(), name);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getWorkPhone(), workPhone);
        assertSame(user.getAddress(), address);
        assertSame(user.getRole(), role);

        String mobilePhone2 = "0112121522";
        String workPhone2 = "0236541252";
        String homePhone2 = "0147852635";
        String name2 = "toto2";
        String firstName2 = "tutu2";
        String email2 = "sdsqdf@dsq2f.fr";
        Address address2 = new AddressImpl();
        String ldapName2 = "toto2";
        String password2 = "456153";
        Role role2 = new RoleImpl();

        user.setAddress(address);
        user.setEmail(email2);
        user.setFirstName(firstName2);
        user.setHomePhone(homePhone2);
        user.setLdapName(ldapName2);
        user.setMobilePhone(mobilePhone2);
        user.setName(name2);
        user.setPassword(password2);
        user.setWorkPhone(workPhone2);
        user.setRole(role);

        assertEquals(user.getEmail(), email2);
        assertEquals(user.getFirstName(), firstName2);
        assertEquals(user.getHomePhone(), homePhone2);
        assertEquals(user.getLdapName(), ldapName2);
        assertEquals(user.getMobilePhone(), mobilePhone2);
        assertEquals(user.getName(), name2);
        assertEquals(user.getPassword(), password2);
        assertEquals(user.getWorkPhone(), workPhone2);
        assertSame(user.getAddress(), address);
        assertSame(user.getRole(), role);

    }

    /**
     * this method test the add of User in the database
     *
     */
    public void testAddUser() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            Address address1 = (Address) new AbstractDataModel(
                    VacationImpl.class, "From AddressImpl").getList().get(0);
            Role role1 = (Role) new AbstractDataModel(VacationImpl.class,
                    "From RoleImpl").getList().get(0);

            User user1 = new UserImpl("0612345678", "firstname1",
                    "mail1@univ-mlv.fr", "0123456789", "0198765432", "name1",
                    address1, "ldapname1", role1, "pass1");

            //1rst add
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(user1);
            ModelManager.getInstance().commit();

            //2nd add to see if there are doublons
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(user1);
            ModelManager.getInstance().commit();

            AbstractDataModel tmpModel = new AbstractDataModel(UserImpl.class,
                    "From UserImpl");

            int find = 0;
            for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
                User tmp = (User) it.next();

                if (user1.getName().equals(tmp.getName())
                        && user1.getPassword().equals(tmp.getPassword())) {
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
     * this method test the update of User in the database
     *
     */
    public void testUpdateUser(){
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "ldapname1";
            String pass = "pass1";

            User user1 = null;

            for (Iterator it = new AbstractDataModel(UserImpl.class,
                    "From UserImpl").getList().iterator(); it.hasNext();) {
                User tmp = (User) it.next();

                if (name.equals(tmp.getLdapName())
                        && pass.equals(tmp.getPassword())) {
                    user1 = tmp;
                    break;
                }
            }

            if(user1==null)assertNull(user1);

            user1.setPassword("newpass1");
            
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().update(user1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(UserImpl.class,
                    "From UserImpl").getList().iterator(); it.hasNext();) {
                User tmp = (User) it.next();

                if (user1.getName().equals(tmp.getName())
                        && user1.getPassword().equals(tmp.getPassword())) {
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
     * this method remove a User in the database
     *
     */
    public void testRemoveUser() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "ldapname1";
            String pass = "newpass1";

            User user1 = null;

            for (Iterator it = new AbstractDataModel(UserImpl.class,
                    "From UserImpl").getList().iterator(); it.hasNext();) {
                User tmp = (User) it.next();

                if (name.equals(tmp.getLdapName())
                        && pass.equals(tmp.getPassword())) {
                    user1 = tmp;
                    break;
                }
            }

            if(user1==null)assertNull(user1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().delete(user1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(UserImpl.class,
                    "From UserImpl").getList().iterator(); it.hasNext();) {
                User tmp = (User) it.next();

                if (user1.getName().equals(tmp.getName())
                        && user1.getPassword().equals(tmp.getPassword())) {
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
