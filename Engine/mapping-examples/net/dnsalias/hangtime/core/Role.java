package net.dnsalias.hangtime.core;


public interface Role extends Ressource  {
   public Boolean isViewedt();
   public void setViewedt(Boolean b);
   
   public Boolean isEditedt();
   public void setEditedt(Boolean b);
   
   public Boolean isViewressource();
   public void setViewressource(Boolean b);
   
   public Boolean isViewstat();
   public void setViewstat(Boolean b);
   
   public Boolean isEditressource();
   public void setEditressource(Boolean b);
   
   public Boolean isAdmin();
   public void setAdmin(Boolean b);
}
