package de.berlios.jgss.engine.session.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import de.berlios.jgss.core.Entity;
import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Session;
import de.berlios.jgss.engine.session.Transaction;

public class SessionImpl implements Session{

	private final Connection connection;
	
	protected SessionImpl(String url,String driverClass,String user,String pwd) throws SQLException, ClassNotFoundException{
		Class.forName(driverClass);
		connection=DriverManager.getConnection(url,user,pwd);
	}
	public Transaction beginTransaction()  throws JGSSException{
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new JGSSException(e);
		}
		return null;
	}

	public List<Entity> find(String query) throws JGSSException {
		// TODO Auto-generated method stub
		return null;
	}

	public void close()  throws JGSSException{
		try {
			connection.close();
		} catch (SQLException e) {
			throw new JGSSException(e);
		}
	}

	public Entity load(Class clas, Serializable id) throws JGSSException {
		return null;
	}

	public void save(Entity e) throws JGSSException {
		
	}

	public void update(Entity e) throws JGSSException{
		
	}

	public void delete(Entity e) throws JGSSException{
			
	}

}
