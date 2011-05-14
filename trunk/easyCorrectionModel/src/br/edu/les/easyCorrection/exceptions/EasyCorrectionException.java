package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for treat all exceptions of system Easy Lab Correction. The super class of the other classes of exception. <br> 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.
 * @version 1.0 14 of May of 2011.
 */
public class EasyCorrectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of the class.<br>
	 */
	public EasyCorrectionException() {
		super();
	}

	/**
	 * Constructor that receives two arguments as parameter, a string message - the message that informs what exception occurred and a Throwable cause - the caused of the exception.<br>
	 * @param message - the string corresponding the error occurred.
	 * @param cause - the caused of the exception.
	 */
	public EasyCorrectionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public EasyCorrectionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EasyCorrectionException(Throwable cause) {
		super(cause);
	}

}
