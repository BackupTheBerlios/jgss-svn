package de.berlios.jgss.engine.session;

import de.berlios.jgss.engine.exception.JGSSException;

public interface Transaction {
	public void commit() throws JGSSException;

	public void rollback()  throws JGSSException;
}
