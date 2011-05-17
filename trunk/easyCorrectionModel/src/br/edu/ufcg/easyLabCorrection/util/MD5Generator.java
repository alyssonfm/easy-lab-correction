package br.edu.ufcg.easyLabCorrection.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

	public static String md5(String password) {  
		String pass = "";  
		MessageDigest md = null;  
		try {  
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));  
			pass = hash.toString(16);              
			return pass;
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		}  
		return null;
	}
}
