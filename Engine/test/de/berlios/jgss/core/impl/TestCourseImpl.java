package de.berlios.jgss.core.impl;

import java.util.Date;

import de.berlios.jgss.core.Course;
import de.berlios.jgss.core.Professor;
import de.berlios.jgss.core.RoomGroup;
import de.berlios.jgss.core.Teaching;
import de.berlios.jgss.core.impl.CourseImpl;
import de.berlios.jgss.core.impl.ProfessorImpl;
import de.berlios.jgss.core.impl.RoomGroupImpl;
import de.berlios.jgss.core.impl.TeachingImpl;

import junit.framework.TestCase;


/**
 * 
 * @author rlataix
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestCourseImpl extends TestCase{
    public void testPojoClass(){
        Date date=new Date();
        Integer length=new Integer(1);
        Professor prof=new ProfessorImpl();
        RoomGroup roomGroup=new RoomGroupImpl();
        Teaching teaching=new TeachingImpl();
        
        Course course=new CourseImpl(date,length,prof,roomGroup,teaching);
        //getter
        assertEquals(date,course.getDateTime());
        assertEquals(length,course.getLength());
        assertSame(prof,course.getProf());
        assertSame(roomGroup,course.getRoomGroup());
        assertSame(teaching,course.getTeaching());
        
        //setter
        Date date2=new Date();
        Integer length2=new Integer(2);
        Professor prof2=new ProfessorImpl();
        RoomGroup roomGroup2=new RoomGroupImpl();
        Teaching teaching2=new TeachingImpl();
        
        course.setDateTime(date2);
        assertEquals(date2,course.getDateTime());
        course.setLength(length2);
        assertEquals(length2,course.getLength());
        course.setProf(prof2);
        assertSame(prof2,course.getProf());
        course.setRoomGroup(roomGroup2);
        assertSame(roomGroup2,course.getRoomGroup());
        course.setTeaching(teaching2);
        assertSame(teaching2,course.getTeaching());
    }
}
