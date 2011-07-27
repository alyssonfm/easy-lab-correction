package br.edu.ufcg.easyLabCorrection.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.exceptions.NonexistantAttributeException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;

/**
 * Class responsible for the reflection and exchange of attributes.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class SwapperAtributosReflect {

	/**
	 * Function which copies the attributes of an object (objAtributosACopiar) to 
	 * another (objetoOriginal), except that Object Id is formed by 
	 * idNomedaClasse and serialVersionUID. A copy of the attributes 
	 * is performed also with attributes of the superclass.<br>
	 * 
	 * @param objetoOriginal The original object.<br>
	 * @param objAtributosACopiar The atrributes to be copied.<br>
	 * @param classe The class for which you want to copy the attributes.<br>
	 * @return The object with the new attributes.<br>
	 * @throws EasyCorrectionException An exception which to be launched during the swap.<br>
	 */
	@SuppressWarnings("unchecked")
	public static Object swapObject(Object objetoOriginal,
			Object objAtributosACopiar, Class classe)
			throws EasyCorrectionException {
		return swapObject(objetoOriginal, objAtributosACopiar, true, classe);
	}

	/**
	 * Function which it also allows to verify or dismiss the methods of the 
	 * class or the methods of the parent class.<br>
	 * 
	 * @param objetoOriginal The original object.<br>
	 * @param objAtributosACopiar The atrributes to be copied.<br>
	 * @param classePai The boolean indicating whether it is 
	 * part of the parent class.<br>
	 * @param classe The class child.<br>
	 * @return The object exchanged.<br>
	 * @throws EasyCorrectionException An exception which to be launched during the swap.<br>
	 */
	@SuppressWarnings("unchecked")
	public static Object swapObject(Object objetoOriginal,
			Object objAtributosACopiar, boolean classePai, Class classe)
			throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(objAtributosACopiar)) {
			List<String> restricoesAtributos = new LinkedList<String>();
			restricoesAtributos.add("serialVersionUID");
			restricoesAtributos.add("id");
			restricoesAtributos.add("id" + classe.getSimpleName());
			ArrayList<Field> fields = new ArrayList<Field>();
			ArrayList<Method> declaredMetodos = new ArrayList<Method>();
			// adicionar os metodos da superclass
			if (classePai) {
				Class superClasse = classe.getSuperclass();
				restricoesAtributos.add("id" + superClasse.getSimpleName());
				fields.addAll(Arrays.asList(superClasse.getDeclaredFields()));
				declaredMetodos.addAll(Arrays.asList(superClasse
						.getDeclaredMethods()));
			}
			fields.addAll(Arrays.asList(classe.getDeclaredFields()));
			declaredMetodos.addAll(Arrays.asList(classe.getDeclaredMethods()));
			for (Field atributo : fields) {
				// retirar o id e serialVersionUID
				String nomeAtributo = atributo.getName();
				if (!restricoesAtributos.contains(nomeAtributo)) {
					Object valorAtributo = getAtributo(objAtributosACopiar,
							nomeAtributo, atributo.getType());

					setAtributo(objetoOriginal, getMetodoSetDoAtributo(
							nomeAtributo, declaredMetodos), valorAtributo);
					// atributos[i].getClass().cast
				}
			}
		}
		return objetoOriginal;
	}

	/**
	 * Function that returns a method contained in an array of methods 
	 * whose name is "set" + attributeName
	 * 
	 * @param nomeAtributo The attribute name desired.<br>
	 * @param declaredMetodos The methods declarated.<br>
	 * @return The method desired.<br>
	 */
	public static Method getMetodoSetDoAtributo(String nomeAtributo,
			ArrayList<Method> declaredMetodos) {
		String nomeMetodo = corrigeStringAtributo(nomeAtributo);
		for (Method m : declaredMetodos) {
			if (m.getName().endsWith("set" + nomeMetodo)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Returns an attribute of the object passed, provided that it 
	 * implements the method according to the convention to get the 
	 * desired attribute.<br>
	 * 
	 * @param objeto The object under which it invoked the get.<br>
	 * @param atributo The desired attribute of the object.<br>            
	 * @return The value of the specified attribute of the object.<br>
	 * @throws NonexistantAttributeException If the specified 
	 * attribute does not exist.<br>
	 * 
	 */
	public static Object getAtributo(Object objeto, String atributo,
			boolean isBoolean) throws NonexistantAttributeException {
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
			throw new NonexistantAttributeException(MsgErrors.ATRIBUTO_INVALIDO
					.msg(atributo));
		}
	}

	/**
	 * Function that returns a attribute, receives an object, a string and a type.<br>
	 * @param objeto The object passed as parameter.<br>
	 * @param atributo The attribute passed as parameter.<br>
	 * @param type The type passed as parameter.<br>
	 * @return The attribute.<br>
	 * @throws NonexistantAttributeException If the attribute does not exist.<br>
	 */
	public static Object getAtributo(Object objeto, String atributo,
			Class<?> type) throws NonexistantAttributeException {
		if (type.getName().equalsIgnoreCase("boolean")) {
			return getAtributo(objeto, atributo, true);
		} else {
			return getAtributo(objeto, atributo, false);
		}
	}

	/**
	 * Makes the first letter of a word in upper case.<br>
	 * 
	 * @param atributo The string should be modified.<br>
	 *            
	 * @return A new String equivalent to the first, but 
	 * with the first letter miuscula.<br>
	 */
	public static String corrigeStringAtributo(String atributo) {
		String primeiraLetra = String.valueOf(atributo.charAt(0));
		return atributo
				.replaceFirst(primeiraLetra, primeiraLetra.toUpperCase());
	}

	/**
	 * Procedure used to set an attributte, receives an object, 
	 * a method and a value as parameter.<br>
	 * @param objeto The object passed as parameter.<br>
	 * @param metodo The method passed as parameter.<br>
	 * @param value The value passed as parameter.<br>
	 * @throws EasyCorrectionException If it is not possible 
	 * to set the requested attribute.<br>
	 */
	public static void setAtributo(Object objeto, Method metodo, Object value)
			throws EasyCorrectionException {
		try {
			metodo.invoke(objeto, value);
		} catch (InvocationTargetException e) {
			throw new EasyCorrectionException(e.getCause().getMessage());
		} catch (Exception e) {
			throw new NonexistantAttributeException(MsgErrors.ATRIBUTO_INVALIDO
					.msg(metodo.getName()));
		}
	}

	/**
	 * Changes an attribute of the object passed, provided that it implements 
	 * the set method according to the convention for the desired attribute.<br>
	 * 
	 * @param objeto The object under which it will invoke the get.<br>
	 * @param atributo The attribute you wish to alter the object passed.<br>
	 * @param value The new value of specified attribute.<br>
	 * @throws EasyCorrectionException If it is not possible to set the requested attribute.<br>
	 */
	@SuppressWarnings("unchecked")
	public static void setAtributo(Object objeto, String atributo,
			Object value, Class classeValorObjeto)
			throws EasyCorrectionException {
		Method m;
		try {
			m = objeto.getClass().getDeclaredMethod(
					"set" + corrigeStringAtributo(atributo), classeValorObjeto);
			// Class.forName(value.getClass().getName()));
			m.invoke(objeto, value);
		} catch (InvocationTargetException e) {
			throw new EasyCorrectionException(e.getCause().getMessage());
		} catch (Exception e) {
			throw new NonexistantAttributeException(MsgErrors.ATRIBUTO_INVALIDO
					.msg(atributo));
		}
	}
	
}
