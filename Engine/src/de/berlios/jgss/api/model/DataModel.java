/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.api.model;

import java.rmi.Remote;

import de.berlios.jgss.core.event.EntityEvent;


/**
 * DataModel
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
public interface DataModel extends Remote{
	public void addDataListener(DataListener l);
	public void removeDataListener(DataListener l);
	
	public void remoteDataChanged(EntityEvent e);
}
