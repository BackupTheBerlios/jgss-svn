package net.dnsalias.hangtime.core;

import de.berlios.jgss.core.Entity;



public interface Address extends Entity {

  
   
    public String getStreet();

    public void setStreet(String street);

    public Long getPostalCode();

    public void setPostalCode(Long postalCode);

    public String getCity();

    public void setCity(String city) ;
}
