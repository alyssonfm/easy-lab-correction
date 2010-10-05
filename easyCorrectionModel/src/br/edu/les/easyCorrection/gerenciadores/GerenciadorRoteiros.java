package br.edu.les.easyCorrection.gerenciadores;

import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;

public class GerenciadorRoteiros {

	public final int TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT = 10000;
	public final int NUMERO_MAXIMO_ENVIOS_DEFAULT = 3;
	public final double PENALIDADE_DIA_ATRASO_DEFAULT = 0.5;
	public final double PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT = 1;
	
	public Periodo getPeriodo(int periodoId){
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}
	
	public Roteiro getRoteiro(int roteiroId){
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(roteiroId);
	}


	/*
	 * Pseudo codigo do metodo:
	 * 	Pesquisa roteiro pelo nome (os nomes devem ser atributos �nicos) - ver isso
	 * 	Se j� existir:
	 * 		Pega o estado atual do objeto (criar metodo para dizer qual o estado)
	 * 		
	 * 		metodo_de_validacao_do_roteiro_estado_altera() // se passar dessa linha � um roteiro v�lido
	 * 		OU
	 * 		metodo_de_validacao_do_roteiro_estado_bloqueia() // se passar dessa linha � um roteiro v�lido
	 * 		OU
	 * 		metodo_de_validacao_do_roteiro_estado_fechado() // se passar dessa linha � um roteiro v�lido
	 * 
	 * 		save_changes()
	 * 
	 * 	Se n�o existir:
	 * 		
	 * 		metodo_de_validacao_do_roteiro_estado_cria() // se passar dessa linha � um roteiro v�lido
	 * 		
	 * 		save_changes() 		
	 */
	
	@SuppressWarnings("deprecation")
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp) throws CriacaoRoteiroException {
		Date dataAtual = new Date();
		Date dataAux = roteiroTemp.getDataLiberacao();
		Date dataAux2 = roteiroTemp.getDataLiberacao();
		dataAux.setDate(dataAux.getDate()+7);
		dataAux2.setDate(dataAux2.getDate()+21);
		if(roteiroTemp.getNome()=="" || roteiroTemp.getNome() == null){
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO.msg("Nome da atividade invalido. O Roteiro nao pode ser criado!"));			
		}		
		else if(roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Data de Liberacao invalida. O roteiro nao pode ser ser criado!"));
		}		
		else if(roteiroTemp.getNome() != null || roteiroTemp.getNome() != "" && !roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() != null 
				&& roteiroTemp.getDataFinalEntrega() == dataAux 
				&& roteiroTemp.getDataFinalDiscussao() == dataAux2
				&& roteiroTemp.getNumeroMaximoEnvios() == NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getPorcentagemTestesAutomaticos()== PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() == PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() == TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT){
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
			System.out.println("Roteiro criado com sucesso");
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);			
		}
		else if(roteiroTemp.getDataFinalEntrega().before(roteiroTemp.getDataLiberacao()) || roteiroTemp.getDataFinalEntrega() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Data Limite para Entrega anterior a Data de Liberacao. O Roteiro nao pode ser criado/atualizado!"));
		}
		else if(roteiroTemp.getDataFinalDiscussao().before(roteiroTemp.getDataFinalEntrega()) || roteiroTemp.getDataFinalDiscussao() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Data Limite para Discussao anterior a Data Limite para Entrega. O Roteiro nao pode ser criado/atualizado!"));
		}
		else if(roteiroTemp.getNumeroMaximoEnvios() <= 0 || roteiroTemp.getNumeroMaximoEnvios() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("O numero maximo de integrantes deve ser sempre maior ou igual a 1 integrante. O Roteiro nao pode ser criado/atualizado!"));
		}
		else if(roteiroTemp.getNumeroMaximoEnvios() <= 0 || roteiroTemp.getNumeroMaximoEnvios() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("O numero maximo de envios deve ser sempre maior ou igual a 1. Roteiro nao pode ser criado/atualizado!"));
		}
		else if(roteiroTemp.getPenalidadeDiasAtraso()< 0.0 || roteiroTemp.getPenalidadeDiasAtraso() > 10.0){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("A Penalidade por dia de atraso deve ser sempre maior ou igual a 0,0 e menor ou igual a 10,0. O Roteiro nao pode ser criado/atualizado!"));
		}
		else if(roteiroTemp.getTempoLimiteTestes() < 0 || roteiroTemp.getTempoLimiteTestes() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Time-limit de execu��o dos testes por m�todo inv�lido, deve ser sempre >= 0. O Roteiro nao pode ser criado/atualizado!"));
		}
		else if(roteiroTemp.getNome() != null || roteiroTemp.getNome() != "" && !roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() != null 
				&& roteiroTemp.getDataFinalEntrega() != dataAux 
				&& roteiroTemp.getDataFinalEntrega() != null 
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() != NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& roteiroTemp.getPorcentagemTestesAutomaticos()!= PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != null
				){
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
			System.out.println("Roteiro criado/atualizado com sucesso!");
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);			
		}
		else if(roteiroTemp.getNome() != null || roteiroTemp.getNome() != "" && !roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() != null 
				&& roteiroTemp.getDataFinalEntrega() != dataAux 
				&& roteiroTemp.getDataFinalEntrega() != null 
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() != NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& roteiroTemp.getPorcentagemTestesAutomaticos()!= PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != null
				&& (roteiroTemp.getDiretorioTestes() == null || roteiroTemp.getDiretorioTestes() == "")){
			throw new CriacaoRoteiroException("Formato do Arquivo de Testes Automaticos nao eh .zip nem .java. O Roteiro nao pode ser criado/atualizado!");
		}
		else if(roteiroTemp.getNome() != null || roteiroTemp.getNome() != "" && !roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() != null 
				&& roteiroTemp.getDataFinalEntrega() != dataAux 
				&& roteiroTemp.getDataFinalEntrega() != null 
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() != NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& roteiroTemp.getPorcentagemTestesAutomaticos()!= PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != null
				&& (roteiroTemp.getDiretorioInterface() == null || roteiroTemp.getDiretorioInterface() == "")){
			throw new CriacaoRoteiroException("Formato do arquivo da interface deve ser .java. O Roteiro nao pode ser criado/atualizado!");
		}
		else if(roteiroTemp.getDataLiberacao() == dataAtual && (roteiroTemp.getDiretorioInterface() == null 
				|| roteiroTemp.getNumeroMaximoEnvios() <= 0 || roteiroTemp.getDiretorioInterface() == null 
				|| roteiroTemp.getPorcentagemTestesAutomaticos() < 0 || roteiroTemp.getPorcentagemTestesAutomaticos() > 100)){
			throw new CriacaoRoteiroException("Roteiro nao pode ser liberado");
		}
		else{
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
			System.out.println("Roteiro criado/atualizado com sucesso!");
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		}
	}

	public void excluirRoteiro(Roteiro roteiro){
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}
	
	
}
