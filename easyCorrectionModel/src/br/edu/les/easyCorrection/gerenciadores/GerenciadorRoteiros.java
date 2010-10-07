package br.edu.les.easyCorrection.gerenciadores;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.CampoVazioException;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;

public class GerenciadorRoteiros {

	public final double PENALIDADE_DIA_ATRASO_DEFAULT = -1;
	public final double PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT = -1;

	public final int ESTADO_INEXISTENTE = 0;
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

		if (validaRoteiroEmCriacao(roteiroTemp)) {
			int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);

			// As frases de sucesso s�o implementadas na GUI, por isso mantemos
			// essa flag aqui. Para termos uma certeza de que, mesmo sem gui, o
			// roteiro foi criado com sucesso
			System.out.println("Roteiro criado com sucesso!");

			return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
		} else {
			throw new CriacaoRoteiroException("Roteiro n�o p�de ser criado");
		}
	}

	public Roteiro editarRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException, CampoVazioException {

		int estadoAtualRoteiro = computaEstadoRoteiro(roteiroTemp);
		this.criacaoOuAtualizacaoMsg = "atualizado";

		if (estadoAtualRoteiro == ROTEIRO_CRIADO) {
			if (validaRoteiroEstadoCriado(roteiroTemp)) {
				// OK, passou pelos casos de excecao, pode seguir em frente! =)
			}
		} else if (estadoAtualRoteiro == ROTEIRO_LIBERADO) {
			if (validaRoteiroEstadoLiberado(roteiroTemp)
					&& validaRoteiroEstadoCriado(roteiroTemp)) {
				// OK, passou pelos casos de excecao, pode seguir em frente! =)
			}
		} else if (estadoAtualRoteiro == ROTEIRO_FECHADO) {
			if (validaRoteiroEstadoFechado(roteiroTemp)) {
				// Nunca deve chegar aqui! Caso chegue...
				throw new EdicaoRoteiroException(
						"Roteiros fechados n�o podem ser editados!");
			}
		} else {
			throw new EdicaoRoteiroException(MsgErros.OPER_NAO_REALIZADA
					.msg("A edi��o do roteiro n�o p�de ser realizada!"));
		}

		// As frases de sucesso s�o implementadas na GUI, por isso mantemos
		// essa flag aqui. Para termos uma certeza de que, mesmo sem gui, o
		// roteiro foi atualizado com sucesso
		System.out.println("Roteiro atualizado com sucesso!");

		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}

	private int computaEstadoRoteiro(Roteiro roteiroTemp) {
		Date dataAtual = new Date();
		if (roteiroTemp.isBloqueado()
				|| dataAtual.before(roteiroTemp.getDataLiberacao())) {
			return ROTEIRO_CRIADO;
		} else if (dataAtual.after(roteiroTemp.getDataLiberacao())
				&& dataAtual.before(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_LIBERADO;
		} else if (dataAtual.after(roteiroTemp.getDataFinalEntrega())) {
			return ROTEIRO_FECHADO;
		} else {
			return ESTADO_INEXISTENTE;
		}
	}

	/*
	 * REVER ISSO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	public Roteiro bloquearRoteiro(Roteiro roteiro) {

		// Apenas para garantias que houve sucesso
		System.out.println("Roteiro " + roteiro.getNome()
				+ " bloqueado com sucesso!");

		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiro);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}

	public Roteiro liberarRoteiro(Roteiro roteiro)
			throws LiberaRoteiroException {

		Date dataAtual = new Date();

		if (roteiro.getDataLiberacao().equals(dataAtual)
				&& (roteiro.getDiretorioInterface() == null
						|| roteiro.getNumeroMaximoParticipantes() <= 0
						|| roteiro.getNumeroMaximoEnvios() <= 0 || ((roteiro
						.getPorcentagemTestesAutomaticos() > 0 || roteiro
						.getPorcentagemTestesAutomaticos() <= 100) && roteiro
						.getDiretorioTestes() == null))) {
			throw new LiberaRoteiroException(
					"Roteiro "
							+ roteiro.getNome()
							+ " n�o p�de ser liberado! Roteiro n�o p�de ser liberado na data "
							+ roteiro.getDataLiberacao()
							+ ", devido a falhas em sua especifica��o."
							+ System.getProperty("line.separator")
							+ "Ver descri��o do roteiro aqui <link>."
							+ System.getProperty("line.separator")
							+ System.getProperty("line.separator")
							+ "Easy Lab Correction");
		} else if (roteiro.isBloqueado()) {
			throw new LiberaRoteiroException("Roteiro bloquado para libera��o!");
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

	public boolean validaRoteiroEmCriacao(Roteiro roteiro)
			throws CriacaoRoteiroException {

		// System.out.println("++++ROTEIRO+++");
		// System.out.println("Nome: " + roteiro.getNome());
		// System.out.println("Liberacao:  " + roteiro.getDataLiberacao());
		// System.out.println("Descricao: " + roteiro.getDescricao());
		// System.out.println("Entrega: " + roteiro.getDataFinalEntrega());
		// System.out
		// .println("Data Discussao: " + roteiro.getDataFinalDiscussao());
		// System.out.println("Max Envios: " + roteiro.getNumeroMaximoEnvios());
		// System.out.println("Max Participantes: "
		// + roteiro.getNumeroMaximoParticipantes());
		// System.out.println("Porcento Testes: "
		// + roteiro.getPorcentagemTestesAutomaticos());
		// System.out.println("Penalidades: " +
		// roteiro.getPenalidadeDiasAtraso());
		// System.out.println("TimeLimit Testes: "
		// + roteiro.getTempoLimiteTestes());
		// System.out.println("Dir Testes: " + roteiro.getDiretorioTestes());
		// System.out.println("Dir Interface: " +
		// roteiro.getDiretorioInterface());
		// System.out.println();

		Date dataAtual = new Date();

		if (roteiro.getNome() == null || roteiro.getNome().equals("")) {
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO
					.msg("Nome da atividade inv�lido. O Roteiro n�o p�de ser "
							+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataLiberacao() == null
				|| roteiro.getDataLiberacao().before(dataAtual)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data de Libera��o inv�lida. O roteiro n�o p�de ser ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataFinalEntrega() != null
				&& roteiro.getDataFinalEntrega().before(
						roteiro.getDataLiberacao())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Entrega anterior � Data de Libera��o. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getDataFinalDiscussao() != null
				&& roteiro.getDataFinalDiscussao().before(
						roteiro.getDataFinalEntrega())) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Data Limite para Discuss�o anterior � Data Limite para Entrega. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getNumeroMaximoParticipantes() != null
				&& roteiro.getNumeroMaximoParticipantes() <= 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("O n�mero m�ximo de integrantes deve ser sempre maior ou igual a 1 integrante. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getNumeroMaximoEnvios() != null
				&& roteiro.getNumeroMaximoEnvios() <= 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("O n�mero m�ximo de envios deve ser sempre maior ou igual a 1. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getPenalidadeDiasAtraso() != PENALIDADE_DIA_ATRASO_DEFAULT
				&& roteiro.getPenalidadeDiasAtraso() < 0.0
				|| roteiro.getPenalidadeDiasAtraso() > 10.0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("A Penalidade por dia de atraso deve ser sempre maior ou igual a 0,0 e menor ou igual a 10,0. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& (roteiro.getPorcentagemTestesAutomaticos() < 0 || roteiro
						.getPorcentagemTestesAutomaticos() > 100)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("A Porcentagem autom�tica da avalia��o deve ser sempre >= 0 e <= 100. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getTempoLimiteTestes() != null
				&& ((roteiro.getPorcentagemTestesAutomaticos() > 0 && roteiro
						.getPorcentagemTestesAutomaticos() <= 1)
				&& roteiro.getTempoLimiteTestes() < 0)) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Time-limit de execu��o dos testes por m�todo inv�lido, deve ser sempre > 0 quando a Porcentagem Autom�tica de Avalia��o for > 0 e <= 100. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));

		} else if (roteiro.getPorcentagemTestesAutomaticos() != PORCENTAGEM_TESTES_AUTOMATICOS_DEFAULT
				&& roteiro.getTempoLimiteTestes() != null
				&& roteiro.getPorcentagemTestesAutomaticos() == 0
				&& roteiro.getTempoLimiteTestes() > 0) {
			throw new CriacaoRoteiroException(
					MsgErros.VALORINVALIDO
							.msg("Se a Porcentagem Autom�tica da Avalia��o � 0, o Time-limit dos testes por m�todo deve ser tamb�m 0. O Roteiro n�o p�de ser "
									+ criacaoOuAtualizacaoMsg + "!"));
		} else if ((roteiro.getDiretorioTestes() != null && roteiro
				.getDiretorioTestes().equals(""))
				&& !checkDiretorioTestesFileExtension(new File(roteiro
						.getDiretorioTestes()))) {
			throw new CriacaoRoteiroException(
					"Alguns arquivos de Testes Autom�ticos n�o possu�am a extens�o .java. O Roteiro n�o p�de ser "
							+ criacaoOuAtualizacaoMsg + "!");

		} else if ((roteiro.getDiretorioInterface() != null && roteiro
				.getDiretorioInterface().equals(""))
				&& !checkDiretorioInterfaceFileExtension(roteiro
						.getDiretorioInterface())) {
			throw new CriacaoRoteiroException(
					"Formato do arquivo da interface deve ser .java. O Roteiro n�o p�de ser "
							+ criacaoOuAtualizacaoMsg + "!");

		} else {
			return true;
		}

	}

	private boolean checkDiretorioTestesFileExtension(File testeDirectory) {

		File[] allFiles = testeDirectory.listFiles();

		for (File file : allFiles) {
			if (file.isDirectory()) {
				return checkDiretorioTestesFileExtension(file);
			} else if (file.isFile()
					&& !file.getAbsolutePath().endsWith(".java")) {
				return false;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean checkDiretorioInterfaceFileExtension(String testeFileName) {

		File f = new File(testeFileName);

		if (f.isFile()) {
			if (testeFileName.endsWith(".java")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean validaRoteiroEstadoCriado(Roteiro roteiro)
			throws EdicaoRoteiroException, CampoVazioException {
		try {
			return validaRoteiroEmCriacao(roteiro);
		} catch (CriacaoRoteiroException e) {
			throw new EdicaoRoteiroException(e.getMessage());
		}
	}

	public boolean validaRoteiroEstadoLiberado(Roteiro roteiro)
			throws EdicaoRoteiroException {

		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiro);
		Roteiro roteiroNoBD = DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);

		if (!roteiro.getNome().equals(roteiroNoBD.getNome())) {
			throw new EdicaoRoteiroException(
					MsgErros.ERRO_ALTERACAO
							.msg("N�o � poss�vel modificar o nome do roteiro ap�s sua libera��o!"));
		} else if (!roteiro.getDataLiberacao().equals(
				roteiroNoBD.getDataLiberacao())) {
			throw new EdicaoRoteiroException(
					MsgErros.ERRO_ALTERACAO
							.msg("N�o � poss�vel modificar a data de libera��o ap�s a libera��o j� haver ocorrido!"));
		}

		return true;
	}

	public boolean validaRoteiroEstadoFechado(Roteiro roteiro)
			throws EdicaoRoteiroException {

		Date dataAtual = new Date();

		if (!roteiro.getDataFinalEntrega().before(dataAtual)) {
			throw new EdicaoRoteiroException(
					MsgErros.ERRO_ALTERACAO
							.msg("N�o � poss�vel editar um Roteiro ap�s a data de entrega do mesmo!"));
		} else {
			return true;
		}
	}

}
