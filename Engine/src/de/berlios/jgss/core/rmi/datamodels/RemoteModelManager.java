/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.core.rmi.datamodels;

import java.io.Serializable;
import java.rmi.Remote;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.engine.exception.JGSSException;


/**
 * RemoteModelManager
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
public interface RemoteModelManager extends Remote{
    public static String REGISTRY_NAME="remotemanager";
	public Entity load(Class clas,Serializable id)throws JGSSException ;
	public RemoteDataModel getModel(Class clas,String query);
    public Entity save(Entity e,String sessionId)throws JGSSException ;
    public Entity update(Entity e,String sessionId)throws JGSSException ;
    public void delete(Entity e,String sessionId)throws JGSSException ;
    public String beginTransaction()throws JGSSException ;
    public void commit(String sessionId)throws JGSSException ;
    public void rollback(String sessionId)throws JGSSException;
}
