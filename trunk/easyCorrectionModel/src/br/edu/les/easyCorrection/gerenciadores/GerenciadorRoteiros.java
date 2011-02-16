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

	/*
	 * Pseudo codigo do metodo:
	 * 
	 * valida_roteiro_antes_da_cria save_changes() exception
	 */
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp)
			throws CriacaoRoteiroException, LiberaRoteiroException {

		if (roteiroTemp == null) {
			throw new CriacaoRoteiroException("Roteiro inexistente!");
		}

		this.criacaoOuAtualizacaoMsg = "criado";

		if (validaRoteiroInicializandoDiretorios(roteiroTemp)) {
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

		int estadoAtualRoteiro = computaEstadoRoteiro(roteiroTemp);
		this.criacaoOuAtualizacaoMsg = "atualizado";

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
			// Essa validacao eh desnecessaria, pois jah foi feita a checagem da
			// data na computacao do estado
			validaRoteiroEstadoFechado(roteiroTemp);

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

	private boolean validaRoteiroInicializandoDiretorios(Roteiro roteiro)
			throws CriacaoRoteiroException {

		// Estes campos podem vir null, soh serao usados apos a primeira edicao
		// (quando serah adicionado o caminho no servidor)
		roteiro.setDiretorioTestes("");
		roteiro.setDiretorioInterface("");

		return validaRoteiroEmCriacao(roteiro);
	}

	/*
	 * Metodos de Validacao de Roteiros para Criação e Edição
	 */

	private boolean validaRoteiroEmCriacao(Roteiro roteiro)
			throws CriacaoRoteiroException {

		this.validacoesGeraisDeRoteiroEditavel(roteiro);

		if (roteiro.getDataLiberacao() == null
				|| roteiro.getDataLiberacao().before(
						easyCorrectionUtil.getDataNow())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data de Liberação inválida. O roteiro não pôde ser ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		}

		return true;

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

		// Os testes de LIBERADO são apenas os Gerais
		validacoesGeraisDeRoteiroEditavel(roteiro);

//		if (roteiro.getDataLiberacao().before(easyCorrectionUtil.getDataNow())) {
//			// Se a data de Liberacao eh anterior a hoje, ela nao pode ser
//			// modificada, assim lemos a do banco e escrevemos novamente
//			roteiro.setDataLiberacao(this.getRoteiro(roteiro.getId())
//					.getDataLiberacao());
//		}

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
	 */
	private boolean validaRoteiroEstadoFechado(Roteiro roteiro)
			throws EdicaoRoteiroException {

		Date dataNow = Calendar.getInstance().getTime();

		if (roteiro.getDataFinalEntrega().before(dataNow)) {
			throw new EdicaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Não é possível editar um Roteiro após a data de entrega do mesmo!"));
		} else {
			return true;
		}
	}

	private boolean validacoesGeraisDeRoteiroEditavel(Roteiro roteiro)
			throws CriacaoRoteiroException {

		if (roteiro.getNome() == null || roteiro.getNome().equals("")) {
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO
					.msg("Nome da atividade inválido. O Roteiro não pôde ser "
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

	private int computaEstadoRoteiro(Roteiro roteiroTemp) {

		// Tempo nesse instante
		Date tempoAtualExato = Calendar.getInstance().getTime();

		// A data do roteiro eh setada da seguinte forma: dia XX as 12h, ops...

		if (roteiroTemp.getDataLiberacao() == null || roteiroTemp.getId() == 0) {
			return ROTEIRO_EM_CRIACAO;
		} else if (tempoAtualExato.before(roteiroTemp.getDataLiberacao())) {
			return ROTEIRO_JAH_CRIADO;
		} else if (tempoAtualExato.after(roteiroTemp.getDataLiberacao())
				&& tempoAtualExato.before(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_LIBERADO;
		} else if (tempoAtualExato.after(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_FECHADO;
		} else {
			return ESTADO_INEXISTENTE;
		}
	}

}