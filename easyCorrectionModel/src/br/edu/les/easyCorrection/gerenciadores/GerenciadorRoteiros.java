package br.edu.les.easyCorrection.gerenciadores;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.BloqueiaRoteiroException;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
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
	public final int ROTEIRO_JAH_CRIADO = 1;
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

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}

	/*
	 * Pseudo codigo do metodo:
	 * 
	 * valida_roteiro_antes_da_cria save_changes() exception
	 */
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp)
			throws CriacaoRoteiroException {

		if (roteiroTemp == null) {
			throw new CriacaoRoteiroException("Roteiro inexistente!");
		}

		this.criacaoOuAtualizacaoMsg = "criado";

		if (validaRoteiroEmCriacao(roteiroTemp)) {
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);

			System.out.println("Roteiro criado com sucesso!");

			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		} else {
			throw new CriacaoRoteiroException("Roteiro não pôde ser criado");
		}
	}

	public Roteiro editarRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException, CriacaoRoteiroException {

		if (roteiroTemp == null) {
			throw new EdicaoRoteiroException("Roteiro inexistente!");
		}

		int estadoAtualRoteiro = computaEstadoRoteiro(roteiroTemp);
		this.criacaoOuAtualizacaoMsg = "atualizado";

		if (estadoAtualRoteiro == ROTEIRO_EM_CRIACAO) {
			validaRoteiroEmCriacao(roteiroTemp);
		} else if (estadoAtualRoteiro == ROTEIRO_JAH_CRIADO) {
			validaRoteiroJahCriado(roteiroTemp);
		} else if (estadoAtualRoteiro == ROTEIRO_LIBERADO) {
			validaRoteiroEstadoLiberado(roteiroTemp);
		} else if (estadoAtualRoteiro == ROTEIRO_FECHADO) {
			validaRoteiroEstadoFechado(roteiroTemp);
		} else {
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
	 * Metodos de Validacao de Roteiros para Criação e Edição
	 */

	private boolean validaRoteiroEmCriacao(Roteiro roteiro)
			throws CriacaoRoteiroException {
		Date dataHojeZero = easyCorrectionUtil.getDataNow();

		if (roteiro.getNome() == null || roteiro.getNome().equals("")) {
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO
					.msg("Nome da atividade inválido. O Roteiro não pôde ser "
							+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataLiberacao() == null
				|| roteiro.getDataLiberacao().before(dataHojeZero)) {
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

	private boolean validaRoteiroJahCriado(Roteiro roteiro)
			throws CriacaoRoteiroException, EdicaoRoteiroException {

		// Os testes de JAH_CRIADO são somados aos de EM_CRIACAO
		validaRoteiroEmCriacao(roteiro);

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

	private boolean validaRoteiroEstadoLiberado(Roteiro roteiro)
			throws EdicaoRoteiroException, CriacaoRoteiroException {

		// Os testes de LIBERADO são somados aos de EM_CRIACAO e JAH_CRIADO
		validaRoteiroEmCriacao(roteiro);
		validaRoteiroJahCriado(roteiro);

		return true;
	}

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

	public Roteiro bloquearRoteiro(Roteiro roteiro, boolean bloqueia)
			throws BloqueiaRoteiroException {

		if (roteiro == null) {
			throw new BloqueiaRoteiroException("Roteiro inexistente!");
		}

		if (bloqueia) {
			if (roteiro.isBloqueado()) {
				throw new BloqueiaRoteiroException(
						"O Roteiro já está bloqueado!");
			} else {
				// Apenas para garantia que houve sucesso
				System.out.println("Roteiro " + roteiro.getNome()
						+ " bloqueado com sucesso!");
			}
		} else {
			if (!roteiro.isBloqueado()) {
				throw new BloqueiaRoteiroException(
						"O Roteiro já está desbloqueado!");
			} else {
				// Apenas para garantias que houve sucesso
				System.out.println("Roteiro " + roteiro.getNome()
						+ " desbloqueado com sucesso!");
			}
		}
		roteiro.setBloqueado(bloqueia);

		Roteiro r;

		try {
			r = getRoteiro(roteiro.getId());
			r = (Roteiro) SwapperAtributosReflect.swapObject(r, roteiro,
					Roteiro.class);
			DAOFactory.DEFAULT.buildRoteiroDAO().update(r);
		} catch (EasyCorrectionException e) {
			throw new BloqueiaRoteiroException("Roteiro inexistente!");
		}

		return r;
	}

	/*
	 * Esse método serah utilizado mais tarde!
	 */
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

	public List<Roteiro> getRoteirosLiberados() {
		Date dataAtual = easyCorrectionUtil.getDataNow();
		return DAOFactory.DEFAULT.buildRoteiroDAO().findByRoteiroLiberado(
				dataAtual);
	}

	public Roteiro getRoteiroLiberado(Integer id) {
		Date dataAtual = easyCorrectionUtil.getDataNow();
		List<Roteiro> lista = DAOFactory.DEFAULT.buildRoteiroDAO()
				.findByRoteiroLiberado(dataAtual, id);
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	public void excluirRoteiro(Roteiro roteiro) throws ExclusaoRoteiroException {

		if (roteiro == null) {
			throw new ExclusaoRoteiroException("Roteiro inexistente!");
		}
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	/*
	 * UTIL
	 */

	protected int computaEstadoRoteiro(Roteiro roteiroTemp) {

		Date dataTodayTimeZero = easyCorrectionUtil.getDataNowTimeZero(Calendar
				.getInstance().getTime());

		if (roteiroTemp.getDataLiberacao() == null || roteiroTemp.getId() == 0) {
			return ROTEIRO_EM_CRIACAO;
		} else if (roteiroTemp.isBloqueado()
				|| dataTodayTimeZero.before(roteiroTemp.getDataLiberacao())) {
			return ROTEIRO_JAH_CRIADO;
		} else if (dataTodayTimeZero.after(roteiroTemp.getDataLiberacao())
				&& dataTodayTimeZero.before(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_LIBERADO;
		} else if (dataTodayTimeZero.after(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_FECHADO;
		} else {
			return ESTADO_INEXISTENTE;
		}
	}
}