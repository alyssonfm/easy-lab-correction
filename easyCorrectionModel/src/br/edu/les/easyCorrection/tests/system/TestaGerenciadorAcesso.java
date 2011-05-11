package br.edu.les.easyCorrection.tests.system;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import br.edu.les.easyCorrection.sistema.Facade;


public class TestaGerenciadorAcesso {
	
	private Facade facade; 
	
	public TestaGerenciadorAcesso() {
		facade = new Facade();
	}
	
	@BeforeClass
	public void startClass(){
		facade.reinicializaBancoDeDados();
	}

	@Before
	public void setUpMethod(){
		
	}
	
	@After
	public void tearDownMethod(){
		
	}
	
	
	
}
