package br.edu.ufcg.easyLabCorrection.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcg.easyLabCorrection.exceptions.NonexistantAttributeException;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;

/**
 * Class that contains some functions that are used by several classes of system ELC.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 1-August-2011.<br>
 */
public class easyCorrectionUtil {

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
			throw new NonexistantAttributeException(ErrorMsgs.INEXISTENT_ATTRIBUTE
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
	
	public static String getEmailMessage(UserGroup ug){
		
		String message = "<BR>Dear "+ ug.getUser().getName() + ",</BR>" +
			"<BR></BR>" +  
			"<BR>Congratulations, an ELC student account has been created for you.</BR>" +
			"<BR></BR>" +				
			"<BR>Enjoy the most from the system assignment submission facilities.</BR>" + 
			"<BR>Your access data are the following:</BR>" +
			"<BR></BR>" +
			"<BR>Login:</BR>" + 
			"<BR>"+ ug.getUser().getLogin() + "</BR>" + 
			"<BR>Password:</BR><BR>" + ug.getUser().getPassword() +"</br>" + 
			"<p>Obs.: Please remember! Your password was randomly generated and it unstraferable, " +
			"so we suggest you to update it imediatelly.</p>" + 
			"<BR>You can log in at</BR>" +
			"<BR><a href=https://les.dsc.ufcg.edu.br:8443/EasyLabCorrection/>https://les.dsc.ufcg.edu.br:8443/EasyLabCorrection/</a></BR>" +
			"<BR></BR>" +
			"<BR>Graciously,</BR>" +
			"<BR>Easy Lab Correction Team</BR>";

		return message;
	}
}
