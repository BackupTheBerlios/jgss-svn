package de.berlios.jgss.engine.session.impl;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Interceptor;
import de.berlios.jgss.engine.session.Session;
import de.berlios.jgss.pref.ServerPreferences;

public class HibernateSessionFactory extends de.berlios.jgss.engine.session.SessionFactory{
	private final SessionFactory sessionFactory;
	public HibernateSessionFactory(){
		try {
			sessionFactory =getConfiguration().buildSessionFactory();
		} catch (Exception e) {
            throw new RuntimeException("Problème de configuration : "+ e.getMessage(), e);
        }
		
	}
	
	
	public Session openSession(Interceptor interceptor)throws JGSSException  {
		try {
			return new HibernateSessionAdapter(sessionFactory.openSession(new HibernateInterceptorAdapter(interceptor)));
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}
	private static Configuration config;
  
  
  
	public static Configuration getConfiguration() {
      if(config == null){
          try {
              config=new Configuration().configure();
              String bddURL=ServerPreferences.getInstance().getDBUrl();
              
              String bddUser=ServerPreferences.getInstance().getDBUser();
              String bddPassWord=ServerPreferences.getInstance().getDBPassWord();
              if(bddUser==null)
					throw new RuntimeException("User Not Defined");
              if(bddPassWord==null)       
					throw new RuntimeException("PassWord Not Defined");
              config.setProperty("hibernate.connection.url",bddURL);
              config.setProperty("hibernate.connection.username",bddUser);
              config.setProperty("hibernate.connection.password",bddPassWord);
              config.setProperty("hibernate.connection.driver_class",ServerPreferences.getInstance().getDBDriver());
				config.setProperty("dialect",ServerPreferences.getInstance().getDBDialect());
				config.setProperty("hibernate.transaction.factory_class","de.berlios.jgss.core.transaction.JDBCTransactionFactory");
				
				config.setProperty("hibernate.show_sql","true");
		  } catch (Exception e) {
              throw new RuntimeException("Problème de configuration : "+ e.getMessage(), e);
          }
      }
      return config;
  }

}
