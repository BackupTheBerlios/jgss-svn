package de.berlios.jgss.engine.session;

import java.util.Iterator;

public interface Interceptor {
	public void postFlush(Iterator entities);
}
