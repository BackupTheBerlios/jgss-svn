package net.dnsalias.hangtime.core;

import java.util.Set;

public interface Group extends Ressource {

     public String getEmail();

    public void setEmail(String email);

    public Group getParent();

    public void setParent(Group parent);

    public Set getChilds();

    public void setChilds(Set childs);
    
    public void setVacations(Set vacations);

    public Set getVacations();

    public HColor getColor();

    public void setColor(HColor color);
    
    public Integer getSize();

    public void setSize(Integer size);

}
