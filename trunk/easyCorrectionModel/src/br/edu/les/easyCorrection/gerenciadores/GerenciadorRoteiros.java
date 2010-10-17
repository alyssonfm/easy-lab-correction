package br.edu.les.easyCorrection.gerenciadores;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorRoteiros {

	public final double PENALIDADE_DIA_ATRASO_DEFAULT = -1;
	public final double PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT = -1;

	public final int ESTADO_INEXISTENTE = -1;

	/*
	 * Estado prévio à criacao do roteiro
	 */
	public final int ROTEIRO_EM_CRIACAO = 0;
	/*
	 * Estado posterior a criacao do roteiro, mas anterior a data de liberacao
	 * deste
	 */
	public final int ROTEIRO_CRIADO = 1;
	/*
	 * Estado posterior a data de liberacao do roteiro, mas anterior a data de
	 * fechamento (entrega) deste
	 */
	public final int ROTEIRO_LIBERADO = 2;
	/*
	 * Estado posterior a data de entrega do roteiro
	 */
	public final int ROTEIRO_FECHADO = 3;

	/*
	 * String a qual receberah o nome do metodo chamado: - Se foi
	 * cadastraRoteiro, recebe "criado" - Se foi editaRoteiro, recebe
	 * "atualizado"
	 * 
	 * Isso permite-nos reusar o codigo de validacao dos roteiros
	 */
	String criacaoOuAtualizacaoMsg = "NULL";

	public Periodo getPeriodo(int periodoId) {
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}

	public List<Periodo> getPeriodoAtual() {
		return DAOFactory.DEFAULT.buildPeriodoDAO().findAll();
	}

	public Roteiro getRoteiro(int roteiroId) {
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(roteiroId);
	}

	public List<Roteiro> getRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}

	public void excluirRoteiro(Roteiro roteiro) {
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}

	/*
	 * Pseudo codigo do metodo:
	 * 
	 * valida_roteiro_antes_da_cria save_changes() exception
	 */
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp)
			throws CriacaoRoteiroException, CampoVazioException {

		this.criacaoOuAtualizacaoMsg = "criado";

		if (validaRoteiroEmCriacaoEAlteracao(roteiroTemp)) {
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);

			// As frases de sucesso são implementadas na GUI, por isso mantemos
			// essa flag aqui. Para termos uma certeza de que, mesmo sem gui, o
			// roteiro foi criado com sucesso
			System.out.println("Roteiro criado com sucesso!");

			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		} else {
			throw new CriacaoRoteiroException("Roteiro não pôde ser criado");
		}
	}

	public Roteiro editarRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException, CampoVazioException {

		int estadoAtualRoteiro = computaEstadoRoteiro(roteiroTemp);
		this.criacaoOuAtualizacaoMsg = "atualizado";

		if (estadoAtualRoteiro == ROTEIRO_EM_CRIACAO) {
			if (validaRoteiroEstadoCriado(roteiroTemp)) {
				// OK, passou pelos casos de excecao, pode seguir em frente! =)
			}
		}
		if (estadoAtualRoteiro == ROTEIRO_CRIADO) {
			if (validaRoteiroEstadoCriado(roteiroTemp)
					&& validaDirTestesEInterface(roteiroTemp)) {
				// OK, passou pelos casos de excecao, pode seguir em frente! =)
			}
		} else if (estadoAtualRoteiro == ROTEIRO_LIBERADO) {
			if (validaRoteiroEstadoLiberado(roteiroTemp)
					&& validaRoteiroEstadoCriado(roteiroTemp)
					&& validaDirTestesEInterface(roteiroTemp)) {
				// OK, passou pelos casos de excecao, pode seguir em frente! =)
			}
		} else if (estadoAtualRoteiro == ROTEIRO_FECHADO) {
			if (validaRoteiroEstadoFechado(roteiroTemp)) {
				// Nunca deve chegar aqui! Caso chegue...
				throw new EdicaoRoteiroException(
						"Roteiros fechados não podem ser editados!");
			}
		} else {
			throw new EdicaoRoteiroException(MsgErros.VALORINVALIDO
					.msg("A edição do roteiro não pôde ser realizada!"));
		}

		// As frases de sucesso são implementadas na GUI, por isso mantemos
		// essa flag aqui. Para termos uma certeza de que, mesmo sem gui, o
		// roteiro foi atualizado com sucesso
		System.out.println("Roteiro atualizado com sucesso!");
		
		//TODO o save nao atualiza objetos e sim salva, para atualizar tem que usar o comando update
		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}

	private boolean validaDirTestesEInterface(Roteiro roteiro)
			throws EdicaoRoteiroException {
		
		String testesDirDefault = "periodo" + roteiro.getPeriodo().toString()
				+ "/testes/roteiroID_" + roteiro.getId() + "/";
		String interfaceDirDefault = "periodo"
				+ roteiro.getPeriodo().toString() + "/interface/roteiroID_"
				+ roteiro.getId() + "/";

		if ((roteiro.getDiretorioTestes() != null && !roteiro
				.getDiretorioTestes().equals(""))
				&& !roteiro.getDiretorioTestes().equals(testesDirDefault)) {
			throw new EdicaoRoteiroException(
					"Hierarquia de Diretórios de Testes Automáticos diferente do default: '/periodo<periodo>/testes/roteiroID_<numero>/'. O Roteiro não pôde ser atualizado!");
		} else if ((roteiro.getDiretorioInterface() != null && !roteiro
				.getDiretorioInterface().equals(""))
				&& !roteiro.getDiretorioInterface().equals(interfaceDirDefault)) {
			throw new EdicaoRoteiroException(
					"Hierarquia de Diretórios da Interface diferente do default: '/periodo<periodo>/interface/roteiroID_<numero>/'. O Roteiro não pôde ser atualizado!");
		}
		return true;
	}

	protected int computaEstadoRoteiro(Roteiro roteiroTemp) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		if (roteiroTemp.getDataLiberacao() == null || roteiroTemp.getId() == 0) {
			return ROTEIRO_EM_CRIACAO;
		} else if (roteiroTemp.isBloqueado()
				|| calendar.getTime().before(roteiroTemp.getDataLiberacao())) {
			return ROTEIRO_CRIADO;
		} else if (calendar.getTime().after(roteiroTemp.getDataLiberacao())
				&& calendar.getTime().before(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_LIBERADO;
		} else if (calendar.getTime().after(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_FECHADO;
		} else {
			return ESTADO_INEXISTENTE;
		}
	}

	public Roteiro bloquearRoteiro(Roteiro roteiro) {

		// Apenas para garantias que houve sucesso
		System.out.println("Roteiro " + roteiro.getNome()
				+ " bloqueado com sucesso!");

		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiro);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}
	
	public Roteiro desbloquearRoteiro(Roteiro roteiro) {

		// Apenas para garantias que houve sucesso
		System.out.println("Roteiro " + roteiro.getNome()
				+ " desbloqueado com sucesso!");

		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiro);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}

	public Roteiro liberarRoteiro(Roteiro roteiro)
			throws LiberaRoteiroException {

		Date dataAtual = Calendar.getInstance().getTime();

		if (roteiro.getDataLiberacao().before(dataAtual)
				&& (roteiro.getDiretorioInterface() == null
						|| roteiro.getNumeroMaximoParticipantes() <= 0
						|| roteiro.getNumeroMaximoEnvios() <= 0 || ((roteiro
						.getPorcentagemTestesAutomaticos() > 0 || roteiro
						.getPorcentagemTestesAutomaticos() <= 100) && roteiro
						.getDiretorioTestes() == null))) {
			throw new LiberaRoteiroException(
					"O Roteiro "
							+ roteiro.getNome()
							+ " não pôde ser liberado devido a falhas em sua especificação!");
		} else if (roteiro.isBloqueado()) {
			throw new LiberaRoteiroException("Roteiro bloquado para liberação!");
		}

		else {

			// Apenas para garantias que houve sucesso
			System.out.println("Roteiro " + roteiro.getNome()
					+ " liberado com sucesso!");

			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiro);
			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		}
	}

	/*
	 * Metodos de validacao de Roteiros
	 */

	public boolean validaRoteiroEmCriacaoEAlteracao(Roteiro roteiro)
			throws CriacaoRoteiroException {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		if (roteiro.getNome() == null || roteiro.getNome().equals("")) {
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO
					.msg("Nome da atividade inválido. O Roteiro não pôde ser "
							+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataLiberacao() == null
				|| roteiro.getDataLiberacao().before(calendar.getTime())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data de Liberação inválida. O roteiro não pôde ser ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataFinalEntrega() != null
				&& roteiro.getDataFinalEntrega().before(
						roteiro.getDataLiberacao())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Entrega anterior à Data de Liberação. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataFinalDiscussao() != null
				&& roteiro.getDataFinalDiscussao().before(
						roteiro.getDataFinalEntrega())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Discussão anterior à Data Limite para Entrega. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getNumeroMaximoParticipantes() != null
				&& roteiro.getNumeroMaximoParticipantes() <= 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("O número máximo de integrantes deve ser sempre maior ou igual a 1 integrante. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getNumeroMaximoEnvios() != null
				&& roteiro.getNumeroMaximoEnvios() <= 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("O número máximo de envios deve ser sempre maior ou igual a 1. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiro.getPenalidadeDiasAtraso() < 0.0
				|| roteiro.getPenalidadeDiasAtraso() > 10.0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("A Penalidade por dia de atraso deve ser sempre maior ou igual a 0,0 e menor ou igual a 10,0. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& (roteiro.getPorcentagemTestesAutomaticos() < 0 || roteiro
						.getPorcentagemTestesAutomaticos() > 100)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("A Porcentagem automática da avaliação deve ser sempre >= 0 e <= 100. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if ((roteiro.getTempoLimiteTestes() != null && roteiro
				.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT)
				&& ((roteiro.getPorcentagemTestesAutomaticos() > 0 && roteiro
						.getPorcentagemTestesAutomaticos() <= 100) && roteiro
						.getTempoLimiteTestes() <= 0)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Time-limit de execução dos testes por método inválido, deve ser sempre > 0 quando a Porcentagem Automática de Avaliação for > 0 e <= 100. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if ((roteiro.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT && roteiro
				.getTempoLimiteTestes() != null)
				&& (roteiro.getPorcentagemTestesAutomaticos() == 0 && roteiro
						.getTempoLimiteTestes() != 0)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Se a Porcentagem Automática da Avaliação é 0, o Time-limit dos testes por método deve ser também 0. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else {
			return true;
		}

	}

	public boolean validaRoteiroEstadoCriado(Roteiro roteiro)
			throws EdicaoRoteiroException, CampoVazioException {
		try {
			return validaRoteiroEmCriacaoEAlteracao(roteiro);
		} catch (CriacaoRoteiroException e) {
			throw new EdicaoRoteiroException(e.getMessage());
		}
	}

	public boolean validaRoteiroEstadoLiberado(Roteiro roteiro)
			throws EdicaoRoteiroException {

//		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiro);
//		Roteiro roteiroNoBD = DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
//
//		if (!roteiro.getNome().equals(roteiroNoBD.getNome())) {
//			throw new EdicaoRoteiroException(
//					MsgErros.ERRO_ALTERACAO
//							.msg("Não é possível modificar o nome do roteiro após sua liberação!"));
//		} else if (!roteiro.getDataLiberacao().equals(
//				roteiroNoBD.getDataLiberacao())) {
//			throw new EdicaoRoteiroException(
//					MsgErros.ERRO_ALTERACAO
//							.msg("Não é possível modificar a data de liberação após a liberação já haver ocorrido!"));
//		}

		return true;
	}

	public boolean validaRoteiroEstadoFechado(Roteiro roteiro)
			throws EdicaoRoteiroException {

		Calendar calendar = Calendar.getInstance();

		if (roteiro.getDataFinalEntrega().before(calendar.getTime())) {
			throw new EdicaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Não é possível editar um Roteiro após a data de entrega do mesmo!"));
		} else {
			return true;
		}
	}
	
	public List<Roteiro> getRoteirosLiberados(){
		Date dataAtual = easyCorrectionUtil.getDataNow(); 
		return DAOFactory.DEFAULT.buildRoteiroDAO().findByRoteiroLiberado(dataAtual);
	}
}
