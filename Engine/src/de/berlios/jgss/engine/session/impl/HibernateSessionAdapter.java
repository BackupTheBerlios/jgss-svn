package de.berlios.jgss.engine.session.impl;

import java.io.Serializable;
import java.util.List;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Transaction;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;


public class HibernateSessionAdapter implements de.berlios.jgss.engine.session.Session{
	private final Session session;
	
	public HibernateSessionAdapter(Session _session){
		session=_session;
	}
	
	public Transaction beginTransaction() throws JGSSException {
		try {
			return new HibernateTransactionAdapter(session.beginTransaction());
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}
	
	public void close() throws JGSSException {
		try {
			session.close();
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}
	public void delete(Entity e) throws JGSSException {
		try {
			session.delete(e);
		} catch (HibernateException ex) {
			throw new JGSSException(ex);
		}
	}
	
	public Entity load(Class clas, Serializable id) throws JGSSException {
		try {
			return (Entity) session.load(clas,id);
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}
	
	public List<Entity> find(String query) throws JGSSException {
		try {
			return session.find(query);
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}
	
	public void save(Entity e) throws JGSSException {
		try {
			session.save(e);
		} catch (HibernateException ex) {
			throw new JGSSException(ex);
		}
	}
	
	public void update(Entity e) throws JGSSException {
		try {
			session.update(e);
		} catch (HibernateException ex) {
			throw new JGSSException(ex);
		}
	}
}
