package br.edu.les.easyCorrection.util;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;



public class easyCorrectionUtil { 

	
	/**
	 * Verifica se uma string é composta apenas por espacos em branco ou vazia. 
	 * Por exemplor para as string "    "  ou "" o metodo retornaira true. 
	 * @param valor
	 * @return
	 */
	public static boolean isVazio(String valor){
	//	valor = valor.replaceAll(" ", ""); //substituindo os espacos em branco
		valor = valor.trim(); 
		return valor.isEmpty();
	}
	
	
	/**
	 * Verifica se um valor � null ou vazio, incluindo os car 
	 * @param valor
	 * @return
	 */
	public static boolean isNullOrVazio(String valor){
		return isNull(valor) || isVazio(valor);
	}
	
	/**
	 * Verifica se um objeto � null
	 * @param valor
	 * @return
	 */
	public static boolean isNull(Object valor) {
		return valor==null;
	}



	/**
	 * Recupera a data Atual
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
			if(isNull(valorID)){
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Recupera a data Atual
	 * @return
	 */
	public static Date getDataNowTimeZero(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(data.getTime());
		c.set(Calendar.HOUR,0);
		c.set(Calendar.AM_PM,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		return c.getTime();
	}
	
	

}
