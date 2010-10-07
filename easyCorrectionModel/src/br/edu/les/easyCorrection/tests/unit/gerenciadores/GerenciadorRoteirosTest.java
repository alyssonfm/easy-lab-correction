package br.edu.les.easyCorrection.tests.unit.gerenciadores;

import java.util.Date;

import org.junit.*;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorRoteiros;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;


public class GerenciadorRoteirosTest {

	private GerenciadorRoteiros gr;
	private Roteiro r;

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
		Assert.assertEquals(r.getPenalidadeDiasAtraso(), null);
		Assert.assertEquals(r.getPorcentagemTestesAutomaticos(), null);
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
			Roteiro r2 = gr.cadastrarRoteiro(r);
			Assert.assertEquals(r2.getDescricao(), r.getDescricao());
			Assert.assertEquals(r2.getPenalidadeDiasAtraso(), gr.PENALIDADE_DIA_ATRASO_DEFAULT);
			Assert.assertEquals(r2.getPorcentagemTestesAutomaticos(), gr.PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT);
			Assert.assertEquals(r2.getDataFinalEntrega(), d);
			Assert.assertEquals(r2.getDataFinalDiscussao(), d1);
		} catch (EasyCorrectionException e) {
			e.getMessage();
		}
	}
		
	@SuppressWarnings("deprecation")
	@Test
	public void creates(){
		try {
			r.setId(1);
			r.setBloqueado(false);
			r.setNome("Roteiro Teste");
			r.setDataLiberacao(new Date(2010, 10, 7));
			r.setDescricao("Roteiro de testes de unidade para a release 1");
			Assert.assertEquals(gr.cadastrarRoteiro(r).getClass(), Roteiro.class);
			Assert.assertTrue(gr.validaRoteiroEstadoLiberado(r));
		} catch (EasyCorrectionException e) {
			e.printStackTrace();
		}
	}
	
	
}
