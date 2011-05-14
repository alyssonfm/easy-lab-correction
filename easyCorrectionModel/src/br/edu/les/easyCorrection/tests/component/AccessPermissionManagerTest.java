package br.edu.les.easyCorrection.tests.component;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.sistema.Facade;

/**
 * This test class is going to test the PermissionManager, that has the
 * following roles: Menu CRUD, Function CRUD, Group CRUD and Permission CRUD
 * 
 */
public class AccessPermissionManagerTest {

	private Facade facade;

	public AccessPermissionManagerTest() {
		facade = new Facade();
	}

	@BeforeClass
	public void restartDatabase() {
		facade.reinicializaBancoDeDados();
	}
	
	@Test
	public void menuTest() {
		
		GerenciadorAcesso access = new GerenciadorAcesso();
		
		/*
		 * RETRIEVE 
		 */
		
		Menu mNULL = access.getMenu(1); // null
		List<Menu> list1 = access.listarMenu(); // size = 0
		List<Menu> list2 = access.listarMenusOrdenados(); // size = 0
		
		Assert.assertNull(mNULL);
		Assert.assertEquals(list1.size(), 0);
		Assert.assertEquals(list2.size(), 0);
		
		/*
		 * CREATE 
		 */

		//Menu mNULL = null;
		Menu mERROR1 = new Menu(-1, "jkjkjkj", "Kjkjk");
		Menu mERROR2 = new Menu(0, "jkjkjkj", "Kjkjk");
		Menu m4 = new Menu(1, null, "Kjkjk");
		Menu m5 = new Menu(2, "", "Kjkjk");
		Menu m6 = new Menu(3, "kjlk", null);
		Menu m7 = new Menu(3, "kjlk", "");
		
		try {
			access.cadastrarMenu(mERROR1);
		} catch (EasyCorrectionException e2) {
			// WE ARE CHECKING IF THE EXCEPTION IS BEING RAISED ONLY, NOT THE MESSAGE IT SENDS 
		}
		try {
			access.cadastrarMenu(mERROR2);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m4);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m5);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m6);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m7);
		} catch (EasyCorrectionException e2) {
		}
		
		/*
		 * RETRIEVE 
		 */
		
		Menu m8 = access.getMenu(-1); // null
		Menu m9 = access.getMenu(0);  // null 
		List<Menu> list3 = access.listarMenu(); // not size = 0
		List<Menu> list4 = access.listarMenusOrdenados(); // not size = 0
		
		Assert.assertNull(m8);
		Assert.assertNull(m9);
		Assert.assertNotSame(list3.size(), 0);
		Assert.assertNotSame(list4.size(), 0);
		
		/*
		 * UPDATE
		 */
		
		Menu m10 = new Menu(1, null, "Kjkjk");
		Menu m11 = new Menu(2, "", "Kjkjk");
		Menu m12 = new Menu(3, "kjlk", null);
		Menu m13 = new Menu(3, "kjlk", "");
		
		try {
			access.cadastrarMenu(m10);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.cadastrarMenu(m11);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.cadastrarMenu(m12);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.cadastrarMenu(m13);
		} catch (EasyCorrectionException e1) {
		}
		/*
		 * DELETE
		 */
		
		try {
			access.excluirMenu(mNULL);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(mERROR1);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(mERROR2);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void functionTest() {

	}

	@Test
	public void groupTest() {

	}

	@Test
	public void permissionTest() {

	}
}