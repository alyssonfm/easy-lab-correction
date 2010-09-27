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
		u = ga.getUsuario(1);
		ga.getFuncao(1);
		ga.getGrupo(1);
		ga.getGrupoUsuario(1);
		ga.getGrupoUsuarioPorGrupoEUsuario(1, 1);
		ga.getGrupoUsuarioPorUsuario(1);
		ga.getUsuarioPorLogin("alysson");
		ga.getGrupoPorNome("Administrador");
		ga.getGrupoPorNome(null); //Testes que lancam excecao.
		ga.getUsuarioPorLogin(null); //Testes que lancam excecao.
	}
	
	@Test
	public void alterarSenha(){		
		ga.alterarSenha(u, u.getLogin()); // Metodo nao esta alterando a senha conforme deveria fazer.
		Assert.assertEquals(GeraMd5.md5(u.getLogin()), u.getSenha());
	}
	
	@Test
	public void atualizacao(){
		String email = "alyssonfilgueira@gmail.com";
		u.setEmail(email);
		Assert.assertEquals(email, u.getEmail());
		try {
			ga.atualizarUsuario(u); //Ocorrendo nullPointer, porem fiz testes e vi que ga e u nao sao nulos. 
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(u, ga.getUsuarioPorLogin(u.getLogin()));
	}
	
	@Test
	public void consultas(){
		ga.consultarFuncaoNomeERotulo("cadastrar", "cadastro");
		ga.consultarFuncaoNomeERotulo(null, null);
		ga.consultarFuncaoPorMenu(1);
		ga.consultarFuncaoPorMenu(null);
		ga.consultarGrupoPorNome("Professor");
		ga.consultarGrupoPorNome(null);
//		ga.consultarMenuPorRotuloENome(rotulo, nome);
//		ga.consultarPermissoesPorGrupo(idGrupo);
//		ga.consultarUsuarioPorGrupo(idGrupo);
//		ga.consultarUsuarioPorLogin(login);
	}

}
