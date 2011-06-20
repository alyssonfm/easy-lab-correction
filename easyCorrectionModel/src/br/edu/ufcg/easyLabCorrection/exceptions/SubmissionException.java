package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred during the achievement of a submission in the system Easy Lab Correction.<br> 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class SubmissionException  extends EasyCorrectionException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the class that receives a argument as parameter, a message that corresponding at the error occurred.<br>
	 * @param msg - the string corresponding at the error occurred.<br>
	 */
	public SubmissionException(String msg) {
		super(msg);
	}
}
