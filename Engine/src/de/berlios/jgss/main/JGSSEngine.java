/*
 * Created on 3 f�vr. 2005
 */
package de.berlios.jgss.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import de.berlios.jgss.core.rmi.datamodels.RemoteModelManager;
import de.berlios.jgss.core.rmi.datamodels.impl.RemoteModelManagerImpl;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.pref.ServerPreferences;

/**
 * HangTimeServer<br>
 * <br>
 * @author Gr�gory Cuellar ( HangTime - Development and Technical Director )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Universit� Marne-la-Vall�e<br>
 * 	Cit� Descartes<br>
 *  5, bd Descartes<br>
 *  Champs-sur-Marne<br>
 *  77454 MARNE-LA-VALLEE CEDEX 2<br>
 *  FRANCE<br>
 * <br>
 * e-mail: gcuellar@etudiant.univ-mlv.fr<br>
 * site: http://hangtime.dnsalias.net:8080<br>
 * bugs: http://hangtime.free.fr/mantis<br>
 * <br>
 */
public class JGSSEngine {

    public static String SRV_PORT = "port";
    
    private static Log log ;
       
    
    
    public static void main(String[]args)throws AccessException, RemoteException, AlreadyBoundException, UnknownHostException, JGSSException{
		System.setProperty("java.rmi.server.ignoreStubClasses","true");

        log= LogFactory.getLog(JGSSEngine.class);
        PropertyConfigurator.configure("jgss.log4j.properties");
        
        int port = ServerPreferences.getInstance().getServerPort();
        
        Registry registry = LocateRegistry.createRegistry(port);
        log.info("Serveur D�marr�");
        log.info("IP serveur : " + InetAddress.getLocalHost().getHostAddress());
        log.info("Port serveur : " +port);
        RemoteModelManager manager = RemoteModelManagerImpl.getInstance();
		
		UnicastRemoteObject.exportObject(manager,port);
        registry.bind(RemoteModelManager.REGISTRY_NAME,manager);
        log.info("Export du remote manager en tant que : '"+RemoteModelManager.REGISTRY_NAME+"'");
    }
    
}
