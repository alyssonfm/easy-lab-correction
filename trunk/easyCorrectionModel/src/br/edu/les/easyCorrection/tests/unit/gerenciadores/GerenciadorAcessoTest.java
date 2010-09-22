package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.util.GeraMd5;
import junit.framework.TestCase;

public class GerenciadorAcessoTest extends TestCase{
	
	private GerenciadorAcesso ga;
	private Usuario u;
	
	public GerenciadorAcessoTest() {
		ga = new GerenciadorAcesso();
		u = ga.getUsuario(3);
	}
	
	public void test(){		
		ga.alterarSenha(u, u.getLogin());
		assertEquals(GeraMd5.md5(u.getLogin()), u.getSenha());
		assertEquals("Alysson Filgueira", u.getNome());
		assertEquals("alysson", u.getLogin());	
		assertEquals("", u.getEmail());
	}
	
	public void testAtualizacao(){
		String email = "alyssonfilgueira@gmail.com";
		u.setEmail(email);
		assertEquals(email, u.getEmail());
		try {
			ga.atualizarUsuario(u);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
		}
		assertEquals(u, ga.consultarUsuarioPorLogin("alysson"));
	}
}
