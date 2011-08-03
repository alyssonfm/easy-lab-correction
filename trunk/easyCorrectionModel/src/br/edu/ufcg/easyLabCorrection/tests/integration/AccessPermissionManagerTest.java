package br.edu.ufcg.easyLabCorrection.tests.integration;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.managers.accessPermission.AccessPermissionManager;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.MenuFunction;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;

/**
 * This test class is going to test the PermissionManager, that has the
 * following roles: Menu CRUD, Function CRUD, Group CRUD and Permission CRUD
 * 
 */
public class AccessPermissionManagerTest {

	private AccessPermissionManager access;

	private Menu mOK;
	private Group gOK;
	private MenuFunction fOK;
	private Permission pOK;

	public AccessPermissionManagerTest() {
		access = new AccessPermissionManager();

		mOK = new Menu(0, "OK", "MENUOK");
		fOK = new MenuFunction(0, mOK, "OK", "FunctionOK");
		gOK = new Group(0, "OK");
		pOK = new Permission(0, gOK, fOK);
	}

	// @Test
	public void menuCRUDBadParametersTest() {

		access.rebootDataBase();

		/*
		 * RETRIEVE
		 */

		Menu mNULL = null;
		List<Menu> list1 = access.listMenus(); // size = 0
		List<Menu> list2 = access.listOrderedMenus(); // size = 0

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
			access.saveMenu(mNULL);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
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
		try {
			access.getMenu(null);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getMenu(-1);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getMenu(0);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}

		List<Menu> list3 = access.listMenus(); // NOT EMPTY
		List<Menu> list4 = access.listOrderedMenus(); // NOT EMPTY

		Assert.assertSame(list3.size(), 1);
		Assert.assertSame(list4.size(), 1);

		/*
		 * UPDATE
		 */
		Integer idOK = list3.get(0).getMenuId();

		Menu m10 = new Menu(idOK, null, "MENUOK");
		Menu m11 = new Menu(idOK, "", "MENUOK");
		Menu m12 = new Menu(idOK, "OK", null);
		Menu m13 = new Menu(idOK, "OK", "");

		try {
			access.updateMenu(m10);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.updateMenu(m11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.updateMenu(m12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.updateMenu(m13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		/*
		 * DELETE
		 */
		Menu m3 = new Menu(null, "jkjkjkj", "Kjkjk");
		try {
			access.deleteMenu(mNULL);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteMenu(m3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.deleteMenu(mERROR1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		} catch (ObjectNotFoundException e) {
		}
	}

	@Test
	public void functionCRUDBadParametersTest() {

		access.rebootDataBase();

		MenuFunction fNULL1 = null; // NULL
		List<MenuFunction> list1 = access.listFunctions(); // EMPTY
		List<MenuFunction> list3 = access.consultFunctionsByMenu(1); // EMPTY

		Assert.assertSame(list1.size(), 0);
		Assert.assertEquals(list3.size(), 0);

		/*
		 * CREATE
		 */

		MenuFunction f1 = new MenuFunction(-1, mOK, "9090", "klkl");
		MenuFunction f2 = new MenuFunction(0, null, "9090", "klkl");
		MenuFunction f3 = new MenuFunction(0, mOK, null, "klkl");
		MenuFunction f4 = new MenuFunction(0, mOK, "", "klkl");
		MenuFunction f5 = new MenuFunction(0, mOK, "90909", null);
		MenuFunction f6 = new MenuFunction(0, mOK, "90909", "");

		try {
			access.saveFunction(fNULL1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e7) {
		}
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

		try {
			access.getFunction(null);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getFunction(-1);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getFunction(0);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}

		List<MenuFunction> list4 = access.listFunctions(); // NOT EMPTY
		List<MenuFunction> list5 = access.consultFunctionsByMenu(-1); // EMPTY
		List<MenuFunction> list6 = access.consultFunctionsByMenu(2); // EMPTY

		Assert.assertNotSame(list4.size(), 0);
		Assert.assertEquals(list5.size(), 0);
		Assert.assertEquals(list6.size(), 0);

		/*
		 * UPDATE
		 */

		Integer idOK = list4.get(0).getFunctionId();

		MenuFunction f11 = new MenuFunction(-1, mOK, "9090", "klkl");
		MenuFunction f12 = new MenuFunction(idOK, null, "9090", "klkl");
		MenuFunction f13 = new MenuFunction(idOK, mOK, null, "klkl");
		MenuFunction f14 = new MenuFunction(idOK, mOK, "", "klkl");
		MenuFunction f15 = new MenuFunction(idOK, mOK, "90909", null);
		MenuFunction f16 = new MenuFunction(idOK, mOK, "90909", "");

		try {
			access.updateFunction(f11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.updateFunction(f12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.updateFunction(f13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.updateFunction(f14);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.updateFunction(f15);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.updateFunction(f16);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}

		/*
		 * DELETE
		 */

		try {
			access.deleteFunction(fNULL1);
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

	// @Test
	public void groupCRUDBadParametersTest() {

		access.rebootDataBase();

		Group gNULL1 = null;
		List<Group> lista1 = access.listGroups(); // EMPTY

		Assert.assertEquals(lista1.size(), 0);

		/*
		 * CREATE
		 */

		Group g1 = new Group(-1, "OK");
		Group g2 = new Group(0, null);
		Group g3 = new Group(0, "");

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
		try {
			access.getGroup(null);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getGroup(-1);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getGroup(0);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getGroupByName(null);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getGroupByName("");
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getGroupByName(null);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getGroupByName("");
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}

		List<Group> list2 = access.listGroups(); // NOT EMPTY

		Assert.assertSame(list2.size(), 1);

		/*
		 * UPDATE
		 */

		Integer idOK = list2.get(0).getGroupId();

		Group g0 = new Group(null, "OK");
		Group g4 = new Group(idOK, null);
		Group g5 = new Group(idOK, "");

		try {
			access.updateGroup(g0);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.updateGroup(g4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.updateGroup(g5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		/*
		 * DELETE
		 */

		try {
			access.deleteGroup(gNULL1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.deleteGroup(g0);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void permissionCRUDBadParametersTest() {

		List<Permission> list1 = access.consultPermissionsByGroup(1);// EMPTY

		Assert.assertEquals(list1.size(), 0);

		/*
		 * CREATE
		 */

		Permission p1 = new Permission(-1, gOK, fOK);
		Permission p2 = new Permission(0, null, fOK);
		Permission p3 = new Permission(0, gOK, null);
		Permission p4 = new Permission(0, new Group(-1, "OK"), fOK);
		Permission p5 = new Permission(0, new Group(0, null), fOK);
		Permission p6 = new Permission(0, new Group(0, ""), fOK);
		Permission p7 = new Permission(1, gOK, new MenuFunction(-1, mOK, "OK",
				"FunctionOK"));
		Permission p8 = new Permission(1, gOK, new MenuFunction(0, new Menu(-1,
				"OK", "MENUOK"), "OK", "FunctionOK"));
		Permission p9 = new Permission(1, gOK, new MenuFunction(0, new Menu(1,
				null, "MENUOK"), "OK", "FunctionOK"));
		Permission p10 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "", "MENUOK"), "OK", "FunctionOK"));
		Permission p11 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "OK", null), "OK", "FunctionOK"));
		Permission p12 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "OK", ""), "OK", "FunctionOK"));
		Permission p13 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "OK", ""), null, "FunctionOK"));
		Permission p14 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "OK", ""), "", "FunctionOK"));
		Permission p15 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "OK", ""), "OK", null));
		Permission p16 = new Permission(1, gOK, new MenuFunction(0, new Menu(
				-1, "OK", ""), "OK", ""));

		try {
			access.savePermissions(null);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

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

		try {
			access.getPermission(null);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getPermission(-1);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			access.getPermission(0);
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		
		List<Permission> list4 = access.consultPermissionsByGroup(-1); // EMPTY
		List<Permission> list5 = access.consultPermissionsByGroup(0); // EMPTY

		Assert.assertNotSame(list4.size(), 0);
		Assert.assertNotSame(list5.size(), 0);

		/*
		 * UPDATE
		 */

		try {
			access.updatePermissions(null);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p2);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p3);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p4);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p5);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p6);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p7);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p8);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p9);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p10);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p11);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p12);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p13);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p14);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p15);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(p16);

		try {
			access.updatePermissions(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		list.clear();
		list.add(pOK);

		try {
			access.updatePermissions(list);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

		/*
		 * DELETE - THERE IS NOT A REMOVE METHOD
		 */
	}
}