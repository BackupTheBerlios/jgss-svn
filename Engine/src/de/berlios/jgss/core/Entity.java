/*
 * Créé le 16 janv. 2005
 */
package de.berlios.jgss.core;

import java.io.Serializable;




/**
 * Entity
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
public interface Entity extends Serializable,Comparable{
	public static final int TYPE_DEFAULT=-1;
	
	public Class getClas();
	public int getEntityType();
	public Serializable getId();

    public void setId(Serializable id);
    
    public Long  getVersion ();

	public void setVersion (final Long version);
	
	public boolean use(Entity e);
}
