package de.berlios.jgss.engine.session;

import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.impl.HibernateSessionFactory;


public abstract class SessionFactory {
	
	private static final SessionFactory sessionFact=new HibernateSessionFactory();
	public static SessionFactory getInstance(){
		return sessionFact;
	}
	public abstract Session openSession(Interceptor interceptor) throws JGSSException;
}
