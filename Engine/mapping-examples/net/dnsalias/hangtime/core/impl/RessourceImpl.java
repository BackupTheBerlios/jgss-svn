
package net.dnsalias.hangtime.core.impl;

import de.berlios.jgss.core.impl.EntityImpl;
import net.dnsalias.hangtime.core.Ressource;


public abstract class RessourceImpl extends EntityImpl implements Ressource {
	 private String name;
	 
	 protected RessourceImpl(final int _entityType){
	 	super(_entityType);
	 }
	 protected RessourceImpl(final int _entityType,String name){
	 	super(_entityType);
        this.name = name;
    }
	 public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
