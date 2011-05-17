package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred during the execution of tests of the system Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class CompilationException extends EasyCorrectionException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of class that receives one argument as parameter, the message that corresponding at the error occurred.<br> 
	 * @param msg - the string corresponding at error occurred.<br> 
	 */
	public CompilationException(String msg) {
		super(msg);
	}
}
