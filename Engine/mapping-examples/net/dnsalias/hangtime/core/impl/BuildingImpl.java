package net.dnsalias.hangtime.core.impl;


import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.*;

/** @author Hibernate CodeGenerator */
public class BuildingImpl extends RessourceImpl implements Building {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	/** persistent field */
    private Address address;

    /** full constructor */
    public BuildingImpl(String name, Address address){
    	super(Ressource.TYPE_BUILDING,name);
        this.address = address;
    }

    /** default constructor */
    public BuildingImpl(){
    	super(Ressource.TYPE_BUILDING);
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
