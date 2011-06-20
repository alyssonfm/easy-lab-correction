package br.edu.ufcg.easyLabCorrection.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class responsible for the automatic generation of passwords for the system.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class PasswordGenerator {


	/**
	 * Function that generates the password.<br>
	 * @param digits The digits used to generates the password.<br>
	 * @param key The key used in generated of password.<br>
	 * @return The password generated<br>
	 */
		public static String generatePassword(int digits, String key) {
			String[] keys = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
					"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

			List<String> chars = new ArrayList<String>();
			Random random = new Random();
			String password = "";

			for (int i = 0; i < keys.length; i++) {
				chars.add(keys[i].toString());
			}

			for (int i = 0; i < key.length(); i++) {
				chars.add(key.substring(i, i + 1));
			}

			for (int i = 0; i < digits; i++) {
				password += chars.get(random.nextInt(chars.size()));
			}

			return password;
		}
}
