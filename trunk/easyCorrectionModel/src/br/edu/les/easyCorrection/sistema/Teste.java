package br.edu.les.easyCorrection.sistema;

import java.util.List;

import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;

public class Teste {
	
	//TODO NAO APAGUE ESSE TESTE ELE É IMPORTANTE PARA TESTAR O MAPEAMENTO PRINCIPALMENTE QUANDO ELE VEM QUEBRADO DO SVN
	
	public static void main(String[] args) throws Throwable {
		Facade facade = new Facade();
		System.out.println(facade.getRoteirosLiberados());
		
		Roteiro r = facade.getRoteiro(1);
		r.setNome("Roteiro");
		facade.editarRoteiro(r);
	}
	
	

}

