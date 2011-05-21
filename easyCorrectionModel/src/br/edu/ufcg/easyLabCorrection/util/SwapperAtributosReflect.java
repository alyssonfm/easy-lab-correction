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

public class SwapperAtributosReflect {

	/**
	 * Copia os atributos de um objeto (objAtributosACopiar) para um outro
	 * (objetoOriginal), exceto o Id do Objeto que � formado por
	 * idNomedaClasse e o serialVersionUID . A copia dos atributos � realizada
	 * tamb�m com os atributos da superclasse
	 * 
	 * @param objetoOriginal
	 * @param objAtributosACopiar
	 * @param classe
	 * @return
	 * @throws EasyCorrectionException
	 */
	@SuppressWarnings("unchecked")
	public static Object swapObject(Object objetoOriginal,
			Object objAtributosACopiar, Class classe)
			throws EasyCorrectionException {
		return swapObject(objetoOriginal, objAtributosACopiar, true, classe);
	}

	/**
	 * Permite tb Verifica ou desconsiderar os metodos da classe os metodos da
	 * classe pai
	 * 
	 * @param objetoOriginal
	 * @param objAtributosACopiar
	 * @param classePai
	 * @param classe
	 * @return
	 * @throws EasyCorrectionException
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
	 * retorna um metodo contido em um array de metodos cujo nome �
	 * "set"+nomeAtributo
	 * 
	 * @param nomeAtributo
	 * @param declaredMetodos
	 * @return
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
	 * Retorna um atributo do objeto passado, desde de que ele implemente o
	 * m�todo get segundo a conven��o para o atributo desejado.
	 * 
	 * @param objeto
	 *            O objeto sob o qual se invocar� o get.
	 * @param atributo
	 *            O atributo desejado do objeto.
	 * @return O valor do atributo especificado do objeto.
	 * @throws NonexistantAttributeException
	 *             Caso o atributo especificado n�o exista.
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
			throw new NonexistantAttributeException(MsgErros.ATRIBUTO_INVALIDO
					.msg(atributo));
		}
	}

	public static Object getAtributo(Object objeto, String atributo,
			Class<?> type) throws NonexistantAttributeException {
		if (type.getName().equalsIgnoreCase("boolean")) {
			return getAtributo(objeto, atributo, true);
		} else {
			return getAtributo(objeto, atributo, false);
		}
	}

	/**
	 * Torna a primeira letra de uma palavra em mai�scula.
	 * 
	 * @param atributo
	 *            A string que deve ser modificada.
	 * @return Uma nova String equivalente a primeira, mas com a primeira letra
	 *         mi�scula.
	 */
	public static String corrigeStringAtributo(String atributo) {
		String primeiraLetra = String.valueOf(atributo.charAt(0));
		return atributo
				.replaceFirst(primeiraLetra, primeiraLetra.toUpperCase());
	}

	public static void setAtributo(Object objeto, Method metodo, Object value)
			throws EasyCorrectionException {
		try {
			metodo.invoke(objeto, value);
		} catch (InvocationTargetException e) {
			throw new EasyCorrectionException(e.getCause().getMessage());
		} catch (Exception e) {
			throw new NonexistantAttributeException(MsgErros.ATRIBUTO_INVALIDO
					.msg(metodo.getName()));
		}
	}

	/**
	 * Altera um atributo do objeto passado, desde de que ele implemente o
	 * m�todo set segundo a conven��o para o atributo desejado.
	 * 
	 * @param objeto
	 *            O objeto sob o qual se invocar� o get.
	 * @param atributo
	 *            O atributo que se deseja alterar do objeto passado.
	 * @param value
	 *            O novo valor do Atributo especificado.
	 * @throws JatoException
	 *             Caso n�o seja poss�vel setar o atributo pedido.
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
			throw new NonexistantAttributeException(MsgErros.ATRIBUTO_INVALIDO
					.msg(atributo));
		}
	}
	/*
	 * public static void main(String[] args) { try { //swapObject(new
	 * Professor(), new Professor(), Professor.class); //swapObject(new
	 * Bolsista(), new Bolsista(), Bolsista.class); swapObject(new
	 * ComiteExterno(), new ComiteExterno(), ComiteExterno.class); } catch
	 * (EPibicException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}
