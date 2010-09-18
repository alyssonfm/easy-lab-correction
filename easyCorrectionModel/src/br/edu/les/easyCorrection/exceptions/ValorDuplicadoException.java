package br.edu.les.easyCorrection.exceptions;

public class ValorDuplicadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ValorDuplicadoException(){
	}
	
	
	public ValorDuplicadoException(String msg){
		super(msg);
	}
}
