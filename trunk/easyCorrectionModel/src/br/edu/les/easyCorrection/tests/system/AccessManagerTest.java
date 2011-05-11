package br.edu.les.easyCorrection.tests.system;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.les.easyCorrection.sistema.Facade;

/**
 * This test class is going to test the AccessManager, that has the following
 * roles: Permission CRUD, UserGroup CRUD, User CRUD and User Authentication
 * 
 * @author AUGUSTO
 * 
 */
public class AccessManagerTest {

	private Facade facade;

	public AccessManagerTest() {
		facade = new Facade();
	}

	@BeforeClass
	public void startClass() {
		facade.reinicializaBancoDeDados();
	}

//	@Before
//	public void setUpMethod() {
//
//	}
//
//	@After
//	public void tearDownMethod() {
//
//	}

	@Test
	public void permissionCRUDTest() {

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
