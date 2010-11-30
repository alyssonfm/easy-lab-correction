package br.edu.les.easyCorrection.sistema;

import java.util.List;

import br.edu.les.easyCorrection.exceptions.BloqueiaRoteiroException;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.LiberaRoteiroException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorRoteiros;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class Sistema {

	private GerenciadorAcesso gerenciadorAcesso;
	private GerenciadorRoteiros gerenciadorRoteiros;
	private GerenciadorSubmissoes gerenciadorSubmissoes;

	public Sistema() {
		gerenciadorAcesso = new GerenciadorAcesso();
		gerenciadorRoteiros = new GerenciadorRoteiros();
		gerenciadorSubmissoes = new GerenciadorSubmissoes();
	}
	
	public void reinicializaBancoDeDados(String script){
		gerenciadorAcesso.reinicializaBancoDeDados(script);
	}
	
	
	public void excluirMenu(Menu menu) throws EasyCorrectionException {
		gerenciadorAcesso.excluirMenu(menu);
	}

	public void excluirFuncao(Funcao funcao) throws EasyCorrectionException {
		gerenciadorAcesso.excluirFuncao(funcao);
	}

	public void excluirGrupo(Grupo grupo) throws EasyCorrectionException {
		gerenciadorAcesso.excluirGrupo(grupo);
	}

	public void excluirUsuario(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		gerenciadorAcesso.excluirUsuario(grupoUsuario);
	}

	/******************************************** Controle de Acesso EasyCorrection *********************************************/

	public Funcao getFuncao(Integer id) {
		return gerenciadorAcesso.getFuncao(id);
	}

	public Grupo getGrupo(Integer id) {
		return gerenciadorAcesso.getGrupo(id);
	}

	public GrupoUsuario getGrupoUsuario(Integer id) {
		return gerenciadorAcesso.getGrupoUsuario(id);
	}

	public Menu getMenu(Integer id) {
		return gerenciadorAcesso.getMenu(id);
	}

	public Permissao getPermissao(Integer id) {
		return gerenciadorAcesso.getPermissao(id);
	}

	public Usuario getUsuario(Integer id) {
		return gerenciadorAcesso.getUsuario(id);
	}

	public Usuario getUsuarioPorLogin(String login) {
		return gerenciadorAcesso.getUsuarioPorLogin(login);
	}

	public Funcao cadastrarFuncao(Funcao funcao) throws EasyCorrectionException {
		return gerenciadorAcesso.cadastrarFuncao(funcao);
	}

	public Grupo cadastrarGrupo(Grupo grupo) throws EasyCorrectionException {
		return gerenciadorAcesso.cadastrarGrupo(grupo);
	}

	public GrupoUsuario cadastrarGrupoUsuario(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		return gerenciadorAcesso.cadastrarGrupoUsuario(grupoUsuario);
	}

	public Menu cadastrarMenu(Menu menu) throws EasyCorrectionException {
		return gerenciadorAcesso.cadastrarMenu(menu);
	}

	public List<Permissao> cadastrarPermissao(List<Permissao> permissoes)
			throws EasyCorrectionException {
		return gerenciadorAcesso.cadastrarPermissao(permissoes);
	}

	public GrupoUsuario cadastrarUsuario(GrupoUsuario grupoUsuario)
			throws EasyCorrectionException {
		return gerenciadorAcesso.cadastrarUsuario(grupoUsuario);
	}

	public List<Usuario> listarUsuarios() {
		return gerenciadorAcesso.listarUsuarios();
	}

	public List<Grupo> listarGrupo() {
		return gerenciadorAcesso.listarGrupo();
	}

	public List<Funcao> listarFuncao() {
		return gerenciadorAcesso.listarFuncao();
	}

	public List<Menu> listarMenu() {
		return gerenciadorAcesso.listarMenu();
	}

	public List<Funcao> validaUsuario(Usuario usuario) {
		return gerenciadorAcesso.validaUsuario(usuario);
	}

	public List<GrupoUsuario> listarGrupoUsuarios() {
		return gerenciadorAcesso.listarGrupoUsuario();
	}
	
	public List<GrupoUsuario> listarGrupoUsuariosPorGrupo(String nomeGrupo) {
		return gerenciadorAcesso.listarGrupoUsuarioPorGrupo(nomeGrupo);
	}

	public List<Permissao> consultarPermissoesPorGrupo(Integer idGrupo) {
		return gerenciadorAcesso.consultarPermissoesPorGrupo(idGrupo);
	}

	public List<Permissao> cadastraPermissaoGrupo(Grupo g, List<Funcao> lista)
			throws Throwable {
		return gerenciadorAcesso.cadastraPermissaoGrupo(g, lista);
	}

	public List<Menu> listarMenusOrdenados() {
		return gerenciadorAcesso.listarMenusOrdenados();
	}

	public List<Funcao> consultarFuncaoPorMenu(Integer idMenu) {
		return gerenciadorAcesso.consultarFuncaoPorMenu(idMenu);
	}

	public Grupo getGrupoPorNome(String nome) {
		return gerenciadorAcesso.getGrupoPorNome(nome);
	}

	/*-------------------------------------- USUARIOS -------------------------------------------*/

	public Usuario atualizarUsuario(Usuario usuario)
			throws EasyCorrectionException {
		return gerenciadorAcesso.atualizarUsuario(usuario);
	}

	public Usuario consultarUsuarioPorLogin(String login) {
		return gerenciadorAcesso.consultarUsuarioPorLogin(login);
	}

	public List<GrupoUsuario> consultarUsuarioPorGrupo(Integer idGrupo) {
		return gerenciadorAcesso.consultarUsuarioPorGrupo(idGrupo);
	}

	public List<GrupoUsuario> getGrupoUsuarioPorUsuario(Integer idUsuario) {
		return gerenciadorAcesso.getGrupoUsuarioPorUsuario(idUsuario);
	}

	public Usuario alterarSenha(Usuario usuario, String novaSenha) {
		return gerenciadorAcesso.alterarSenha(usuario, novaSenha);
	}

	/******************************************** Controle de Criacao/Edicao de Roteiros EasyLabCorrection *********************************************/
	public Periodo getPeriodo(int periodoId) {
		return gerenciadorRoteiros.getPeriodo(periodoId);
	}

	public List<Periodo> getPeriodoAtual() {
		return gerenciadorRoteiros.getPeriodoAtual();
	}

	public Roteiro getRoteiro(int roteiroId) {
		return gerenciadorRoteiros.getRoteiro(roteiroId);
	}

	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp)
			throws EasyCorrectionException {
		Roteiro rot = gerenciadorRoteiros.cadastrarRoteiro(roteiroTemp);
		List<Equipe> equipes = gerenciadorSubmissoes.getEquipes();
		List<GrupoUsuario> alunos = gerenciadorAcesso.listarGrupoUsuarioPorGrupo("Aluno");
		gerenciadorSubmissoes.alocaEquipesParaAlunos(rot, equipes, alunos);
		return rot;
	}

	public Roteiro editarRoteiro(Roteiro roteiroTemp)
			throws EdicaoRoteiroException, CriacaoRoteiroException {
		return gerenciadorRoteiros.editarRoteiro(roteiroTemp);
	}

	public Roteiro bloquearRoteiro(Roteiro roteiroTemp, boolean bloqueia)
			throws BloqueiaRoteiroException, LiberaRoteiroException {
		return gerenciadorRoteiros.bloquearRoteiro(roteiroTemp, bloqueia);
	}
	
	public Roteiro liberarRoteiro(Roteiro roteiroTemp)
			throws LiberaRoteiroException, BloqueiaRoteiroException {
		return gerenciadorRoteiros.liberarRoteiro(roteiroTemp);
	}

	public void excluirRoteiro(Roteiro roteiro) throws ExclusaoRoteiroException {
		gerenciadorRoteiros.excluirRoteiro(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return gerenciadorRoteiros.listarRoteiros();
	}
	
	public List<Roteiro> getRoteirosLiberados(){
		return gerenciadorRoteiros.getRoteirosLiberados();
	}

	/******************************************** Controle de Submissão de Roteiros EasyLabCorrection *********************************************/

	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
			Integer idUsuario, Integer idRoteiro) {
		return gerenciadorSubmissoes
				.getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(idUsuario,
						idRoteiro);
	}
	
	public List<Equipe> getEquipes(){
		return gerenciadorSubmissoes.getEquipes();
	}
	
	public Equipe getEquipe(int id){
		return gerenciadorSubmissoes.getEquipe(id);
	}
	
	public Equipe getEquipePorNome(String nomeEquipe) throws Throwable {
		return gerenciadorSubmissoes.getEquipePorNome(nomeEquipe);
	}
	
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiros(){
		return gerenciadorSubmissoes.getEquipeHasUsuarioHasRoteiros();
	}
	
	public EquipeHasUsuarioHasRoteiro cadastraEquipeHasUsuarioHasRoteiro(EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException {
		return gerenciadorSubmissoes.cadastraEquipeHasUsuarioHasRoteiro(equr);
	}
	
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(Integer idEquipe, Integer idRoteiro){
		return gerenciadorSubmissoes.getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(idEquipe, idRoteiro);
	}

	public Submissao submeteRoteiro(Submissao submissao) throws EasyCorrectionException {
		return gerenciadorSubmissoes.submeteRoteiro(submissao);
	}
	
	public String rodarTestesAutomaticos(Submissao submissao) throws EasyCorrectionException{
		return gerenciadorSubmissoes.rodarTestesAutomaticos(submissao);
	}
	
	public int getEquipeAlocadas(Integer idRoteiro){
		return gerenciadorSubmissoes.getEquipeAlocadas(idRoteiro);
	}
	
	public EquipeHasUsuarioHasRoteiro mudarEquipe(EquipeHasUsuarioHasRoteiro eur) throws EasyCorrectionException{
		GrupoUsuario gu = getGrupoUsuarioPorUsuario(eur.getUsuario().getIdUsuario()).get(0);
		if (!gu.getGrupo().getNome().equals("Aluno")){
			throw new EasyCorrectionException(
					MsgErros.ALUNO_INEXISTENTE.msg(""));
		}
		return gerenciadorSubmissoes.mudarEquipe(eur);
	}
	
	public Submissao getSubmissao(int submissaoId){
		return gerenciadorSubmissoes.getSubmissao(submissaoId);
	}

	public void excluirSubmissao(Submissao sub) throws EasyCorrectionException {
		gerenciadorSubmissoes.excluirSubmissao(sub);
	}

	public Equipe cadastrarEquipe(Equipe e) throws EasyCorrectionException {
		return gerenciadorSubmissoes.cadastraEquipe(e);
	}
	
	public Integer numeroSubmissoes(Submissao submissao){
		return gerenciadorSubmissoes.numeroSubmissoes(submissao);
	}
	
	public Integer numeroSubmissoesPorEUR(EquipeHasUsuarioHasRoteiro eur){
		return gerenciadorSubmissoes.numeroSubmissoesPorEUR(eur);
	}

	public String getNomeArquivoInterface(Roteiro roteiro) {
		return gerenciadorSubmissoes.getNomeArquivoInterface(roteiro);
	}

	public String getNomeArquivoTestes(Roteiro roteiro) {
		return gerenciadorSubmissoes.getNomeArquivoTestes(roteiro);
	}

}