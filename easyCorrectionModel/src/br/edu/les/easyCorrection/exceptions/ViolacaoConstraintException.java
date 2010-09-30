package br.edu.les.easyCorrection.exceptions;

@SuppressWarnings("serial")
public class ViolacaoConstraintException extends RuntimeException {

	public ViolacaoConstraintException(String string) {
		super(string);
	}
	
	public ViolacaoConstraintException(){
		
	}

}
