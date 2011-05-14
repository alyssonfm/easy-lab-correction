package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions of attribute not existing.
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.
 * @version 1.0 14 of May of 2011.
 */
public class AttributeNotExistingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of Class.
	 */
	public AttributeNotExistingException() {
		super();
	}
/**
 * Constructor that receives a message as parameter, a string - the message of the error occurred.
 * @param msg - the string of the error occurred.
 */
	public AttributeNotExistingException(String msg) {
		super(msg);
	}

	
}
