package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Bloc;
import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.Group;
import de.berlios.jgss.core.HColor;
import de.berlios.jgss.core.Material;
import de.berlios.jgss.core.Matter;
import de.berlios.jgss.core.Professor;
import de.berlios.jgss.core.RoomGroup;
import de.berlios.jgss.core.Teaching;

/**
 * 
 * TestTeachingImpl <br>
 * <br>
 * 
 * @author Remy Lataix ( HangTime - WebMaster )<br>
 *         UFR Ingenieurs 2000 <br>
 *         <br>
 *         Universite Marne-la-Vallee <br>
 *         Cite Descartes <br>
 *         5, bd Descartes <br>
 *         Champs-sur-Marne <br>
 *         77454 MARNE-LA-VALLEE CEDEX 2 <br>
 *         FRANCE <br>
 *         <br>
 *         e-mail: rlataix@etudiant.univ-mlv.fr <br>
 *         site: http://hangtime.dnsalias.net:8080 <br>
 *         bugs: http://hangtime.free.fr/mantis <br>
 *         <br>
 */
public class TestTeachingImpl extends TestCase {

    /**
     * This method test the getter and the setter of the class
     * 
     */
    public void testPojoClass() {
        String name = "toto";
        Integer factor = 2;
        Integer defLength = 3;
        Long numberHours = 5l;
        Professor defProf = new ProfessorImpl();
        RoomGroup defRoom = new RoomImpl();
        Bloc bloc = new BlocImpl();
        Matter matter = new MatterImpl();
        Material defMat = new MaterialImpl();
        Group group = new GroupImpl();
        HColor color = new HColor();
        Set courses = new TreeSet();

        // test getter
        Teaching t = new TeachingImpl(name, factor, defLength, numberHours,
                defRoom, defProf, bloc, matter, defMat, group, color, courses);
        assertEquals(t.getName(), name);
        assertSame(t.getBloc(), bloc);
        assertSame(t.getColor(), color);
        assertEquals(t.getDefLength(), defLength);
        assertSame(t.getDefMat(), defMat);
        assertSame(t.getDefProf(), defProf);
        assertSame(t.getDefRoom(), defRoom);
        assertEquals(t.getFactor(), factor);
        assertSame(t.getGroup(), group);
        assertSame(t.getMatter(), matter);
        assertEquals(t.getNumberHours(), numberHours);

        // test Setter
        String name2 = "toto";
        Integer factor2 = 2;
        Integer defLength2 = 3;
        Long numberHours2 = 5l;
        Professor defProf2 = new ProfessorImpl();
        RoomGroup defRoom2 = new RoomImpl();
        Bloc bloc2 = new BlocImpl();
        Matter matter2 = new MatterImpl();
        Material defMat2 = new MaterialImpl();
        Group group2 = new GroupImpl();
        HColor color2 = new HColor();
        Set courses2 = new TreeSet();

        t.setBloc(bloc2);
        t.setColor(color2);
        t.setDefLength(defLength2);
        t.setDefMat(defMat2);
        t.setDefProf(defProf2);
        t.setDefRoom(defRoom2);
        t.setFactor(factor2);
        t.setGroup(group2);
        t.setMatter(matter2);
        t.setName(name2);
        t.setNumberHours(numberHours2);

        assertEquals(t.getName(), name2);
        assertSame(t.getBloc(), bloc2);
        assertSame(t.getColor(), color2);
        assertEquals(t.getDefLength(), defLength2);
        assertSame(t.getDefMat(), defMat2);
        assertSame(t.getDefProf(), defProf2);
        assertSame(t.getDefRoom(), defRoom2);
        assertEquals(t.getFactor(), factor2);
        assertSame(t.getGroup(), group2);
        assertSame(t.getMatter(), matter2);
        assertEquals(t.getNumberHours(), numberHours2);
    }

    /**
     * this method test the add of a Teaching in the database
     * 
     */
    public void testAddTeaching() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            Professor defProf1 = (Professor) new AbstractDataModel(
                    ProfessorImpl.class, "From ProfessorImpl").getList().get(0);
            RoomGroup defRoom1 = (RoomGroup) new AbstractDataModel(
                    RoomGroupImpl.class, "From RoomGroupImpl").getList().get(0);
            Bloc bloc1 = (Bloc) new AbstractDataModel(BlocImpl.class,
                    "From BlocImpl").getList().get(0);
            Matter matter1 = (Matter) new AbstractDataModel(MatterImpl.class,
                    "From MatterImpl").getList().get(0);
            Material defMat1 = (Material) new AbstractDataModel(
                    MaterialImpl.class, "From MaterialImpl").getList().get(0);
            Group group1 = (Group) new AbstractDataModel(GroupImpl.class,
                    "From GroupImpl").getList().get(0);

            Teaching teaching1 = new TeachingImpl("teaching1", defProf1,
                    defRoom1, bloc1, matter1, defMat1, group1);

            // 1rst add
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(teaching1);
            ModelManager.getInstance().commit();

            // 2nd add to see if there are doublons
            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().save(teaching1);
            ModelManager.getInstance().commit();
           
            AbstractDataModel tmpModel = new AbstractDataModel(
                    TeachingImpl.class, "From TeachingImpl");

            int find = 0;
            for (Entity e :tmpModel.getList()) {
                Teaching tmp = (Teaching)e;

                if (tmp.getName().equals(teaching1.getName())) {
                    ++find;
                }
            }
            System.out.println("find add" + find);
            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method test the update of Teaching in the database
     * 
     */
    public void testUpdateTeaching() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "teaching1";

            Teaching teaching1 = null;

            for (Iterator it = new AbstractDataModel(TeachingImpl.class,
                    "From TeachingImpl").getList().iterator(); it.hasNext();) {
                Teaching tmp = (Teaching) it.next();

                if (name.equals(tmp.getName())) {
                    teaching1 = tmp;
                    break;
                }
            }

            if (teaching1 == null)
                assertNull(teaching1);

            teaching1.setName("newteaching1");

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().update(teaching1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(TeachingImpl.class,
            "From TeachingImpl").getList().iterator(); it.hasNext();) {
                Teaching tmp = (Teaching) it.next();

                if (teaching1.getName().equals(tmp.getName())) {
                    ++find;
                }
            }
            System.out.println("find update" + find);
            assertTrue(find == 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * this method remove a Teaching in the database
     *
     */
    public void testRemoveTeaching() {
        System.setProperty("java.rmi.server.ignoreStubClasses", "true");

        try {
            String name = "newteaching1";

            Teaching teaching1 = null;

            for (Iterator it = new AbstractDataModel(TeachingImpl.class,
                    "From TeachingImpl").getList().iterator(); it.hasNext();) {
                Teaching tmp = (Teaching) it.next();

                if (name.equals(tmp.getName())) {
                    teaching1 = tmp;
                    break;
                }
            }

            if(teaching1==null)assertNull(teaching1);

            ModelManager.getInstance().beginTransaction();
            ModelManager.getInstance().delete(teaching1);
            ModelManager.getInstance().commit();

            int find = 0;
            for (Iterator it = new AbstractDataModel(TeachingImpl.class,
                    "From TeachingImpl").getList().iterator(); it.hasNext();) {
                Teaching tmp = (Teaching) it.next();

                if (teaching1.getName().equals(tmp.getName())) {
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
