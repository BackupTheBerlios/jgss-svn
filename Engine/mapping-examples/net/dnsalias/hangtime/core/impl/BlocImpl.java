package net.dnsalias.hangtime.core.impl;



import net.dnsalias.hangtime.core.Bloc;
import net.dnsalias.hangtime.core.Ressource;


/** @author Hibernate CodeGenerator */
public class BlocImpl extends RessourceImpl implements Bloc {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** full constructor */
    public BlocImpl(String name) {
    	super(Ressource.TYPE_BLOC,name);
    }

    /** default constructor */
    public BlocImpl() {
    	super(Ressource.TYPE_BLOC);
    }
}
