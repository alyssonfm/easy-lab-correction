package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory02;

import java.util.List;

import br.edu.ufcg.easyLabCorrection.pojo.permission.*;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.system.Facade;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class FacadeAcceptanceTestUS02{
	
	protected Facade facadeSistema;

	public FacadeAcceptanceTestUS02() {
		facadeSistema = new Facade();
	}

	public void reinicializaBancoDeDados(){
		facadeSistema.rebootsDataBase();
	}
	
// ******************************************* Menus ******************************************
	
	//EasyAcceptOK
	public Object getAtributoMenu(int id, String nomeAtributo) throws Throwable{
		Menu objMenu = getMenu(id);
		return easyCorrectionUtil.getAttribute(objMenu, nomeAtributo, false);
	}
	
	//EasyAcceptOK
	public int cadastrarMenu(String nomeMenu, String rotulo)
			throws Throwable{
		Menu menuAux = new Menu();
		menuAux.setName(nomeMenu);
		menuAux.setLabel(rotulo);
		Menu n = facadeSistema.saveMenu(menuAux);
		return n.getMenuId();
	}

	//EasyAcceptOK
	public int editarMenu(int id, String nomeMenu, String rotulo) throws Throwable {
		Menu menuAux = new Menu();
		menuAux.setMenuId(id);
		menuAux.setName(nomeMenu);
		menuAux.setLabel(rotulo);
		Menu n = facadeSistema.saveMenu(menuAux);
		return n.getMenuId();
	}

	//EasyAcceptOK
	public void excluirMenu(int idMenu) throws Throwable {
		facadeSistema.removeMenu(facadeSistema.getMenu(idMenu));
	}

	//EasyAcceptOK
	public List<Menu> listarMenus() throws Throwable{
		List<Menu> listarMenus = facadeSistema.menuList();
		return listarMenus;
	}
	
	//EasyAcceptOK
	public Object getAtributoMenuListaMenus(int id, String nomeAtributo) throws Throwable{
		List<Menu> listaMenus = listarMenus();
		for(Menu menu: listaMenus){
			if(menu.getMenuId() == id){
				return easyCorrectionUtil.getAttribute(menu, nomeAtributo, false); 
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
		Function objFuncao = getFuncao(id);
		return easyCorrectionUtil.getAttribute(objFuncao, nomeAtributo, false);
	}
	
	//EasyAcceptOK
	public int cadastrarFuncao(String nomeFuncao, String rotulo, int idMenu)
			throws Throwable{
		Function funcaoAux = new Function();
		funcaoAux.setName(nomeFuncao);
		funcaoAux.setLabel(rotulo);
		funcaoAux.setMenu(getMenu(idMenu));
		Function n = facadeSistema.saveFunction(funcaoAux);
		return n.getFunctionId();
	}
	
	//EasyAcceptOK
	public int editarFuncao(int id, String nomeFuncao, String rotulo, int idMenu) throws Throwable {
		Function funcaoAux = new Function();
		funcaoAux.setFunctionId(id);
		funcaoAux.setName(nomeFuncao);
		funcaoAux.setLabel(rotulo);
		funcaoAux.setMenu(getMenu(idMenu));
		Function n = facadeSistema.saveFunction(funcaoAux);
		return n.getFunctionId();
	}

	//EasyAcceptOK
	public void excluirFuncao(int idFuncao) throws Throwable {
		facadeSistema.removeFunction(facadeSistema.getFunction(idFuncao));
	}

	//EasyAcceptOK
	public List<Function> listarFuncaos() throws Throwable{
		List<Function> listarFuncaos = facadeSistema.functionList();
		return listarFuncaos;
	}
	
	//EasyAcceptOK
	public Object getAtributoFuncaoListaFuncoes(int id, String nomeAtributo) throws Throwable{
		List<Function> listaFuncaos = listarFuncaos();
		for(Function funcao: listaFuncaos){
			if(funcao.getFunctionId() == id){
				return easyCorrectionUtil.getAttribute(funcao, nomeAtributo, false); 
			}
		}
		return "Funcao Inexistente.";
	}

	//EasyAcceptOK
	public Function getFuncao(int idFuncao) throws Throwable {
		return facadeSistema.getFunction(idFuncao);
	}
	
//******************************************* Grupos ******************************************
	
	//EasyAcceptOK
	public Object getAtributoGrupo(int id, String nomeAtributo) throws Throwable{
		Group objGrupo = getGrupo(id);
		return easyCorrectionUtil.getAttribute(objGrupo, nomeAtributo, false);
	}
	
	//EasyAcceptOK
	public int cadastrarGrupo(String nomeGrupo)
			throws Throwable{
		Group grupoAux = new Group();
		grupoAux.setName(nomeGrupo);
		Group n = facadeSistema.saveGroup(grupoAux);
		return n.getGroupId();
	}

	//EasyAcceptOK
	public int editarGrupo(int id, String nomeGrupo) throws Throwable {
		Group grupoAux = new Group();
		grupoAux.setGroupId(id);
		grupoAux.setName(nomeGrupo);
		Group n = facadeSistema.saveGroup(grupoAux);
		return n.getGroupId();
	}

	//EasyAcceptOK
	public void excluirGrupo(int idGrupo) throws Throwable {
		facadeSistema.removeGroup(facadeSistema.getGroup(idGrupo));
	}

	//EasyAcceptOK
	public List<Group> listarGrupos() throws Throwable{
		List<Group> listarGrupos = facadeSistema.groupList();
		return listarGrupos;
	}
	
	//EasyAcceptOK
	public Object getAtributoGrupoListaGrupos(int id, String nomeAtributo) throws Throwable{
		List<Group> listaGrupos = listarGrupos();
		for(Group Grupo: listaGrupos){
			if(Grupo.getGroupId() == id){
				return easyCorrectionUtil.getAttribute(Grupo, nomeAtributo, false); 
			}
		}
		return "Grupo Inexistente.";
	}

	//EasyAcceptOK
	public Group getGrupo(int idGrupo) throws Throwable {
		return facadeSistema.getGroup(idGrupo);
	}
// ******************************************* Usuarios *****************************************
	
	//EasyAcceptOK
	public Object getAtributoGrupoUsuario(int id, String nomeAtributo) throws Throwable{
		UserGroup objGrupoUsuario = getGrupoUsuario(id);
		if (nomeAtributo.equals("idGrupoUsuario")){
			return easyCorrectionUtil.getAttribute(objGrupoUsuario, nomeAtributo, false);
		}
		else{
			return easyCorrectionUtil.getAttribute(objGrupoUsuario.getUser(), nomeAtributo, false);
		}
	}
	
	//EasyAcceptOK
	public int cadastrarUsuario(int grupoId, String nome, String login, String senha, String email) throws Throwable{
		UserGroup usuarioAux = new UserGroup();
		usuarioAux.setGroup(facadeSistema.getGroup(grupoId));
		User usu = new User(login, nome, senha, email);
		usu.setUserId(0);
		usuarioAux.setUser(usu);
		UserGroup n = facadeSistema.saveUser(usuarioAux);
		return n.getUserGroupId();
	}

	//EasyAcceptOK
	public int editarUsuario(int grupoUsuarioId, 
								int grupoId,
								String nome, 
								String login, 
								String senha,
								String email) throws Throwable {
		
		UserGroup usuarioAux = new UserGroup();
		usuarioAux.setUserGroupId(grupoUsuarioId);
		usuarioAux.setGroup(facadeSistema.getGroup(grupoId));
		User us = facadeSistema.getUserByLogin(login);
		us.setName(nome);
		us.setLogin(login);
		us.setPassword(senha);
		us.setEmail(email);
		usuarioAux.setUser(us);
		UserGroup n = facadeSistema.saveUser(usuarioAux);
		return n.getUserGroupId();
	}

	//EasyAcceptOK
	public void excluirUsuario(int idGrupoUsuario) throws Throwable {
		facadeSistema.removeUserGroup(facadeSistema.getUserGroup(idGrupoUsuario));
	}
	
	//EasyAcceptOK
	public Object getAtributoGrupoUsuarioListaFuncoes(int id, String nomeAtributo) throws Throwable{
		List<UserGroup> listaGrupoUsuarios = listarGrupoUsuarios();
		for(UserGroup GrupoUsuario: listaGrupoUsuarios){
			if(GrupoUsuario.getUserGroupId() == id){
				return easyCorrectionUtil.getAttribute(GrupoUsuario, nomeAtributo, false); 
			}
		}
		return "grupoUsuario Inexistente.";
	}
	
	//EasyAcceptOK
	public User getUsuario(int idUsuario) throws Throwable {
		return facadeSistema.getUser(idUsuario);
	}

	//EasyAcceptOK
	public List<UserGroup> listarGrupoUsuarios() throws Throwable{
		List<UserGroup> listarGrupoUsuarios = facadeSistema.listUserGroups();
		return listarGrupoUsuarios;
	}
	
	//EasyAcceptOK
	public UserGroup getGrupoUsuario(int idGrupoUsuario) throws Throwable {
		return facadeSistema.getUserGroup(idGrupoUsuario);
	}
	
	//EasyAcceptOK
	public List<User> listarUsuarios() throws Throwable{
		List<User> listarFuncoes = facadeSistema.listUsers();
		return listarFuncoes;
	}

	

}


