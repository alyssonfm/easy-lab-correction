package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions of attribute not existing.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class AttributeNotExistingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of Class.<br>
	 */
	public AttributeNotExistingException() {
		super();
	}
/**
 * Constructor that receives a message as parameter, a string - the message of the error occurred.<br>
 * @param msg - the string of the error occurred.<br>
 */
	public AttributeNotExistingException(String msg) {
		super(msg);
	}

	
}
