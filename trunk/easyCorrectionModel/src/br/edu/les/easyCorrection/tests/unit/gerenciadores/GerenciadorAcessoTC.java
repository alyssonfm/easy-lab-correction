package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;

public class GerenciadorAcessoTC {

	private GerenciadorAcesso ga;
	private GrupoUsuario gpNULL, gpUsuario1, gpUsuario2, gpUsuario3;
	private Grupo gNULL, g;
	private Usuario uNULL, u;

	@Before
	public void setup() {
		ga = new GerenciadorAcesso();

		uNULL = null;
		u = new Usuario("alyssonOK", "Alysson Filgueira", "123123",
				"alyssonfilgueir@gmail.com");
		u.setIdUsuario(0);
		u.setPeriodo(ga.getPeriodo(1));

		gNULL = null;
		g = DAOFactory.DEFAULT.buildGrupoDAO().findAll().get(0);

		gpNULL = null;

		gpUsuario1 = new GrupoUsuario();
		gpUsuario1.setUsuario(uNULL);
		gpUsuario1.setGrupo(g);
		gpUsuario1.setIdGrupoUsuario(0);

		gpUsuario2 = new GrupoUsuario();
		gpUsuario2.setUsuario(u);
		gpUsuario2.setGrupo(gNULL);
		gpUsuario2.setIdGrupoUsuario(0);

		gpUsuario3 = new GrupoUsuario();
		gpUsuario3.setUsuario(u);
		gpUsuario3.setGrupo(g);
		gpUsuario3.setIdGrupoUsuario(0);

	}

	@Test
	public void criaAlteraRemove() {

		try {
			ga.cadastrarUsuario(gpNULL);
		} catch (EasyCorrectionException e) {
			Assert.assertEquals("O GrupoUsuario nao foi encontrado(a).", e
					.getMessage());
		}
		try {
			ga.cadastrarUsuario(gpUsuario1);
		} catch (EasyCorrectionException e1) {
			Assert.assertEquals("O Usuario nao foi encontrado(a).", e1
					.getMessage());
		}

		try {
			ga.cadastrarUsuario(gpUsuario2);
		} catch (EasyCorrectionException e2) {
			Assert.assertEquals("O Grupo nao foi encontrado(a).", e2
					.getMessage());
		}

		try {
			gpUsuario3 = ga.cadastrarUsuario(gpUsuario3);
			Assert.assertTrue(gpUsuario3.getIdGrupoUsuario() > 0);
		} catch (EasyCorrectionException e3) {
			Assert.assertFalse(true);
		}

		// alteracao

		try {
			ga.atualizarUsuario(uNULL);
		} catch (EasyCorrectionException e) {
			Assert.assertEquals("O Usuario nao foi encontrado(a).", e
					.getMessage());
		}
		try {
			u = ga.atualizarUsuario(gpUsuario3.getUsuario());
			Assert.assertTrue(gpUsuario3.getIdGrupoUsuario() > 0);
		} catch (EasyCorrectionException e1) {
			Assert.assertTrue(false);
		}

		// remocao

		try {
			ga.excluirUsuario(gpNULL);
		} catch (EasyCorrectionException e) {
			Assert.assertEquals("O GrupoUsuario nao foi encontrado(a).", e
					.getMessage());
		}
		try {
			ga.excluirUsuario(gpUsuario3);
		} catch (EasyCorrectionException e) {
			Assert.assertFalse(true);
		}
	}
}
