package net.dnsalias.hangtime.core.impl;


import net.dnsalias.hangtime.core.Role;


/** @author Hibernate CodeGenerator */
public class RoleImpl extends RessourceImpl implements Role {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

    private Boolean isAdmin;
    private Boolean isEditedt;
    private Boolean isEditressource;
    private Boolean isViewedt;
    private Boolean isViewressource;
    private Boolean isViewstat;
    
	/** full constructor */
    public RoleImpl(String name,Boolean isAdmin, Boolean isEditedt,
            Boolean isEditressource, Boolean isViewedt,
            Boolean isViewressource, Boolean isViewstat){
    	super(TYPE_ROLE,name);
         this.isAdmin = isAdmin;
         this.isEditedt = isEditedt;
         this.isEditressource = isEditressource;
         this.isViewedt = isViewedt;
         this.isViewressource = isViewressource;
         this.isViewstat = isViewstat;
    }

    /** default constructor */
    public RoleImpl(){
    	super(TYPE_ROLE);
    }
    
    public Boolean isAdmin() {
        return isAdmin;
    }
    public Boolean isEditedt() {
        return isEditedt;
    }
    public Boolean isEditressource() {
        return isEditressource;
    }
    public Boolean isViewedt() {
        return isViewedt;
    }
    public Boolean isViewressource() {
        return isViewressource;
    }
    public Boolean isViewstat() {
        return isViewstat;
    }
    public void setAdmin(Boolean b) {
        isAdmin=b;
    }
    public void setEditedt(Boolean b) {
        isEditedt=b;
    }
    public void setEditressource(Boolean b) {
        isEditressource=b;
    }
    public void setViewedt(Boolean b) {
        isViewedt=b;
    }
    public void setViewressource(Boolean b) {
        isViewressource=b;
    }
    public void setViewstat(Boolean b) {
        isViewstat=b;
    }
}
