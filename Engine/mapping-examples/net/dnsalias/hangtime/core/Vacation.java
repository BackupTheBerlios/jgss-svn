package net.dnsalias.hangtime.core;


import java.util.Date;
import de.berlios.jgss.core.Entity;

public interface Vacation extends Entity {

    public Date getBeginDate();

    public void setBeginDate(Date beginDate);

    public Date getEndDate();

    public void setEndDate(Date endDate);

    public Group getGroup();

    public void setGroup(Group group);

}
