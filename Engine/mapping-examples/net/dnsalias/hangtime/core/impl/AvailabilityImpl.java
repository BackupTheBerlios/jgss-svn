package net.dnsalias.hangtime.core.impl;

import java.util.Date;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.impl.EntityImpl;

import net.dnsalias.hangtime.core.*;


/** @author Hibernate CodeGenerator */
public class AvailabilityImpl extends EntityImpl implements Availability {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private Integer days;

    /** nullable persistent field */
    private Date beginDate;

    /** nullable persistent field */
    private Boolean type;

    /** nullable persistent field */
    private Date endDate;

    /** nullable persistent field */
    private Long hours;

    /** persistent field */
    protected Professor prof;

    /** full constructor */
    public AvailabilityImpl(Integer days, Date beginDate, Boolean type, Date endDate, Long hours, Professor prof){
    	super(Ressource.TYPE_AVAILABILITY);
    	this.days = days;
        this.beginDate = beginDate;
        this.type = type;
        this.endDate = endDate;
        this.hours = hours;
        this.prof = prof;
    }

    /** default constructor */
    public AvailabilityImpl(){
    	super(Ressource.TYPE_AVAILABILITY);
    }

    /** minimal constructor */
    public AvailabilityImpl(Professor prof){
    	super(Ressource.TYPE_AVAILABILITY);
        this.prof = prof;
    }

    public Integer getDays() {
        return this.days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Boolean getType() {
        return this.type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getHours() {
        return this.hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

   
    public Professor getProf() {
        return prof;
    }

    public void setProf(Professor prof) {
     	this.prof=prof;
    }
    public boolean use(Entity e) {
		return (prof==null)?false:prof.equals(e);
	}
}
