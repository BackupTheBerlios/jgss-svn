package net.dnsalias.hangtime.core.impl;

import net.dnsalias.hangtime.core.Address;
import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.Group;
import net.dnsalias.hangtime.core.Student;


/** @author Hibernate CodeGenerator */
public class StudentImpl extends PeopleImpl implements Student {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	/** persistent field */
    private Group group;

    /** full constructor */
    public StudentImpl(String mobilePhone, String firstName, String email, String homePhone, String workPhone, String name, Address address, Group group){
        super(TYPE_STUDENT,mobilePhone, firstName, email, homePhone, workPhone, name, address);
        this.group = group;
    }

    /** default constructor */
    public StudentImpl(){
    	super(TYPE_STUDENT);
    }

    /** minimal constructor */
    public StudentImpl(String name, Address address, Group group){
      super(TYPE_STUDENT,name, address);
        this.group = group;
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
