package br.edu.les.easyCorrection.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.les.easyCorrection.exceptions.AtributoNaoExisteException;

public class easyCorrectionUtil {

	/**
	 * Verifica se uma string é composta apenas por espacos em branco ou vazia.
	 * Por exemplor para as string "    " ou "" o metodo retornaira true.
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isVazio(String valor) {
		// valor = valor.replaceAll(" ", ""); //substituindo os espacos em
		// branco
		valor = valor.trim();
		return valor.isEmpty();
	}

	/**
	 * Verifica se um valor � null ou vazio, incluindo os car
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isNullOrVazio(String valor) {
		return isNull(valor) || isVazio(valor);
	}

	/**
	 * Verifica se um objeto � null
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isNull(Object valor) {
		return valor == null;
	}

	/**
	 * Recupera a data Atual
	 * 
	 * @return
	 */
	public static Date getDataNow() {
		return getDataNowTimeZero(new Date());
	}

	public static boolean objetoIdIsNull(Object objeto) {
		Method m;
		try {
			m = objeto.getClass().getMethod("getId");
			Object valorID = m.invoke(objeto);
			if (isNull(valorID)) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}

		return false;
	}

	/**
	 * Recupera a data Atual
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

	public static Date formataData(String data) throws Exception {
		if (data == null || data.equals(""))
			return null;
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	/**
	 * Retorna um atributo do objeto passado, desde de que ele implemente o
	 * metodo get segundo a convencao para o atributo desejado.
	 * 
	 * @param objeto
	 *            O objeto sob o qual se invocara o get.
	 * @param atributo
	 *            O atributo desejado do objeto.
	 * @return O valor do atributo especificado do objeto.
	 * @throws AtributoNaoExisteExeption
	 *             Caso o atributo especificado nao exista.
	 */
	public static Object getAtributo(Object objeto, String atributo,
			boolean isBoolean) throws AtributoNaoExisteException {
		try {
			Method m = null;
			if (isBoolean) {
				m = objeto.getClass().getMethod(
						"is" + corrigeStringAtributo(atributo));
			} else {
				m = objeto.getClass().getMethod(
						"get" + corrigeStringAtributo(atributo));
			}
			Object valor = m.invoke(objeto);
			return valor;
		} catch (Exception e) {
			throw new AtributoNaoExisteException(MsgErros.ATRIBUTO_INVALIDO
					.msg(atributo));
		}
	}

	private static String corrigeStringAtributo(String atributo) {
		String primeiraLetra = String.valueOf(atributo.charAt(0));
		return atributo
				.replaceFirst(primeiraLetra, primeiraLetra.toUpperCase());
	}

}
