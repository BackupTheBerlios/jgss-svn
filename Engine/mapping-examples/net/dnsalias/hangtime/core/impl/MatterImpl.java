package net.dnsalias.hangtime.core.impl;


import net.dnsalias.hangtime.core.Matter;


/** @author Hibernate CodeGenerator */
public class MatterImpl extends RessourceImpl implements Matter {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** full constructor */
    public MatterImpl(String name) {
    	super(TYPE_MATTER,name);
    }

    /** default constructor */
    public MatterImpl(){
    	super(TYPE_MATTER);
    }
}
