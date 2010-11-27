package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.les.easyCorrection.exceptions.BloqueiaRoteiroException;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorRoteiros;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;

/**
 * Nosso foco com testes de unidade está em detectar falhas causadas por
 * parâmetros fora do conjunto dos valores esperados. Não testaremos situações
 * de acerto, nem mensagens de retorno, isso tudo já foi considerado nos testes
 * de aceitação. Para maiores informações contactar os clientes.
 * 
 */
public class GerenciadorRoteirosTC {

	private GerenciadorRoteiros gr;
	private Roteiro roteiroMinimo, roteiroVariavel, roteiroNULL;

	@Before
	public void setup() {
		gr = new GerenciadorRoteiros();

		roteiroMinimo = new Roteiro();
		roteiroMinimo.setId(0);
		roteiroMinimo.setNome("Roteiro Teste 1");
		roteiroMinimo.setDataLiberacao(Calendar.getInstance().getTime());
		roteiroMinimo.setPeriodo(gr.getPeriodo(1));

		roteiroVariavel = new Roteiro();
		roteiroVariavel.setId(0);
		roteiroVariavel.setBloqueado(false);
		roteiroVariavel.setNome("Roteiro Teste 2");
		roteiroVariavel.setNumeroMaximoEnvios(3);
		roteiroVariavel.setNumeroMaximoParticipantes(10);
		roteiroVariavel.setPeriodo(gr.getPeriodo(1));
		roteiroVariavel.setDataLiberacao(Calendar.getInstance().getTime());
		roteiroVariavel
				.setDescricao("Roteiro de testes de unidade para a release 1");

		roteiroNULL = null;
	}

	@After
	public void tearDown() {
		gr = null;
		roteiroMinimo = null;
		roteiroVariavel = null;
	}

	@Test
	public void cadastrarEditarBloquearExcluir() {
		try {
			roteiroNULL = gr.cadastrarRoteiro(roteiroNULL);
		} catch (CriacaoRoteiroException e) {
			Assert.assertTrue("O Cadastro não deve ser realizado", true);
		}

		try {
			roteiroMinimo = gr.cadastrarRoteiro(roteiroMinimo);
			Assert.assertTrue("O Cadastro deve ser realizado", true);
		} catch (CriacaoRoteiroException e) {
			Assert.assertFalse("O Cadastro deve ser realizado", true);
		}

		try {
			roteiroVariavel = gr.cadastrarRoteiro(roteiroVariavel);
			Assert.assertTrue("O Cadastro deve ser realizado", true);
		} catch (CriacaoRoteiroException e) {
			Assert.assertFalse("O Cadastro deve ser realizado", true);
		}

		/*
		 * Editar
		 */

		try {
			gr.editarRoteiro(roteiroNULL);
		} catch (EdicaoRoteiroException e) {
			Assert.assertTrue("A edição não deve ser realizada", true);
		} catch (CriacaoRoteiroException e) {
			Assert.assertTrue("A edição não deve ser realizada", true);
		}

		roteiroMinimo.setNome("OPAAA mudei o nome");
		roteiroMinimo.setDataLiberacao(Calendar.getInstance().getTime());

		try {
			Assert.assertTrue(((gr.editarRoteiro(roteiroMinimo)).getId() > 0));
			Assert.assertTrue("A edição deve ser realizada", true);
		} catch (EdicaoRoteiroException e) {
			Assert.assertFalse("A edição deve ser realizada", true);
		} catch (CriacaoRoteiroException e) {
			Assert.assertFalse("A edição deve ser realizada", true);
		}

		roteiroVariavel.setNome("OPAAA mudei o nome");
		roteiroVariavel.setDataLiberacao(Calendar.getInstance().getTime());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 10);
		roteiroVariavel.setDataFinalEntrega(cal.getTime());

		try {
			Assert
					.assertTrue(((gr.editarRoteiro(roteiroVariavel)).getId() > 0));
			Assert.assertTrue("A edição deve ser realizada", true);
		} catch (EdicaoRoteiroException e) {
			Assert.assertFalse("A edição não deve ser realizada", true);
		} catch (CriacaoRoteiroException e) {
			Assert.assertFalse("A edição não deve ser realizada", true);
		}

		// Bloquear
		
		try {
			gr.bloquearRoteiro(roteiroNULL, true);
		} catch (BloqueiaRoteiroException e) {
			Assert.assertTrue("O bloqueio não deve ser realizado", true);
		} catch (LiberaRoteiroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Assert
					.assertTrue(gr.bloquearRoteiro(roteiroMinimo, true).getId() > 0);
			Assert.assertTrue("O bloqueio deve ser realizado", true);
		} catch (BloqueiaRoteiroException e) {
			Assert.assertFalse("O bloqueio deve ser realizado", true);
		} catch (LiberaRoteiroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(roteiroMinimo.isBloqueado());

		try {
			Assert
					.assertTrue(gr.bloquearRoteiro(roteiroMinimo, false)
							.getId() > 0);
			Assert.assertTrue("O desbloqueio deve ser realizado", true);
		} catch (BloqueiaRoteiroException e) {
			Assert.assertFalse("O desbloqueio deve ser realizado", true);
		} catch (LiberaRoteiroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertFalse(roteiroMinimo.isBloqueado());

		try {
			Assert
					.assertTrue(gr.bloquearRoteiro(roteiroMinimo, false)
							.getId() > 0);
		} catch (BloqueiaRoteiroException e) {
			Assert.assertTrue("O desbloqueio não deve ser realizado", true);
			Assert.assertEquals("O Roteiro já está desbloqueado!", e
					.getMessage());
		} catch (LiberaRoteiroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Remover
		
		try {
			gr.excluirRoteiro(roteiroNULL);
		} catch (ExclusaoRoteiroException e) {
			Assert.assertTrue("A exclusao não deve ser realizada", true);
		}

		try {
			gr.excluirRoteiro(roteiroMinimo);
			Assert.assertTrue("A exclusao deve ser realizada", true);
		} catch (ExclusaoRoteiroException e) {
			Assert.assertFalse("A exclusao deve ser realizada", true);
		}

		try {
			gr.excluirRoteiro(roteiroVariavel);
			Assert.assertTrue("A exclusao deve ser realizada", true);
		} catch (ExclusaoRoteiroException e) {
			Assert.assertFalse("A exclusao deve ser realizada", true);
		}
	}
}
