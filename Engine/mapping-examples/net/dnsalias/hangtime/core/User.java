package net.dnsalias.hangtime.core;

public interface User extends People {

    public String getLdapName();

    public void setLdapName(String ldapName);

    public Role getRole();

    public void setRole(Role role);

    public void setPassword(String password); 
    public String getPassword();
}
