/*
 * Créé le 24 janv. 2005
 */
package de.berlios.jgss.client.model;

import de.berlios.jgss.api.model.ModelManager;
import de.berlios.jgss.core.impl.TeachingImpl;
import de.berlios.jgss.core.rmi.datamodels.RemoteDataModel;
import junit.framework.TestCase;




/**
 * 
 * @author RLataix
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestModelManager extends TestCase{
	public void testModelManagerSingleton(){
        assertSame(ModelManager.getInstance(),ModelManager.getInstance());
    }
	
	public void testModelManagerModel(){
		ModelManager modelManager=ModelManager.getInstance();
    }
	
	public void testModelManagerObject(){
		ModelManager modelManager=ModelManager.getInstance();
		RemoteDataModel model = modelManager.getModel(TeachingImpl.class,"From Teaching");
    }
	
	public void testModelManagerLoad(){
		ModelManager modelManager=ModelManager.getInstance();
    }
	
	public void testModelManagerSave(){
		ModelManager modelManager=ModelManager.getInstance();
    }
	
	public void testModelManagerUpdate(){
		ModelManager modelManager=ModelManager.getInstance();
    }
	
	public void testModelManagerDelete(){
		ModelManager modelManager=ModelManager.getInstance();
    }
}
