package de.berlios.jgss.asmagent.asm;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.CodeVisitor;

/**
 * 
 * RMIClassTransformer
 * <br>
 * @author Grégory Cuellar ( HangTime - Development and Technical Director )<br>
 * UFR Ingenieurs 2000<br>
 * <br>
 * Université Marne-la-Vallée<br>
 * 	Cité Descartes<br>
 *  5, bd Descartes<br>
 *  Champs-sur-Marne<br>
 *  77454 MARNE-LA-VALLEE CEDEX 2<br>
 *  FRANCE<br>
 * <br>
 * e-mail: gcuellar@etudiant.univ-mlv.fr<br>
 * site: http://hangtime.dnsalias.net:8080
 * bugs: http://hangtime.free.fr/mantis
 * <br>
 */
public class RMIClassTransformer implements ClassFileTransformer{
	/** The unicast remote object class name **/
	private static final String UNICAST_REMOTE_RMI_CLASSNAME= getInternalName (UnicastRemoteObject.class);
	/** The remote interface class name **/
	private static final String REMOTE_CLASSNAME			= getInternalName (Remote.class);
	/** The remote exception class name **/
	private static final String REMOTE_EXCEPTION_CLASSNAME	= getInternalName (RemoteException.class);

	
	/** Set of the objet methods **/
	private static Set<String> objectMethods = new HashSet<String>();
	
	static{
		objectMethods.add ("equals"+"(Ljava/lang/Object;)Z");
		objectMethods.add ("toString"+"()Ljava/lang/String;");
		objectMethods.add ("hashCode"+"()I");
	}
	
	
	private static Method defineClass;
	
	static{
		try{
			Class [] classes = new Class [] {String.class, byte[].class, int.class, int.class, ProtectionDomain.class};  
			
			defineClass = ClassLoader.class.getDeclaredMethod ("defineClass", classes);
		}
		catch (NoSuchMethodException e){
			throw new AssertionError(e);
		}
		
		defineClass.setAccessible (true);
	}
	
	//----------------------------------------------------------//
	//------------------- PUBLIC METHODS -----------------------//
	//----------------------------------------------------------//
	
	/**
	 * Method used to transform the interested classes
	 * 
	 * @see java.lang.instrument.ClassFileTransformer#transform(java.lang.ClassLoader, java.lang.String, java.lang.Class, java.security.ProtectionDomain, byte[])
	 */
	public byte[] transform (final ClassLoader loader, final String szName, final Class clazz, final ProtectionDomain domain, final byte[] code) throws IllegalClassFormatException{
		// if it's not the first load or a bootstrap class
		if (loader == null || clazz != null)
			return null;
		
		ClassReader reader = new ClassReader (code);
		final RemoteClassVisitor visitor = new RemoteClassVisitor ();
		reader.accept (visitor, true);
		
		if (!visitor.isRemote)
			return null;
		
		ClassWriter writer = new ClassWriter (false);
		
		RewriteClassAdapter rewriter = new RewriteClassAdapter (writer);
		reader.accept (rewriter, false);
		
		return writer.toByteArray();
	}
	
	
	/**
	 * Pre main method called first
	 * 
	 * @param szAgentArgs the agent arguments
	 * @param inst 		  the instrumentation
	 */
	public static void premain (final String szAgentArgs, final Instrumentation inst){
		inst.addTransformer (new RMIClassTransformer());
	}
	
	
	//----------------------------------------------------------//
	//------------------ PRIVATE METHODS -----------------------//
	//----------------------------------------------------------//
	
	/**
	 * To transform the class name by replacing the . by /
	 * 
	 * @param clazz the class
	 * 
	 * @return the internal class name
	 */
	private static String getInternalName (final Class clazz){
		return clazz.getName ().replace('.', '/');
	}
	
	//----------------------------------------------------------//
	//------------------ PRIVATE CLASSES -----------------------//
	//----------------------------------------------------------//

	
	private class RemoteClassVisitor implements ClassVisitor
	{
		/** Boolean to know if it is a remote implemented class **/
		private boolean isRemote = false;
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visit(int, java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
		 */
		public void visit (final int iAccess, final String szName, final String szSuperName, final String [] interfaces, final String szSourceFile){
			if ((REMOTE_CLASSNAME.equals (szSuperName))||(UNICAST_REMOTE_RMI_CLASSNAME.equals (szSuperName))){
				isRemote = true;
				return;
			}
			
			for (int i = 0; i < interfaces.length; i++){
				if (REMOTE_CLASSNAME.equals (interfaces [i])){
					isRemote = true;
					break;
				}
			}
		}
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visitAttribute(org.objectweb.asm.Attribute)
		 */
		public void visitAttribute (final Attribute attribute){}		
		
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visitField(int, java.lang.String, java.lang.String, java.lang.Object, org.objectweb.asm.Attribute)
		 */
		public void visitField (final int iAccess, final String szName, final String szDesc, final Object value, final Attribute attrs){}
		
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visitInnerClass(java.lang.String, java.lang.String, java.lang.String, int)
		 */
		public void visitInnerClass (final String szName, final String szOuterName, final String szInnerName, final int iAccess){}
		
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visitMethod(int, java.lang.String, java.lang.String, java.lang.String[], org.objectweb.asm.Attribute)
		 */
		public CodeVisitor visitMethod (final int iAccess, final String szName, final String szDesc, final String[] exceptions, final Attribute attrs){
			return null;
		}
		
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visitEnd()
		 */
		public void visitEnd (){}
	}
	
	
	/**
	 * Private class used to rewrite the class
	 * 
	 * @author Ludo
	 */
	private static class RewriteClassAdapter extends ClassAdapter
	{
		/**
		 * Constructor
		 * 
		 * @param writer the class writer
		 */
		public RewriteClassAdapter (final ClassWriter writer){
			super (writer);
		}
		
		
		/**
		 * @see org.objectweb.asm.ClassVisitor#visitMethod(int, java.lang.String, java.lang.String, java.lang.String[], org.objectweb.asm.Attribute)
		 */
		public CodeVisitor visitMethod (final int iAccess, final String szName, final String szDesc, final String[] exceptions, final Attribute attrs){
			if (!Modifier.isStatic (iAccess) && !objectMethods.contains (szName + szDesc)){
				Set<String> set = null;
				
				if (exceptions == null)
					set = new HashSet <String>();
				else
					set = new HashSet <String>(Arrays.asList (exceptions));
				
				set.add(REMOTE_EXCEPTION_CLASSNAME);
				
				return super.visitMethod (iAccess, szName, szDesc, (String []) set.toArray (new String [set.size()]), attrs);  
			}
			
			return super.visitMethod (iAccess, szName, szDesc, exceptions, attrs);
		}
	}
}
