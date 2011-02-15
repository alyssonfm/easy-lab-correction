package br.edu.les.easyCorrection.gerenciadores;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.textui.TestRunner;

import org.junit.runner.JUnitCore;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.ExecucaoTestesException;
import br.edu.les.easyCorrection.exceptions.ObjetoNaoEncontradoException;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.servlet.ServletUpload;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorSubmissoes {

	private GerenciadorRoteiros gerenciadorRoteiros;
	private GerenciadorTestes gerenciadorTestes;

	public GerenciadorSubmissoes() {
		super();
		gerenciadorRoteiros = new GerenciadorRoteiros();
		gerenciadorTestes = new GerenciadorTestes();
	}

	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
			Integer idUsuario, Integer idRoteiro) {
		List<EquipeHasUsuarioHasRoteiro> lista = DAOFactory.DEFAULT
				.buildEquipeHasUsuarioHasRoteiroDAO().findByUsuarioERoteiro(
						idUsuario, idRoteiro);
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	public Equipe getEquipe(int id) {
		Equipe equipe = DAOFactory.DEFAULT.buildEquipeDAO().getById(id);
		if (easyCorrectionUtil.isNull(equipe)) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("equipe"));
		}
		return equipe;
	}

	public Equipe getEquipePorNome(String nome) {
		List<Equipe> equipes = DAOFactory.DEFAULT.buildEquipeDAO().findByNome(
				nome);
		Equipe eq = equipes.get(0);
		if (easyCorrectionUtil.isNull(eq)) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("equipe"));
		}
		return eq;
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
			Integer idEquipe, Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByEquipeERoteiro(idEquipe, idRoteiro);
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiro(
			Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiro(idRoteiro);
	}

	public EquipeHasUsuarioHasRoteiro mudarEquipe(EquipeHasUsuarioHasRoteiro eur)
			throws EasyCorrectionException {

		if (eur.getRoteiro().getNumeroMaximoParticipantes() == 1) {
			throw new ObjetoNaoEncontradoException(MsgErros.ROTEIRO_INDIVIDUAL
					.msg(""));
		}
		if (eur.getRoteiro().getNumeroMaximoParticipantes() == getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
				eur.getEquipe().getId(), eur.getRoteiro().getId()).size()) {
			String[] params = { eur.getEquipe().getNome(),
					eur.getRoteiro().getNumeroMaximoParticipantes().toString() };
			throw new ObjetoNaoEncontradoException(
					MsgErros.EQUIPE_HAS_ROTEIRO_COMPLETA.msg(params));
		}
		try {
			getEquipe(eur.getEquipe().getId());
		} catch (Exception e) {
			throw new ObjetoNaoEncontradoException(MsgErros.EQUIPE_INEXISTENTE
					.msg(""));
		}

		EquipeHasUsuarioHasRoteiro equipeUsuarioRoteiro = getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
				eur.getUsuario().getIdUsuario(), eur.getRoteiro().getId());
		if (!easyCorrectionUtil.isNull(equipeUsuarioRoteiro)) {
			excluiEquipeHasRoteiroHasUsuario(equipeUsuarioRoteiro);
			equipeUsuarioRoteiro = cadastraEquipeHasUsuarioHasRoteiro(eur);
		} else {
			equipeUsuarioRoteiro = cadastraEquipeHasUsuarioHasRoteiro(eur);

		}
		return equipeUsuarioRoteiro;
	}

	public int getEquipeAlocadas(Integer idRoteiro) {
		if (easyCorrectionUtil.isNull(idRoteiro) || idRoteiro < 1) {
			throw new ObjetoNaoEncontradoException(
					MsgErros.ID_ROTEIRO_INEXISTENTE.msg(""));
		}

		Roteiro roteiro = gerenciadorRoteiros.getRoteiro(idRoteiro);
		if (roteiro == null) {
			throw new ObjetoNaoEncontradoException(MsgErros.ROTEIRO_INEXISTENTE
					.msg(""));
		}

		List<EquipeHasUsuarioHasRoteiro> lista = DAOFactory.DEFAULT
				.buildEquipeHasUsuarioHasRoteiroDAO().findByRoteiro(idRoteiro);
		return lista.size();
	}

	public List<Equipe> getEquipes() {
		return DAOFactory.DEFAULT.buildEquipeDAO().findAll();
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiros() {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findAll();
	}

	public Integer numeroSubmissoes(Submissao submissao) {
		List<Submissao> lista = DAOFactory.DEFAULT.buildSubmissaoDAO()
				.findByEquipeERoteiro(
						submissao.getEquipeHasUsuarioHasRoteiro().getEquipe()
								.getId(),
						submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro()
								.getId());
		return lista.size();
	}
	
	public List<Submissao> getSubmissoesPorRoteiroEquipe(Roteiro rot, Equipe eq) {
		List<Submissao> lista = DAOFactory.DEFAULT.buildSubmissaoDAO()
				.findByEquipeERoteiro(eq.getId(),
						rot.getId());
		return lista;
	} 

	public Integer numeroSubmissoesPorEUR(EquipeHasUsuarioHasRoteiro eur) {
		return getSubmissoesPorRoteiroEquipe(eur.getRoteiro(), eur.getEquipe()).size();
	}

	public Submissao submeteRoteiro(Submissao submissao)
			throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(submissao)) {
			if (numeroSubmissoes(submissao) < (submissao
					.getEquipeHasUsuarioHasRoteiro().getRoteiro()
					.getNumeroMaximoEnvios())) {
				submissao.setDataSubmissao(easyCorrectionUtil.getDataNow());
				Integer id = DAOFactory.DEFAULT.buildSubmissaoDAO().save(
						submissao);
				submissao.setId(id);
			} else {
				throw new EasyCorrectionException(
						MsgErros.NUMERO_MAXIMO_SUBMISSOES_EXCEDIDO
								.msg(submissao.getEquipeHasUsuarioHasRoteiro()
										.getRoteiro().getNome()));
			}
		}
		return submissao;
	}

	public String primeiraOcorrencia(String[] listaArquivos) {
		try {
			String nomeArquivo = "";
			for (int i = 0; i < listaArquivos.length; i++) {
				nomeArquivo = listaArquivos[i];
				if (nomeArquivo.substring(nomeArquivo.length() - 4,
						nomeArquivo.length()).equals("java")) {
					return nomeArquivo;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public String primeiraOcorrenciaZip(String[] listaArquivos) {
		try {
			String nomeArquivo = "";
			for (int i = 0; i < listaArquivos.length; i++) {
				nomeArquivo = listaArquivos[i];
				if (nomeArquivo.substring(nomeArquivo.length() - 3,
						nomeArquivo.length()).equals("zip")) {
					return nomeArquivo;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public String[] parametrosCompilador(String diretorioLib,
			String diretorioSource, String diretorioInterface,
			String diretorioTestes, String[] listaArquivosSource,
			String[] listaArquivosInterface, String[] listaArquivosTestes) {

		List<String> params = new ArrayList<String>();
		params.add("-sourcepath");
		params.add(diretorioSource + ";" + diretorioInterface + ";"
				+ diretorioTestes);
		params.add("-classpath");
		params.add(diretorioLib + "junit.jar");
		String nomeArquivo = "";
		for (int i = 0; i < listaArquivosSource.length; i++) {
			nomeArquivo = listaArquivosSource[i];
			if (nomeArquivo.substring(nomeArquivo.length() - 4,
					nomeArquivo.length()).equals("java")) {
				params.add(diretorioSource + nomeArquivo);
			}
		}
		for (int i = 0; i < listaArquivosInterface.length; i++) {
			nomeArquivo = listaArquivosInterface[i];
			if (nomeArquivo.substring(nomeArquivo.length() - 4,
					nomeArquivo.length()).equals("java")) {
				params.add(diretorioInterface + nomeArquivo);
			}
		}
		for (int i = 0; i < listaArquivosTestes.length; i++) {
			nomeArquivo = listaArquivosTestes[i];
			if (nomeArquivo.substring(nomeArquivo.length() - 4,
					nomeArquivo.length()).equals("java")) {
				params.add(diretorioTestes + nomeArquivo);
			}
		}
		params.add("-d");
		params.add(diretorioSource);
		return params.toArray(new String[params.size()]);
	}

	public String rodarTestesAutomaticos(Submissao submissao)
			throws EasyCorrectionException {

		String diretorioTestes = ServletUpload.local
				+ submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro()
						.getDiretorioTestes().replace("/", File.separator);
		String diretorioInterface = ServletUpload.local
				+ submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro()
						.getDiretorioInterface().replace("/", File.separator);
		String diretorioSource = ServletUpload.local
				+ submissao.getUrl().replace("/", File.separator);

		File dirTestes = new File(diretorioTestes);
		String arquivoDeTeste = primeiraOcorrencia(dirTestes.list());
		File dirInterface = new File(diretorioInterface);
		String arquivoDeInterface = primeiraOcorrencia(dirInterface.list());
		File dirSource = new File(diretorioSource);
		String arquivoSource = primeiraOcorrencia(dirSource.list());

		TestResult resultadoTeste;

		try {
			resultadoTeste = gerenciadorTestes.executarTestes(diretorioTestes,
					arquivoDeTeste, diretorioInterface, arquivoDeInterface,
					diretorioSource, arquivoSource);
		} catch (ExecucaoTestesException e) {
			excluirSubmissao(submissao);
			return e.getMessage();
		}

		return gerenciadorTestes.getSaidaDosTestes(resultadoTeste, submissao
				.getEquipeHasUsuarioHasRoteiro());
	}

	public void verificaSeEquipePossuiMaximoParticipantes(
			EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException {
		List<EquipeHasUsuarioHasRoteiro> lista = getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
				equr.getEquipe().getId(), equr.getRoteiro().getId());
		if (lista.size() >= equr.getRoteiro().getNumeroMaximoParticipantes()) {
			throw new EasyCorrectionException(
					MsgErros.NUMERO_MAXIMO_PARTICIPANTES.msg(""));
		}

	}

	public EquipeHasUsuarioHasRoteiro cadastraEquipeHasUsuarioHasRoteiro(
			EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(equr)) {
			verificaSeEquipePossuiMaximoParticipantes(equr);
			Integer id = DAOFactory.DEFAULT
					.buildEquipeHasUsuarioHasRoteiroDAO().save(equr);
			equr.setId(id);
		}
		return equr;
	}

	public Equipe cadastraEquipe(Equipe e) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(e)) {
			List<Equipe> equipes = getEquipes();
			if (equipes.isEmpty()) {
				e.setNome("Equipe 1");
				Integer id = DAOFactory.DEFAULT.buildEquipeDAO().save(e);
				e.setId(id);
			} else {
				int ultimoNumero = Integer.parseInt(equipes.get(
						equipes.size() - 1).getNome().split(" ")[1]) + 1;
				List<Equipe> lista = DAOFactory.DEFAULT.buildEquipeDAO()
						.findByNome(e.getNome());
				if (lista.isEmpty()) {
					e.setNome("Equipe " + ultimoNumero);
					Integer id = DAOFactory.DEFAULT.buildEquipeDAO().save(e);
					e.setId(id);
				}
			}
		}
		return e;
	}

	public void excluiEquipeHasRoteiroHasUsuario(EquipeHasUsuarioHasRoteiro equr)
			throws EasyCorrectionException {
		EquipeHasUsuarioHasRoteiro e = getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
				equr.getUsuario().getIdUsuario(), equr.getRoteiro().getId());
		e = (EquipeHasUsuarioHasRoteiro) SwapperAtributosReflect.swapObject(e,
				equr, EquipeHasUsuarioHasRoteiro.class);
		DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().delete(e);
	}

	public Submissao getSubmissao(int submissaoId) {
		return DAOFactory.DEFAULT.buildSubmissaoDAO().getById(submissaoId);
	}

	public void excluirSubmissao(Submissao sub) throws EasyCorrectionException {
		if (sub == null) {
			throw new ExclusaoRoteiroException("Submissao inexistente!");
		}
		Submissao submissao = getSubmissao(sub.getId());
		submissao = (Submissao) SwapperAtributosReflect.swapObject(submissao,
				sub, Submissao.class);
		DAOFactory.DEFAULT.buildSubmissaoDAO().delete(submissao);
	}

	public void alocaEquipesParaAlunos(Roteiro rot, List<Equipe> equipes,
			List<GrupoUsuario> alunos) throws EasyCorrectionException {
		for (int i = 0; i < alunos.size(); i++) {
			EquipeHasUsuarioHasRoteiro eur = new EquipeHasUsuarioHasRoteiro();
			eur.setRoteiro(rot);
			eur.setEquipe(equipes.get(i));
			eur.setUsuario(alunos.get(i).getUsuario());
			cadastraEquipeHasUsuarioHasRoteiro(eur);
		}
	}

	public String getNomeArquivoInterface(Roteiro roteiro) {
		String diretorioInterface = ServletUpload.local
				+ roteiro.getDiretorioInterface().replace("/", File.separator);
		File dirInterface = new File(diretorioInterface);
		return primeiraOcorrencia(dirInterface.list());
	}

	public String getNomeArquivoTestes(Roteiro roteiro) {
		String diretorioTestes = ServletUpload.local
				+ roteiro.getDiretorioTestes().replace("/", File.separator);
		File dirTestes = new File(diretorioTestes);
		return primeiraOcorrenciaZip(dirTestes.list());
	}

}
