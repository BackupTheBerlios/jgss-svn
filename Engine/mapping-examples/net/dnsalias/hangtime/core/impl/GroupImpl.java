package net.dnsalias.hangtime.core.impl;

import java.util.Set;

import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.*;



/** @author Hibernate CodeGenerator */
public class GroupImpl extends RessourceImpl implements Group {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private String email;

    /** persistent field */
    private Group parent;

    /** persistent field */
    private Set childs;
    
    /** persistent field */
    private Set vacations;
    
    /** persistent field */
    private HColor color;
    
    /** nullable persistent field */
    private Integer size;
    
    /** full constructor */
    public GroupImpl(String name, String email, Group parent, Set childs,Set vacations,HColor color,Integer size) {
        super(TYPE_GROUP,name);
        this.email = email;
        this.parent = parent;
        this.childs = childs;
        this.vacations=vacations;
        this.color=color;
        this.size=size;
    }

    /** default constructor */
    public GroupImpl() {
    	super(TYPE_GROUP);
    }

    /** minimal constructor */
    public GroupImpl(String name, Group parent, Set childs) {
        super(TYPE_GROUP,name);
        this.parent = parent;
        this.childs = childs;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getParent() {
        return this.parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Set getChilds() {
        return this.childs;
    }

    public void setChilds(Set childs) {
        this.childs = childs;
    }
    
    public void setVacations(Set vacations) {
        this.vacations = vacations;
    }

    public Set getVacations() {
        return this.vacations;
    }

    public HColor getColor() {
        return this.color;
    }

    public void setColor(HColor color) {
        this.color = color;
    }
    
    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    public boolean use(Entity e) {
		return (((parent==null)?false:parent.equals(e))||((childs==null)?false:childs.contains(e))||((vacations==null)?false:vacations.contains(e)));
	}
}
