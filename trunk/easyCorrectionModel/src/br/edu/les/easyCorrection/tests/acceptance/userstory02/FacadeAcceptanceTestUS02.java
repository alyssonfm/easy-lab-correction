package br.edu.les.easyCorrection.tests.acceptance.userstory02;

import java.util.List;
import br.edu.les.easyCorrection.pojo.acesso.*;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class FacadeAcceptanceTestUS02{
	
	private Facade facadeSistema;

	public FacadeAcceptanceTestUS02() {
		facadeSistema = new Facade();
	}

// ******************************************* Menus ******************************************
	
	//EasyAcceptOK
	public Object getAtributoMenu(int id, String nomeAtributo) throws Throwable{
		Menu objMenu = getMenu(id);
		return easyCorrectionUtil.getAtributo(objMenu, nomeAtributo, false);
	}
	
	//EasyAcceptOK
	public int cadastrarMenu(String nomeMenu, String rotulo)
			throws Throwable{
		Menu menuAux = new Menu();
		menuAux.setNome(nomeMenu);
		menuAux.setRotulo(rotulo);
		Menu n = facadeSistema.cadastrarMenu(menuAux);
		return n.getIdMenu();
	}

	//EasyAcceptOK
	public int editarMenu(int id, String nomeMenu, String rotulo) throws Throwable {
		Menu menuAux = new Menu();
		menuAux.setIdMenu(id);
		menuAux.setNome(nomeMenu);
		menuAux.setRotulo(rotulo);
		Menu n = facadeSistema.cadastrarMenu(menuAux);
		return n.getIdMenu();
	}

	//EasyAcceptOK
	public void excluirMenu(int idMenu) throws Throwable {
		facadeSistema.excluirMenu(facadeSistema.getMenu(idMenu));
	}

	//EasyAcceptOK
	public List<Menu> listarMenus() throws Throwable{
		List<Menu> listarMenus = facadeSistema.listarMenus();
		return listarMenus;
	}
	
	//EasyAcceptOK
	public Object getAtributoMenuListaMenus(int id, String nomeAtributo) throws Throwable{
		List<Menu> listaMenus = listarMenus();
		for(Menu menu: listaMenus){
			if(menu.getIdMenu() == id){
				return easyCorrectionUtil.getAtributo(menu, nomeAtributo, false); 
			}
		}
		return "Menu Inexistente.";
	}

	//EasyAcceptOK
	public Menu getMenu(int idMenu) throws Throwable {
		return facadeSistema.getMenu(idMenu);
	}

// ******************************************* Funcoes *****************************************
	
	//EasyAcceptOK
	public Object getAtributoFuncao(int id, String nomeAtributo) throws Throwable{
		Funcao objFuncao = getFuncao(id);
		return easyCorrectionUtil.getAtributo(objFuncao, nomeAtributo, false);
	}
	
	//EasyAcceptOK
	public int cadastrarFuncao(String nomeFuncao, String rotulo, int idMenu)
			throws Throwable{
		Funcao funcaoAux = new Funcao();
		funcaoAux.setNome(nomeFuncao);
		funcaoAux.setRotulo(rotulo);
		funcaoAux.setMenu(getMenu(idMenu));
		Funcao n = facadeSistema.cadastrarFuncao(funcaoAux);
		return n.getIdFuncao();
	}
	
	//EasyAcceptOK
	public int editarFuncao(int id, String nomeFuncao, String rotulo, int idMenu) throws Throwable {
		Funcao funcaoAux = new Funcao();
		funcaoAux.setIdFuncao(id);
		funcaoAux.setNome(nomeFuncao);
		funcaoAux.setRotulo(rotulo);
		funcaoAux.setMenu(getMenu(idMenu));
		Funcao n = facadeSistema.cadastrarFuncao(funcaoAux);
		return n.getIdFuncao();
	}

	//EasyAcceptOK
	public void excluirFuncao(int idFuncao) throws Throwable {
		facadeSistema.excluirFuncao(facadeSistema.getFuncao(idFuncao));
	}

	//EasyAcceptOK
	public List<Funcao> listarFuncaos() throws Throwable{
		List<Funcao> listarFuncaos = facadeSistema.listarFuncoes();
		return listarFuncaos;
	}
	
	//EasyAcceptOK
	public Object getAtributoFuncaoListaFuncoes(int id, String nomeAtributo) throws Throwable{
		List<Funcao> listaFuncaos = listarFuncaos();
		for(Funcao funcao: listaFuncaos){
			if(funcao.getIdFuncao() == id){
				return easyCorrectionUtil.getAtributo(funcao, nomeAtributo, false); 
			}
		}
		return "Funcao Inexistente.";
	}

	//EasyAcceptOK
	public Funcao getFuncao(int idFuncao) throws Throwable {
		return facadeSistema.getFuncao(idFuncao);
	}
	
//******************************************* Grupos ******************************************
	
	//EasyAcceptOK
	public Object getAtributoGrupo(int id, String nomeAtributo) throws Throwable{
		Grupo objGrupo = getGrupo(id);
		return easyCorrectionUtil.getAtributo(objGrupo, nomeAtributo, false);
	}
	
	//EasyAcceptOK
	public int cadastrarGrupo(String nomeGrupo)
			throws Throwable{
		Grupo grupoAux = new Grupo();
		grupoAux.setNome(nomeGrupo);
		Grupo n = facadeSistema.cadastrarGrupo(grupoAux);
		return n.getIdGrupo();
	}

	//EasyAcceptOK
	public int editarGrupo(int id, String nomeGrupo) throws Throwable {
		Grupo grupoAux = new Grupo();
		grupoAux.setIdGrupo(id);
		grupoAux.setNome(nomeGrupo);
		Grupo n = facadeSistema.cadastrarGrupo(grupoAux);
		return n.getIdGrupo();
	}

	//EasyAcceptOK
	public void excluirGrupo(int idGrupo) throws Throwable {
		facadeSistema.excluirGrupo(facadeSistema.getGrupo(idGrupo));
	}

	//EasyAcceptOK
	public List<Grupo> listarGrupos() throws Throwable{
		List<Grupo> listarGrupos = facadeSistema.listarGrupos();
		return listarGrupos;
	}
	
	//EasyAcceptOK
	public Object getAtributoGrupoListaGrupos(int id, String nomeAtributo) throws Throwable{
		List<Grupo> listaGrupos = listarGrupos();
		for(Grupo Grupo: listaGrupos){
			if(Grupo.getIdGrupo() == id){
				return easyCorrectionUtil.getAtributo(Grupo, nomeAtributo, false); 
			}
		}
		return "Grupo Inexistente.";
	}

	//EasyAcceptOK
	public Grupo getGrupo(int idGrupo) throws Throwable {
		return facadeSistema.getGrupo(idGrupo);
	}
// ******************************************* Usuarios *****************************************
	
	//EasyAcceptOK
	public Object getAtributoGrupoUsuario(int id, String nomeAtributo) throws Throwable{
		GrupoUsuario objGrupoUsuario = getGrupoUsuario(id);
		if (nomeAtributo.equals("idGrupoUsuario")){
			return easyCorrectionUtil.getAtributo(objGrupoUsuario, nomeAtributo, false);
		}
		else{
			return easyCorrectionUtil.getAtributo(objGrupoUsuario.getUsuario(), nomeAtributo, false);
		}
	}
	
	//EasyAcceptOK
	public int cadastrarUsuario(int grupoId, String nome, String login, String senha, String email) throws Throwable{
		GrupoUsuario usuarioAux = new GrupoUsuario();
		usuarioAux.setGrupo(facadeSistema.getGrupo(grupoId));
		Usuario usu = new Usuario(login, nome, senha, email);
		usu.setIdUsuario(0);
		usuarioAux.setUsuario(usu);
		GrupoUsuario n = facadeSistema.cadastrarUsuario(usuarioAux);
		return n.getIdGrupoUsuario();
	}

	//EasyAcceptOK
	public int editarUsuario(int grupoUsuarioId, 
								int grupoId,
								String nome, 
								String login, 
								String senha,
								String email) throws Throwable {
		
		GrupoUsuario usuarioAux = new GrupoUsuario();
		usuarioAux.setIdGrupoUsuario(grupoUsuarioId);
		usuarioAux.setGrupo(facadeSistema.getGrupo(grupoId));
		Usuario us = facadeSistema.getUsuarioPorLogin(login);
		us.setNome(nome);
		us.setLogin(login);
		us.setSenha(senha);
		us.setEmail(email);
		usuarioAux.setUsuario(us);
		GrupoUsuario n = facadeSistema.cadastrarUsuario(usuarioAux);
		return n.getIdGrupoUsuario();
	}

	//EasyAcceptOK
	public void excluirUsuario(int idGrupoUsuario) throws Throwable {
		facadeSistema.excluirUsuario(facadeSistema.getGrupoUsuario(idGrupoUsuario));
	}
	
	//EasyAcceptOK
	public Object getAtributoGrupoUsuarioListaFuncoes(int id, String nomeAtributo) throws Throwable{
		List<GrupoUsuario> listaGrupoUsuarios = listarGrupoUsuarios();
		for(GrupoUsuario GrupoUsuario: listaGrupoUsuarios){
			if(GrupoUsuario.getIdGrupoUsuario() == id){
				return easyCorrectionUtil.getAtributo(GrupoUsuario, nomeAtributo, false); 
			}
		}
		return "grupoUsuario Inexistente.";
	}
	
	//EasyAcceptOK
	public Usuario getUsuario(int idUsuario) throws Throwable {
		return facadeSistema.getUsuario(idUsuario);
	}

	//EasyAcceptOK
	public List<GrupoUsuario> listarGrupoUsuarios() throws Throwable{
		List<GrupoUsuario> listarGrupoUsuarios = facadeSistema.listarGrupoUsuarios();
		return listarGrupoUsuarios;
	}
	
	//EasyAcceptOK
	public GrupoUsuario getGrupoUsuario(int idGrupoUsuario) throws Throwable {
		return facadeSistema.getGrupoUsuario(idGrupoUsuario);
	}
	
	//EasyAcceptOK
	public List<Usuario> listarUsuarios() throws Throwable{
		List<Usuario> listarFuncoes = facadeSistema.listarUsuarios();
		return listarFuncoes;
	}

	

}


