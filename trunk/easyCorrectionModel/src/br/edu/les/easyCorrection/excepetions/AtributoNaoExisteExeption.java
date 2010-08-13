package br.edu.les.easyCorrection.excepetions;

public class AtributoNaoExisteExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AtributoNaoExisteExeption() {
		super();
	}

	public AtributoNaoExisteExeption(String msg) {
		super(msg);
	}

	
}
