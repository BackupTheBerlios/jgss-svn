/*
 * Cr�� le 19 janv. 2005
 */
package de.berlios.jgss.core.event;

import de.berlios.jgss.engine.session.Interceptor;



/**
 * EventManager
 * <br>
 * @author Gr�gory Cuellar ( HangTime - Development and Technical Director )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Universit� Marne-la-Vall�e<br>
 * 	Cit� Descartes<br>
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
public interface EntityEventManager extends  Interceptor {
	public void addEntityEventListener(Class clas,EntityEventListener listener);
	public void removeEntityEventListener(Class clas,EntityEventListener listener);
	
	public void addToWaiting(EntityEvent[] e);
	public void addToWaiting(EntityEvent e);
	public void sendAllWaiting();
	public void clearWaiting();
}

