/*
 * Créé le 19 janv. 2005
 */
package de.berlios.jgss.core.event.impl;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.event.EntityEvent;

/**
 * EntityEventImpl
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
public class EntityEventImpl implements EntityEvent {
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private final int type;
	private final Entity entity;
	
	public EntityEventImpl(final int type, final Entity entity) {
		super();
		this.type = type;
		this.entity = entity;
	}
	public int getType() {
		return type;
	}

	public Entity getEntity() {
		return entity;
	}

}
