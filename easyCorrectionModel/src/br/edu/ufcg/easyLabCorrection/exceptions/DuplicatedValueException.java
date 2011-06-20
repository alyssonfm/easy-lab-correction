package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred for values duplicated in the system Easy Lab Correction. <br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class DuplicatedValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor of class, creates a new object DuplicateValueException.<br>
	 */
	public DuplicatedValueException(){
	}
	
	/**
	 * Constructor of class that receives a argument as parameter, a message that corresponding at the error occurred.<br>
	 * @param msg - the string that informs the error occurred.<br>
	 */
	public DuplicatedValueException(String msg){
		super(msg);
	}
}
