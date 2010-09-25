package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.util.GeraMd5;


public class GerenciadorAcessoTC {
		
	private GerenciadorAcesso ga;
	private Usuario u;
	

	@Before
	public void setup(){
		ga = new GerenciadorAcesso();
		u = new Usuario("alysson", "Alysson Filgueira", null, null);
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void Gets(){
		ga.getMenu(1);
		ga.getUsuario(1);
		ga.getFuncao(1);
		ga.getGrupo(1);
		ga.getGrupoUsuario(1);
		ga.getGrupoUsuarioPorGrupoEUsuario(1, 1);
		ga.getGrupoUsuarioPorUsuario(1);
		ga.getPermissao(1);
		ga.getUsuarioPorLogin(null);
		ga.getGrupoPorNome(null);
	}
	
	@Test
	public void alterarSenha(){		
		ga.alterarSenha(u, u.getLogin());
		Assert.assertEquals(GeraMd5.md5(u.getLogin()), u.getSenha());
	}
	
	
	
	@Test
	public void atualizacao(){
		String email = "alyssonfilgueira@gmail.com";
		u.setEmail(email);
		Assert.assertEquals(email, u.getEmail());
		try {
			ga.atualizarUsuario(u);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(u, ga.consultarUsuarioPorLogin("alysson"));
	}
	
	

}
