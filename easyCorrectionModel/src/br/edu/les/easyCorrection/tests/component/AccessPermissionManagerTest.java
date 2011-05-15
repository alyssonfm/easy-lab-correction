package br.edu.les.easyCorrection.tests.component;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.sistema.Facade;

/**
 * This test class is going to test the PermissionManager, that has the
 * following roles: Menu CRUD, Function CRUD, Group CRUD and Permission CRUD
 * 
 */
public class AccessPermissionManagerTest {

	private Facade facade;
	private GerenciadorAcesso access;

	public AccessPermissionManagerTest() {
		facade = new Facade();
		access = new GerenciadorAcesso();
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

		//Menu mNULL = null;
		Menu mERROR1 = new Menu(-1, "jkjkjkj", "Kjkjk");
		Menu m4 = new Menu(1, null, "Kjkjk");
		Menu m5 = new Menu(1, "", "Kjkjk");
		Menu m6 = new Menu(1, "kjlk", null);
		Menu m7 = new Menu(1, "kjlk", "");
		
		
		try {
			access.cadastrarMenu(mERROR1);
		} catch (EasyCorrectionException e2) {
			// WE ARE CHECKING IF THE EXCEPTION IS BEING RAISED ONLY, NOT THE MESSAGE IT SENDS 
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
		
		// MENU OK
		Menu mOK = new Menu(1, "OK", "MENUOK");
		try {
			access.cadastrarMenu(mOK);
		} catch (EasyCorrectionException e2) {
			e2.printStackTrace();
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
		
		Menu m10 = new Menu(1, null, "MENUOK");
		Menu m11 = new Menu(2, "", "MENUOK");
		Menu m12 = new Menu(3, "OK", null);
		Menu m13 = new Menu(3, "OK", "");
		
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
	}

	@Test
	public void functionTest() {
	
		Funcao fNULL1 = access.getFuncao(1); // NULL
		List<Funcao> list1 = access.listarFuncao(); // EMPTY
		Funcao f0 = access.consultarFuncaoNomeERotulo("90909", "909090"); // NULL
		List<Funcao> list3 = access.consultarFuncaoPorMenu(1); // EMPTY
		
		Assert.assertNull(fNULL1);
		Assert.assertNotSame(list1.size(), 0);
		Assert.assertNull(f0);
		Assert.assertEquals(list3.size(), 0);
		
		/*
		 * CREATE
		 */

		Menu menuOK = new Menu(1, "OK", "MENUOK");
		
		Funcao f1 = new Funcao(-1, menuOK, "9090", "klkl");  
		Funcao f2 = new Funcao(1, null, "9090", "klkl");
		Funcao f3 = new Funcao(1, menuOK, null, "klkl");
		Funcao f4 = new Funcao(1, menuOK, "", "klkl");
		Funcao f5 = new Funcao(1, menuOK, "90909", null);
		Funcao f6 = new Funcao(1, menuOK, "90909", "");
		
		try {
			access.cadastrarFuncao(f1);
		} catch (EasyCorrectionException e7) {
		}
		try {
			access.cadastrarFuncao(f2);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.cadastrarFuncao(f3);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.cadastrarFuncao(f4);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.cadastrarFuncao(f5);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.cadastrarFuncao(f6);
		} catch (EasyCorrectionException e2) {
		}
		
		Funcao fOK = new Funcao(1, menuOK, "OK", "FUNCAOOK");
		
		try {
			access.cadastrarFuncao(fOK);
		} catch (EasyCorrectionException e1) {
			e1.printStackTrace();
		}
		
		/*
		 * RETRIEVE
		 */
		
		Funcao fNULL2 = access.getFuncao(-1); // NULL
		List<Funcao> list4 = access.listarFuncao(); // NOT EMPTY
		List<Funcao> list5 = access.consultarFuncaoPorMenu(-1); // EMPTY
		List<Funcao> list6 = access.consultarFuncaoPorMenu(2); // EMPTY
		Funcao f7 = access.consultarFuncaoNomeERotulo(null, "FUNCAOOK"); // NULL
		Funcao f8 = access.consultarFuncaoNomeERotulo("", "FUNCAOOK"); // NULL
		Funcao f9 = access.consultarFuncaoNomeERotulo("OK", null); // NULL
		Funcao f10 = access.consultarFuncaoNomeERotulo("OK", ""); // NULL
		

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
		
		Funcao f11 = new Funcao(-1, menuOK, "9090", "klkl");  
		Funcao f12 = new Funcao(1, null, "9090", "klkl");
		Funcao f13 = new Funcao(1, menuOK, null, "klkl");
		Funcao f14 = new Funcao(1, menuOK, "", "klkl");
		Funcao f15 = new Funcao(1, menuOK, "90909", null);
		Funcao f16 = new Funcao(1, menuOK, "90909", "");
		
		try {
			access.cadastrarFuncao(f11);
		} catch (EasyCorrectionException e6) {
		}
		try {
			access.cadastrarFuncao(f12);
		} catch (EasyCorrectionException e5) {
		}
		try {
			access.cadastrarFuncao(f13);
		} catch (EasyCorrectionException e4) {
		}
		try {
			access.cadastrarFuncao(f14);
		} catch (EasyCorrectionException e3) {
		}
		try {
			access.cadastrarFuncao(f15);
		} catch (EasyCorrectionException e2) {
		}
		try {
			access.cadastrarFuncao(f16);
		} catch (EasyCorrectionException e1) {
		}
		
		/*
		 * DELETE
		 */
		
		try {
			access.excluirFuncao(fNULL2);
		} catch (EasyCorrectionException e) {
		}
		
		try {
			access.excluirFuncao(f11);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f12);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f13);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f14);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f15);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirFuncao(f16);
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
		} catch (EasyCorrectionException e) {
		}
		try {
			access.cadastrarGrupo(g2);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.cadastrarGrupo(g3);
		} catch (EasyCorrectionException e) {
		}

		Grupo gOK = new Grupo(1, "OK");
		try {
			access.cadastrarGrupo(gOK);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
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
		} catch (EasyCorrectionException e) {
		}
		try {
			access.cadastrarGrupo(g5);
		} catch (EasyCorrectionException e) {
		}
		
		/*
		 * DELETE
		 */
		
		try {
			access.excluirGrupo(gNULL2);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirGrupo(g4);
		} catch (EasyCorrectionException e) {
		}
		try {
			access.excluirGrupo(g5);
		} catch (EasyCorrectionException e) {
		}
	}

	@Test
	public void permissionTest() {

	}
}