package br.edu.les.easyCorrection.gerenciadores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestResult;

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
	private TeamManager teamManager;

	public GerenciadorSubmissoes() {
		super();
		gerenciadorRoteiros = new GerenciadorRoteiros();
		gerenciadorTestes = new GerenciadorTestes();
		teamManager = new TeamManager();
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
			.findByEquipeERoteiro(eq.getId(), rot.getId());
		return lista;
	}

	public Integer numeroSubmissoesPorEUR(EquipeHasUsuarioHasRoteiro eur) {
		return getSubmissoesPorRoteiroEquipe(eur.getRoteiro(), eur.getEquipe()).size();
	}

	public Submissao submeteRoteiro(Submissao submissao)
			throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(submissao)) {
			if(!easyCorrectionUtil.isNull(gerenciadorRoteiros.getRoteiroLiberado(submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro().getId()))){
				if (numeroSubmissoes(submissao) < (submissao
					.getEquipeHasUsuarioHasRoteiro().getRoteiro()
					.getNumeroMaximoEnvios())) {
				
					submissao.setDataSubmissao(easyCorrectionUtil.getDataNow());
					Integer id = DAOFactory.DEFAULT.buildSubmissaoDAO().save(
							submissao);
					submissao.setId(id);
				}
				else{
					throw new EasyCorrectionException(
							MsgErros.NUMERO_MAXIMO_SUBMISSOES_EXCEDIDO
									.msg(submissao.getEquipeHasUsuarioHasRoteiro()
											.getRoteiro().getNome()));
				}
			} else {
				throw new EasyCorrectionException(
						MsgErros.TEMPO_MAXIMO_SUBMISSOES_EXCEDIDO
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
		
		if(submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro().getPorcentagemTestesAutomaticos() > 0){
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
	
			return gerenciadorTestes.getSaidaDosTestes(resultadoTeste, submissao);
		}
		else{
			String resultado = "Este roteiro não possui testes automáticos.";
			gerenciadorTestes.salvaAvaliacao(submissao, 0, resultado);
			return "Resultado: " + resultado;
		}
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
	
	public String getNomeArquivoCodigo(Submissao submissao) {
		String diretorioCodigo = ServletUpload.local
				+ submissao.getUrl().replace("/", File.separator);
		File dirCodigo = new File(diretorioCodigo);
		return primeiraOcorrenciaZip(dirCodigo.list());
	}

}
