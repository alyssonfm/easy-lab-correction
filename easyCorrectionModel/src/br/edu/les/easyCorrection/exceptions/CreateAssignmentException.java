package br.edu.les.easyCorrection.exceptions;

/**
 * Class responsible for exceptions that occurred of creation of assignments in the system Easy Lab Correction. <br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class CreateAssignmentException extends EasyCorrectionException{
	
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the Class CreateAssignmentException that receives a message as parameter, a string - the message of the error occurred.<br>
	 * @param msg - the string of the error occurred.<br>
	 */
	public CreateAssignmentException(String msg){
		super(msg);
	}

}
