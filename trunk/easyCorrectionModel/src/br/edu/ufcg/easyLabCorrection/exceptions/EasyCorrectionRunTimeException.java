package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred overdue to problems at runtime in system Easy Lab Correction. <br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Mestre. <br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class EasyCorrectionRunTimeException extends RuntimeException {

	/**
	 * Variable used to classes serializable.<br>
	 * @serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of class, creates a new object EasyCorrectionRunTimeException.<br>
	 */
	public EasyCorrectionRunTimeException() {
		super();
	}

	/**
	 * Constructor for class that receives two arguments as parameter, the message that informs error occurred and the cause that informs the motive of error.<br>
	 * @param message - the string that informs the occurrence of a error.<br>
	 * @param cause - the cause of error occurred.<br>
	 */
	public EasyCorrectionRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for class that receives one argument as parameter, the message that informs error occurred.<br>
	 * @param message - the string that informs the occurrence of a error.<br>
	 */
	public EasyCorrectionRunTimeException(String message) {
		super(message);
	}

	/**
	 * Constructor for class that receives one argument as parameter, the cause that informs the motive of error.<br>
	 * @param cause - the cause of error occurred.<br>
	 */
	public EasyCorrectionRunTimeException(Throwable cause) {
		super(cause);
	}

}
