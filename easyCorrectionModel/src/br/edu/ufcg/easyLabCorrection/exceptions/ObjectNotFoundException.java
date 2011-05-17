package br.edu.ufcg.easyLabCorrection.exceptions;

/**
 * Class responsible for exceptions occurred for objects not found in the system Easy Lab Correction. <br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor of class, creates a new object ObjectNotFoundException.<br>
	 */
	public ObjectNotFoundException(){
	}
	
	/**
	 * Constructor of class that receives one argument as parameter, the message corresponding at the error occurred.<br>
	 * @param msg - the string that corresponds at the error occurred.<br>
	 */
	public ObjectNotFoundException(String msg){
		super(msg);
	}
}
