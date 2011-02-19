package br.edu.les.easyCorrection.gerenciadores;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorRoteiros extends Gerenciador {

	/*
	 * Computacao dos estados de um Roteiro
	 */
	private final int ESTADO_INEXISTENTE = -1;

	/*
	 * Estado prévio à criacao do roteiro
	 */
	private final int ROTEIRO_EM_CRIACAO = 0;
	/*
	 * Estado posterior a criacao do roteiro, mas anterior a data de liberacao
	 * deste
	 */
	private final int ROTEIRO_JAH_CRIADO = 1;
	/*
	 * Estado posterior a data de liberacao do roteiro, mas anterior a data de
	 * fechamento (entrega) deste
	 */
	private final int ROTEIRO_LIBERADO = 2;
	/*
	 * Estado posterior a data de entrega do roteiro
	 */
	private final int ROTEIRO_FECHADO = 3;

	/*
	 * Estado posterior a data final para correcao
	 */
	private final int ROTEIRO_CORRIGIDO = 4;

	private final double PENALIDADE_DIA_ATRASO_DEFAULT = -1;
	private final double PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT = -1;

	/*
	 * String a qual receberah o nome do metodo chamado: - Se foi
	 * cadastraRoteiro, recebe "criado" - Se foi editaRoteiro, recebe
	 * "atualizado"
	 * 
	 * Isso permite-nos reusar o codigo de validacao dos roteiros
	 */
	private String criacaoOuAtualizacaoMsg = "NULL";

	public Roteiro getRoteiro(int roteiroId) {
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(roteiroId);
	}

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}

	public List<Roteiro> getRoteirosLiberados() {
		Date dataAtual = easyCorrectionUtil.getDataNow();
		List<Roteiro> roteirosLiberados = DAOFactory.DEFAULT.buildRoteiroDAO()
				.findByRoteiroLiberado(dataAtual);
		return roteirosLiberados;
	}

	public Roteiro getRoteiroLiberado(Integer id) {
		Date dataAtual = easyCorrectionUtil.getDataNow();
		List<Roteiro> lista = DAOFactory.DEFAULT.buildRoteiroDAO()
				.findByRoteiroLiberado(dataAtual, id);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp)
			throws CriacaoRoteiroException, LiberaRoteiroException {

		if (roteiroTemp == null) {
			throw new CriacaoRoteiroException("Roteiro inexistente!");
		}

		this.criacaoOuAtualizacaoMsg = "criado";

		// Estes campos podem vir null, soh serao usados apos a primeira edicao
		// (quando serah adicionado o caminho no servidor)
		roteiroTemp.setDiretorioTestes("");
		roteiroTemp.setDiretorioInterface("");
		
		if (validaRoteiroEmCriacao(roteiroTemp)) {
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);

			System.out.println("O Roteiro " + roteiroTemp.getNome()
					+ " foi criado com sucesso!");

			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		} else {
			throw new CriacaoRoteiroException("Roteiro não pôde ser criado");
		}
	}

	public Roteiro editarRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException, CriacaoRoteiroException,
			LiberaRoteiroException {

		if (roteiroTemp == null) {
			throw new EdicaoRoteiroException("Roteiro inexistente!");
		}

		this.criacaoOuAtualizacaoMsg = "atualizado";
		
		int estadoAtualRoteiro = computaEstadoNovoRoteiro(roteiroTemp);

		switch (estadoAtualRoteiro) {
		case (ROTEIRO_EM_CRIACAO):
			// Esse caso nunca deve ocorrer, pois nesse estado deve haver o
			// cadastro de roteiros e não a edição
			validaRoteiroEmCriacao(roteiroTemp);
			break;

		case (ROTEIRO_JAH_CRIADO):
			validaRoteiroJahCriado(roteiroTemp);
			break;

		case (ROTEIRO_LIBERADO):
			validaRoteiroEstadoLiberado(roteiroTemp);
			break;

		case (ROTEIRO_FECHADO):
			validaRoteiroEstadoFechado(roteiroTemp);
			break;

		case (ROTEIRO_CORRIGIDO):
			validaRoteiroEstadoCorrigido(roteiroTemp);
			break;

		default:
			throw new EdicaoRoteiroException(MsgErros.VALORINVALIDO
					.msg("A edição do roteiro não pôde ser realizada!"));
		}

		// OK, passou pelos casos de excecao, pode seguir em frente! =)
		Roteiro r;

		try {
			r = getRoteiro(roteiroTemp.getId());
			r = (Roteiro) SwapperAtributosReflect.swapObject(r, roteiroTemp,
					Roteiro.class);
			DAOFactory.DEFAULT.buildRoteiroDAO().update(r);

			System.out.println("Roteiro atualizado com sucesso!");

		} catch (EasyCorrectionException e) {
			throw new EdicaoRoteiroException("Roteiro inexistente!");
		}
		return r;
	}

	/*
	 * Nao estah sendo usado ainda... E nao estah em nossos planos usa-lo...
	 */
	public void excluirRoteiro(Roteiro roteiro) throws ExclusaoRoteiroException {

		if (roteiro == null) {
			throw new ExclusaoRoteiroException("Roteiro inexistente!");
		}
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	/* ************************************************************
	 * METODOS PRIVADOS
	 * ************************************************************
	 */

	private int computaEstadoNovoRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException {

		// Antes de Computar o estado do novo roteiro, vamos checar se houve
		// alguma modificacao com relacao ao antigo estado e aplicar as devidas
		// restricoes
		if (roteiroTemp == null) {
			throw new EdicaoRoteiroException(
					"Roteiro invalido. Tente Novamente...");
		}

		this.checaModificacaoDatas(roteiroTemp);
		return getEstadoAtualRoteiro(roteiroTemp);
	}

	private void checaModificacaoDatas(Roteiro roteiroNovo)
			throws EdicaoRoteiroException {

		// Tempo nesse instante
		Date dataHoje = easyCorrectionUtil.getDataNow();

		Roteiro roteiroAntigo = this.getRoteiro(roteiroNovo.getId());

		int estadoRoteiroAntigo = (roteiroAntigo == null) ? ROTEIRO_EM_CRIACAO
				: getEstadoAtualRoteiro(roteiroAntigo);

		switch (estadoRoteiroAntigo) {
		case (ROTEIRO_EM_CRIACAO):
		case (ROTEIRO_JAH_CRIADO):
			// LIBERAR, ENTREGA E DISCUSSAO PODEM SER MUDADAS
			// Mas soh para o dia atual ou depois
			if (roteiroNovo.getDataLiberacao() == null
					|| roteiroNovo.getDataLiberacao().before(dataHoje)) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pôde ser ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalEntrega() != null
					&& roteiroNovo.getDataFinalEntrega().before(dataHoje)) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalDiscussao() != null
					&& roteiroNovo.getDataFinalDiscussao().before(dataHoje)) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}

			break;

		case (ROTEIRO_LIBERADO):
			// ENTREGA E DISCUSSAO PODEM SER MUDADAS
			// Mas soh para o dia atual ou depois
			// LIBERAR: se nao for modificada: mantem a antiga
			// se for modificada: soh para o dia atual ou depois

			if (roteiroNovo.getDataLiberacao() == null
					|| (roteiroNovo.getDataLiberacao().before(dataHoje) && (!roteiroNovo
							.getDataLiberacao().equals(
									roteiroAntigo.getDataLiberacao())))) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pôde ser ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalEntrega() != null
					&& roteiroNovo.getDataFinalEntrega().before(dataHoje)) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalDiscussao() != null
					&& roteiroNovo.getDataFinalDiscussao().before(dataHoje)) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}
			break;

		case (ROTEIRO_FECHADO):
			// DISCUSSAO PODEM SER MUDADAS
			// Mas soh para o dia atual ou depois
			// LIBERAR E ENTREGA
			// se nao for modificada: mantem a antiga
			// se for modificada: soh para o dia atual ou depois

			if (roteiroNovo.getDataLiberacao() == null
					|| (roteiroNovo.getDataLiberacao().before(dataHoje) && (!roteiroNovo
							.getDataLiberacao().equals(
									roteiroAntigo.getDataLiberacao())))) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pôde ser ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalEntrega() == null
					|| (roteiroNovo.getDataFinalEntrega().before(dataHoje) && (!roteiroNovo
							.getDataFinalEntrega().equals(
									roteiroAntigo.getDataFinalEntrega())))) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalDiscussao() != null
					&& roteiroNovo.getDataFinalDiscussao().before(dataHoje)) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}
			break;

		case (ROTEIRO_CORRIGIDO):
			// LIBERAR, ENTREGA E DISCUSSAO
			// se nao for modificada: mantem a antiga
			// se for modificada: soh para o dia atual ou depois

			if (roteiroNovo.getDataLiberacao() == null
					|| (roteiroNovo.getDataLiberacao().before(dataHoje) && (!roteiroNovo
							.getDataLiberacao().equals(
									roteiroAntigo.getDataLiberacao())))) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data de Liberação inválida. O roteiro não pôde ser ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalEntrega() == null
					|| (roteiroNovo.getDataFinalEntrega().before(dataHoje) && (!roteiroNovo
							.getDataFinalEntrega().equals(
									roteiroAntigo.getDataFinalEntrega())))) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Entrega inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			} else if (roteiroNovo.getDataFinalDiscussao() == null
					|| (roteiroNovo.getDataFinalDiscussao().before(dataHoje) && (!roteiroNovo
							.getDataFinalDiscussao().equals(
									roteiroAntigo.getDataFinalDiscussao())))) {
				throw new EdicaoRoteiroException(
						MsgErros.VALORINVALIDO
								.msg("Data Limite para Discussão inválida. O Roteiro não pôde ser "
										+ criacaoOuAtualizacaoMsg + "!"));

			}

			break;

		default:
			break;
		}

		if (roteiroNovo.getDataFinalEntrega() != null
				&& roteiroNovo.getDataLiberacao() != null
				&& (roteiroNovo.getDataFinalEntrega().before(
						roteiroNovo.getDataLiberacao()) || roteiroNovo
						.getDataFinalEntrega().equals(
								roteiroNovo.getDataLiberacao()))) {
			throw new EdicaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Entrega anterior/igual à Data de Liberação. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiroNovo.getDataFinalDiscussao() != null
				&& roteiroNovo.getDataFinalEntrega() != null
				&& (roteiroNovo.getDataFinalDiscussao().before(
						roteiroNovo.getDataFinalEntrega()) || roteiroNovo
						.getDataFinalDiscussao().equals(
								roteiroNovo.getDataFinalEntrega()))) {
			throw new EdicaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Discussão anterior/igual à Data Limite para Entrega. O Roteiro não pôde ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		}
	}

	private int getEstadoAtualRoteiro(Roteiro roteiro) {
		// Tempo nesse instante
		Date tempoAtualExato = Calendar.getInstance().getTime();

		if (roteiro.getDataLiberacao() == null || roteiro.getId() == 0) {
			return ROTEIRO_EM_CRIACAO;
		} else if (tempoAtualExato.before(roteiro.getDataLiberacao())) {
			return ROTEIRO_JAH_CRIADO;
		} else if (tempoAtualExato.after(roteiro.getDataLiberacao())
				&& tempoAtualExato.before(roteiro.getDataFinalEntrega())) {
			return ROTEIRO_LIBERADO;
		} else if (tempoAtualExato.after(roteiro.getDataFinalEntrega())) {
			return ROTEIRO_FECHADO;
		} else if (tempoAtualExato.after(roteiro.getDataFinalDiscussao())) {
			return ROTEIRO_CORRIGIDO;
		} else {
			return ESTADO_INEXISTENTE;
		}
	}

	/*
	 * Metodos de Validacao de Roteiros para Criação e Edição
	 */

	private boolean validaRoteiroEmCriacao(Roteiro roteiro)
			throws CriacaoRoteiroException {

		return this.validacoesBasicasDosAtributosDoRoteiro(roteiro);

	}

	private boolean validaRoteiroJahCriado(Roteiro roteiro)
			throws CriacaoRoteiroException, EdicaoRoteiroException {

		// Os testes de JAH_CRIADO são somados aos de EM_CRIACAO
		validaRoteiroEmCriacao(roteiro);

		String testesDirDefault = "/periodo" + roteiro.getPeriodo().toString()
				+ "/testes/" + roteiro.getId() + "/";
		String interfaceDirDefault = "/periodo"
				+ roteiro.getPeriodo().toString() + "/interface/"
				+ roteiro.getId() + "/";

		// E checa se o caminho do servidor para as pastas de testes e interface
		// estah/mantem-se correto
		if ((roteiro.getDiretorioTestes() != null && !roteiro
				.getDiretorioTestes().equals(""))
				&& !roteiro.getDiretorioTestes().endsWith(testesDirDefault)) {
			throw new EdicaoRoteiroException(
					"Hierarquia de Diretórios de Testes Automáticos diferente do default: '/periodo<periodo>/testes/<roteiro_id>/'. O Roteiro não pôde ser atualizado!");
		} else if ((roteiro.getDiretorioInterface() != null && !roteiro
				.getDiretorioInterface().equals(""))
				&& !roteiro.getDiretorioInterface().endsWith(
						interfaceDirDefault)) {
			throw new EdicaoRoteiroException(
					"Hierarquia de Diretórios da Interface diferente do default: '/periodo<periodo>/interface/<roteiro_id>/'. O Roteiro não pôde ser atualizado!");
		}

		return true;
	}

	private boolean validaRoteiroEstadoLiberado(Roteiro roteiro)
			throws EdicaoRoteiroException, CriacaoRoteiroException,
			LiberaRoteiroException {

		validacoesBasicasDosAtributosDoRoteiro(roteiro);

		// E checa se este possui as caracteristicas ideais para ser/manter-se
		// liberado
		if (roteiro.getDiretorioInterface() == null
				|| roteiro.getNumeroMaximoParticipantes() <= 0
				|| roteiro.getNumeroMaximoEnvios() <= 0
				|| ((roteiro.getPorcentagemTestesAutomaticos() > 0 || roteiro
						.getPorcentagemTestesAutomaticos() <= 100) && roteiro
						.getDiretorioTestes() == null)) {
			throw new LiberaRoteiroException(
					"O Roteiro "
							+ roteiro.getNome()
							+ " não pôde ser liberado devido a falhas em sua especificação!");
		}
		return true;
	}

	/**
	 * Um Roteiro fechado não pode ser editado
	 * 
	 * @param roteiro
	 * @return
	 * @throws EdicaoRoteiroException
	 * @throws CriacaoRoteiroException
	 */
	private boolean validaRoteiroEstadoFechado(Roteiro roteiro)
			throws CriacaoRoteiroException {

		return validacoesBasicasDosAtributosDoRoteiro(roteiro);
	}

	private boolean validaRoteiroEstadoCorrigido(Roteiro roteiro)
			throws CriacaoRoteiroException {

		return validacoesBasicasDosAtributosDoRoteiro(roteiro);

	}

	private boolean validacoesBasicasDosAtributosDoRoteiro(Roteiro roteiro)
			throws CriacaoRoteiroException {

		if (roteiro.getNome() == null || roteiro.getNome().equals("")) {
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO
					.msg("Nome da atividade inválido. O Roteiro não pôde ser "
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

}