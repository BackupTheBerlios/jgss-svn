/*
 * Créé le 16 janv. 2005
 */
package de.berlios.jgss.core.impl;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.impl.EntityImpl;
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

public class TestEntityImpl extends TestCase{
    public void testPojoClass(){
        Long id=new Long(123456);
        Long version=new Long(654321);
        Class clas=getClass();
        int _entityType=1234;
        
        
        Entity entity=new EntityImpl(id,version,clas,_entityType);
         
        //getter
        assertEquals(id,entity.getId());
        assertEquals(version,entity.getVersion());
        assertSame(clas,entity.getClas());
        assertEquals(_entityType,entity.getEntityType());
        
        Long id2=new Long(456789);
        Long version2=new Long(987654);
        
        //setter
        entity.setId(id2);
        assertEquals(id2,entity.getId());
        entity.setVersion(version2);
        assertEquals(version2,entity.getVersion());
    }
}
