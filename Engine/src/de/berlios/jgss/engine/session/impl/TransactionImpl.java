package de.berlios.jgss.engine.session.impl;

import java.sql.Connection;
import java.sql.SQLException;

import de.berlios.jgss.engine.exception.JGSSException;
import de.berlios.jgss.engine.session.Transaction;

public class TransactionImpl implements Transaction{
	private final Connection connection;
	private boolean hasCommited;
	protected TransactionImpl(Connection connection){
		this.connection=connection;
	}
	public void commit() throws JGSSException{
		try {
			connection.commit();
			hasCommited=true;
		} catch (SQLException e) {
			throw new JGSSException(e);
		}
	}

	public void rollback()  throws JGSSException{
		try{
			connection.rollback();
			hasCommited=true;
		} catch (SQLException e) {
			throw new JGSSException(e);
		}
	}
	public boolean hasCommited(){
		return hasCommited;
	}
}
