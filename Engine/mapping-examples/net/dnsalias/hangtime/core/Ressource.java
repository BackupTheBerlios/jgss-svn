
package net.dnsalias.hangtime.core;

import de.berlios.jgss.core.Entity;

public interface Ressource extends Entity {
	public static final int TYPE_ADDRESS=0;
	public static final int TYPE_AVAILABILITY=1;
	public static final int TYPE_BLOC=2;
	public static final int TYPE_BUILDING=3;
	public static final int TYPE_COURSE=4;
	public static final int TYPE_GROUP=5;
	public static final int TYPE_MATERIAL=6;
	public static final int TYPE_MATERIALTYPE=7;
	public static final int TYPE_MATTER=8;
	public static final int TYPE_PEOPLE=9;
	public static final int TYPE_PROFESSOR=10;
	public static final int TYPE_RESSOURCE=11;
	public static final int TYPE_ROLE=12;
	public static final int TYPE_ROOM=13;
	public static final int TYPE_ROOMGROUP=14;
	public static final int TYPE_ROOMTYPE=15;
	public static final int TYPE_STUDENT=16;
	public static final int TYPE_TEACHING=17;
	public static final int TYPE_USER=18;
	public static final int TYPE_VACATION=19;
	
	public String getName();

    public void setName(String name);
}
