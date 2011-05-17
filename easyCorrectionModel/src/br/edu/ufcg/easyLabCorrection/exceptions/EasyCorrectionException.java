package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for treat all exceptions of system Easy Lab Correction. The super class of the other classes of exception. <br> 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class EasyCorrectionException extends Exception {

	/**
	 * Variable used to classes serializable.<br>
	 * @serial
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
	 * @param message - the string corresponding the error occurred.<br>
	 * @param cause - the caused of the exception.<br>
	 */
	public EasyCorrectionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor that receives one argument as parameter, a string message - the message that informs what exception occurred.<br>
	 * @param message - the string corresponding the error occurred.<br>
	 */
	public EasyCorrectionException(String message) {
		super(message);
	}

	/**
	 * Constructor that receives one argument as parameter, a Throwable cause - the caused of the exception.<br>
	 * @param cause - the caused of the exception.<br>
	 */
	public EasyCorrectionException(Throwable cause) {
		super(cause);
	}

}
