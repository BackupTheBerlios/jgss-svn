package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Address;
import de.berlios.jgss.core.Group;
import de.berlios.jgss.core.Student;
import de.berlios.jgss.core.impl.AddressImpl;
import de.berlios.jgss.core.impl.GroupImpl;
import de.berlios.jgss.core.impl.StudentImpl;
import de.berlios.jgss.core.impl.VacationImpl;

import junit.framework.TestCase;

/**
 * 
 * TestStudentImpl<br>
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
public class TestStudentImpl extends TestCase {
    /**
     * This method test the getter and the setter of the TestStudentImpl class
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
        Group group = new GroupImpl();

        Student s = new StudentImpl(mobilePhone, firstName, email, homePhone,
                workPhone, name, address, group);
        assertEquals(s.getEmail(), email);
        assertEquals(s.getFirstName(), firstName);
        assertEquals(s.getHomePhone(), homePhone);
        assertEquals(s.getMobilePhone(), mobilePhone);
        assertEquals(s.getName(), name);
        assertEquals(s.getWorkPhone(), workPhone);
        assertSame(s.getAddress(), address);
        assertSame(s.getGroup(), group);

        // test setter

        String mobilePhone2 = "0112121522";
        String workPhone2 = "0236541252";
        String homePhone2 = "0147852635";
        String name2 = "toto2";
        String firstName2 = "tutu2";
        String email2 = "sdsqdf@dsq2f.fr";
        Address address2 = new AddressImpl();
        Group group2 = new GroupImpl();

        s.setAddress(address);
        s.setEmail(email2);
        s.setFirstName(firstName2);
        s.setHomePhone(homePhone2);
        s.setMobilePhone(mobilePhone2);
        s.setName(name2);
        s.setWorkPhone(workPhone2);
        s.setGroup(group2);

        assertEquals(s.getEmail(), email2);
        assertEquals(s.getFirstName(), firstName2);
        assertEquals(s.getHomePhone(), homePhone2);
        assertEquals(s.getMobilePhone(), mobilePhone2);
        assertEquals(s.getName(), name2);
        assertEquals(s.getWorkPhone(), workPhone2);
        assertSame(s.getAddress(), address);
        assertSame(s.getGroup(), group2);

    }

    /**
     * this method test the add of Student in the database
     * 
     */
    public void testAddStudent() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String mobilePhone = "0112121212";
            String workPhone = "0236541254";
            String homePhone = "0147852365";
            String name = "studentName1";
            String firstName = "studentFirstName1";
            String email = "mail1@mail.fr";
            Address address1 = (Address) new AbstractDataModel(
                    VacationImpl.class, "From AddressImpl").getList().get(0);
            Group group1 = (Group) new AbstractDataModel(GroupImpl.class,
                    "From GroupImpl").getList().get(0);

            Student student1 = new StudentImpl(mobilePhone, firstName, email,
                    homePhone, workPhone, name, address1, group1);

            // 1rst add
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(student1);
            ModelManager.getInstance().commit();

            // 2nd add to see if there are doublons
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(student1);
            ModelManager.getInstance().commit();

            AbstractDataModel tmpModel = new AbstractDataModel(
                    StudentImpl.class, "From StudentImpl");

            int find = 0;
            for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
                Student tmp = (Student) it.next();

                if (student1.getName().equals(tmp.getName())) {
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
     * this method test the update of Student in the database
     * 
     */
    
    public void testUpdateStudent() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "studentName1";
            String firstName = "studentFirstName1";
            String email = "mail1@mail.fr";
            
            Student student1 = null;

            for (Iterator it = new AbstractDataModel(StudentImpl.class,
                    "From StudentImpl").getList().iterator(); it.hasNext();) {
                student1 = (Student) it.next();

                if (name.equals(student1.getName())
                        && firstName.equals(student1.getFirstName())
                        && email.equals(student1.getEmail())) {
                    break;
                }
            }

            if (student1 == null)
                assertNull(student1);

            student1.setEmail("newmail1@mail.fr");

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().update(student1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(StudentImpl.class,
                    "From StudentImpl").getList().iterator(); it.hasNext();) {
                Student tmp = (Student) it.next();

                if (student1.getName().equals(tmp.getName())
                        && student1.getFirstName().equals(tmp.getFirstName())
                        && student1.getEmail().equals(tmp.getEmail())) {
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
     * this method remove a Student in the database
     * 
     */
    public void testRemoveStudent() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "studentName1";
            String firstName = "studentFirstName1";
            String email = "newmail1@mail.fr";

            Student student1 = null;

            for (Iterator it = new AbstractDataModel(StudentImpl.class,
                    "From StudentImpl").getList().iterator(); it.hasNext();) {
                Student tmp = (Student) it.next();

                if (name.equals(tmp.getName())
                        && firstName.equals(tmp.getFirstName())
                        && email.equals(tmp.getEmail())) {
                    student1 = tmp;
                    break;
                }
            }

            if (student1 == null)
                assertNull(student1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().delete(student1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(StudentImpl.class,
                    "From StudentImpl").getList().iterator(); it.hasNext();) {
                Student tmp = (Student) it.next();

                if (name.equals(tmp.getName())
                        && firstName.equals(tmp.getFirstName())
                        && email.equals(tmp.getEmail())) {
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
