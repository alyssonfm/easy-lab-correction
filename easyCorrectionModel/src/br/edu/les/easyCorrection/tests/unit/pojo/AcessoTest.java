package br.edu.les.easyCorrection.tests.unit.pojo;

import junit.framework.TestCase;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;


public class AcessoTest extends TestCase{
	
	private Usuario u1;
	private Grupo g1;
	private Funcao f1;
	private Menu m1;
	private Permissao p1;
	private GrupoUsuario gu1;
	
	public AcessoTest() {
		u1 = new Usuario();
		g1 = new Grupo();
		f1 = new Funcao();
		m1 = new Menu();
		p1 = new Permissao();
		gu1 = new GrupoUsuario();
	}
	
	public void testCriacaoUsuario(){
		u1.setIdUsuario(1);
		u1.setNome("Test1");		
		u1.setLogin("test1");
		u1.setEmail("test@test.com");
		u1.setSenha("test1");
		assertEquals("test1", u1.getLogin());
		assertEquals("test@test.com", u1.getEmail());
		assertEquals("Test1", u1.getNome());
		assertEquals("test1", u1.getSenha());
		assertEquals(1, u1.getIdUsuario().intValue());
	}
	
	public void testCriacaoGrupo(){
		g1.setIdGrupo(0);
		g1.setNome("Usuarios");
		assertEquals("Usuarios", g1.getNome());
		assertEquals(0, g1.getIdGrupo().intValue());
	}
	
	public void testCriacaoMenu(){
		m1.setIdMenu(2);
		m1.setNome("Testando");
		m1.setRotulo("Testando");
	}
	
	public void testCriacaoFuncao(){
		f1.setIdFuncao(3);
		f1.setMenu(m1);
		f1.setNome("Testa");
		f1.setRotulo("Testa");
		assertEquals(3, f1.getIdFuncao().intValue());
		assertEquals("Testa", f1.getNome());
		assertEquals("Testa", f1.getRotulo());
		assertEquals(m1, f1.getMenu());
	}
	
	public void testCriacaoPermissao(){
		p1.setFuncao(f1);
		p1.setGrupo(g1);
		p1.setIdPermissao(2);
		assertEquals(2, p1.getIdPermissao().intValue());
		assertEquals(f1, p1.getFuncao());
		assertEquals(g1, p1.getGrupo());
	}
	
	public void testCriacaoGrupoUsuario(){
		gu1.setGrupo(g1);
		gu1.setUsuario(u1);
		gu1.setIdGrupoUsuario(1);
		assertEquals(1, gu1.getIdGrupoUsuario().intValue());
		assertEquals(g1, gu1.getGrupo());
		assertEquals(u1, gu1.getUsuario());
	}
}
