package net.dnsalias.hangtime.core.impl;


import net.dnsalias.hangtime.core.MaterialType;


/** @author Hibernate CodeGenerator */
public class MaterialTypeImpl extends RessourceImpl implements MaterialType {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** full constructor */
    public MaterialTypeImpl(String name) {
    	super(TYPE_MATERIALTYPE,name);
    }

    /** default constructor */
    public MaterialTypeImpl() {
    	super(TYPE_MATERIALTYPE);
    }
}
