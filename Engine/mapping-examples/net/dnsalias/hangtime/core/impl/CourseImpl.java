package net.dnsalias.hangtime.core.impl;

import java.util.Date;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.impl.EntityImpl;
import net.dnsalias.hangtime.core.*;


/** @author Hibernate CodeGenerator */
public class CourseImpl extends EntityImpl implements Course {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private Date dateTime;

    /** nullable persistent field */
    private Integer length;

    /** persistent field */
    private Professor prof;

    /** persistent field */
    private RoomGroup roomGroup;

    /** persistent field */
    private Teaching teaching;

    /** full constructor */
    public CourseImpl(Date dateTime, Integer length, Professor prof, RoomGroup roomGroup, Teaching teaching){
        super(Ressource.TYPE_COURSE);
    	this.dateTime = dateTime;
        this.length = length;
        this.prof = prof;
        this.roomGroup = roomGroup;
        this.teaching = teaching;
    }

    /** default constructor */
    public CourseImpl() {
    	super(Ressource.TYPE_COURSE);
    }

    /** minimal constructor */
    public CourseImpl(Professor prof, RoomGroup roomGroup, Teaching teaching){
    	super(Ressource.TYPE_COURSE);
        this.prof = prof;
        this.roomGroup = roomGroup;
        this.teaching = teaching;
    }
    
    public Course duplicate(Date date) {
		return new CourseImpl(date,getLength(),getProf(),getRoomGroup(),getTeaching());
	}

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Professor getProf() {
        return this.prof;
    }

    public void setProf(Professor prof) {
        this.prof = prof;
    }

    public RoomGroup getRoomGroup() {
        return this.roomGroup;
    }

    public void setRoomGroup(RoomGroup roomGroup) {
        this.roomGroup = roomGroup;
    }

    public Teaching getTeaching() {
        return this.teaching;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }
    public boolean use(Entity e) {
		return (((prof==null)?false:prof.equals(e))||((roomGroup==null)?false:roomGroup.equals(e))||((teaching==null)?false:teaching.equals(e)));
	}
}
