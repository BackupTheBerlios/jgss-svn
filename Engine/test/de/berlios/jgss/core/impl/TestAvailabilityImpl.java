package de.berlios.jgss.core.impl;

import java.util.Date;

import de.berlios.jgss.core.Availability;
import de.berlios.jgss.core.Professor;
import de.berlios.jgss.core.impl.AvailabilityImpl;
import de.berlios.jgss.core.impl.ProfessorImpl;

import junit.framework.TestCase;

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

public class TestAvailabilityImpl extends TestCase{
    public void testPojoClass(){ 
        
        Integer days=new Integer(1);
        Date beginDate=new Date(System.currentTimeMillis()-100000);
        Boolean type=true;
        Date endDate=new Date(System.currentTimeMillis());
        Long hours=new Long(3600);
        Professor prof=new ProfessorImpl();
        
        
        Availability availability=new AvailabilityImpl(days,beginDate,type,endDate,hours,prof);
        
        //getter
        assertEquals(availability.getDays(),days);
        assertEquals(availability.getBeginDate(),beginDate);
        assertEquals(availability.getType(),type);
        assertEquals(availability.getEndDate(),endDate);
        assertEquals(availability.getHours(),hours);
        assertSame(availability.getProf(),prof);
        
        Integer days2=new Integer(2);
        Date beginDate2=new Date(System.currentTimeMillis()-200000);
        Boolean type2=false;
        Date endDate2=new Date(System.currentTimeMillis()-500);
        Long hours2=new Long(7200);
        Professor prof2=new ProfessorImpl();
        
        //setter
        availability.setDays(days2);
        assertEquals(availability.getDays(),days2);
        availability.setBeginDate(beginDate2);
        assertEquals(availability.getBeginDate(),beginDate2);
        availability.setType(type2);
        assertEquals(availability.getType(),type2);
        availability.setEndDate(endDate2);
        assertEquals(availability.getEndDate(),endDate2);
        availability.setHours(hours2);
        assertEquals(availability.getHours(),hours2);
        availability.setProf(prof2);
        assertSame(availability.getProf(),prof2);
    } 
}
