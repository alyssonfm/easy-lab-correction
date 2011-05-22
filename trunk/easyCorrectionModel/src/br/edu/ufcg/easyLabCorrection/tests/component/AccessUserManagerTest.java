package br.edu.ufcg.easyLabCorrection.tests.component;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.AccessUserManager;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.system.Facade;

/**
 * This test class is going to test the UserManager, that has the following
 * roles: UserGroup CRUD, User CRUD and User Authentication
 * 
 */
public class AccessUserManagerTest {

	private Facade facade;
	private AccessUserManager access;
	
	private User us;
	private UserGroup usgr;
	private Group gr;

	public AccessUserManagerTest() {
		facade = new Facade();
		access = new AccessUserManager();
		
		us = new User("userTestOk", "UserTest", "123123", "usertest@test.com");
		gr = new Group(1, "GroupTest");
		usgr = new UserGroup(1, gr, us);
	}
	
	@BeforeClass
	public void restartDatabase() {
		facade.reinicializaBancoDeDados();
	}

	@Test
	public void userGroupCRUDTest() {
		/*
		* CREATE
		*/
		UserGroup usgrError = new UserGroup(1, null, us);
		UserGroup usgr1 = new UserGroup(-1, gr, us);
		UserGroup usgr2 = new UserGroup(1, gr, null);
		
		try {
			access.saveUserGroup(usgrError);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}		
		try {
			access.saveUserGroup(usgr1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.saveUserGroup(usgr2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}	
		
		/*
		 * User Group OK.
		 */
		
		try {
			access.saveUserGroup(usgr);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE 
		 */		
		UserGroup usgr3 = access.getUserGroup(-1); // null
		UserGroup usgr4 = access.getUserGroup(0); // null
		UserGroup usgr5 = access.getUserGroup(1); // UserGroup OK.
		List<UserGroup> listusgr = access.listUserGroups(); // Not Empty
		List<UserGroup> listusgr2 = access.listUserGroupsByGroup("GroupTest"); // Not Empty
		
		Assert.assertNull(usgr3);
		Assert.assertNull(usgr4);
		Assert.assertNull(usgr5);
		Assert.assertNotSame(listusgr.size(), 0);
		Assert.assertNotSame(listusgr2.size(), 0);
		
		/*
		 * UPDATE
		 */
		UserGroup usgr6 = new UserGroup(-1, gr, us);
		UserGroup usgr7 = new UserGroup(0, gr, us);
		UserGroup usgr8 = new UserGroup(1, null, us);
		UserGroup usgr9 = new UserGroup(1, gr, null);
		
		try {
			access.saveUserGroup(usgr6);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.saveUserGroup(usgr7);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.saveUserGroup(usgr8);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveUserGroup(usgr9);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		// UserGroup OK
		try {
			access.saveUserGroup(usgr);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
				
		/*
		 * DELETE
		 */
		try {
			access.deleteUser(usgr6);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteUser(usgr7);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteUser(usgr8);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.deleteUser(usgr9);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		// UserGroup OK
		try {
			access.deleteUser(usgr);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void userCRUDTest() {
		/*
		 * CREATE
		 */		
		User usError = new User("", "ELCTest", "123123", "elctest@test,com");
		User us1 = new User("loginTest", "", "123123", "elctest@test1,com");
		User us2 = new User("lTest", "Test", "", "elctest@test1,com");
		User us3 = new User("loginTest", "Test", "123123", "");
		User us4 = new User(null, "ELCTest", "123123", "elctest@test1,com");
		User us5 = new User("ELCTest", null, "123123", "elctest@test1,com");
		User us6 = new User("ELCTest", "TestName", null, "elctest@test1,com");
		User us7 = new User("ELCTest", "TestName", "elctest", null);
		
		try {
			usgr.setUser(usError);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us1);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us2);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us3);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us4);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us5);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us6);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us7);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		/*
		 * User OK.
		 */
		try {
			usgr.setUser(us);
			access.saveUser(usgr);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */		
		User us8 = access.getUser(-1); //null
		User us9 = access.getUser(0); //null
		User us10 = access.getUser(1); // User OK
		User us11 = access.getUserByLogin(null); // null
		User us12 = access.getUserByLogin(""); //null
		User us13 = access.getUserByLogin("userTestOk"); // User OK
		List<User> list1 = access.listUsers(); // Not Empty
		
		Assert.assertNull(us8);
		Assert.assertNull(us9);
		Assert.assertNull(us11);
		Assert.assertNull(us12);
		Assert.assertNotNull(us10);
		Assert.assertNotNull(us13);
		Assert.assertNotSame(list1.size(), 0);
		
		/*
		 * UPDATE
		 */
		User us14 = new User(null, "test14", "123456", "test14@test.com");
		User us15 = new User("test15", null, "123456", "test15@test.com");
		User us16 = new User("test14", "123456", null, "test16@test.com");
		User us17 = new User("test14", "123456", "test14", null);
		User us18 = new User("", "test14", "123456", "test18@test.com");
		User us19 = new User("test14", "", "123456", "test19@test.com");
		User us20 = new User("test14", "123456", "", "test20@test.com");
		User us21 = new User("test14", "123456", "test21", "");
		
		try {
			usgr.setUser(us14);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us15);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us16);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us17);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us18);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us19);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us20);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us21);
			access.saveUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		/*
		 * User OK.
		 */
		try {
			usgr.setUser(us);
			access.saveUser(usgr);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * DELETE
		 */
		try {
			usgr.setUser(us14);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us15);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us16);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us17);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us18);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us19);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us20);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			usgr.setUser(us21);
			access.deleteUser(usgr);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		// User OK
		try {
			usgr.setUser(us);
			access.deleteUser(usgr);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void userAuthenticationTest() {
		
		// Changes on passwords.
		access.changePassword(us, null);
		Assert.assertTrue(false);
		
		access.changePassword(us, "12345"); // The passwords can not be less than six characters.
		Assert.assertTrue(false);
		
		access.changePassword(us, "123456");
		Assert.assertTrue(true);
		
		// Consultations
		User us22 = access.consultUserByLogin("userTestOk");
		Assert.assertTrue(true);
		
		Assert.assertEquals(us22.getName(), us.getName());
		Assert.assertEquals(us22.getLogin(), us.getLogin());
		Assert.assertEquals(us22.getEmail(), us.getEmail());
		
		access.consultUserByGroup(-1);
		Assert.assertTrue(false);
		
		access.consultUserByGroup(gr.getGroupId());
		Assert.assertTrue(true);
	}

}
