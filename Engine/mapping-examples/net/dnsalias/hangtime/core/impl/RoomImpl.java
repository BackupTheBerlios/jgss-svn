package net.dnsalias.hangtime.core.impl;

import java.util.Set;

import net.dnsalias.hangtime.core.Building;
import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.HColor;
import net.dnsalias.hangtime.core.Room;
import net.dnsalias.hangtime.core.RoomType;

/** @author Hibernate CodeGenerator */
public class RoomImpl extends RoomGroupImpl implements Room {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private Integer capacity;

    /** nullable persistent field */
    private Integer stage;

    /** persistent field */
    private RoomType roomType;

    /** persistent field */
    private Building building;

    /** persistent field */
    private Set roomsGroups;

    /** full constructor */
    public RoomImpl(String name, Set rooms, Integer capacity, Integer stage, RoomType roomType, Building building, Set roomsGroups,HColor color,Set courses){
        super(TYPE_ROOM,name, rooms,color,courses);
        this.capacity = capacity;
        this.stage = stage;
        this.roomType = roomType;
        this.building = building;
        this.roomsGroups = roomsGroups;
    }

    /** default constructor */
    public RoomImpl() {
    	super(TYPE_ROOM);
    }

    /** minimal constructor */
    public RoomImpl(String name, Set rooms, RoomType roomType, Building building, Set roomsGroups,HColor color,Set courses) {
      super(TYPE_ROOM,name, rooms,color,courses);
        this.roomType = roomType;
        this.building = building;
        this.roomsGroups = roomsGroups;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStage() {
        return this.stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Building getBuilding() {
        return this.building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set getRoomsGroups() {
        return this.roomsGroups;
    }

    public void setRoomsGroups(Set roomsGroups) {
        this.roomsGroups = roomsGroups;
    }
    
    public boolean use(Entity e) {
		return (((roomType==null)?false:roomType.equals(e))||((building==null)?false:building.equals(e))||((roomsGroups==null)?false:roomsGroups.contains(e)));
	}
}
