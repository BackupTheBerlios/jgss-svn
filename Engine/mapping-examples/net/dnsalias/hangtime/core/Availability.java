package net.dnsalias.hangtime.core;

import java.util.Date;
import de.berlios.jgss.core.Entity;

public interface Availability extends Entity  {

    public Integer getDays();

    public void setDays(Integer days);

    public Date getBeginDate();

    public void setBeginDate(Date beginDate);

    public Boolean getType();

    public void setType(Boolean type);

    public Date getEndDate();

    public void setEndDate(Date endDate);

    public Long getHours();

    public void setHours(Long hours);

    public Professor getProf();

    public void setProf(Professor prof);
}
