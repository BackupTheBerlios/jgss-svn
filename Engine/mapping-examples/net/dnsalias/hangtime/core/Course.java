package net.dnsalias.hangtime.core;


import java.util.Date;
import de.berlios.jgss.core.Entity;

public interface Course extends Entity{

    public Course duplicate(Date date);
    
    public Date getDateTime();

    public void setDateTime(Date dateTime);
    
    public Integer getLength();
    
    public void setLength(Integer length);

    public Professor getProf();

    public void setProf(Professor prof);

    public RoomGroup getRoomGroup();

    public void setRoomGroup(RoomGroup roomGroup);

    public Teaching getTeaching();

    public void setTeaching(Teaching teaching);

}
