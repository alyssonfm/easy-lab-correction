package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;

public class GerenciadorAcessoTC {

	private GerenciadorAcesso ga;
	private GrupoUsuario gpUsuario1, gpUsuario2;
	private Usuario uNULL, u;

	@Before
	public void setup() {
		ga = new GerenciadorAcesso();
		uNULL = new Usuario();
		u = new Usuario("alysson", "Alysson Filgueira", "123123",
				"alyssonfilgueira@gmail.com");
		
		
		
	}

	@Test
	public void criaAlteraRemove() {

		Assert.assertNull(ga.cadastrarUsuario((uNULL, "haha"));
		
		

	}
}
