/*
 * Created on 4 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.berlios.jgss.pref;

import java.rmi.registry.Registry;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * HangTimePreference<br>
 * <br>
 * @author Grégory Cuellar ( HangTime - Development and Technical Director )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Université Marne-la-Vallée<br>
 * 	Cité Descartes<br>
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
public class ServerPreferences {
    /** The configuration **/
	private XMLConfiguration config;
	
	/** The server config file **/
	private static final String CONFIG_FILE = "jgss.conf.xml";
	
	/** Optimized Singleton instance **/
	private static final ServerPreferences INSTANCE = new ServerPreferences (CONFIG_FILE);
	
	
    private ServerPreferences(String path){
		try
		{
			XMLConfiguration.setDelimiter('\n');
			this.config = new XMLConfiguration (path);
			
		}
		catch (ConfigurationException e)
		{
			throw new RuntimeException (e);
		}
    }
    
	
	/**
	 * To get the optimized singleton instance
	 * 
	 * @return the optimized singleton instance
	 */
	public static ServerPreferences getInstance ()
	{
		return INSTANCE;
	}


	public int getServerPort() {
		return config.getInt("SERVER.PORT",Registry.REGISTRY_PORT);
	}


	public String getDBUrl() {
		return config.getString("DB.URL");
	}


	public String getDBUser() {
		return config.getString("DB.USER","");
	}


	public String getDBPassWord() {
		return config.getString("DB.PWD","");
	}
	public String getDBDriver() {
		return config.getString("DB.DRIVER","org.postgresql.Driver");
	}

	public String getDBDialect() {
		return config.getString("DB.DIALECT","net.sf.hibernate.dialect.PostgreSQLDialect");
	}
}