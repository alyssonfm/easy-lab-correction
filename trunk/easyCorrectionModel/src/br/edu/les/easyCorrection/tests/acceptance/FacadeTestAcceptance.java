package br.edu.les.easyCorrection.tests.acceptance;

import java.util.List;
import br.edu.les.easyCorrection.pojo.acesso.*;
import br.edu.les.easyCorrection.sistema.Facade;

public class FacadeTestAcceptance {
	
	private Facade facadeSistema;

	public FacadeTestAcceptance() {
		facadeSistema = new Facade();
	}

// ******************************************* Menus ******************************************
	
	//EasyAcceptOK
	public Menu cadastrarMenu(String nomeMenu, String rotulo)
			throws Throwable{
		Menu menuAux = new Menu();
		menuAux.setNome(nomeMenu);
		menuAux.setRotulo(rotulo);
		Menu n = facadeSistema.cadastrarMenu(menuAux);
		return n;
	}

	//EasyAcceptOK
	public Menu editarMenu(int id, String nomeMenu,
			String rotulo) throws Throwable {
		Menu menuAux = new Menu();
		menuAux.setIdMenu(id);
		menuAux.setNome(nomeMenu);
		menuAux.setRotulo(rotulo);
		Menu n = facadeSistema.cadastrarMenu(menuAux);
		return n;
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
	public Menu getMenu(int idMenu) throws Throwable {
		return facadeSistema.getMenu(idMenu);
	}

// ******************************************* Funcoes *****************************************
	
	//EasyAcceptOK
	public Funcao cadastrarFuncao(String nomeFuncao, String rotulo, Menu menu)
			throws Throwable{
		Funcao funcaoAux = new Funcao();
		funcaoAux.setNome(nomeFuncao);
		funcaoAux.setRotulo(rotulo);
		funcaoAux.setMenu(menu);
		Funcao n = facadeSistema.cadastrarFuncao(funcaoAux);
		return n;
	}

	//EasyAcceptOK
	public Funcao editarFuncao(int id, String nomeFuncao,
			String rotulo, Menu menu) throws Throwable {
		Funcao funcaoAux = new Funcao();
		funcaoAux.setIdFuncao(id);
		funcaoAux.setNome(nomeFuncao);
		funcaoAux.setRotulo(rotulo);
		funcaoAux.setMenu(menu);
		Funcao n = facadeSistema.cadastrarFuncao(funcaoAux);
		return n;
	}

	//EasyAcceptOK
	public void excluirFuncao(int idFuncao) throws Throwable {
		facadeSistema.excluirFuncao(facadeSistema.getFuncao(idFuncao));
	}

	//EasyAcceptOK
	public List<Funcao> listarFuncoes() throws Throwable{
		List<Funcao> listarFuncoes = facadeSistema.listarFuncoes();
		return listarFuncoes;
	}

	//EasyAcceptOK
	public Funcao getFuncao(int idFuncao) throws Throwable {
		return facadeSistema.getFuncao(idFuncao);
	}
	
//******************************************* Grupos ******************************************
	
	//EasyAcceptOK
	public Grupo cadastrarGrupo(String nomeGrupo) throws Throwable{
		Grupo grupoAux = new Grupo();
		grupoAux.setNome(nomeGrupo);
		Grupo n = facadeSistema.cadastrarGrupo(grupoAux);
		return n;
	}

	//EasyAcceptOK
	public Grupo editarGrupo(String nomeGrupo) throws Throwable {
		Grupo grupoAux = new Grupo();
		grupoAux.setNome(nomeGrupo);
		Grupo n = facadeSistema.cadastrarGrupo(grupoAux);
		return n;
	}

	//EasyAcceptOK
	public void excluirGrupo(int idGrupo) throws Throwable {
		facadeSistema.excluirGrupo(facadeSistema.getGrupo(idGrupo));
	}

	//EasyAcceptOK
	public List<Grupo> listarGrupos() throws Throwable{
		List<Grupo> listarFuncoes = facadeSistema.listarGrupos();
		return listarFuncoes;
	}

	//EasyAcceptOK
	public Grupo getGrupo(int idGrupo) throws Throwable {
		return facadeSistema.getGrupo(idGrupo);
	}

// ******************************************* Usuarios *****************************************
	
	//EasyAcceptOK
	public GrupoUsuario cadastrarUsuario(int grupoId, int idUsuario) throws Throwable{
		GrupoUsuario usuarioAux = new GrupoUsuario();
		usuarioAux.setGrupo(facadeSistema.getGrupo(grupoId));
		usuarioAux.setUsuario(facadeSistema.getUsuario(idUsuario));
		GrupoUsuario n = facadeSistema.cadastrarUsuario(usuarioAux);
		return n;
	}

	//EasyAcceptOK
	public GrupoUsuario editarUsuario(int grupoUsuarioId, int grupoId, int idUsuario) throws Throwable {
		GrupoUsuario usuarioAux = new GrupoUsuario();
		usuarioAux.setIdGrupoUsuario(grupoUsuarioId);
		usuarioAux.setGrupo(facadeSistema.getGrupo(grupoId));
		usuarioAux.setUsuario(facadeSistema.getUsuario(idUsuario));
		GrupoUsuario n = facadeSistema.cadastrarUsuario(usuarioAux);
		return n;
	}

	//EasyAcceptOK
	public void excluirUsuario(int idGrupoUsuario) throws Throwable {
		facadeSistema.excluirUsuario(facadeSistema.getGrupoUsuario(idGrupoUsuario));
	}

	//EasyAcceptOK
	public List<Usuario> listarUsuarios() throws Throwable{
		List<Usuario> listarFuncoes = facadeSistema.listarUsuarios();
		return listarFuncoes;
	}

	//EasyAcceptOK
	public Usuario getUsuario(int idUsuario) throws Throwable {
		return facadeSistema.getUsuario(idUsuario);
	}

}


