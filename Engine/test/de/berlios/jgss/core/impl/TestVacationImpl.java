package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Iterator;

import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Group;
import de.berlios.jgss.core.Vacation;
import de.berlios.jgss.core.impl.GroupImpl;
import de.berlios.jgss.core.impl.VacationImpl;

import junit.framework.TestCase;

/**
 * 
 * TestVacationImpl<br>
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
public class TestVacationImpl extends TestCase {

    public void testPojoClass() {

        Date begin = Date.valueOf("2005-02-03");
        Date end = Date.valueOf("2005-02-03");
        Group grp = new GroupImpl();

        Vacation vacation = new VacationImpl(begin, end, grp);
        // test getter
        assertEquals(vacation.getBeginDate(), begin);
        assertEquals(vacation.getEndDate(), end);
        assertSame(vacation.getGroup(), grp);

        // test setter
        Date begin2 = Date.valueOf("2005-02-04");
        Date end2 = Date.valueOf("2005-02-05");
        Group group2 = new GroupImpl();
        vacation.setBeginDate(begin2);
        vacation.setEndDate(end2);
        vacation.setGroup(group2);

        assertEquals(vacation.getBeginDate(), begin2);
        assertEquals(vacation.getEndDate(), end2);
        assertSame(vacation.getGroup(), group2);
    }

    public void testAddVacation() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            Group group1 = (Group) new AbstractDataModel(GroupImpl.class,
                    "From GroupImpl").getList().get(0);

            Vacation vacation1 = new VacationImpl(Date.valueOf("2004-05-03"),
                    Date.valueOf("2005-02-03"), group1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(vacation1);
            ModelManager.getInstance().commit();

            AbstractDataModel tmpModel = new AbstractDataModel(
                    VacationImpl.class, "From VacationImpl");

            int find = 0;
            for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
                Vacation tmp = (Vacation) it.next();

                // System.out.println("begin :" + tmp.getBeginDate() + "
                // vacation:" + vacation1.getBeginDate());
                // System.out.println(tmp.getEndDate() + " " +
                // vacation1.getEndDate()); System.out.println(tmp.getGroup() +
                // " " +
                // vacation1.getGroup());

                if (tmp.getBeginDate().equals(vacation1.getBeginDate())
                        && tmp.getEndDate().equals(vacation1.getEndDate())
                        && tmp.getGroup().equals(vacation1.getGroup())) {
                    ++find;
                }
            }
            // System.out.println("find " + find);
            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void testRemoveVacation() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            Vacation vacation1 = (Vacation) new AbstractDataModel(
                    VacationImpl.class, "From VacationImpl").getList().get(0);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().delete(vacation1);
            ModelManager.getInstance().commit();

            AbstractDataModel tmpModel = new AbstractDataModel(
                    VacationImpl.class, "From VacationImpl");

            int find = 0;

            for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
                Vacation tmp = (Vacation) it.next();
                if (tmp.getBeginDate().equals(vacation1.getBeginDate())
                        && tmp.getEndDate().equals(vacation1.getEndDate())
                        && tmp.getGroup().equals(vacation1.getGroup())) {
                    ++find;
                }
            }

            assertTrue(find == 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void testUpdateVacation() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try { // add an element
            Group group1 = (Group) new AbstractDataModel(GroupImpl.class,
                    "From GroupImpl").getList().get(0);

            Vacation vacation1 = new VacationImpl(Date.valueOf("2004-05-17"),
                    Date.valueOf("2004-05-19"), group1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(vacation1);
            ModelManager.getInstance().commit(); // get the element

            AbstractDataModel tmpModel = new AbstractDataModel(
                    VacationImpl.class, "From VacationImpl");

            Vacation vacation2 = (Vacation) tmpModel.getList().get(0);

            vacation2.setBeginDate(Date.valueOf("2004-05-18"));

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().update(vacation2);
            ModelManager.getInstance().commit();

            AbstractDataModel tmpModel2 = new AbstractDataModel(
                    VacationImpl.class, "From VacationImpl");

            tmpModel2 = new AbstractDataModel(VacationImpl.class,
                    "From VacationImpl");

            int find = 0;

            for (Iterator it = tmpModel2.getList().iterator(); it.hasNext();) {
                Vacation tmp = (Vacation) it.next();
                if (tmp.getBeginDate().equals(vacation2.getBeginDate())
                        && tmp.getEndDate().equals(vacation2.getEndDate())
                        && tmp.getGroup().equals(vacation2.getGroup())) {
                    ++find;
                }
            }

            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
