package de.berlios.jgss.engine.session.impl;

import de.berlios.jgss.engine.exception.JGSSException;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Transaction;

public class HibernateTransactionAdapter implements de.berlios.jgss.engine.session.Transaction{
	private final Transaction transaction;
	
	public HibernateTransactionAdapter(Transaction _transaction){
		transaction= _transaction;
	}

	public void commit() throws JGSSException {
		try {
			transaction.commit();
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}

	public void rollback() throws JGSSException {
		try {
			transaction.rollback();
		} catch (HibernateException e) {
			throw new JGSSException(e);
		}
	}
}
