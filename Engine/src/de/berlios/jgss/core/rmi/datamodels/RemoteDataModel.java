/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.core.rmi.datamodels;

import java.rmi.Remote;
import java.util.List;

import de.berlios.jgss.api.model.DataModel;
import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.event.EntityEventListener;



/**
 * RemoteDataModel
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
public interface RemoteDataModel extends Remote,EntityEventListener{
	public void addListener(DataModel m);
	public void removeListener(DataModel m);
	public List<Entity> getLightDatas();
}
