package net.dnsalias.hangtime.core;

public interface People extends Ressource {

    public String getMobilePhone();

    public void setMobilePhone(String mobilePhone);
    
    public String getFirstName();

    public void setFirstName(String firstName);

    public String getEmail();

    public void setEmail(String email);

    public String getHomePhone();

    public void setHomePhone(String homePhone);

    public String getWorkPhone();

    public void setWorkPhone(String workPhone);

    public Address getAddress();

    public void setAddress(Address address) ;

}
