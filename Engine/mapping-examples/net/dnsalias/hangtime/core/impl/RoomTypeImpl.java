package net.dnsalias.hangtime.core.impl;

import net.dnsalias.hangtime.core.RoomType;


/** @author Hibernate CodeGenerator */
public class RoomTypeImpl extends RessourceImpl implements RoomType {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** full constructor */
    public RoomTypeImpl(String name){
    	super(TYPE_ROOMTYPE,name);
    }

    /** default constructor */
    public RoomTypeImpl(){
    	super(TYPE_ROOMTYPE);
    }
}
