package de.berlios.jgss.core.impl;

import java.rmi.RemoteException;
import java.util.Iterator;

import junit.framework.TestCase;
import de.berlios.jgss.api.model.AbstractDataModel;
import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.Bloc;

/**
* <br>
* @author Sébastien Guinchard ( HangTime )<br>
* UFR Ingenieurs 2000<br>
* <br>
* Université Marne-la-Vallée<br>
* 	Cité Descartes<br>
*  5, bd Descartes<br>
*  Champs-sur-Marne<br>
*  77454 MARNE-LA-VALLEE CEDEX 2<br>
*  FRANCE<br>
* <br>
* e-mail: sguincha@etudiant.univ-mlv.fr<br>
* site: http://hangtime.dnsalias.net:8080
* bugs: http://hangtime.free.fr/mantis
* <br>
*/

public class TestBlocImpl extends TestCase{
    
	AbstractDataModel tmpModel=null;
    String name1="bloc seb1";
    String name2="bloc seb2";
    Bloc bloc1=new BlocImpl(name1);
    Bloc bloc2=new BlocImpl(name2);
    
	
	public void testPojoClass(){
        assertEquals(bloc1.getName(),name1);

    }
    
	public void testAddBloc() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
    	

			
		try {
			ModelManager.getInstance().beginTransaction(); 
			ModelManager.getInstance().save(bloc1);
			ModelManager.getInstance().commit();

			tmpModel=new AbstractDataModel(BlocImpl.class,"From BlocImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Bloc bloc = (Bloc)it.next();
				if(bloc.getName().equals(bloc1.getName())) {
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(bloc);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			fail();

		} catch (RemoteException e) {e.printStackTrace();}		       
    }
    
    
   public void testRemoveBloc() {
    	System.setProperty("java.rmi.server.ignoreStubClasses","true");
    	
    	ModelManager.getInstance().beginTransaction();
		ModelManager.getInstance().save(bloc2);
		ModelManager.getInstance().commit();
		try {
			
			
			tmpModel=new AbstractDataModel(BlocImpl.class,"From BlocImpl");
			for (Iterator it = tmpModel.getList().iterator(); it.hasNext();) {
				Bloc bloc= (Bloc)it.next();
				if(bloc.getName().equals(bloc2.getName())) { 				 
					ModelManager.getInstance().beginTransaction();
					ModelManager.getInstance().delete(bloc);
					ModelManager.getInstance().commit();
					assertTrue(true);
					return;
				}
			}
			
		} catch (RemoteException e) {e.printStackTrace();}
		assertFalse(true);
    }
      
}
