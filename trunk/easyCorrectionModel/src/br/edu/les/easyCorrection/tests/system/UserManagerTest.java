package br.edu.les.easyCorrection.tests.system;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.les.easyCorrection.sistema.Facade;

/**
 * This test class is going to test the UserManager, that has the following
 * roles: UserGroup CRUD, User CRUD and User Authentication
 * 
 */
public class UserManagerTest {

	private Facade facade;

	public UserManagerTest() {
		facade = new Facade();
	}

	@BeforeClass
	public void restartDatabase() {
		facade.reinicializaBancoDeDados();
	}

	@Test
	public void userGroupCRUDTest() {
		
	}

	@Test
	public void userCRUDTest() {
		
	}

	@Test
	public void userAuthenticationTest() {

	}

}
