package net.dnsalias.hangtime.core.impl;

import java.util.Date;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.core.impl.EntityImpl;
import net.dnsalias.hangtime.core.Group;
import net.dnsalias.hangtime.core.Ressource;
import net.dnsalias.hangtime.core.Vacation;


/** @author Hibernate CodeGenerator */
public class VacationImpl extends EntityImpl implements Vacation {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private Date beginDate;

    /** nullable persistent field */
    private Date endDate;

    /** persistent field */
    private Group group;

    /** full constructor */
    public VacationImpl(Date beginDate, Date endDate, Group group) {
        super(Ressource.TYPE_VACATION);
    	this.beginDate = beginDate;
        this.endDate = endDate;
        this.group = group;
    }

    /** default constructor */
    public VacationImpl(){
    	super(Ressource.TYPE_VACATION);
    }

    /** minimal constructor */
    public VacationImpl(Group group){
    	super(Ressource.TYPE_VACATION);
        this.group = group;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public boolean use(Entity e) {
		return (group==null)?false:group.equals(e);
	}
}
