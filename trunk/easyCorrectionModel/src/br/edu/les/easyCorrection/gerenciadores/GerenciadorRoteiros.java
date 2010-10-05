package br.edu.les.easyCorrection.gerenciadores;

import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;

public class GerenciadorRoteiros {

	public final int TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT = 10000;
	public final int NUMERO_MAXIMO_ENVIOS_DEFAULT = 3;
	public final double PENALIDADE_DIA_ATRASO_DEFAULT = 0.5;
	public final double PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT = 1;

	/*
	 * Estado anterior a criacao de um roteiro
	 */
	public final int ESTADO_CRIACAO_ROTEIRO = 10;
	/*
	 * Estado posterior a criacao do roteiro, mas anterior a data de liberacao
	 * deste
	 */
	public final int ESTADO_ALTERACAO_ROTEIRO = 20;
	/*
	 * Estado posterior a data de liberacao do roteiro, mas anterior a data de
	 * fechamento (entrega) deste
	 */
	public final int ESTADO_LIBERADO_ROTEIRO = 30;
	/*
	 * Estado posterior a data de entrega do roteiro
	 */
	public final int ESTADO_FECHADO_ROTEIRO = 40;
	/*
	 * Estado provisorio do roteiro, pode acontecer a qualquer momento apos a
	 * criacao deste. Esse estado eh totalmente equivalente ao
	 * ESTADO_ALTERACAO_ROTEIRO
	 */
	public final int ESTADO_BLOQUEADO_ROTEIRO = 50;

	public Periodo getPeriodo(int periodoId) {
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}

	public Roteiro getRoteiro(int roteiroId) {
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(roteiroId);
	}

	/*
	 * Pseudo codigo do metodo:
	 * 
	 * valida_roteiro_antes_da_cria
	 * 
	 * save_changes()
	 */

	@SuppressWarnings("deprecation")
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp)
			throws CriacaoRoteiroException {
		Date dataAtual = new Date();
		Date dataAux = roteiroTemp.getDataLiberacao();
		Date dataAux2 = roteiroTemp.getDataLiberacao();
		dataAux.setDate(dataAux.getDate() + 7);
		dataAux2.setDate(dataAux2.getDate() + 21);
		
		if (roteiroTemp.getNome() == "" || roteiroTemp.getNome() == null) {
			throw new CriacaoRoteiroException(
					MsgErros.NOMEVAZIO
							.msg("Nome da atividade inválido. O Roteiro não pôde ser criado!"));
		} else if (roteiroTemp.getDataLiberacao() == null
				|| roteiroTemp.getDataLiberacao().before(dataAtual)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data de Liberação inválida. O roteiro não pôde ser ser criado!"));
		} else if (roteiroTemp.getDataFinalEntrega() == null
				|| roteiroTemp.getDataFinalEntrega().before(roteiroTemp.getDataLiberacao())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Entrega anterior à Data de Liberação. O Roteiro não pôde ser criado/atualizado!"));
		} else if (roteiroTemp.getDataFinalDiscussao() == null 
				|| roteiroTemp.getDataFinalDiscussao().before(roteiroTemp.getDataFinalEntrega())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Discussao anterior a Data Limite para Entrega. O Roteiro nao pode ser criado/atualizado!"));
		} else if (roteiroTemp.getNumeroMaximoEnvios() == null || roteiroTemp.getNumeroMaximoEnvios() <= 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("O numero maximo de integrantes deve ser sempre maior ou igual a 1 integrante. O Roteiro nao pode ser criado/atualizado!"));
		} else if (roteiroTemp.getNumeroMaximoEnvios() == null || roteiroTemp.getNumeroMaximoEnvios() <= 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("O número máximo de envios deve ser sempre maior ou igual a 1. Roteiro não pôde ser criado/atualizado!"));
		} else if (roteiroTemp.getPenalidadeDiasAtraso() < 0.0
				|| roteiroTemp.getPenalidadeDiasAtraso() > 10.0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("A Penalidade por dia de atraso deve ser sempre maior ou igual a 0,0 e menor ou igual a 10,0. O Roteiro nao pode ser criado/atualizado!"));
		}
		else if((roteiroTemp.getNome() != null
				|| roteiroTemp.getNome() != "")
				&& (!roteiroTemp.getDataLiberacao().before(dataAtual)
				|| roteiroTemp.getDataLiberacao() != null)
				&& roteiroTemp.getDataFinalEntrega() != dataAux
				&& roteiroTemp.getDataFinalEntrega() != null
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() >= 1
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& (roteiroTemp.getPorcentagemTestesAutomaticos() < 0
				|| roteiroTemp.getPorcentagemTestesAutomaticos() > 100)
				&& roteiroTemp.getPenalidadeDiasAtraso() > 0.0
				&& roteiroTemp.getPenalidadeDiasAtraso() > 10.0
				&& roteiroTemp.getTempoLimiteTestes() >= 0
				&& roteiroTemp.getTempoLimiteTestes() != null
				){
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Porcentagem automática da avaliação deve ser sempre >= 0 e <= 100. " +
									"O Roteiro não pôde ser criado/atualizado!"));
		}
		
		else if (roteiroTemp.getTempoLimiteTestes() < 0
				|| roteiroTemp.getTempoLimiteTestes() == null) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Time-limit de execução dos testes por método inválido, deve ser sempre >= 0. O roteiro não pôde ser criado/atualizado!"));
		} else if ((roteiroTemp.getNome() != null
				|| roteiroTemp.getNome() != "")
				&& (!roteiroTemp.getDataLiberacao().before(dataAtual)
				|| roteiroTemp.getDataLiberacao() != null)
				&& roteiroTemp.getDataFinalEntrega() != dataAux
				&& roteiroTemp.getDataFinalEntrega() != null
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() != NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& roteiroTemp.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != null) {
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
			System.out.println("Roteiro criado/atualizado com sucesso!");
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		} else if ((roteiroTemp.getNome() != null
				|| roteiroTemp.getNome() != "")
				&& (!roteiroTemp.getDataLiberacao().before(dataAtual)
				|| roteiroTemp.getDataLiberacao() != null)
				&& roteiroTemp.getDataFinalEntrega() != dataAux
				&& roteiroTemp.getDataFinalEntrega() != null
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() != NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& roteiroTemp.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != null
				&& (roteiroTemp.getDiretorioTestes() == null || roteiroTemp
						.getDiretorioTestes() == "")) {
			throw new CriacaoRoteiroException(
					"Formato do Arquivo de Testes Automaticos nao eh .zip nem .java. O Roteiro nao pode ser criado/atualizado!");
		} else if ((roteiroTemp.getNome() != null
				|| roteiroTemp.getNome() != "")
				&& (!roteiroTemp.getDataLiberacao().before(dataAtual)
				|| roteiroTemp.getDataLiberacao() != null)
				&& roteiroTemp.getDataFinalEntrega() != dataAux
				&& roteiroTemp.getDataFinalEntrega() != null
				&& roteiroTemp.getDataFinalDiscussao() != dataAux2
				&& roteiroTemp.getDataFinalDiscussao() != null
				&& roteiroTemp.getNumeroMaximoEnvios() != NUMERO_MAXIMO_ENVIOS_DEFAULT
				&& roteiroTemp.getNumeroMaximoEnvios() != null
				&& roteiroTemp.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiroTemp.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT
				&& roteiroTemp.getTempoLimiteTestes() != null
				&& (roteiroTemp.getDiretorioInterface() == null || roteiroTemp
						.getDiretorioInterface() == "")) {
			throw new CriacaoRoteiroException(
					"Formato do arquivo da interface deve ser .java. O Roteiro nao pode ser criado/atualizado!");
		} else if (roteiroTemp.getDataLiberacao() == dataAtual
				&& (roteiroTemp.getDiretorioInterface() == null
						|| roteiroTemp.getNumeroMaximoEnvios() <= 0
						|| roteiroTemp.getDiretorioInterface() == null
						|| roteiroTemp.getPorcentagemTestesAutomaticos() < 0 || roteiroTemp
						.getPorcentagemTestesAutomaticos() > 100)) {
			throw new CriacaoRoteiroException("Roteiro nao pode ser liberado");
		}
		
		else if ((roteiroTemp.getNome() != null
			|| roteiroTemp.getNome() != "")
			&& (!roteiroTemp.getDataLiberacao().before(dataAtual)
			|| roteiroTemp.getDataLiberacao() != null)
			&& roteiroTemp.getDataFinalEntrega() == dataAux
			&& roteiroTemp.getDataFinalDiscussao() == dataAux2
			&& roteiroTemp.getNumeroMaximoEnvios() == NUMERO_MAXIMO_ENVIOS_DEFAULT
			&& roteiroTemp.getPorcentagemTestesAutomaticos() == PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
			&& roteiroTemp.getPenalidadeDiasAtraso() == PENALIDADE_DIA_ATRASO_DEFAULT
			&& roteiroTemp.getTempoLimiteTestes() == TEMPO_LIMITE_EXECUCAO_TESTES_DEFAULT) {
			
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
			System.out.println("Roteiro criado com sucesso");
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		
		}else {
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
			System.out.println("Roteiro criado/atualizado com sucesso!");
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		}
	}

	/*
	 * Pega o estado atual do objeto (criar metodo para dizer qual o estado)
	 * 
	 * metodo_de_validacao_do_roteiro_estado_altera() // se passar dessa linha é
	 * um roteiro válido OU metodo_de_validacao_do_roteiro_estado_bloqueia() //
	 * se passar dessa linha é um roteiro válido OU
	 * metodo_de_validacao_do_roteiro_estado_fechado() // se passar dessa linha
	 * é um roteiro válido
	 * 
	 * save_changes()
	 */
	public Roteiro editarRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException {

		
		int estadoAtualRoteiro = computaEstadoRoteiro(roteiroTemp);

		if (estadoAtualRoteiro == ESTADO_ALTERACAO_ROTEIRO) {
			
		} else if (estadoAtualRoteiro == ESTADO_ALTERACAO_ROTEIRO) {

		} else if (estadoAtualRoteiro == ESTADO_ALTERACAO_ROTEIRO) {

		} else {
			throw new EdicaoRoteiroException(
					MsgErros.OPER_NAO_REALIZADA
							.msg("A edicao do roteiro nao pode ser realizada!"));
		}

		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);

		//System.out.println("Roteiro criado/atualizado com sucesso!");
		
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		
	}

	private int computaEstadoRoteiro(Roteiro roteiroTemp) {
		Date dataAtual = new Date();
		if (dataAtual.before(roteiroTemp.getDataLiberacao())) {
			return ESTADO_ALTERACAO_ROTEIRO;
		} else if (dataAtual.after(roteiroTemp.getDataLiberacao())
				&& dataAtual.before(roteiroTemp.getDataFinalEntrega())) {
			return ESTADO_LIBERADO_ROTEIRO;
		} else if (dataAtual.after(roteiroTemp.getDataFinalEntrega())) {
			return ESTADO_FECHADO_ROTEIRO;
		} else {
			return 0;
		}

	}

	public void excluirRoteiro(Roteiro roteiro) {
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}

	/*
	 * Metodos de validacao de Roteiros
	 */

	public boolean validaRoteiroEstadoCriacao(Roteiro roteiro) {
		return true;
	}

	public boolean validaRoteiroEstadoAlteracao(Roteiro roteiro) {
		return true;
	}

	public boolean validaRoteiroEstadoLiberado(Roteiro roteiro) {
		return true;
	}

	public boolean validaRoteiroEstadoFechado(Roteiro roteiro) {
		return true;
	}
	
}
