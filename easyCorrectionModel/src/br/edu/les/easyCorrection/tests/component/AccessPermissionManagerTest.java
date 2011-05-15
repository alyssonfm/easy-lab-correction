package br.edu.les.easyCorrection.tests.component;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.sistema.Facade;

/**
 * This test class is going to test the PermissionManager, that has the
 * following roles: Menu CRUD, Function CRUD, Group CRUD and Permission CRUD
 * 
 */
public class AccessPermissionManagerTest {

	private Facade facade;
	private GerenciadorAcesso access;
	
	private Menu mOK;
	private Grupo gOK;
	private Funcao fOK;
	private Permissao pOK; 	

	public AccessPermissionManagerTest() {
		facade = new Facade();
		access = new GerenciadorAcesso();
		
		mOK = new Menu(1, "OK", "MENUOK");
		fOK = new Funcao(1, mOK, "OK", "FUNCAOOK");
		gOK = new Grupo(1, "OK");
		pOK = new Permissao(1, gOK, fOK);
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
		List<Menu> list1 = access.listarMenu(); // size = 0
		List<Menu> list2 = access.listarMenusOrdenados(); // size = 0
		
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
			access.cadastrarMenu(mERROR1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m6);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarMenu(m7);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		
		// MENU OK
		try {
			access.cadastrarMenu(mOK);
		} catch (EasyCorrectionException e2) {
			e2.printStackTrace();
			Assert.assertTrue(false);
		}
		
		
		/*
		 * RETRIEVE 
		 */
		
		Menu m8 = access.getMenu(-1); // null
		Menu m9 = access.getMenu(0);  // null 
		List<Menu> list3 = access.listarMenu(); // NOT EMPTY
		List<Menu> list4 = access.listarMenusOrdenados(); // NOT EMPTY
		
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
			access.cadastrarMenu(m10);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.cadastrarMenu(m11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.cadastrarMenu(m12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		try {
			access.cadastrarMenu(m13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		/*
		 * DELETE
		 */
		
		try {
			access.excluirMenu(mNULL);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(mERROR1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(m10);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(m11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(m12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirMenu(m13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void functionTest() {
	
		Funcao fNULL1 = access.getFuncao(1); // NULL
		List<Funcao> list1 = access.listarFuncao(); // EMPTY
		List<Funcao> list3 = access.consultarFuncaoPorMenu(1); // EMPTY
		
		Assert.assertNull(fNULL1);
		Assert.assertNotSame(list1.size(), 0);
		Assert.assertEquals(list3.size(), 0);
		
		/*
		 * CREATE
		 */
		
		Funcao f1 = new Funcao(-1, mOK, "9090", "klkl");  
		Funcao f2 = new Funcao(1, null, "9090", "klkl");
		Funcao f3 = new Funcao(1, mOK, null, "klkl");
		Funcao f4 = new Funcao(1, mOK, "", "klkl");
		Funcao f5 = new Funcao(1, mOK, "90909", null);
		Funcao f6 = new Funcao(1, mOK, "90909", "");
		
		try {
			access.cadastrarFuncao(f1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e7) {
		}
		try {
			access.cadastrarFuncao(f2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.cadastrarFuncao(f3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.cadastrarFuncao(f4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.cadastrarFuncao(f5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.cadastrarFuncao(f6);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		
		
		try {
			access.cadastrarFuncao(fOK);
		} catch (EasyCorrectionException e1) {
			e1.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		Funcao fNULL2 = access.getFuncao(-1); // NULL
		List<Funcao> list4 = access.listarFuncao(); // NOT EMPTY
		List<Funcao> list5 = access.consultarFuncaoPorMenu(-1); // EMPTY
		List<Funcao> list6 = access.consultarFuncaoPorMenu(2); // EMPTY		

		Assert.assertNull(fNULL2);
		Assert.assertNotSame(list4.size(), 0);
		Assert.assertEquals(list5.size(), 0);
		Assert.assertEquals(list6.size(), 0);
		
		/*
		 * UPDATE 
		 */
		
		Funcao f11 = new Funcao(-1, mOK, "9090", "klkl");  
		Funcao f12 = new Funcao(1, null, "9090", "klkl");
		Funcao f13 = new Funcao(1, mOK, null, "klkl");
		Funcao f14 = new Funcao(1, mOK, "", "klkl");
		Funcao f15 = new Funcao(1, mOK, "90909", null);
		Funcao f16 = new Funcao(1, mOK, "90909", "");
		
		try {
			access.cadastrarFuncao(f11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.cadastrarFuncao(f12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.cadastrarFuncao(f13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.cadastrarFuncao(f14);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.cadastrarFuncao(f15);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarFuncao(f16);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e1) {
		}
		
		/*
		 * DELETE
		 */
		
		try {
			access.excluirFuncao(fNULL2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		try {
			access.excluirFuncao(f11);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f12);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f13);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f14);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f15);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f16);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
	}

	@Test
	public void groupTest() {

		Grupo gNULL1 = access.getGrupo(1); // NULL
		Grupo gNULL2 = access.getGrupoPorNome("Grupo");  // NULL
		Grupo gNULL3 = access.getGrupoPorNome("Grupo"); // NULL
		List<Grupo> lista1 = access.listarGrupo(); // EMPTY
		
		Assert.assertNull(gNULL1);
		Assert.assertNull(gNULL2);
		Assert.assertNull(gNULL3);
		Assert.assertEquals(lista1.size(), 0); 	
		
		/*
		 * CREATE
		 */
		
		Grupo g1 = new Grupo(-1, "OK");
		Grupo g2 = new Grupo(1, null);
		Grupo g3 = new Grupo(1, "");

		try {
			access.cadastrarGrupo(g1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.cadastrarGrupo(g2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.cadastrarGrupo(g3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		try {
			access.cadastrarGrupo(gOK);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		Grupo gNULL4 = access.getGrupo(-1); // NULL
		Grupo gNULL5 = access.getGrupoPorNome(null);  // NULL
		Grupo gNULL6 = access.getGrupoPorNome("");  // NULL
		Grupo gNULL7 = access.getGrupoPorNome(null); // NULL
		Grupo gNULL8 = access.getGrupoPorNome(""); // NULL
		List<Grupo> lista2 = access.listarGrupo(); // NOT EMPTY
		
		Assert.assertNull(gNULL4);
		Assert.assertNull(gNULL5);
		Assert.assertNull(gNULL6);
		Assert.assertNull(gNULL7);
		Assert.assertNull(gNULL8);
		Assert.assertNotSame(lista2.size(), 0);
		
		/*
		 * UPDATE
		 */
		
		Grupo g4 = new Grupo(1, null);
		Grupo g5 = new Grupo(1, "");
		
		try {
			access.cadastrarGrupo(g4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.cadastrarGrupo(g5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		/*
		 * DELETE
		 */
		
		try {
			access.excluirGrupo(gNULL2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirGrupo(g4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirGrupo(g5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void permissionTest() {

		
		
		Permissao pNULL = access.getPermissao(1); // NULL
		List<Permissao> list1 = access.consultarPermissoesPorGrupo(1);// EMPTY
		
		Assert.assertNull(pNULL);
		Assert.assertEquals(list1.size(), 0);
		
		/*
		 * CREATE
		 */
		
		Permissao p1 = new Permissao(-1, gOK, fOK);
		Permissao p2 = new Permissao(1, null, fOK);
		Permissao p3 = new Permissao(1, gOK, null);
		Permissao p4 = new Permissao(1, new Grupo(-1, "OK"), fOK);
		Permissao p5 = new Permissao(1, new Grupo(1, null), fOK);
		Permissao p6 = new Permissao(1, new Grupo(1, ""), fOK);
		Permissao p7 = new Permissao(1, gOK, new Funcao(-1, mOK, "OK", "FUNCAOOK"));
		Permissao p8 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", "MENUOK"), "OK", "FUNCAOOK"));
		Permissao p9 = new Permissao(1, gOK, new Funcao(1, new Menu(1, null, "MENUOK"), "OK", "FUNCAOOK"));
		Permissao p10 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "", "MENUOK"), "OK", "FUNCAOOK"));
		Permissao p11 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", null), "OK", "FUNCAOOK"));
		Permissao p12 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", ""), "OK", "FUNCAOOK"));
		Permissao p13 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", ""), null, "FUNCAOOK"));
		Permissao p14 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", ""), "", "FUNCAOOK"));
		Permissao p15 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", ""), "OK", null));
		Permissao p16 = new Permissao(1, gOK, new Funcao(1, new Menu(-1, "OK", ""), "OK", ""));
		
		ArrayList<Permissao> list = new ArrayList<Permissao>();
		list.add(p1);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		list.clear();
		list.add(p2);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p3);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p4);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p5);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p6);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p7);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p8);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p9);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p10);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p11);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p12);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p13);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p14);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p15);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p16);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		
		list.clear();
		list.add(pOK);
		
		try {
			access.cadastrarPermissao(list);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		Permissao pNULL2 = access.getPermissao(-1); // NULL
		Permissao pNULL3 = access.getPermissao(0); // NULL
		List<Permissao> list4 = access.consultarPermissoesPorGrupo(-1); // EMPTY
		List<Permissao> list5 = access.consultarPermissoesPorGrupo(0); // EMPTY
		
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
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p3);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p4);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p5);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p6);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p7);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p8);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p9);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p10);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p11);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p12);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p13);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p14);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p15);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		list.clear();
		list.add(p16);
		
		try {
			access.cadastrarPermissao(list);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		
		list.clear();
		list.add(pOK);
		
		try {
			access.cadastrarPermissao(list);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		/*
		 * DELETE - THERE IS NOT A REMOVE METHOD
		 */
	}
}