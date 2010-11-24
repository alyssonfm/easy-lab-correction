package br.edu.les.easyCorrection.sistema;

import br.edu.les.easyCorrection.pojo.roteiros.Submissao;

public class Teste {
	
	public static void main(String[] args) throws Throwable {
		Facade facade = new Facade();
		//facade.reinicializaBancoDeDados();
		//System.out.println(facade.getRoteirosLiberados());
		
		/*Roteiro r = facade.getRoteiro(1);
		r.setNome("Roteiro");
		facade.editarRoteiro(r);*/
		
		Submissao sub = facade.getSubmissao(51);
		facade.excluirSubmissao(sub);
	}
	
	

}

