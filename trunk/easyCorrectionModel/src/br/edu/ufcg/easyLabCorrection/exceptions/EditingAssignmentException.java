package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred in the editing of assignments.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class EditingAssignmentException extends EasyCorrectionException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the class that receives as parameter a message, the string corresponding at error occurred.<br>
	 * @param msg - the string that informs the error.<br>
	 */
	public EditingAssignmentException(String msg) {
		super(msg);
	}

}
