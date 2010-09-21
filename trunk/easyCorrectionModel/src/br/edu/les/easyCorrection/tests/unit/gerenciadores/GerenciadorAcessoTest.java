package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import junit.framework.TestCase;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;


public class GerenciadorAcessoTest extends TestCase{
	
	private Usuario u1;
	private GerenciadorAcesso ga;
	
	public GerenciadorAcessoTest() {
		u1 = new Usuario();
		ga = new GerenciadorAcesso();
	}
	
	public void testCriacaoUsuario(){
		u1.setLogin("test1");
		u1.setEmail("test@test.com");
		u1.setIdUsuario(3);
		ga.alterarSenhaAutomatico(u1.getIdUsuario());
	}
}
