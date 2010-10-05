package br.edu.les.easyCorrection.sistema;

import java.util.Date;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;



public class Teste {

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Throwable {
		Facade facade = new Facade();
		facade.cadastrarFuncao(new Funcao());
		Roteiro r = new Roteiro();
		r.setDataFinalDiscussao(new Date(30,3,2010));
		r.setDataLiberacao(new Date(10,3,2010));
		r.setDescricao("Roteiro de teste elaborado por Alysson");
		r.setDataFinalEntrega(new Date(25,3,2010));
		r.setDiretorioInterface("D:\\Teste\\interface\\");
		r.setDiretorioTestes("D:\\Teste\\tests");
		r.setId(123);
		r.setNome("Roteiro 00");
		r.setNumeroMaximoEnvios(3);
		r.setPenalidadeDiasAtraso(0.5);
		r.setPeriodo(new Periodo());
		r.setPorcentagemTestesAutomaticos(0.8);
		r.setTempoLimiteTestes(1000);
		r.setNumeroMaximoParticipantes(3);
		r.setBloqueado(false);
		facade.cadastrarRoteiro(r);
		
	}
}
