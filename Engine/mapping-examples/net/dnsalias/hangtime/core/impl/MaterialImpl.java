package net.dnsalias.hangtime.core.impl;


import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.*;

/** @author Hibernate CodeGenerator */
public class MaterialImpl extends RessourceImpl implements Material {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

    /** persistent field */
    private People owner;

    /** persistent field */
    private MaterialType typeMat;

    /** persistent field */
    private Room room;

    /** persistent field */
    private HColor color;
    
    /** full constructor */
    public MaterialImpl(String name, People owner, MaterialType typeMat, Room room,HColor color) {
    	super(TYPE_MATERIAL,name);
        this.owner = owner;
        this.typeMat = typeMat;
        this.room = room;
        this.color=color;
    }

    /** default constructor */
    public MaterialImpl() {
    	super(TYPE_MATERIAL);
    }

    public People getOwner() {
        return this.owner;
    }

    public void setOwner(People owner) {
        this.owner = owner;
    }

    public MaterialType getTypeMat() {
        return this.typeMat;
    }

    public void setTypeMat(MaterialType typeMat) {
        this.typeMat = typeMat;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HColor getColor() {
        return this.color;
    }

    public void setColor(HColor color) {
        this.color = color;
    }

    public boolean use(Entity e) {
		return (((owner==null)?false:owner.equals(e))||((typeMat==null)?false:typeMat.equals(e))||((room==null)?false:room.equals(e)));
	}
}
