package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Set;

import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Address;
import de.berlios.jgss.core.Professor;
import de.berlios.jgss.core.Room;
import de.berlios.jgss.core.impl.AddressImpl;
import de.berlios.jgss.core.impl.ProfessorImpl;
import de.berlios.jgss.core.impl.RoomImpl;

import junit.framework.TestCase;

public class TestProfessorImpl extends TestCase {
    public void testPojoClass() {

        String name = "Prof 1";
        Professor prof = new ProfessorImpl(name, null, null, null);
        assertTrue(prof.getName().equals(name));
    }

    public void testAddProfessor() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            Address address = (Address) new AbstractDataModel(
                    AddressImpl.class, "From AddressImpl").getList().get(0);
            Room office = (Room) new AbstractDataModel(RoomImpl.class,
                    "From RoomImpl").getList().get(0);
            Set availabilities = null;

            Professor prof = new ProfessorImpl("teacher1", address, office,
                    availabilities);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(prof);
            ModelManager.getInstance().commit();

            int find = 0;

            for (Iterator it = new AbstractDataModel(ProfessorImpl.class,
                    "From ProfessorImpl").getList().iterator(); it.hasNext();) {
                Professor pr = (Professor) it.next();
                if (pr.getName().equals(prof.getName())) {
                    System.out.println(pr.getName()+" "+prof.getName());
                    ++find;
                }
            }
            System.out.println("find add " + find);
            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void testRemoveProfessor() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "teacher1";

            Professor teacher1 = null;

            for (Iterator it = new AbstractDataModel(ProfessorImpl.class,
                    "From ProfessorImpl").getList().iterator(); it.hasNext();) {
                Professor tmp = (Professor) it.next();

                if (name.equals(tmp.getName())) {
                    teacher1 = tmp;
                    break;
                }
            }

            if (teacher1 == null)
                assertNull(teacher1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().delete(teacher1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(ProfessorImpl.class,
                    "From ProfessorImpl").getList().iterator(); it.hasNext();) {
                Professor tmp = (Professor) it.next();

                if (teacher1.getName().equals(tmp.getName())) {
                    ++find;
                }
            }
            System.out.println("find delete" + find);
            assertTrue(find == 0);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
