package net.dnsalias.hangtime.core.impl;

import net.dnsalias.hangtime.core.Address;
import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.People;


/** @author Hibernate CodeGenerator */
public class PeopleImpl extends RessourceImpl implements People {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private String mobilePhone;

    /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private String homePhone;

    /** nullable persistent field */
    private String workPhone;

    /** persistent field */
    private Address address;

    /** full constructor */
    public PeopleImpl(String mobilePhone, String firstName, String email, String homePhone, String workPhone, String name, Address address){
        super(TYPE_PEOPLE,name);
    	this.mobilePhone = mobilePhone;
        this.firstName = firstName;
        this.email = email;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.address = address;
    }
    
    /** default constructor */
    public PeopleImpl(){
    	super(TYPE_PEOPLE);
    }
    
    /** minimal constructor */
    public PeopleImpl(String name, Address address){
    	super(TYPE_PEOPLE,name);
        this.address = address;
    }
    protected PeopleImpl(final int entityType,String mobilePhone, String firstName, String email, String homePhone, String workPhone, String name, Address address){
        super(entityType,name);
    	this.mobilePhone = mobilePhone;
        this.firstName = firstName;
        this.email = email;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.address = address;
    }
    protected PeopleImpl(final int entityType){
    	super(entityType);
    }
    protected PeopleImpl(final int entityType,String name, Address address){
    	super(entityType,name);
        this.address = address;
    }
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return this.workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public boolean use(Entity e) {
		return (address==null)?false:address.equals(e);
	}
}
