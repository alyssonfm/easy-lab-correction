package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions of user authentication on system Easy Lab Correction. 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.
 * @version 1.0 14 of May of 2011.
 */
public class AuthenticationException extends RuntimeException {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of Class.
	 */
	public AuthenticationException() {
		super();
	}
	/**
	 * Constructor that receives a message as parameter, a string - the message of the error occurred.
	 * @param msg - the string of the error occurred.
	 */
	public AuthenticationException(String msg) {
		super(msg);
	}

	
}
