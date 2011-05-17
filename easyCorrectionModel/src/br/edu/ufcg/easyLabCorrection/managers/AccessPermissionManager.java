package br.edu.ufcg.easyLabCorrection.managers;

import java.util.LinkedList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.AuthenticationException;
import br.edu.ufcg.easyLabCorrection.exceptions.DuplicateValueException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Team;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Function;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.MD5Generator;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class AccessPermissionManager extends Manager {

	private AssignmentManager assignmentManager;
	private TeamManager teamManager;

	public AccessPermissionManager() {
		super();
		assignmentManager = new AssignmentManager();
		teamManager = new TeamManager();
	}

	public Menu getMenu(Integer id) {
		List<Menu> menu = DAOFactory.DEFAULT.buildMenuDAO().findById(id);
		if (menu.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("menu"));
		}
		return menu.get(0);
	}

	private Menu consultMenuByLabelAndName(String rotulo, String nome) {
		List<Menu> lista = DAOFactory.DEFAULT.buildMenuDAO()
				.findByNameAndLabel(nome, rotulo);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	// TODO Separar
	public Menu saveMenu(Menu menu) throws EasyCorrectionException {
		Menu m = new Menu();
		Menu men = new Menu();
		if (!easyCorrectionUtil.isNull(menu)) {
			if (easyCorrectionUtil.isNull(menu.getMenuId())
					|| menu.getMenuId().equals(new Integer(0))) {
				// Verifica se o rotulo ou o nome ja existe
				m = consultMenuByLabelAndName(menu.getName(), menu.getLabel());
				// Se o rotulo/nome nao existe e o id é null
				if (easyCorrectionUtil.isNull(m)) {
					Integer id = DAOFactory.DEFAULT.buildMenuDAO().save(menu);
					menu.setMenuId(id);
					// Se o rótulo existe
				} else if (!easyCorrectionUtil.isNull(m)) {
					throw new DuplicateValueException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
				// Se o id eh diferente de null
			} else {
				men = consultMenuByLabelAndName(menu.getName(), menu.getLabel());
				if (easyCorrectionUtil.isNull(men)) {
					men = getMenu(menu.getMenuId());
					men = (Menu) SwapperAtributosReflect.swapObject(men, menu,
							Menu.class);
					DAOFactory.DEFAULT.buildMenuDAO().update(men);
				} else if (menu.getMenuId().equals(men.getMenuId())) {
					men = (Menu) SwapperAtributosReflect.swapObject(men, menu,
							Menu.class);
					DAOFactory.DEFAULT.buildMenuDAO().update(men);
				} else {
					throw new DuplicateValueException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
			}
		}
		return menu;
	}

	public void deleteMenu(Menu menu) throws EasyCorrectionException {
		Menu m = getMenu(menu.getMenuId());
		m = (Menu) SwapperAtributosReflect.swapObject(m, menu, Menu.class);
		DAOFactory.DEFAULT.buildMenuDAO().delete(m);
	}

	public List<Menu> listOrderedMenus() {
		return DAOFactory.DEFAULT.buildMenuDAO().findAllByOrder();
	}

	public List<Menu> listMenus() {
		return DAOFactory.DEFAULT.buildMenuDAO().findAll();
	}

	/*
	 * FUNCTION
	 */
	// TODO: Separar em CreateFunction and UpdateFunction
	public Function saveFunction(Function function)
			throws EasyCorrectionException {
		Function f = new Function();
		Function fun = new Function();
		if (!easyCorrectionUtil.isNull(function)) {
			if (easyCorrectionUtil.isNull(function.getFunctionId())
					|| function.getFunctionId().equals(new Integer(0))) {
				// Verifica se o rótulo ou o nome já existe
				f = consultFunctionByNameAndLabel(function.getName(), function
						.getLabel());
				// Se o rótulo/nome não existe e e o id é null
				if (easyCorrectionUtil.isNull(f)) {
					Integer id = DAOFactory.DEFAULT.buildFunctionDAO().save(
							function);
					function.setFunctionId(id);
					// Se o rótulo existe
				} else if (!easyCorrectionUtil.isNull(f)) {
					throw new DuplicateValueException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
				// Se o id é diferente de null
			} else {
				fun = consultFunctionByNameAndLabel(function.getName(),
						function.getLabel());
				if (easyCorrectionUtil.isNull(fun)) {
					fun = getFunction(function.getFunctionId());
					fun = (Function) SwapperAtributosReflect.swapObject(fun,
							function, Function.class);
					DAOFactory.DEFAULT.buildFunctionDAO().update(fun);
				} else if (function.getFunctionId().equals(fun.getFunctionId())) {
					fun = (Function) SwapperAtributosReflect.swapObject(fun,
							function, Function.class);
					DAOFactory.DEFAULT.buildFunctionDAO().update(fun);
				} else {
					throw new DuplicateValueException(MsgErros.VALOR_DUPLICADO
							.msg("nome ou rotulo"));
				}
			}
		}
		return function;
	}

	public Function getFunction(Integer id) {
		List<Function> functions = DAOFactory.DEFAULT.buildFunctionDAO()
				.findById(id);
		if (functions.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("funcao"));
		}
		return functions.get(0);
	}

	private Function consultFunctionByNameAndLabel(String name, String label) {
		List<Function> list = DAOFactory.DEFAULT.buildFunctionDAO()
				.findByNameAndLabel(name, label);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	private boolean containsFunction(List<Permission> list, Function function) {
		for (Permission p : list) {
			if (p.getFunction().getFunctionId()
					.equals(function.getFunctionId())) {
				return true;
			}
		}
		return false;
	}

	public List<Function> listFunctions() {
		return DAOFactory.DEFAULT.buildFunctionDAO().findAll();
	}

	public void deleteFunction(Function function)
			throws EasyCorrectionException {
		Function f = getFunction(function.getFunctionId());
		f = (Function) SwapperAtributosReflect.swapObject(f, function,
				Function.class);
		DAOFactory.DEFAULT.buildFunctionDAO().delete(f);
	}

	public List<Function> consultFunctionsByMenu(Integer menuId) {
		return DAOFactory.DEFAULT.buildFunctionDAO().findByMenu(menuId);
	}

	/*
	 * GROUP
	 */
	public Group saveGroup(Group group) throws EasyCorrectionException {
		Group g = new Group();
		Group gr = new Group();
		if (!easyCorrectionUtil.isNull(group)) {
			if (easyCorrectionUtil.isNull(group.getGroupId())
					|| group.getGroupId().equals(new Integer(0))) {
				// Verifica se o nome já existe
				g = consultGroupByName(group.getName());
				// Se o nome não existe e e o id é null
				if (easyCorrectionUtil.isNull(g)) {
					Integer id = DAOFactory.DEFAULT.buildGroupDAO().save(group);
					group.setGroupId(id);
					// Se o rótulo existe
				} else if (!easyCorrectionUtil.isNull(g)) {
					throw new DuplicateValueException(MsgErros.VALOR_DUPLICADO
							.msg("nome"));
				}
				// Se o id é diferente de null
			} else {
				gr = consultGroupByName(group.getName());
				if (easyCorrectionUtil.isNull(gr)) {
					gr = getGroup(group.getGroupId());
					gr = (Group) SwapperAtributosReflect.swapObject(gr, group,
							Group.class);
					DAOFactory.DEFAULT.buildGroupDAO().update(gr);
				} else if (group.getGroupId().equals(gr.getGroupId())) {
					gr = (Group) SwapperAtributosReflect.swapObject(gr, group,
							Group.class);
					DAOFactory.DEFAULT.buildGroupDAO().update(gr);
				} else {
					throw new DuplicateValueException(MsgErros.VALOR_DUPLICADO
							.msg("nome"));
				}
			}
		}
		return group;
	}

	public Group getGroup(Integer id) {
		List<Group> group = DAOFactory.DEFAULT.buildGroupDAO().findById(id);
		if (group.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo"));
		}
		return group.get(0);
	}

	public List<Group> listGroups() {
		return DAOFactory.DEFAULT.buildGroupDAO().findAll();
	}

	public void deleteGroup(Group group) throws EasyCorrectionException {
		Group g = getGroup(group.getGroupId());
		g = (Group) SwapperAtributosReflect.swapObject(g, group, Group.class);
		DAOFactory.DEFAULT.buildGroupDAO().delete(g);
	}

	private Group consultGroupByName(String name) {
		List<Group> list = DAOFactory.DEFAULT.buildGroupDAO().findByName(name);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public Group getGroupByName(String name) {
		List<Group> list = DAOFactory.DEFAULT.buildGroupDAO().findByName(name);
		if (list.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo"));
		}
		return list.get(0);
	}

	/*
	 * PERMISSION
	 */

	public List<Permission> savePermission(List<Permission> permissions)
			throws EasyCorrectionException {
		Permission p = new Permission();
		List<Permission> list = new LinkedList<Permission>();
		for (Permission permission : permissions) {
			if (!easyCorrectionUtil.isNull(permission)
					&& easyCorrectionUtil.isNull(permission.getPermissionId())) {
				Integer id = DAOFactory.DEFAULT.buildPermissionDAO().save(
						permission);
				permission.setPermissionId(id);
				list.add(permission);
			} else {
				try {
					p = getPermission(permission.getPermissionId());
					p = (Permission) SwapperAtributosReflect.swapObject(p,
							permission, Permission.class);
					DAOFactory.DEFAULT.buildPermissionDAO().update(p);
					list.add(p);

				} catch (ObjectNotFoundException e) {
					Integer id = DAOFactory.DEFAULT.buildPermissionDAO().save(
							permission);
					permission.setPermissionId(id);
					list.add(permission);
				}
			}
		}
		return list;
	}

	public List<Permission> saveGroupPermission(Group g, List<Function> list) {
		List<Permission> permissaoDoGrupoBanco = DAOFactory.DEFAULT
				.buildPermissionDAO().findByGroupId(g.getGroupId());
		List<Permission> newList = new LinkedList<Permission>();
		// criando a lista de Permissao
		for (Function f : list) {
			Permission p = new Permission();
			p.setFunction(f);
			p.setGroup(g);
			newList.add(p);
		}
		// gravando as novas permissoes
		for (Permission addPermission : newList) {
			if (!containsFunction(permissaoDoGrupoBanco, addPermission
					.getFunction())) {
				Integer id = DAOFactory.DEFAULT.buildPermissionDAO().save(
						addPermission);
				addPermission.setPermissionId(id);
			} else {
				List<Permission> anotherP = DAOFactory.DEFAULT
						.buildPermissionDAO().findByGroupAndFunction(
								g.getGroupId(),
								addPermission.getFunction().getFunctionId());
				addPermission = anotherP.get(0); // eh garantido que a lista não
				// é vazia
			}
		}
		// removendo as permissoes não passada na lista
		for (Permission delPermissao : permissaoDoGrupoBanco) {
			if (!containsFunction(newList, delPermissao.getFunction())) {
				DAOFactory.DEFAULT.buildPermissionDAO().delete(delPermissao);
			}
		}

		return newList;
	}

	public Permission getPermission(Integer id) {
		List<Permission> permissions = DAOFactory.DEFAULT.buildPermissionDAO()
				.findById(id);
		if (permissions.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("permissao"));
		}
		return permissions.get(0);
	}

	private List<Function> verifyPermissions(Integer userId) {
		List<Function> functionsList = new LinkedList<Function>();
		List<UserGroup> GUlist = DAOFactory.DEFAULT.buildUserGroupDAO()
				.findByUserId(userId);
		if (!GUlist.isEmpty()) {
			for (UserGroup gU : GUlist) {
				List<Permission> pList = DAOFactory.DEFAULT
						.buildPermissionDAO().findByGroupId(
								gU.getGroup().getGroupId());
				if (!pList.isEmpty()) {
					for (Permission p : pList) {
						if (!verifyRepeated(functionsList, p.getFunction())) {
							functionsList.add(p.getFunction());
						}
					}
				}
			}
		}
		return functionsList;
	}

	public List<Permission> consultPermissionsByGroup(Integer idGrupo) {
		return DAOFactory.DEFAULT.buildPermissionDAO().findByGroupId(idGrupo);
	}

	/*
	 * USER GROUP
	 */
	public UserGroup getUserGroup(Integer id) {
		List<UserGroup> userGroup = DAOFactory.DEFAULT.buildUserGroupDAO()
				.findById(id);
		if (userGroup.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario grupo"));
		}
		return userGroup.get(0);
	}

	public UserGroup saveUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {
		UserGroup ugs = new UserGroup();
		if (!easyCorrectionUtil.isNull(userGroup)) {
			try {
				ugs = getUserGroupByGroupAndUser(userGroup.getGroup()
						.getGroupId(), userGroup.getUser().getUserId());
				ugs = (UserGroup) SwapperAtributosReflect.swapObject(ugs,
						userGroup, UserGroup.class);
				DAOFactory.DEFAULT.buildUserGroupDAO().update(ugs);
			} catch (ObjectNotFoundException e) {
				Integer id = DAOFactory.DEFAULT.buildUserGroupDAO().save(
						userGroup);
				userGroup.setUserGroupId(id);
			}
		}
		return userGroup;
	}

	public List<UserGroup> listUserGroups() {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findAllUserGroup();
	}

	public List<UserGroup> listUserGroupsByGroup(String groupName) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(groupName);
	}

	public User getUser(Integer id) {
		List<User> users = DAOFactory.DEFAULT.buildUserDAO().findById(id);
		if (users.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return users.get(0);
	}

	public User getUserByLogin(String login) {
		List<User> users = DAOFactory.DEFAULT.buildUserDAO().findByLogin(login);
		if (users.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return users.get(0);
	}

	private UserGroup getUserGroupByGroupAndUser(Integer groupId, Integer userId) {
		List<UserGroup> list = DAOFactory.DEFAULT.buildUserGroupDAO()
				.findByUserAndGroup(groupId, userId);
		if (list.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo usuario"));
		}
		return list.get(0);
	}

	public List<UserGroup> getUserGroupByUser(Integer userId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByUserId(userId);
	}

	/*
	 * USER
	 */
	public User consultUserByLogin(String login) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO().findByLogin(login);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	private User getUserByEmail(String email) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO().findByEmail(email);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	private void verifyIfUsersNumberGreaterThenTeamsNumber(UserGroup userGroup)
			throws EasyCorrectionException {
		Team team = new Team();
		if (userGroup.getGroup().getName().equalsIgnoreCase("Aluno")) {
			List<UserGroup> gu = DAOFactory.DEFAULT.buildUserGroupDAO()
					.findByGroup("Aluno");
			List<Team> teams = teamManager.getTeams();
			int userNumber = gu.size() + 1;
			if (userNumber > teams.size()) {
				for (int i = 0; i < 5; i++) {
					teams = teamManager.getTeams();
					if (teams.isEmpty()) {
						team.setName("Team 1");
					} else {
						int index = teams.get(teams.size() - 1).getId() + 1;
						team.setName("Team " + index);
					}
					Team t = teamManager.saveTeam(team);
					if (i == 0) {
						allocateUserToTeam(userGroup.getUser(), t);
					}
				}
			} else {
				allocateUserToTeam(userGroup.getUser());
			}
		}
	}

	// TODO: MOVE TO System
	private void allocateUserToTeam(User us, Team te)
			throws EasyCorrectionException {
		List<Assignment> assigns = assignmentManager.listAssignments();
		for (Assignment roteiro : assigns) {
			TeamHasUserHasAssignment tua = new TeamHasUserHasAssignment();
			tua.setTeam(te);
			tua.setUser(us);
			tua.setAssignment(roteiro);
			teamManager.saveTeamHasUserHasAssignment(tua);
		}
	}

	// TODO: MOVE TO System
	private void allocateUserToTeam(User us) throws EasyCorrectionException {
		List<Assignment> assigns = assignmentManager.listAssignments();
		for (Assignment assignment : assigns) {
			List<TeamHasUserHasAssignment> eurs = teamManager
					.getTeamHasUserHasAssignmentByAssignment(assignment.getId());
			Team eq = teamManager.getTeams().get(0);
			if (eurs.size() > 0) {
				int novoId = eurs.get(eurs.size() - 1).getTeam().getId() + 1;
				eq = teamManager.getTeam(novoId);
			}
			TeamHasUserHasAssignment eur = new TeamHasUserHasAssignment();
			eur.setTeam(eq);
			eur.setUser(us);
			eur.setAssignment(assignment);
			teamManager.saveTeamHasUserHasAssignment(eur);
		}
	}

	public UserGroup saveUser(UserGroup userGroup)
			throws EasyCorrectionException {
		User u = new User();
		User us = new User();
		User use = new User();

		if (easyCorrectionUtil.isNull(userGroup)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O GrupoUsuario"));
		}
		if (easyCorrectionUtil.isNull(userGroup.getUser())) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O Usuario"));
		}
		if (easyCorrectionUtil.isNull(userGroup.getGroup())) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O Grupo"));
		}

		if (!easyCorrectionUtil.isNull(userGroup.getUser())) {
			if (!userGroup.getUser().getUserId().equals(new Integer(0))) {
				u = getUser(userGroup.getUser().getUserId());
				if (!u.getPassword().equals(userGroup.getUser().getPassword())) {
					// Gera o md5 da senha
					String password = MD5Generator.md5(userGroup.getUser()
							.getPassword());
					userGroup.getUser().setPassword(password);
				}
			} else {
				// Gera o md5 da senha
				String password = "";
				if (userGroup.getUser().getPassword().equals("")) {
					password = MD5Generator.md5(userGroup.getUser().getLogin());
				} else {
					password = MD5Generator.md5(userGroup.getUser()
							.getPassword());
				}
				userGroup.getUser().setPassword(password);
			}
			// Cadastrar o usuário
			u = consultUserByLogin(userGroup.getUser().getLogin());
			use = getUserByEmail(userGroup.getUser().getEmail());
			// Se o login não existe e e o id é null
			if (easyCorrectionUtil.isNull(u) && easyCorrectionUtil.isNull(use)) {
				try {
					us = getUser(userGroup.getUser().getUserId());
					us = (User) SwapperAtributosReflect.swapObject(us,
							userGroup.getUser(), User.class);
					DAOFactory.DEFAULT.buildUserDAO().update(us);
					userGroup.getUser().setUserId(us.getUserId());
				} catch (ObjectNotFoundException e) {
					Integer id = DAOFactory.DEFAULT.buildUserDAO().save(
							userGroup.getUser());
					userGroup.getUser().setUserId(id);
					verifyIfUsersNumberGreaterThenTeamsNumber(userGroup);
				}
			} else {
				try {
					us = getUser(userGroup.getUser().getUserId());
					if (!easyCorrectionUtil.isNull(u)) {
						if (!userGroup.getUser().getUserId().equals(
								u.getUserId())) {
							throw new ObjectNotFoundException(MsgErros.LOGIN
									.msg(""));
						}
					}
					if (!easyCorrectionUtil.isNull(use)) {
						if (!userGroup.getUser().getUserId().equals(
								use.getUserId())) {
							throw new ObjectNotFoundException(MsgErros.EMAIL
									.msg(""));
						}
					}
					us = (User) SwapperAtributosReflect.swapObject(us,
							userGroup.getUser(), User.class);
					DAOFactory.DEFAULT.buildUserDAO().update(us);
					userGroup.getUser().setUserId(us.getUserId());
				} catch (ObjectNotFoundException e) {
					if (!easyCorrectionUtil.isNull(u)) {
						throw new ObjectNotFoundException(MsgErros.LOGIN
								.msg(""));
					}
					if (!easyCorrectionUtil.isNull(use)) {
						throw new ObjectNotFoundException(MsgErros.EMAIL
								.msg(""));
					}
				}
			}

		}
		// Cadastra o grupoUsuário do usuário
		saveUserGroup(userGroup);
		return userGroup;
	}

	public User updateUser(User user) throws EasyCorrectionException {

		if (easyCorrectionUtil.isNull(user)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O Usuario"));
		}
		// Gera o md5 da senha
		String password = MD5Generator.md5(user.getPassword());
		user.setPassword(password);

		User us = getUser(user.getUserId());
		us = (User) SwapperAtributosReflect.swapObject(us, user, User.class);
		DAOFactory.DEFAULT.buildUserDAO().update(us);
		return us;
	}

	public void deleteUser(UserGroup userGroup) throws EasyCorrectionException {

		List<Assignment> assigns = assignmentManager.listAssignments();
		if (userGroup.getGroup().getName().equals("Aluno")
				&& assigns.size() > 0) {
			throw new EasyCorrectionException(MsgErros.USUARIO_ALOCADO
					.msg("O GrupoUsuario"));
		}

		if (easyCorrectionUtil.isNull(userGroup)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O GrupoUsuario"));
		}

		// Exclui grupoUsuario
		UserGroup ug = getUserGroup(userGroup.getUserGroupId());
		ug = (UserGroup) SwapperAtributosReflect.swapObject(ug, userGroup,
				UserGroup.class);
		DAOFactory.DEFAULT.buildUserGroupDAO().delete(ug);

		// Exclui usuário
		User u = getUser(userGroup.getUser().getUserId());
		u = (User) SwapperAtributosReflect.swapObject(u, userGroup.getUser(),
				User.class);
		DAOFactory.DEFAULT.buildUserDAO().delete(u);
	}

	public List<User> listUsers() {
		return DAOFactory.DEFAULT.buildUserDAO().findAll();
	}

	public List<Function> validateUser(User user) {
		List<Function> functions = new LinkedList<Function>();

		// Gera o md5 da senha
		String password = MD5Generator.md5(user.getPassword());
		user.setPassword(password);

		// Verifica se o login e senha são válidas
		User u = verifyLoginAndPassword(user);
		if (!easyCorrectionUtil.isNull(u)) {
			// Verifica as permissões do usuário e retorna um conjunto de
			// funções
			functions = verifyPermissions(u.getUserId());
		}
		return functions;
	}

	private User verifyLoginAndPassword(User user) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO()
				.findByLoginAndPassword(user.getLogin(), user.getPassword());
		if (list.isEmpty()) {
			throw new AuthenticationException(MsgErros.AUTENTICACAO.msg());
		}
		return list.get(0);

	}

	private boolean verifyRepeated(List<Function> list, Function function) {
		for (Function f : list) {
			if (f.equals(function)) {
				return true;
			}
		}
		return false;
	}

	public List<UserGroup> consultUserByGroup(Integer groupId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(groupId);
	}

	public User changePassword(User user, String newPassword) {
		// Gera o md5 da senha
		String password = MD5Generator.md5(newPassword);
		User bdUser = getUserByLogin(user.getLogin());

		if (!easyCorrectionUtil.isNull(bdUser)
				&& bdUser.getPassword().equals(user.getPassword())) {
			bdUser.setPassword(password);
			DAOFactory.DEFAULT.buildUserDAO().update(bdUser);
		} else {
			throw new ObjectNotFoundException(MsgErros.AUTENTICACAO.msg());
		}
		return bdUser;
	}

}
