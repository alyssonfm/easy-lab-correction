package br.edu.les.easyCorrection.tests.system;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.les.easyCorrection.sistema.Facade;
/**
 * This test class is going to test the AccessManager
 * @author AUGUSTO
 *
 */
public class GuiManagerTest {
	
	private Facade facade; 
	
	public GuiManagerTest() {
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
	
	@Test
	public void menuTest(){
		
		
		
		
	}
	
}
