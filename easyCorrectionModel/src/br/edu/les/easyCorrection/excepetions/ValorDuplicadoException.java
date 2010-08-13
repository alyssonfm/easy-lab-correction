package br.edu.les.easyCorrection.excepetions;

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
