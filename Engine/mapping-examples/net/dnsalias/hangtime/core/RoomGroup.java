package net.dnsalias.hangtime.core;

import java.util.Set;

public interface RoomGroup extends Ressource  {

    public Set getRooms();
    
    public void setRooms(Set rooms);

    public HColor getColor();

    public void setColor(HColor color);

}
