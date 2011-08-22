package br.edu.ufcg.easyLabCorrection.util;

import java.security.Permission;

public class SecuritySupport extends SecurityManager{

	private Object secret;
	
	public SecuritySupport(Object pass){ 
		secret = pass; 
	}
	
	public void disable(Object pass) {
		if (pass == secret){
			secret = null;
		}
	}
	
	public void checkWrite(String file) { 
		if (!file.endsWith(".tmp") || secret == null) {
			throw new SecurityException ("Write attempt: " 
				+ file);
		}
	}
	
	public void checkRead(String file) {
		if(secret == null){
			throw new SecurityException ("Read attempt: " 
					+ file);
		}
	}
	
	public void checkCreateClassLoader(){
		if(secret == null){
			throw new SecurityException ("Create ClassLoader attempt!");
		}
	}
	
	public void checkPermission(Permission perm){
		if(secret == null){
			throw new SecurityException ("Permission attempt!");
		}
	}
	
	// ... override checkXXX method(s) here.
	// Always allow them to succeed when secret==null

}
