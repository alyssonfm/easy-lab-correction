package br.edu.ufcg.easyLabCorrection.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class responsible for generating the MD5 in the system.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class MD5Generator {

	/**
	 * Function that generating the MD5.<br>
	 * @param password The string that have generated the MD5.<br>
	 * @return The MD5.<br>
	 */
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
