package net.dnsalias.hangtime.core.impl;

import java.util.Set;

import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.HColor;
import net.dnsalias.hangtime.core.RoomGroup;



/** @author Hibernate CodeGenerator */
public class RoomGroupImpl extends RessourceImpl implements RoomGroup {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** persistent field */
    private Set rooms;

    /** persistent field */
    private HColor color;
    
    private Set courses;
    
    /** full constructor */
    public RoomGroupImpl(String name, Set rooms,HColor color,Set courses) {
    	super(TYPE_ROOMGROUP,name);
        this.rooms = rooms;
        this.color=color;
        this.courses=courses;
    }

    /** default constructor */
    public RoomGroupImpl() {
    	super(TYPE_ROOMGROUP);
    }
    protected RoomGroupImpl(final int entityType,String name, Set rooms,HColor color,Set courses) {
    	super(entityType,name);
        this.rooms = rooms;
        this.color=color;
        this.courses=courses;
    }

    protected RoomGroupImpl(final int entityType) {
    	super(entityType);
    }
    public void setCourses(Set courses){
    	this.courses=courses;
    }
    
    public Set getCourses(){
    	return courses;
    }
    
    public Set getRooms() {
        return this.rooms;
    }

    public void setRooms(Set rooms) {
        this.rooms = rooms;
    }

    public HColor getColor() {
        return this.color;
    }

    public void setColor(HColor color) {
        this.color = color;
    }
    public boolean use(Entity e) {
		return ((rooms==null)?false:rooms.contains(e))||((courses==null)?false:courses.contains(e));
	}
}
