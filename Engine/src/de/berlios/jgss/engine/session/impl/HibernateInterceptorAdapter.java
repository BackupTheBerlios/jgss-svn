package de.berlios.jgss.engine.session.impl;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.type.Type;
import de.berlios.jgss.engine.session.Interceptor;

public class HibernateInterceptorAdapter implements net.sf.hibernate.Interceptor{
	private final Interceptor interceptor;
	
	public HibernateInterceptorAdapter(Interceptor _interceptor){
		interceptor=_interceptor;
	}

	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		return false;
	}

	public boolean onFlushDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) throws CallbackException {
		return false;
	}

	public boolean onSave(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		return false;
	}

	public void onDelete(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		
	}

	public void preFlush(Iterator arg0) throws CallbackException {
		
	}

	public void postFlush(Iterator arg0) throws CallbackException {
		interceptor.postFlush(arg0);
	}

	public Boolean isUnsaved(Object arg0) {
		return null;
	}

	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) {
		return null;
	}

	public Object instantiate(Class arg0, Serializable arg1) throws CallbackException {
		return null;
	}
}
