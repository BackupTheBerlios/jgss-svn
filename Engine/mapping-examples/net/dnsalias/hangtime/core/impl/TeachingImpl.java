package net.dnsalias.hangtime.core.impl;

import java.util.Set;

import net.dnsalias.hangtime.core.Bloc;
import de.berlios.jgss.core.Entity;
import net.dnsalias.hangtime.core.Group;
import net.dnsalias.hangtime.core.HColor;
import net.dnsalias.hangtime.core.Material;
import net.dnsalias.hangtime.core.Matter;
import net.dnsalias.hangtime.core.Professor;
import net.dnsalias.hangtime.core.RoomGroup;
import net.dnsalias.hangtime.core.Teaching;


/** @author Hibernate CodeGenerator */
public class TeachingImpl extends RessourceImpl implements Teaching {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private Integer factor;

    /** nullable persistent field */
    private Integer defLength;

    /** nullable persistent field */
    private Long numberHours;

    /** persistent field */
    private Professor defProf;

    /** persistent field */
    private RoomGroup defRoom;

    /** persistent field */
    private Bloc bloc;

    /** persistent field */
    private Matter matter;

    /** persistent field */
    private Material defMat;

    /** persistent field */
    private Group group;

    /** persistent field */
    private HColor color;

    private Set courses;
    
    /** full constructor */
    public TeachingImpl(String name, Integer factor, Integer defLength, Long numberHours, RoomGroup defRoom, Professor defProf, Bloc bloc, Matter matter, Material defMat, Group group, HColor color,Set courses){
    	super(TYPE_TEACHING,name);
        this.factor = factor;
        this.defLength = defLength;
        this.numberHours = numberHours;
        this.defRoom = defRoom;
        this.defProf = defProf;
        this.bloc = bloc;
        this.matter = matter;
        this.defMat = defMat;
        this.group = group;
        this.color = color;
        this.courses=courses;
    }

    /** default constructor */
    public TeachingImpl() {
    	super(TYPE_TEACHING);
    }

    /** minimal constructor */
    public TeachingImpl(String name, Professor defProf, RoomGroup defRoom, Bloc bloc, Matter matter, Material defMat, Group group){
    	super(TYPE_TEACHING,name);
        this.defProf = defProf;
        this.defRoom = defRoom;
        this.bloc = bloc;
        this.matter = matter;
        this.defMat = defMat;
        this.group = group;
    }
    
    public void setCourses(Set courses){
    	this.courses=courses;
    }
    
    public Set getCourses(){
    	return courses;
    }
    
    public Integer getFactor() {
        return this.factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public HColor getColor() {
        return this.color;
    }

    public void setColor(HColor color) {
        this.color = color;
    }

    public Integer getDefLength() {
        return this.defLength;
    }

    public void setDefLength(Integer defLength) {
        this.defLength = defLength;
    }

    public Long getNumberHours() {
        return this.numberHours;
    }

    public void setNumberHours(Long numberHours) {
        this.numberHours = numberHours;
    }

    public Professor getDefProf() {
        return this.defProf;
    }

    public void setDefProf(Professor defProf) {
        this.defProf = defProf;
    }

    public RoomGroup getDefRoom() {
        return this.defRoom;
    }

    public void setDefRoom(RoomGroup defRoom) {
        this.defRoom = defRoom;
    }

    public Bloc getBloc() {
        return this.bloc;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    public Matter getMatter() {
        return this.matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public Material getDefMat() {
        return this.defMat;
    }

    public void setDefMat(Material defMat) {
        this.defMat = defMat;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
  
    public boolean use(Entity e) {
		return (((defRoom==null)?false:defRoom.equals(e))||((defProf==null)?false:defProf.equals(e))||((bloc==null)?false:bloc.equals(e))||((matter==null)?false:matter.equals(e))||((defMat==null)?false:defMat.equals(e))||((group==null)?false:group.equals(e))||((courses==null)?false:courses.contains(e)));
	}
}
