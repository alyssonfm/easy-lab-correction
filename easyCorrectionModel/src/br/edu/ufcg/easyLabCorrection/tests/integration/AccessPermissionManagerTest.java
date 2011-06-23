package br.edu.ufcg.easyLabCorrection.tests.integration;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.AccessPermissionManager;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Function;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.system.Facade;

/**
 * This test class is going to test the PermissionManager, that has the
 * following roles: Menu CRUD, Function CRUD, Group CRUD and Permission CRUD
 * 
 */
public class AccessPermissionManagerTest {

	private Facade facade;
	private AccessPermissionManager access;
	
	private Menu mOK;
	private Group gOK;
	private Function fOK;
	private Permission pOK; 	

	public AccessPermissionManagerTest() {
		facade = new Facade();
		access = new AccessPermissionManager();
		
		mOK = new Menu(1, "OK", "MENUOK");
		fOK = new Function(1, mOK, "OK", "FunctionOK");
		gOK = new Group(1, "OK");
		pOK = new Permission(1, gOK, fOK);
	}

	@BeforeClass
	public void restartDatabase() {
		facade.rebootsDataBase();
	}
	
	@Test
	public void menuCRUDBadParametersTest() {
		
		/*
		 * RETRIEVE 
		 */
		
		Menu mNULL = access.getMenu(1); // null
		List<Menu> list1 = access.listMenus(); // size = 0
		List<Menu> list2 = access.listOrderedMenus(); // size = 0
		
		Assert.assertNull(mNULL);
		Assert.assertEquals(list1.size(), 0);
		Assert.assertEquals(list2.size(), 0);
		
		/*
		 * CREATE 
		 */

		Menu mERROR1 = new Menu(-1, "jkjkjkj", "Kjkjk");
		Menu m4 = new Menu(1, null, "Kjkjk");
		Menu m5 = new Menu(1, "", "Kjkjk");
		Menu m6 = new Menu(1, "kjlk", null);
		Menu m7 = new Menu(1, "kjlk", "");
		
		
		try {
			access.saveMenu(mERROR1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m6);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveMenu(m7);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		
		// MENU OK
		try {
			access.saveMenu(mOK);
		} catch (EasyCorrectionException e2) {
			e2.printStackTrace();
			Assert.assertTrue(false);
		}
		
		
		/*
		 * RETRIEVE 
		 */
		
		Menu m8 = access.getMenu(-1); // null
		Menu m9 = access.getMenu(0);  // null 
		List<Menu> list3 = access.listMenus(); // NOT EMPTY
		List<Menu> list4 = access.listOrderedMenus(); // NOT EMPTY
		
		Assert.assertNull(m8);
		Assert.assertNull(m9);
		Assert.assertNotSame(list3.size(), 0);
		Assert.assertNotSame(list4.size(), 0);
		
		/*
		 * UPDATE
		 */
		
		Menu m10 = new Menu(1, null, "MENUOK");
		Menu m11 = new Menu(1, "", "MENUOK");
		Menu m12 = new Menu(1, "OK", null);
		Menu m13 = new Menu(1, "OK", "");
		
		try {
			access.saveMenu(m10);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveMenu(m11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveMenu(m12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.saveMenu(m13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		/*
		 * DELETE
		 */
		
		try {
			access.deleteMenu(mNULL);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(mERROR1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(m10);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(m11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(m12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(m13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void functionCRUDBadParametersTest() {
	
		Function fNULL1 = access.getFunction(1); // NULL
		List<Function> list1 = access.listFunctions(); // EMPTY
		List<Function> list3 = access.consultFunctionsByMenu(1); // EMPTY
		
		Assert.assertNull(fNULL1);
		Assert.assertNotSame(list1.size(), 0);
		Assert.assertEquals(list3.size(), 0);
		
		/*
		 * CREATE
		 */
		
		Function f1 = new Function(-1, mOK, "9090", "klkl");  
		Function f2 = new Function(1, null, "9090", "klkl");
		Function f3 = new Function(1, mOK, null, "klkl");
		Function f4 = new Function(1, mOK, "", "klkl");
		Function f5 = new Function(1, mOK, "90909", null);
		Function f6 = new Function(1, mOK, "90909", "");
		
		try {
			access.saveFunction(f1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e7) {
		}
		try {
			access.saveFunction(f2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.saveFunction(f3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.saveFunction(f4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.saveFunction(f5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.saveFunction(f6);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		
		
		try {
			access.saveFunction(fOK);
		} catch (EasyCorrectionException e1) {
			e1.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		Function fNULL2 = access.getFunction(-1); // NULL
		List<Function> list4 = access.listFunctions(); // NOT EMPTY
		List<Function> list5 = access.consultFunctionsByMenu(-1); // EMPTY
		List<Function> list6 = access.consultFunctionsByMenu(2); // EMPTY		

		Assert.assertNull(fNULL2);
		Assert.assertNotSame(list4.size(), 0);
		Assert.assertEquals(list5.size(), 0);
		Assert.assertEquals(list6.size(), 0);
		
		/*
		 * UPDATE 
		 */
		
		Function f11 = new Function(-1, mOK, "9090", "klkl");  
		Function f12 = new Function(1, null, "9090", "klkl");
		Function f13 = new Function(1, mOK, null, "klkl");
		Function f14 = new Function(1, mOK, "", "klkl");
		Function f15 = new Function(1, mOK, "90909", null);
		Function f16 = new Function(1, mOK, "90909", "");
		
		try {
			access.saveFunction(f11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.saveFunction(f12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.saveFunction(f13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.saveFunction(f14);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.saveFunction(f15);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.saveFunction(f16);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		
		/*
		 * DELETE
		 */
		
		try {
			access.deleteFunction(fNULL2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		try {
			access.deleteFunction(f11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f14);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f15);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteFunction(f16);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
	}

	@Test
	public void groupCRUDBadParametersTest() {

		Group gNULL1 = access.getGroup(1); // NULL
		Group gNULL2 = access.getGroupByName("Group");  // NULL
		Group gNULL3 = access.getGroupByName("Group"); // NULL
		List<Group> lista1 = access.listGroups(); // EMPTY
		
		Assert.assertNull(gNULL1);
		Assert.assertNull(gNULL2);
		Assert.assertNull(gNULL3);
		Assert.assertEquals(lista1.size(), 0); 	
		
		/*
		 * CREATE
		 */
		
		Group g1 = new Group(-1, "OK");
		Group g2 = new Group(1, null);
		Group g3 = new Group(1, "");

		try {
			access.saveGroup(g1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.saveGroup(g2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.saveGroup(g3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		try {
			access.saveGroup(gOK);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		Group gNULL4 = access.getGroup(-1); // NULL
		Group gNULL5 = access.getGroupByName(null);  // NULL
		Group gNULL6 = access.getGroupByName("");  // NULL
		Group gNULL7 = access.getGroupByName(null); // NULL
		Group gNULL8 = access.getGroupByName(""); // NULL
		List<Group> lista2 = access.listGroups(); // NOT EMPTY
		
		Assert.assertNull(gNULL4);
		Assert.assertNull(gNULL5);
		Assert.assertNull(gNULL6);
		Assert.assertNull(gNULL7);
		Assert.assertNull(gNULL8);
		Assert.assertNotSame(lista2.size(), 0);
		
		/*
		 * UPDATE
		 */
		
		Group g4 = new Group(1, null);
		Group g5 = new Group(1, "");
		
		try {
			access.saveGroup(g4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.saveGroup(g5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		/*
		 * DELETE
		 */
		
		try {
			access.deleteGroup(gNULL2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteGroup(g4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteGroup(g5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void permissionCRUDBadParametersTest() {

		Permission pNULL = access.getPermission(1); // NULL
		List<Permission> list1 = access.consultPermissionsByGroup(1);// EMPTY
		
		Assert.assertNull(pNULL);
		Assert.assertEquals(list1.size(), 0);
		
		/*
		 * CREATE
		 */
		
		Permission p1 = new Permission(-1, gOK, fOK);
		Permission p2 = new Permission(1, null, fOK);
		Permission p3 = new Permission(1, gOK, null);
		Permission p4 = new Permission(1, new Group(-1, "OK"), fOK);
		Permission p5 = new Permission(1, new Group(1, null), fOK);
		Permission p6 = new Permission(1, new Group(1, ""), fOK);
		Permission p7 = new Permission(1, gOK, new Function(-1, mOK, "OK", "FunctionOK"));
		Permission p8 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", "MENUOK"), "OK", "FunctionOK"));
		Permission p9 = new Permission(1, gOK, new Function(1, new Menu(1, null, "MENUOK"), "OK", "FunctionOK"));
		Permission p10 = new Permission(1, gOK, new Function(1, new Menu(-1, "", "MENUOK"), "OK", "FunctionOK"));
		Permission p11 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", null), "OK", "FunctionOK"));
		Permission p12 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", ""), "OK", "FunctionOK"));
		Permission p13 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", ""), null, "FunctionOK"));
		Permission p14 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", ""), "", "FunctionOK"));
		Permission p15 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", ""), "OK", null));
		Permission p16 = new Permission(1, gOK, new Function(1, new Menu(-1, "OK", ""), "OK", ""));
		
		ArrayList<Permission> list = new ArrayList<Permission>();
		list.add(p1);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		list.clear();
		list.add(p2);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p3);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p4);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p5);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p6);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p7);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p8);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p9);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p10);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p11);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p12);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p13);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p14);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p15);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p16);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		
		list.clear();
		list.add(pOK);
		
		try {
			access.savePermissions(list);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		Permission pNULL2 = access.getPermission(-1); // NULL
		Permission pNULL3 = access.getPermission(0); // NULL
		List<Permission> list4 = access.consultPermissionsByGroup(-1); // EMPTY
		List<Permission> list5 = access.consultPermissionsByGroup(0); // EMPTY
		
		Assert.assertNull(pNULL2);
		Assert.assertNull(pNULL3);
		Assert.assertNotSame(list4.size(), 0);
		Assert.assertNotSame(list5.size(), 0);
		
		/*
		 * UPDATE
		 */
				
		list.clear();
		list.add(p2);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p3);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p4);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p5);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p6);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p7);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p8);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p9);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p10);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p11);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p12);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p13);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p14);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p15);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p16);
		
		try {
			access.savePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		
		list.clear();
		list.add(pOK);
		
		try {
			access.savePermissions(list);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * DELETE - THERE IS NOT A REMOVE METHOD
		 */
	}
}