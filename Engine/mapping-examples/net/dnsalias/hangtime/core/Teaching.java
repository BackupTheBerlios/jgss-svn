package net.dnsalias.hangtime.core;


public interface Teaching extends Ressource {

    public Integer getFactor();

    public void setFactor(Integer factor);

    public HColor getColor();

    public void setColor(HColor color);

    public Integer getDefLength();

    public void setDefLength(Integer defLength);

    public Long getNumberHours();

    public void setNumberHours(Long numberHours);

    public Professor getDefProf();

    public void setDefProf(Professor defProf);

    public RoomGroup getDefRoom();

    public void setDefRoom(RoomGroup defRoom);

    public Bloc getBloc();

    public void setBloc(Bloc bloc);

    public Matter getMatter();

    public void setMatter(Matter matter);

    public Material getDefMat();

    public void setDefMat(Material defMat);

    public Group getGroup();

    public void setGroup(Group group);

}
