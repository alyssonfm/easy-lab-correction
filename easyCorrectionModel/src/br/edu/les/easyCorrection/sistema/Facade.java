package br.edu.les.easyCorrection.sistema;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.GeradorSenhas;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class Facade {

	private Sistema sistema;
	static Logger log = Logger.getLogger("br.les.edu.facade");

	public Facade() {
		sistema = new Sistema();

	}

	public void reinicializaBancoDeDados() {
		sistema.reinicializaBancoDeDados("backupEasyCorrection");
	}

	/*******************************************************************
	 * Controle de Acesso
	 * **********************************************************************
	 * 
	 * @throws Throwable
	 */
	public Funcao getFuncao(Integer id) throws Throwable {
		try {
			return sistema.getFuncao(id);
		} catch (Throwable e) {
			log.error("getFuncao()", e);
			throw e;
		}
	}

	public Grupo getGrupo(Integer id) throws Throwable {
		try {
			return sistema.getGrupo(id);
		} catch (Throwable e) {
			log.error("getGrupo()", e);
			throw e;
		}
	}

	public GrupoUsuario getGrupoUsuario(Integer id) throws Throwable {
		try {
			return sistema.getGrupoUsuario(id);
		} catch (Throwable e) {
			log.error("getGrupoUsuario()", e);
			throw e;
		}
	}

	public Menu getMenu(Integer id) throws Throwable {
		try {
			return sistema.getMenu(id);
		} catch (Throwable e) {
			log.error("getMenu()", e);
			throw e;
		}
	}

	public Permissao getPermissao(Integer id) throws Throwable {
		try {
			return sistema.getPermissao(id);
		} catch (Throwable e) {
			log.error("getPermissao()", e);
			throw e;
		}
	}

	public Usuario getUsuario(Integer id) throws Throwable {
		try {
			return sistema.getUsuario(id);
		} catch (Throwable e) {
			log.error("getUsuario()", e);
			throw e;
		}
	}

	public Usuario getUsuarioPorLogin(String login) throws Throwable {
		try {
			return sistema.getUsuarioPorLogin(login);
		} catch (Throwable e) {
			log.error("getUsuarioPorLogin()", e);
			throw e;
		}
	}

	public Funcao cadastrarFuncao(Funcao funcao) throws Throwable {
		try {
			return sistema.cadastrarFuncao(funcao);
		} catch (Throwable e) {
			log.error("cadastrarFuncao()", e);
			throw e;
		}
	}

	public Grupo cadastrarGrupo(Grupo grupo) throws Throwable {
		try {
			return sistema.cadastrarGrupo(grupo);
		} catch (Throwable e) {
			log.error("cadastrarGrupo()", e);
			throw e;
		}
	}

	public GrupoUsuario cadastrarGrupoUsuario(GrupoUsuario grupoUsuario)
			throws Throwable {
		try {
			return sistema.cadastrarGrupoUsuario(grupoUsuario);
		} catch (Throwable e) {
			log.error("cadastrarGrupoUsuario()", e);
			throw e;
		}
	}

	public Menu cadastrarMenu(Menu menu) throws Throwable {
		try {
			return sistema.cadastrarMenu(menu);
		} catch (Throwable e) {
			log.error("cadastrarMenu()", e);
			throw e;
		}
	}

	public List<Permissao> cadastrarPermissao(List<Permissao> permissoes)
			throws Throwable {
		try {
			return sistema.cadastrarPermissao(permissoes);
		} catch (Throwable e) {
			log.error("cadastrarPermissao()", e);
			throw e;
		}
	}

	public GrupoUsuario cadastrarUsuario(GrupoUsuario grupoUsuario)
			throws Throwable {
		try {
			return sistema.cadastrarUsuario(grupoUsuario);
		} catch (Throwable e) {
			log.error("cadastrarUsuario()", e);
			throw e;
		}
	}

	public List<Funcao> validaUsuario(Usuario usuario) throws Throwable {
		try {
			return sistema.validaUsuario(usuario);
		} catch (Throwable e) {
			log.error("validaUsuario()", e);
			throw e;
		}
	}

	public List<Usuario> listarUsuarios() throws Throwable {
		try {
			return sistema.listarUsuarios();
		} catch (Throwable e) {
			log.error("listarUsuarios()", e);
			throw e;
		}
	}

	public List<Grupo> listarGrupos() throws Throwable {
		try {
			return sistema.listarGrupo();
		} catch (Throwable e) {
			log.error("listarGrupo()", e);
			throw e;
		}
	}

	public List<Funcao> listarFuncoes() throws Throwable {
		try {
			return sistema.listarFuncao();
		} catch (Throwable e) {
			log.error("listarFuncao()", e);
			throw e;
		}
	}

	public List<Menu> listarMenus() throws Throwable {
		try {
			return sistema.listarMenu();
		} catch (Throwable e) {
			log.error("listarMenus()", e);
			throw e;
		}
	}

	public void excluirMenu(Menu menu) throws Throwable {
		try {
			sistema.excluirMenu(menu);
		} catch (Throwable e) {
			log.error("excluirMenu()", e);
			throw e;
		}
	}

	public void excluirFuncao(Funcao funcao) throws Throwable {
		try {
			sistema.excluirFuncao(funcao);
		} catch (Throwable e) {
			log.error("excluirFuncao()", e);
			throw e;
		}
	}

	public void excluirGrupo(Grupo grupo) throws Throwable {
		try {
			sistema.excluirGrupo(grupo);
		} catch (Throwable e) {
			log.error("excluirGrupo()", e);
			throw e;
		}
	}

	public void excluirUsuario(GrupoUsuario grupoUsuario) throws Throwable {
		try {
			sistema.excluirUsuario(grupoUsuario);
		} catch (Throwable e) {
			log.error("excluirUsuario()", e);
			throw e;
		}
	}

	public List<GrupoUsuario> listarGrupoUsuarios() throws Throwable {
		try {
			return sistema.listarGrupoUsuarios();
		} catch (Throwable e) {
			log.error("listarGrupoUsuarios()", e);
			throw e;
		}
	}

	public List<GrupoUsuario> listarGrupoUsuariosPorGrupo(String nomeGrupo)
			throws Throwable {
		try {
			return sistema.listarGrupoUsuariosPorGrupo(nomeGrupo);
		} catch (Throwable e) {
			log.error("listarGrupoUsuarios()", e);
			throw e;
		}
	}

	public List<Permissao> consultarPermissoesPorGrupo(Integer idGrupo)
			throws Throwable {
		try {
			return sistema.consultarPermissoesPorGrupo(idGrupo);
		} catch (Throwable e) {
			log.error("consultarPermissoesPorGrupo()", e);
			throw e;
		}
	}

	public List<Permissao> cadastraPermissaoGrupo(Grupo g, List<Funcao> lista)
			throws Throwable {
		try {
			return sistema.cadastraPermissaoGrupo(g, lista);
		} catch (Throwable e) {
			log.error("cadastraPermissaoGrupo()", e);
			throw e;
		}
	}

	public List<Menu> listarMenusOrdenados() throws Throwable {
		try {
			return sistema.listarMenusOrdenados();
		} catch (Throwable e) {
			log.error("listarMenusOrdenados()", e);
			throw e;
		}
	}

	public List<Funcao> consultarFuncaoPorMenu(Integer idMenu) throws Throwable {
		try {
			return sistema.consultarFuncaoPorMenu(idMenu);
		} catch (Throwable e) {
			log.error("listarMenusOrdenados()", e);
			throw e;
		}
	}

	public Grupo getGrupoPorNome(String nome) throws Throwable {
		try {
			return sistema.getGrupoPorNome(nome);
		} catch (Throwable e) {
			log.error("getGrupoPorNome()", e);
			throw e;
		}
	}

	public Usuario atualizarUsuario(Usuario usuario) throws Throwable {
		try {
			return sistema.atualizarUsuario(usuario);
		} catch (Throwable e) {
			log.error("atualizarUsuario()", e);
			throw e;
		}
	}

	public Usuario consultarUsuarioPorLogin(String login) throws Throwable {
		try {
			return sistema.consultarUsuarioPorLogin(login);
		} catch (Throwable e) {
			log.error("getProfessorPorUsuario()", e);
			throw e;
		}
	}

	public List<GrupoUsuario> consultarUsuarioPorGrupo(Integer idGrupo)
			throws Throwable {
		try {
			return sistema.consultarUsuarioPorGrupo(idGrupo);
		} catch (Throwable e) {
			log.error("consultarUsuarioPorGrupo()", e);
			throw e;
		}
	}

	public List<GrupoUsuario> getGrupoUsuarioPorUsuario(Integer idUsuario)
			throws Throwable {
		try {
			return sistema.getGrupoUsuarioPorUsuario(idUsuario);
		} catch (Throwable e) {
			log.error("getGrupoUsuarioPorUsuario()", e);
			throw e;
		}
	}

	public Date getDataNow() throws Throwable {
		try {
			return easyCorrectionUtil.getDataNow();
		} catch (Throwable e) {
			log.error("getDataNow()", e);
			throw e;
		}
	}

	public String gerarSenha(int numDigitos, String nomeUsuario)
			throws Throwable {
		try {
			return GeradorSenhas.gerarSenha(numDigitos, nomeUsuario);
		} catch (Throwable e) {
			log.error("gerarSenha()", e);
			throw e;
		}
	}

	public Usuario alterarSenha(Usuario usuario, String novaSenha)
			throws Throwable {
		try {
			return sistema.alterarSenha(usuario, novaSenha);
		} catch (Throwable e) {
			log.error("alterarSenha()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Sistema (em geral)
	 * **********************************************************************
	 */

	public Periodo getPeriodo(int periodoId) throws Throwable {
		try {
			return sistema.getPeriodo(periodoId);
		} catch (Throwable e) {
			log.error("getPeriodo()", e);
			throw e;
		}
	}

	public List<Periodo> getPeriodoAtual() throws Throwable {
		try {
			return sistema.getPeriodoAtual();
		} catch (Throwable e) {
			log.error("getPeriodoAtual()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Roteiros
	 * **********************************************************************
	 */

	public Roteiro cadastrarRoteiro(Roteiro roteiro) throws Throwable {
		try {
			return sistema.cadastrarRoteiro(roteiro);
		} catch (Throwable e) {
			log.error("cadastrarRoteiro()", e);
			throw e;
		}
	}

	public Roteiro editarRoteiro(Roteiro roteiro) throws Throwable {
		try {
			return sistema.editarRoteiro(roteiro);
		} catch (Throwable e) {
			log.error("cadastrarRoteiro()", e);
			throw e;
		}
	}

	// public Roteiro bloquearRoteiro(Roteiro roteiro) throws Throwable {
	// try {
	// return sistema.bloquearRoteiro(roteiro);
	// } catch (Throwable e) {
	// log.error("bloquearRoteiro()", e);
	// throw e;
	// }
	// }
	//	
	// public Roteiro desbloquearRoteiro(Roteiro roteiro) throws Throwable {
	// try {
	// return sistema.desbloquearRoteiro(roteiro);
	// } catch (Throwable e) {
	// log.error("bloquearRoteiro()", e);
	// throw e;
	// }
	// }

	// public Roteiro liberarRoteiro(Roteiro roteiroTemp)
	// throws LiberaRoteiroException, BloqueiaRoteiroException {
	// return sistema.liberarRoteiro(roteiroTemp);
	// }

	public Roteiro getRoteiro(int idRoteiro) throws Throwable {
		try {
			return sistema.getRoteiro(idRoteiro);
		} catch (Throwable e) {
			log.error("getRoteiro()", e);
			throw e;
		}
	}

	public List<Roteiro> listarRoteiros() throws Throwable {
		try {
			return sistema.listarRoteiros();
		} catch (Throwable e) {
			log.error("listarRoteiros()", e);
			throw e;
		}
	}

	public void excluirRoteiro(Roteiro roteiro) throws Throwable {
		try {
			sistema.excluirRoteiro(roteiro);
		} catch (Throwable e) {
			log.error("excluirRoteiro()", e);
			throw e;
		}
	}

	public List<Roteiro> getRoteirosLiberados() throws Throwable {
		try {
			return sistema.getRoteirosLiberados();
		} catch (Throwable e) {
			log.error("getRoteirosLiberados()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Submissoes
	 * **********************************************************************
	 */

	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
			Integer idUsuario, Integer idRoteiro) throws Throwable {
		try {
			return sistema.getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
					idUsuario, idRoteiro);
		} catch (Throwable e) {
			log.error("getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro()", e);
			throw e;
		}
	}

	public Equipe cadastrarEquipe(Equipe e) throws EasyCorrectionException {
		return sistema.cadastrarEquipe(e);
	}

	public Equipe getEquipe(int id) throws Throwable {
		try {
			return sistema.getEquipe(id);
		} catch (Throwable e) {
			log.error("getEquipe()", e);
			throw e;
		}
	}

	public Equipe getEquipePorNome(String nomeEquipe) throws Throwable {
		try {
			return sistema.getEquipePorNome(nomeEquipe);
		} catch (Throwable e) {
			log.error("getEquipe()", e);
			throw e;
		}
	}

	public List<Equipe> getEquipes() throws Throwable {
		try {
			return sistema.getEquipes();
		} catch (Throwable e) {
			log.error("getEquipes()", e);
			throw e;
		}
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiros()
			throws Throwable {
		try {
			return sistema.getEquipeHasUsuarioHasRoteiros();
		} catch (Throwable e) {
			log.error("getEquipeHasUsuarioHasRoteiros()", e);
			throw e;
		}
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
			Integer idEquipe, Integer idRoteiro) throws Throwable {
		try {
			return sistema.getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
					idEquipe, idRoteiro);
		} catch (Throwable e) {
			log.error("getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro()", e);
			throw e;
		}
	}

	public int getEquipeAlocadas(Integer idRoteiro) throws Throwable {
		try {
			return sistema.getEquipeAlocadas(idRoteiro);
		} catch (Throwable e) {
			log.error("getEquipeAlocadas()", e);
			throw e;
		}
	}

	public EquipeHasUsuarioHasRoteiro cadastraEquipeHasUsuarioHasRoteiro(
			EquipeHasUsuarioHasRoteiro equr) throws Throwable {
		try {
			return sistema.cadastraEquipeHasUsuarioHasRoteiro(equr);
		} catch (Throwable e) {
			log.error("cadastraEquipeHasUsuarioHasRoteiro()", e);
			throw e;
		}
	}

	public EquipeHasUsuarioHasRoteiro mudarEquipe(EquipeHasUsuarioHasRoteiro eur)
			throws Throwable {
		try {
			return sistema.mudarEquipe(eur);
		} catch (Throwable e) {
			log.error("verificaSeUsuarioEstaCadastrado()", e);
			throw e;
		}
	}

	public String getNomeArquivoInterface(Roteiro roteiro) {
		return sistema.getNomeArquivoInterface(roteiro);
	}

	public String getNomeArquivoTestes(Roteiro roteiro) {
		return sistema.getNomeArquivoTestes(roteiro);
	}

	public String getNomeArquivoCodigo(Submissao submissao) {
		return sistema.getNomeArquivoCodigo(submissao);
	}

	public Submissao submeteRoteiro(Submissao submissao) throws Throwable {
		try {
			return sistema.submeteRoteiro(submissao);
		} catch (Throwable e) {
			log.error("submeteRoteiro()", e);
			throw e;
		}
	}

	public String rodarTestesAutomaticos(Submissao submissao) throws Throwable {
		try {
			return sistema.rodarTestesAutomaticos(submissao);
		} catch (Throwable e) {
			log.error("rodarTestesAutomaticos()", e);
			throw e;
		}
	}

	public Submissao getSubmissao(int submissaoId) {
		return sistema.getSubmissao(submissaoId);
	}

	public void excluirSubmissao(Submissao sub) throws EasyCorrectionException {
		sistema.excluirSubmissao(sub);
	}

	public Integer getNumeroSubmissoes(Submissao submissao) {
		return sistema.numeroSubmissoes(submissao);
	}

	public Integer getNumeroSubmissoesPorEUR(EquipeHasUsuarioHasRoteiro eur) {
		return sistema.numeroSubmissoesPorEUR(eur);
	}

	public List<Submissao> getSubmissoesPorRoteiroEquipeUnicos(Roteiro roteiro) {
		return sistema.getSubmissoesPorRoteiroEquipeUnicos(roteiro);
	}

	public Submissao getUltimaSubmissaoPorRoteiroEquipe(Roteiro roteiro,
			Equipe equipe) {
		return sistema.getUltimaSubmissaoPorRoteiroEquipe(roteiro, equipe);
	}

	/*******************************************************************
	 * Facade Avaliacoes
	 * **********************************************************************
	 */

	/**
	 * Retorna todas as equipes
	 */

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiro(
			Integer idRoteiro, Integer idCorretor) throws Throwable {
		try {
			return sistema.getEquipeHasUsuarioHasRoteiroPorRoteiro(idRoteiro);
		} catch (Throwable e) {
			log.error("getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro()", e);
			throw e;
		}
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiroAgrupadoPorEquipe(
			Integer idRoteiro) throws Throwable {
		try {
			return sistema
					.getEquipeHasUsuarioHasRoteiroPorRoteiroAgrupadoPorEquipe(idRoteiro);
		} catch (Throwable e) {
			log
					.error(
							"getEquipeHasUsuarioHasRoteiroPorRoteiroAgrupadoPorEquipe()",
							e);
			throw e;
		}
	}

	public List<Roteiro> getRoteirosFechados() throws Throwable {
		try {
			return sistema.getRoteirosFechados();
		} catch (Throwable e) {
			log.error("getRoteirosFechados()", e);
			throw e;
		}
	}

	/**
	 * Retorna os usuarios que sao considerados professores, ou seja, checa se
	 * no grupoUsuario o id_grupo eh 2 ou 3 (ver script do banco para maiores
	 * informacoes)
	 * 
	 */
	public List<Usuario> getCorretores() throws Throwable {
		try {
			return sistema.getCorretores();
		} catch (Throwable e) {
			log.error("getCorretores()", e);
			throw e;
		}
	}

	public List<Avaliacao> getAvaliacoesDoRoteiroSemCorretor(int roteiroId) throws Throwable {
		try {
			return sistema.getAvaliacoesDoRoteiroSemCorretor(roteiroId);
		} catch (Throwable e) {
			log.error("getCorretores()", e);
			throw e;
		}
	}

	public List<Avaliacao> getAvaliacoesDoRoteiroComCorretor(int roteiroId, int corretorId) throws Throwable {
		try {
			return sistema.getAvaliacoesDoRoteiroComCorretor(roteiroId, corretorId);
		} catch (Throwable e) {
			log.error("getCorretores()", e);
			throw e;
		}
	}
	
	public Avaliacao salvarAvaliacao(Avaliacao avaliacao) throws Throwable{
		try{
			return sistema.salvarAvaliacao(avaliacao);
		} catch (Throwable e) {
			log.error("cadastrarAvaliacao()", e);
			throw e;
		}
	}
	
	
	public void excluirAvaliacao(Avaliacao avaliacao) throws Throwable{
		try{
			sistema.excluirAvaliacao(avaliacao);
		} catch (Throwable e) {
			log.error("excluirAvaliacao()", e);
			throw e;
		}
	}
	
	public List<Avaliacao> getAvaliacaoPorSubmissao(int idSubmissao) throws Throwable{
		try{
			return sistema.getAvaliacaoPorSubmissao(idSubmissao);
		}catch (Throwable e) {
			log.error("getAvaliacaoPorSubmissao()", e);
			throw e;
		}
	}

}
