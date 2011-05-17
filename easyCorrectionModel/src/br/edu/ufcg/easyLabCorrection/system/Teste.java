package br.edu.ufcg.easyLabCorrection.system;

import br.edu.ufcg.easyLabCorrection.util.ELCScheduler;

public class Teste {
	
	public static void main(String[] args) throws Throwable {
		//Facade facade = new Facade();
		//facade.reinicializaBancoDeDados();
		//System.out.println(facade.getRoteirosLiberados());
		
		new ELCScheduler(6, 23, 0);
		
		/*Roteiro r = facade.getRoteiro(1);
		r.setNome("Roteiro");
		facade.editarRoteiro(r);*/
		
		//Submissao sub = facade.getSubmissao(51);
		//facade.excluirSubmissao(sub);
		//easyCorrectionUtil.realizaDumpBD();
	}
	
	

}

