/*
 * Créé le 16 janv. 2005
 */
package de.berlios.jgss.core.session;

import de.berlios.jgss.core.event.impl.EntityEventManagerImpl;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Session;
import de.berlios.jgss.engine.session.SessionFactory;

/**
 * SessionManager
 * <br>
 * @author Grégory Cuellar ( HangTime - Development and Technical Director )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Université Marne-la-Vallée<br>
 * 	Cité Descartes<br>
 *  5, bd Descartes<br>
 *  Champs-sur-Marne<br>
 *  77454 MARNE-LA-VALLEE CEDEX 2<br>
 *  FRANCE<br>
 * <br>
 * e-mail: gcuellar@etudiant.univ-mlv.fr<br>
 * site: http://hangtime.dnsalias.net:8080
 * bugs: http://hangtime.free.fr/mantis
 * <br>
 */
public class SessionManager {
	
	
	
	public static Session getSession() throws JGSSException {
        SessionFactory sessionFactory=SessionFactory.getInstance();
		synchronized(sessionFactory){
				return sessionFactory.openSession(EntityEventManagerImpl.getManager());
				
	 	}
	}
	  
}
