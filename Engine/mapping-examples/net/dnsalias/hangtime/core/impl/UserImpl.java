package net.dnsalias.hangtime.core.impl;


import net.dnsalias.hangtime.core.Address;
import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.Role;
import net.dnsalias.hangtime.core.User;

/** @author Hibernate CodeGenerator */
public class UserImpl extends PeopleImpl implements User {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** persistent field */
    private String ldapName;

    private String password;
    /** persistent field */
    private Role role;

    /** full constructor */
    public UserImpl(String mobilePhone, String firstName, String email, String homePhone, String workPhone, String name, Address address, String ldapName, Role role,String password){
        super(TYPE_USER,mobilePhone, firstName, email, homePhone, workPhone, name, address);
        this.ldapName = ldapName;
        this.role = role;
        this.password=password;
    }

    /** default constructor */
    public UserImpl() {
    	super(TYPE_USER);
    }

    /** minimal constructor */
    public UserImpl(String name, Address address, String ldapName, Role role) {
      super(TYPE_USER,name, address);
        this.ldapName = ldapName;
        this.role = role;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public String getLdapName() {
        return this.ldapName;
    }

    public void setLdapName(String ldapName) {
        this.ldapName = ldapName;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public boolean use(Entity e) {
		return (role==null)?false:role.equals(e);
	}
}
