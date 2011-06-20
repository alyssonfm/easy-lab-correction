package br.edu.ufcg.easyLabCorrection.system;

import java.lang.System;

public class Teste {
	
	public static void main(String[] args) throws Throwable {
		Facade facade = new Facade();
		//facade.reinicializaBancoDeDados();
		//System.out.println(facade.getRoteirosLiberados());
	
		facade.getUsuario(1);
		System.out.println(facade.getUsuario(1));
		
		//Submissao sub = facade.getSubmissao(51);
		//facade.excluirSubmissao(sub);
		//easyCorrectionUtil.realizaDumpBD();
	}
	
	

}

