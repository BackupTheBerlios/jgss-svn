/*
 * Créé le 19 janv. 2005
 */
package de.berlios.jgss.core.event;

import de.berlios.jgss.core.event.impl.EntityEventManagerImpl;
import junit.framework.TestCase;

/**
 * 
 * @author RLataix
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestEntityEventManager extends TestCase{
	public void testEntityEventManagerSingleton(){
        assertSame(EntityEventManagerImpl.getManager(),EntityEventManagerImpl.getManager());
    }
}

