package de.berlios.jgss.engine.session;

import java.io.Serializable;
import java.util.List;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.engine.exception.JGSSException;

public interface Session {
	public Transaction beginTransaction()  throws JGSSException;

	public List<Entity> find(String query) throws JGSSException ;

	public void close()  throws JGSSException;

	public Entity load(Class clas, Serializable id) throws JGSSException ;

	public void save(Entity e) throws JGSSException ;

	public void update(Entity e) throws JGSSException;

	public void delete(Entity e) throws JGSSException;
}
