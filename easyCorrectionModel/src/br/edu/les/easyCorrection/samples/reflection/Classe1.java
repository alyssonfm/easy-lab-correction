package br.edu.les.easyCorrection.samples.reflection;

public class Classe1 {

	private int funcao1( Object p, int x ) throws NullPointerException {
		if (p == null)
			throw new NullPointerException();
		return x;
	}
	
	public int umMetodo(int a, int b) {  
		return a + b;  
	}
}
