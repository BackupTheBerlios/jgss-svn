package net.dnsalias.hangtime.core;

import java.util.Set;

public interface Professor extends People {

    public Room getOffice();

    public void setOffice(Room office);

    public Set getAvailabilities();

    public void setAvailabilities(Set availabilities);

    public HColor getColor();

    public void setColor(HColor color);

}
