package net.dnsalias.hangtime.core.impl;


import java.rmi.RemoteException;

import net.dnsalias.hangtime.core.Address;
import net.dnsalias.hangtime.core.Ressource;
import de.berlios.jgss.core.impl.EntityImpl;
;

/** @author Hibernate CodeGenerator */
public class AddressImpl extends EntityImpl implements Address {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private String street;

    /** nullable persistent field */
    private Long postalCode;

    /** nullable persistent field */
    private String city;

    /** full constructor 
     * @throws RemoteException*/
    public AddressImpl(String street, Long postalCode, String city) {
    	super(Ressource.TYPE_ADDRESS);
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    /** default constructor */
    public AddressImpl(){
    	super(Ressource.TYPE_ADDRESS);
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
