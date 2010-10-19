package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import java.util.Calendar;
import java.util.Date;

import org.junit.*;

import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorRoteiros;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;


public class GerenciadorRoteirosTest {

	private GerenciadorRoteiros gr;
	private Roteiro r, r2, r3;

	@Before
	public void setup() {
		gr = new GerenciadorRoteiros();
		r = new Roteiro();
	}
	
	@After
	public void tearDown() {

	}
	
	@Test
	public void gets(){
		Assert.assertEquals(gr.getClass(), GerenciadorRoteiros.class);
		Assert.assertEquals(gr.getPeriodo(0), null);
		Assert.assertEquals(gr.getRoteiro(0), null);
		Assert.assertEquals(r.getClass(), Roteiro.class);
		Assert.assertEquals(r.getDescricao(), null);
		Assert.assertEquals(r.getDiretorioInterface(), null);
		Assert.assertEquals(r.getDiretorioTestes(), null);
		Assert.assertEquals(r.getNome(), null);
		Assert.assertEquals(r.getPenalidadeDiasAtraso(), 0.0, 0.0001);
		Assert.assertEquals(r.getPorcentagemTestesAutomaticos(), 0.0, 0.0001);
		Assert.assertEquals(r.getDataFinalDiscussao(), null);
		Assert.assertEquals(r.getDataFinalEntrega(), null);
		Assert.assertEquals(r.getDataLiberacao(), null);
		Assert.assertEquals(r.getId(), null);
		Assert.assertEquals(r.getNumeroMaximoEnvios(), null);
		Assert.assertEquals(r.getNumeroMaximoParticipantes(), null);
		Assert.assertEquals(r.getPeriodo(), null);
		Assert.assertEquals(r.getTempoLimiteTestes(), null);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void sets(){
		r.setBloqueado(false);
		r.setDataLiberacao(new Date());
		r.setDescricao("Roteiro de testes");
		r.setNome("Roteiro 01");
		r.setId(1);
		try {
			Date d = new Date(); 
			Date d1 = new Date();
			d.setDate(r.getDataLiberacao().getDate()+7);
			d1.setDate(r.getDataLiberacao().getDate()+21);
			r2 = gr.cadastrarRoteiro(r);
			Assert.assertEquals(r2.getDescricao(), r.getDescricao());
			Assert.assertEquals(r2.getPenalidadeDiasAtraso(), gr.PENALIDADE_DIA_ATRASO_DEFAULT, 1.0);
			Assert.assertEquals(r2.getPorcentagemTestesAutomaticos(), gr.PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT, 1.0);
			Assert.assertEquals(r2.getDataFinalEntrega(), d);
			Assert.assertEquals(r2.getDataFinalDiscussao(), d1);
		} catch (Exception e) {
			e.getMessage();
		}
	}
		
	@Test
	public void creates(){
		try {
			r3 = new Roteiro();
			r3.setId(5);
			r3.setBloqueado(false);
			r3.setNome("Roteiro Teste");
			r3.setDataLiberacao(Calendar.getInstance().getTime());
			r3.setDescricao("Roteiro de testes de unidade para a release 1");
			gr.cadastrarRoteiro(r3);
			Assert.assertEquals(gr.cadastrarRoteiro(r3).getClass(), Roteiro.class);
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bloquear(){
		gr.bloquearRoteiro(r);
		Assert.assertTrue(r.isBloqueado());
	}
	
	@Test
	public void liberar(){
		r.setDataLiberacao(new Date());
		try {
			gr.liberarRoteiro(r);
			Assert.assertFalse(r.isBloqueado());
		} catch (LiberaRoteiroException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void edits(){
		try {
			r.setDataLiberacao(Calendar.getInstance().getTime());
			r.setId(5);
			Assert.assertTrue(r != null);
			Assert.assertTrue(gr != null);
			r.getDataLiberacao();
			gr.editarRoteiro(r);
			r.setNome("Roteiro Teste");
			
		} catch (EdicaoRoteiroException e) {
			e.printStackTrace();
		} catch (CriacaoRoteiroException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletes(){
		gr.excluirRoteiro(r);
	}
	
}
