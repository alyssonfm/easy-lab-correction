package br.edu.ufcg.easyLabCorrection.tests.component;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.AccessPermissionManager;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Function;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.system.Facade;

/**
 * This test class is going to test the PermissionManager, that has the
 * following roles: Menu CRUD, Function CRUD, Group CRUD and Permission CRUD
 * 
 */
public class AccessPermissionManagerTest {

	private Facade facade;
	private AccessPermissionManager access;

	public AccessPermissionManagerTest() {
		facade = new Facade();
		access = new AccessPermissionManager();
	}

	@BeforeClass
	public void restartDatabase() {
		facade.reinicializaBancoDeDados();
	}
	
	@Test
	public void menuTest() {
		
		/*
		 * RETRIEVE 
		 */
		
		Menu mNULL = access.getMenu(1); // null
		List<Menu> list1 = access.listMenus(); // size = 0
		List<Menu> list2 = access.listOrderMenus(); // size = 0
		
		Assert.assertNull(mNULL);
		Assert.assertEquals(list1.size(), 0);
		Assert.assertEquals(list2.size(), 0);
		
		/*
		 * CREATE 
		 */

		//Menu mNULL = null;
		Menu mERROR1 = new Menu(-1, "jkjkjkj", "Kjkjk");
		Menu m4 = new Menu(1, null, "Kjkjk");
		Menu m5 = new Menu(1, "", "Kjkjk");
		Menu m6 = new Menu(1, "kjlk", null);
		Menu m7 = new Menu(1, "kjlk", "");
		
		
		try {
			access.saveMenu(mERROR1);
		} catch (EasyCorrectionException e2) {
			// WE ARE CHECKING IF THE EXCEPTION IS BEING RAISED ONLY, NOT THE MESSAGE IT SENDS 
		}
		try {
			access.saveMenu(m4);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m5);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m6);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m7);
		} catch (EasyCorrectionException e2) {
		}
		
		// MENU OK
		Menu mOK = new Menu(1, "OK", "MENUOK");
		try {
			access.saveMenu(mOK);
		} catch (EasyCorrectionException e2) {
			e2.printStackTrace();
		}
		
		
		/*
		 * RETRIEVE 
		 */
		
		Menu m8 = access.getMenu(-1); // null
		Menu m9 = access.getMenu(0);  // null 
		List<Menu> list3 = access.listMenus(); // not size = 0
		List<Menu> list4 = access.listOrderMenus(); // not size = 0
		
		Assert.assertNull(m8);
		Assert.assertNull(m9);
		Assert.assertNotSame(list3.size(), 0);
		Assert.assertNotSame(list4.size(), 0);
		
		/*
		 * UPDATE
		 */
		
		Menu m10 = new Menu(1, null, "MENUOK");
		Menu m11 = new Menu(2, "", "MENUOK");
		Menu m12 = new Menu(3, "OK", null);
		Menu m13 = new Menu(3, "OK", "");
		
		try {
			access.saveMenu(m10);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveMenu(m11);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveMenu(m12);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveMenu(m13);
		} catch (EasyCorrectionException e1) {
		}
		/*
		 * DELETE
		 */
		
		try {
			access.deleteMenu(mNULL);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(mERROR1);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void functionTest() {
	
		Function fNULL1 = access.getFunction(1); // NULL
		List<Function> list1 = access.listFunctions(); // EMPTY
		Function f0 = access.consultFunctionByNameAndLabel("90909", "909090"); // NULL
		List<Function> list3 = access.consultFunctionByMenu(1); // EMPTY
		
		Assert.assertNull(fNULL1);
		Assert.assertNotSame(list1.size(), 0);
		Assert.assertNull(f0);
		Assert.assertEquals(list3.size(), 0);
		
		/*
		 * CREATE
		 */

		Menu menuOK = new Menu(1, "OK", "MENUOK");
		
		Function f1 = new Function(-1, menuOK, "9090", "klkl");  
		Function f2 = new Function(1, null, "9090", "klkl");
		Function f3 = new Function(1, menuOK, null, "klkl");
		Function f4 = new Function(1, menuOK, "", "klkl");
		Function f5 = new Function(1, menuOK, "90909", null);
		Function f6 = new Function(1, menuOK, "90909", "");
		
		try {
			access.saveFunction(f1);
		} catch (EasyCorrectionException e7) {
		}
		try {
			access.saveFunction(f2);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.saveFunction(f3);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.saveFunction(f4);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.saveFunction(f5);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.saveFunction(f6);
		} catch (EasyCorrectionException e2) {
		}
		
		Function fOK = new Function(1, menuOK, "OK", "FUNCAOOK");
		
		try {
			access.saveFunction(fOK);
		} catch (EasyCorrectionException e1) {
			e1.printStackTrace();
		}
		
		/*
		 * RETRIEVE
		 */
		
		Function fNULL2 = access.getFunction(-1); // NULL
		List<Function> list4 = access.listFunctions(); // NOT EMPTY
		List<Function> list5 = access.consultFunctionByMenu(-1); // EMPTY
		List<Function> list6 = access.consultFunctionByMenu(2); // EMPTY
		Function f7 = access.consultFunctionByNameAndLabel(null, "FUNCAOOK"); // NULL
		Function f8 = access.consultFunctionByNameAndLabel("", "FUNCAOOK"); // NULL
		Function f9 = access.consultFunctionByNameAndLabel("OK", null); // NULL
		Function f10 = access.consultFunctionByNameAndLabel("OK", ""); // NULL
		

		Assert.assertNull(fNULL2);
		Assert.assertNotSame(list4.size(), 0);
		Assert.assertEquals(list5.size(), 0);
		Assert.assertEquals(list6.size(), 0);
		Assert.assertNull(f7);
		Assert.assertNull(f8);
		Assert.assertNull(f9);
		Assert.assertNull(f10);
		
		/*
		 * UPDATE 
		 */
		
		Function f11 = new Function(-1, menuOK, "9090", "klkl");  
		Function f12 = new Function(1, null, "9090", "klkl");
		Function f13 = new Function(1, menuOK, null, "klkl");
		Function f14 = new Function(1, menuOK, "", "klkl");
		Function f15 = new Function(1, menuOK, "90909", null);
		Function f16 = new Function(1, menuOK, "90909", "");
		
		/*
		 * DELETE
		 */
		
		try {
			access.deleteFunction(f11);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f12);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f13);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f14);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f15);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f16);
		} catch (EasyCorrectionException e) {
		}
		
	}

	@Test
	public void groupTest() {

	}

	@Test
	public void permissionTest() {

	}
}