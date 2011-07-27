package br.edu.ufcg.easyLabCorrection.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcg.easyLabCorrection.exceptions.NonexistantAttributeException;

/**
 * Class that contains some functions that are used by several classes of system ELC.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class easyCorrectionUtil {

	/**
	 * Checks if a string consists only of blank spaces or empty.<br>
	 * By exemplor for string "or" Return therefore the true method.<br>
	 * 
	 * @param value The string to be verified if it is empty.<br>
	 * @return A boolean: true - if the string is empty; false - otherwise.<br>
	 */
	public static boolean isEmpty(String value) {
		value = value.trim();
		return value.isEmpty();
	}

	/**
	 * Checks if a value is null or empty.<br>
	 * 
	 * @param value The value to be checked.<br>
	 * @return A boolean: true - if value is null or empty; false - otherwise.<br>
	 */
	public static boolean isNullOrEmpty(String value) {
		return isNull(value) || isEmpty(value);
	}

	/**
	 * Checks if a object is null.<br>
	 * 
	 * @param value The value to be checked.<br>
	 * @return A boolean: true - if object is null; false - otherwise.<br>
	 */
	public static boolean isNull(Object value) {
		return value == null;
	}

	/**
	 * Function which retrieves the current date.<br>
	 * @return The current date.<br>
	 */
	public static Date getDataNow() {
		return getDataNowTimeZero(new Date());
	}
	
	/**
	 * Function which retrieves the current date, but with time zero (the zero hour today).<br>
	 * @return The current date with time zero (the zero hour today).<br>
	 */
	public static Date getDataNowTimeZero(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(data.getTime());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.AM_PM, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * Function which retrieves the date of real time.<br>
	 * @return The date of real time.<br>
	 */
	public static Date getRealTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * Function which returns the date in the format "dd/mm/yyyy".<br>
	 * @param dateTemp The date temporary before to be formated.<br>
	 * @return The formated date.<br>
	 * @throws Exception The exception which can to be launched.<br>
	 */
	public static Date formatData(String dateTemp) throws Exception {
		if (dateTemp == null || dateTemp.equals(""))
			return null;
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(dateTemp);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	/**
	 * Function that Returns an attribute of the object passed, 
	 * provided that it implements the method according to the 
	 * convention to get the desired attribute.<br>
	 * 
	 * @param objectTemp The object under which it invoked the get.<br>
	 * @param attribute The desired attribute of the object.<br>
	 * @return The attribute value of object specified.<br>
	 * @throws AtributoNaoExisteExeption If the specified attribute does not exist.<br>
	 */
	public static Object getAttribute(Object objectTemp, String attribute,
			boolean isBoolean) throws NonexistantAttributeException {
		try {
			Method m = null;
			if (isBoolean) {
				m = objectTemp.getClass().getMethod(
						"is" + correctsAttributeString(attribute));
			} else {
				m = objectTemp.getClass().getMethod(
						"get" + correctsAttributeString(attribute));
			}
			Object valor = m.invoke(objectTemp);
			return valor;
		} catch (Exception e) {
			throw new NonexistantAttributeException(MsgErrors.ATRIBUTO_INVALIDO
					.msg(attribute));
		}
	}

	/**
	 * Function that returns an attribute with the first 
	 * letter in upper case, given a received attribute as 
	 * a parameter.<br> 
	 * @param attribute The attribute that will put the first letter in upper case.<br>
	 * @return The attribute with the first letter in upper case.<br>
	 */
	private static String correctsAttributeString(String attribute) {
		String firstLetter = String.valueOf(attribute.charAt(0));
		return attribute.replaceFirst(firstLetter, firstLetter.toUpperCase());
	}
}
