package net.dnsalias.hangtime.core;

import java.util.Set;

public interface Room extends RoomGroup {

    public Integer getCapacity();

    public void setCapacity(Integer capacity);

    public Integer getStage();

    public void setStage(Integer stage);

    public RoomType getRoomType();

    public void setRoomType(RoomType roomType);

    public Building getBuilding();

    public void setBuilding(Building building);

    public Set getRoomsGroups();

    public void setRoomsGroups(Set roomsGroups);

}
