package net.dnsalias.hangtime.core.impl;

import java.util.Set;

import net.dnsalias.hangtime.core.Address;
import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.HColor;
import net.dnsalias.hangtime.core.Professor;
import net.dnsalias.hangtime.core.Room;


/** @author Hibernate CodeGenerator */
public class ProfessorImpl extends PeopleImpl implements Professor {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** persistent field */
    private Room office;

    /** persistent field */
    private Set availabilities;

    /** persistent field */
    private HColor color;
    
    private Set courses;
    
    /** full constructor */
    public ProfessorImpl(String mobilePhone, String firstName, String email, String homePhone, String workPhone, String name, Address address, Room office, Set availabilities,HColor color,Set courses){
        super(TYPE_PROFESSOR,mobilePhone, firstName, email, homePhone, workPhone, name, address);
        
        this.office = office;
        this.availabilities = availabilities;
        this.color=color;
        this.courses=courses;
    }

    /** default constructor */
    public ProfessorImpl() {
    	super(TYPE_PROFESSOR);
    }

    public void setCourses(Set courses){
    	this.courses=courses;
    }
    
    public Set getCourses(){
    	return courses;
    }
    /** minimal constructor */
    public ProfessorImpl(String name, Address address, Room office, Set availabilities) {
      super(TYPE_PROFESSOR,name, address);
        this.office = office;
        this.availabilities = availabilities;
    }

    public Room getOffice() {
        return this.office;
    }

    public void setOffice(Room office) {
        this.office = office;
    }

    public Set getAvailabilities() {
        return this.availabilities;
    }

    public void setAvailabilities(Set availabilities) {
        this.availabilities = availabilities;
    }

    public HColor getColor() {
        return this.color;
    }

    public void setColor(HColor color) {
        this.color = color;
    }
    
    public boolean use(Entity e) {
		return (((office==null)?false:office.equals(e))||((availabilities==null)?false:availabilities.contains(e))||((courses==null)?false:courses.contains(e)));
	}
}
