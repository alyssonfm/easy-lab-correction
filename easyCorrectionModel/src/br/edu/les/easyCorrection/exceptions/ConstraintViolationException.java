package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions occurred for violation of constraints in the system Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
@SuppressWarnings("serial")
public class ConstraintViolationException extends RuntimeException {

	/**
	 * Constructor of the class that receives one argument as parameter, a message that corresponds at the error occurred.<br>
	 * @param msg - the string corresponding at the message of error occurred.<br>
	 */
	public ConstraintViolationException(String msg) {
		super(msg);
	}
	
	/**
	 * Default constructor of the class, creates a new object ConstraintViolationException.<br>
	 */
	public ConstraintViolationException(){
		
	}

}
