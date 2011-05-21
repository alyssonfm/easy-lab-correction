package br.edu.ufcg.easyLabCorrection.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcg.easyLabCorrection.exceptions.NonexistantAttributeException;

public class easyCorrectionUtil {

	/**
	 * Verifica se uma string é composta apenas por espacos em branco ou vazia.
	 * Por exemplor para as string "    " ou "" o metodo retornaira true.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		value = value.trim();
		return value.isEmpty();
	}

	/**
	 * Verifica se um valor � null ou vazio, incluindo os car
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullOrEmpty(String value) {
		return isNull(value) || isEmpty(value);
	}

	/**
	 * Verifica se um objeto � null
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(Object value) {
		return value == null;
	}

	/**
	 * Recupera a data Atual
	 * 
	 * @return
	 */
	public static Date getDataNow() {
		return getDataNowTimeZero(new Date());
	}
	
	/**
	 * Recupera a data Atual, mas com o tempo zerado (as zero horas de hoje) 
	 * 
	 * @return
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
	
	public static Date getRealTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

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
	 * Retorna um atributo do objeto passado, desde de que ele implemente o
	 * metodo get segundo a convencao para o atributo desejado.
	 * 
	 * @param objectTemp
	 *            O objeto sob o qual se invocara o get.
	 * @param attribute
	 *            O atributo desejado do objeto.
	 * @return O valor do atributo especificado do objeto.
	 * @throws AtributoNaoExisteExeption
	 *             Caso o atributo especificado nao exista.
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
			throw new NonexistantAttributeException(MsgErros.ATRIBUTO_INVALIDO
					.msg(attribute));
		}
	}

	private static String correctsAttributeString(String attribute) {
		String firstLetter = String.valueOf(attribute.charAt(0));
		return attribute.replaceFirst(firstLetter, firstLetter.toUpperCase());
	}
}
