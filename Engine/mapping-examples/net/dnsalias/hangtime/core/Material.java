package net.dnsalias.hangtime.core;



public interface Material extends Ressource {

    public People getOwner();

    public void setOwner(People owner);

    public MaterialType getTypeMat();

    public void setTypeMat(MaterialType typeMat);

    public Room getRoom();

    public void setRoom(Room room);

    public HColor getColor();

    public void setColor(HColor color);

}
