package br.edu.les.easyCorrection.sistema;

import java.util.List;

import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.EdicaoRoteiroException;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorAcesso;
import br.edu.les.easyCorrection.gerenciadores.GerenciadorRoteiros;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;

public class Sistema {

	private GerenciadorAcesso gerenciadorAcesso;
	private GerenciadorRoteiros gerenciadorRoteiros;

	public Sistema(){
		gerenciadorAcesso = new GerenciadorAcesso();
		gerenciadorRoteiros = new GerenciadorRoteiros();
	}
	
	public void excluirMenu(Menu menu) throws EasyCorrectionException{
		gerenciadorAcesso.excluirMenu(menu);
	}
	
	public void excluirFuncao(Funcao funcao) throws EasyCorrectionException{
		gerenciadorAcesso.excluirFuncao(funcao);
	}
	
	public void excluirGrupo(Grupo grupo) throws EasyCorrectionException{
		gerenciadorAcesso.excluirGrupo(grupo);
	}
	
	public void excluirUsuario(GrupoUsuario grupoUsuario) throws EasyCorrectionException{
		gerenciadorAcesso.excluirUsuario(grupoUsuario);
	}
	
/******************************************** Controle de Acesso EasyCorrection *********************************************/

	
	public Funcao getFuncao(Integer id){
		return gerenciadorAcesso.getFuncao(id);
	}
	
	public Grupo getGrupo(Integer id){
		return gerenciadorAcesso.getGrupo(id);
	}
	
	public GrupoUsuario getGrupoUsuario(Integer id){
		return gerenciadorAcesso.getGrupoUsuario(id);
	}
	
	public Menu getMenu(Integer id){
		return gerenciadorAcesso.getMenu(id);
	} 
	
	public Permissao getPermissao(Integer id){
		return gerenciadorAcesso.getPermissao(id);
	}
	
	public Usuario getUsuario(Integer id){
		return gerenciadorAcesso.getUsuario(id);
	}
	
	public Usuario getUsuarioPorLogin(String login){
		return gerenciadorAcesso.getUsuarioPorLogin(login);
	}
	
	public Funcao cadastrarFuncao(Funcao funcao) throws EasyCorrectionException{
		return gerenciadorAcesso.cadastrarFuncao(funcao);
	}
	
	public Grupo cadastrarGrupo(Grupo grupo) throws EasyCorrectionException{
		return gerenciadorAcesso.cadastrarGrupo(grupo);
	}
	
	public GrupoUsuario cadastrarGrupoUsuario(GrupoUsuario grupoUsuario) throws EasyCorrectionException{
		return gerenciadorAcesso.cadastrarGrupoUsuario(grupoUsuario);
	}
	
	public Menu cadastrarMenu(Menu menu) throws EasyCorrectionException{
		return gerenciadorAcesso.cadastrarMenu(menu);
	}
	
	public List<Permissao> cadastrarPermissao(List <Permissao> permissoes) throws EasyCorrectionException{
		return gerenciadorAcesso.cadastrarPermissao(permissoes);
	}
	
	public GrupoUsuario cadastrarUsuario(GrupoUsuario grupoUsuario) throws EasyCorrectionException{
		return gerenciadorAcesso.cadastrarUsuario(grupoUsuario);
	}
	
	public List<Usuario> listarUsuarios(){
		return gerenciadorAcesso.listarUsuarios();
	}
	
	public List<Grupo> listarGrupo(){
		return gerenciadorAcesso.listarGrupo();
	}
	
	public List<Funcao> listarFuncao(){
		return gerenciadorAcesso.listarFuncao();
	}
	
	public List<Menu> listarMenu(){
		return gerenciadorAcesso.listarMenu();
	}
	
	public List<Funcao> validaUsuario(Usuario usuario){
		return gerenciadorAcesso.validaUsuario(usuario);
	}
	
	public List <GrupoUsuario> listarGrupoUsuarios(){
		return gerenciadorAcesso.listarGrupoUsuario();
	}
	
	public List <Permissao> consultarPermissoesPorGrupo(Integer idGrupo){
		return gerenciadorAcesso.consultarPermissoesPorGrupo(idGrupo);
	}
	
	public List<Permissao> cadastraPermissaoGrupo(Grupo g, List<Funcao> lista) throws Throwable {
		return gerenciadorAcesso.cadastraPermissaoGrupo(g, lista);
	}
	
	public List <Menu> listarMenusOrdenados(){
		return gerenciadorAcesso.listarMenusOrdenados();
	}
	
	public List <Funcao> consultarFuncaoPorMenu(Integer idMenu){
		return gerenciadorAcesso.consultarFuncaoPorMenu(idMenu);
	}
	
	public Grupo getGrupoPorNome(String nome){
		return gerenciadorAcesso.getGrupoPorNome(nome);
	}
	
	/*-------------------------------------- USUARIOS -------------------------------------------*/
	
	public Usuario atualizarUsuario(Usuario usuario) throws EasyCorrectionException{
		return gerenciadorAcesso.atualizarUsuario(usuario);
	}
	
	public Usuario consultarUsuarioPorLogin(String login){
		return gerenciadorAcesso.consultarUsuarioPorLogin(login);
	}
	
	public List <GrupoUsuario> consultarUsuarioPorGrupo(Integer idGrupo){
		return gerenciadorAcesso.consultarUsuarioPorGrupo(idGrupo);
	}

	public List<GrupoUsuario> getGrupoUsuarioPorUsuario(Integer idUsuario){
		return gerenciadorAcesso.getGrupoUsuarioPorUsuario(idUsuario);
	}
	
	public Usuario alterarSenha(Usuario usuario, String novaSenha){
		return gerenciadorAcesso.alterarSenha(usuario, novaSenha);
	}

	/******************************************** Controle de Roteiros EasyLabCorrection *********************************************/	
	public Periodo getPeriodo(int periodoId){
		return gerenciadorRoteiros.getPeriodo(periodoId);
	}
	
	public List<Periodo> getPeriodoAtual() {
		return gerenciadorRoteiros.getPeriodoAtual();
	}
	
	public Roteiro getRoteiro(int roteiroId){
		return gerenciadorRoteiros.getRoteiro(roteiroId);
	}
	
	public List<Roteiro> getRoteiros(){
		return gerenciadorRoteiros.getRoteiros();
	}
	
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp) throws CriacaoRoteiroException{
		return gerenciadorRoteiros.cadastrarRoteiro(roteiroTemp);
	}
	
	public Roteiro editarRoteiro(Roteiro roteiroTemp) throws EasyCorrectionException{
		return gerenciadorRoteiros.editarRoteiro(roteiroTemp);
	}
	

	public Roteiro bloquearRoteiro(Roteiro roteiroTemp) {
		return gerenciadorRoteiros.bloquearRoteiro(roteiroTemp);
	}

	public void excluirRoteiro(Roteiro roteiro) {
		gerenciadorRoteiros.excluirRoteiro(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return gerenciadorRoteiros.listarRoteiros();
	}
}