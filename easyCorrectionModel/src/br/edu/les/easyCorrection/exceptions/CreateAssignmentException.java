package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions that occurred of creation of assignments in the system Easy Lab Correction.
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.
 * @version 1.0 14 of May of 2011.
 */
public class CreateAssignmentException extends EasyCorrectionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the Class CreateAssignmentException that receives a message as parameter, a string - the message of the error occurred.
	 * @param msg - the string of the error occurred.
	 */
	public CreateAssignmentException(String msg){
		super(msg);
	}

}
