package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred during the release of assignments in the system Easy Lab Correction. <br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class ReleasesAssignmentException extends EasyCorrectionException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of class that receives a argument as parameter, the message that informs the error occurred.<br>
	 * @param msg - the string that informs the error occurred.<br>
	 */
	public ReleasesAssignmentException(String msg) {
		super(msg);
	}

}
