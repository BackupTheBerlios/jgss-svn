/*
 * Créé le 16 janv. 2005
 */
package de.berlios.jgss.core.impl;

import java.io.Serializable;

import de.berlios.jgss.core.Entity;


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
public class EntityImpl  implements Entity{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	private Serializable id;
	private final int entityType;
	private Long version;
	private Class clas;
	protected EntityImpl(final int _entityType){
		entityType=_entityType;
	}
	public EntityImpl(Serializable id,Long version,Class clas,final int _entityType){
		entityType=_entityType;
		this.clas=clas;
		this.id=id;
		this.version=version;
	}
	public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }
    
    public Long getVersion ()
	{
		return version;
	}

	public void setVersion (final Long version)
	{
		this.version = version;
	}
	public Class getClas() {
        return (clas==null)?getClass():clas;
	}
	public int getEntityType() {
		return entityType;
	}
	public boolean use(Entity e) {
		return false;
	}
    
    public int compareTo(Object o) {
        Entity e = (Entity)o;
        return (getEntityType()-e.getEntityType())*2+(getId().hashCode()-(e.getId().hashCode()));
    }
    
    public boolean equals(Object obj) {
    	if(obj==null)return false;
        Entity e = (Entity)obj;
        if(getId()==null)
            return super.equals(obj);
        return (e.getEntityType()==getEntityType())&&((getId()==null)?false:getId().equals(e.getId()));
    }
}
