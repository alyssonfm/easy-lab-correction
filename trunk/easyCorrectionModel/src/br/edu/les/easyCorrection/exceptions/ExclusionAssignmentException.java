package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions occurred during the editing of assignments.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class ExclusionAssignmentException extends EasyCorrectionException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of class, receives one argument as parameter - the message that informs the error occurred.<br>
	 * @param msg - the string that informs the error.<br>
	 */
	public ExclusionAssignmentException(String msg) {
		super(msg);
	}
}
