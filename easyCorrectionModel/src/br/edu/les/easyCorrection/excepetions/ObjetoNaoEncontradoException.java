package br.edu.les.easyCorrection.excepetions;

public class ObjetoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ObjetoNaoEncontradoException(){
	}
	
	
	public ObjetoNaoEncontradoException(String msg){
		super(msg);
	}
}
