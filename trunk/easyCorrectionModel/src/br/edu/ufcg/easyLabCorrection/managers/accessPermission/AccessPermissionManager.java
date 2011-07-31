package br.edu.ufcg.easyLabCorrection.managers.accessPermission;

import java.util.LinkedList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.DuplicatedValueException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.managers.Manager;
import br.edu.ufcg.easyLabCorrection.pojo.permission.MenuFunction;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.InternalErrorMsgs;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing access and setting permissions on the system
 * Easy Lab Correction.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class AccessPermissionManager extends Manager {

	public AccessPermissionManager() {
		super();
	}

	/**
	 * Function used to search through a menu system via a identifier.<br>
	 * 
	 * @param id
	 *            The identifier used for search a menu in the system.<br>
	 * @return The menu with the identifier passed as parameter.<br>
	 */
	public Menu getMenu(Integer id) {
		List<Menu> menu = DAOFactory.DEFAULT.buildMenuDAO().findById(id);
		if (menu.isEmpty()) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("menu"));
		}
		return menu.get(0);
	}

	/**
	 * Function used to search through a menu system via a label and a name.<br>
	 * 
	 * @param rotulo
	 *            The label of menu to be researched in the system.<br>
	 * @param nome
	 *            The name of menu to be researched in the system.<br>
	 * @return A menu if any one menu system that has the same label and name
	 *         passed as parameter, null otherwise.<br>
	 */
	private Menu consultMenuByLabelAndName(String rotulo, String nome) {
		List<Menu> lista = DAOFactory.DEFAULT.buildMenuDAO()
				.findByNameAndLabel(nome, rotulo);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Function used to stored a new menu in the system ELC.<br>
	 * 
	 * @return menu The new menu stored in the system.<br>
	 */
	public Menu saveMenu(Menu menu) throws EasyCorrectionException {
		Menu m = new Menu();
		if (menu == null){
			throw new EasyCorrectionException(InternalErrorMsgs.ATRIBUTO_INVALIDO.msg("null"));
		}else{
			if (easyCorrectionUtil.isNull(menu.getMenuId())
					|| menu.getMenuId().equals(new Integer(0))) {

				m = consultMenuByLabelAndName(menu.getName(), menu.getLabel());

				if (easyCorrectionUtil.isNull(m)) {
					Integer id = DAOFactory.DEFAULT.buildMenuDAO().save(menu);
					menu.setMenuId(id);
				} else {
					throw new DuplicatedValueException(
							InternalErrorMsgs.VALOR_DUPLICADO.msg("nome ou rotulo"));
				}
			} else {
				throw new EasyCorrectionException(InternalErrorMsgs.ATRIBUTO_INVALIDO
						.msg("id"));
			}
		}
		return menu;
	}

	public Menu updateMenu(Menu menu) throws EasyCorrectionException {
		Menu men = new Menu();
		if (!easyCorrectionUtil.isNull(menu)) {
			if (!(easyCorrectionUtil.isNull(menu.getMenuId()) || menu
					.getMenuId().equals(new Integer(0)))) {
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
					throw new DuplicatedValueException(
							InternalErrorMsgs.VALOR_DUPLICADO.msg("nome ou rotulo"));
				}
			}
		}
		return menu;
	}

	/**
	 * Procedure used to remove a menu of the system Easy Lab Correction.<br>
	 * 
	 * @param menu
	 *            The menu to be removed.<br>
	 * @throws EasyCorrectionException
	 *             The exception that can launched in the removal.<br>
	 */
	public void deleteMenu(Menu menu) throws EasyCorrectionException {
		if (menu == null) {
			throw new EasyCorrectionException(InternalErrorMsgs.VALORINVALIDO
					.msg("null"));
		}
		Menu m = getMenu(menu.getMenuId());
		m = (Menu) SwapperAtributosReflect.swapObject(m, menu, Menu.class);
		DAOFactory.DEFAULT.buildMenuDAO().delete(m);
	}

	/**
	 * Function used to list all menus of the system ELC orderly way.<br>
	 * 
	 * @return All menus of the system orderly.<br>
	 */
	public List<Menu> listOrderedMenus() {
		return DAOFactory.DEFAULT.buildMenuDAO().findAllByOrder();
	}

	/**
	 * Function used to list all menus of the system ELC.<br>
	 * 
	 * @return All menus of the system.<br>
	 */
	public List<Menu> listMenus() {
		return DAOFactory.DEFAULT.buildMenuDAO().findAll();
	}

	/*
	 * FUNCTION
	 */
	/**
	 * Function used to stored a new function in the system ELC.<br>
	 * 
	 * @return menu The new function stored in the system.<br>
	 */
	public MenuFunction saveFunction(MenuFunction function)
			throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(function)) {
			if (easyCorrectionUtil.isNull(function.getFunctionId())
					|| function.getFunctionId().equals(new Integer(0))) {
				
				MenuFunction f = consultFunctionByNameAndLabel(function
						.getName(), function.getLabel());
				
				if (easyCorrectionUtil.isNull(f)) {
					Integer id = DAOFactory.DEFAULT.buildFunctionDAO().save(
							function);
					function.setFunctionId(id);
					
				} else if (!easyCorrectionUtil.isNull(f)) {
					throw new DuplicatedValueException(
							InternalErrorMsgs.VALOR_DUPLICADO.msg("nome ou rotulo"));
				}
			}
		}
		return function;
	}

	public MenuFunction updateFunction(MenuFunction function)
			throws EasyCorrectionException {

		if (!easyCorrectionUtil.isNull(function)) {
			if (!(easyCorrectionUtil.isNull(function.getFunctionId()) || function
					.getFunctionId().equals(new Integer(0)))) {
				MenuFunction fun = consultFunctionByNameAndLabel(function
						.getName(), function.getLabel());
				if (easyCorrectionUtil.isNull(fun)) {
					fun = getFunction(function.getFunctionId());
					fun = (MenuFunction) SwapperAtributosReflect.swapObject(
							fun, function, MenuFunction.class);
					DAOFactory.DEFAULT.buildFunctionDAO().update(fun);
				} else if (function.getFunctionId().equals(fun.getFunctionId())) {
					fun = (MenuFunction) SwapperAtributosReflect.swapObject(
							fun, function, MenuFunction.class);
					DAOFactory.DEFAULT.buildFunctionDAO().update(fun);
				} else {
					throw new DuplicatedValueException(
							InternalErrorMsgs.VALOR_DUPLICADO.msg("nome ou rotulo"));
				}
			}
		}
		return function;
	}

	/**
	 * Function used to search through a function system via a identifier.<br>
	 * 
	 * @param id
	 *            The identifier used for search a function in the system.<br>
	 * @return The function with the identifier passed as parameter.<br>
	 */
	public MenuFunction getFunction(Integer id) {
		List<MenuFunction> functions = DAOFactory.DEFAULT.buildFunctionDAO()
				.findById(id);
		if (functions.isEmpty()) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("funcao"));
		}
		return functions.get(0);
	}

	/**
	 * Function used to search through a function system via a label and a name.<br>
	 * 
	 * @param rotulo
	 *            The label of function to be researched in the system.<br>
	 * @param nome
	 *            The name of function to be researched in the system.<br>
	 * @return A menu if any one function system that has the same label and
	 *         name passed as parameter, null otherwise.<br>
	 */
	private MenuFunction consultFunctionByNameAndLabel(String name, String label) {
		List<MenuFunction> list = DAOFactory.DEFAULT.buildFunctionDAO()
				.findByNameAndLabel(name, label);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Function used to verify the existence of function a list of permissions.<br>
	 * 
	 * @param list
	 *            The list of permissions used in search.<br>
	 * @param function
	 *            The function it is verifying the existence.<br>
	 * @return A boolean value: true - if exist; false - otherwise.<br>
	 */
	private boolean containsFunction(List<Permission> list,
			MenuFunction function) {
		for (Permission p : list) {
			if (p.getMenuFunction().getFunctionId().equals(
					function.getFunctionId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Function used to list all functions of the system ELC.<br>
	 * 
	 * @return All functions of the system.<br>
	 */
	public List<MenuFunction> listFunctions() {
		return DAOFactory.DEFAULT.buildFunctionDAO().findAll();
	}

	/**
	 * Procedure used to remove a function of the system Easy Lab Correction.<br>
	 * 
	 * @param function
	 *            The function to be removed.<br>
	 */
	public void deleteFunction(MenuFunction function)
			throws EasyCorrectionException {
		MenuFunction f = getFunction(function.getFunctionId());
		f = (MenuFunction) SwapperAtributosReflect.swapObject(f, function,
				MenuFunction.class);
		DAOFactory.DEFAULT.buildFunctionDAO().delete(f);
	}

	/**
	 * Function used to search through a list of function system via a
	 * identifier of menu.<br>
	 * 
	 * @param menuId
	 *            The identifier used for search a list of function in the
	 *            system.<br>
	 * @return The function list with the identifier passed as parameter.<br>
	 */
	public List<MenuFunction> consultFunctionsByMenu(Integer menuId) {
		return DAOFactory.DEFAULT.buildFunctionDAO().findByMenu(menuId);
	}

	/*
	 * GROUP
	 */
	/**
	 * Function used to store a new group in the system Easy Lab Correction.<br>
	 * 
	 * @param group
	 *            The new group to be stored.<br>
	 * @return The new group stored in the system.<br>
	 */
	public Group saveGroup(Group group) throws EasyCorrectionException {

		if (easyCorrectionUtil.isNull(group.getGroupId())
				|| group.getGroupId().equals(new Integer(0))) {

			if (!containsGroupByName(group.getName())) {
				Integer id = DAOFactory.DEFAULT.buildGroupDAO().save(group);
				group.setGroupId(id);
			} else {
				throw new DuplicatedValueException(InternalErrorMsgs.VALOR_DUPLICADO
						.msg("nome"));
			}
		}
		return group;
	}

	public Group updateGroup(Group group) throws EasyCorrectionException {
		Group gr;
		if (!containsGroupByName(group.getName())) {
			gr = getGroup(group.getGroupId());
			gr = (Group) SwapperAtributosReflect.swapObject(gr, group,
					Group.class);
			DAOFactory.DEFAULT.buildGroupDAO().update(gr);

		} else {
			gr = getGroupByName(group.getName());
			if (group.getGroupId().equals(gr.getGroupId())) {
				gr = (Group) SwapperAtributosReflect.swapObject(gr, group,
						Group.class);
				DAOFactory.DEFAULT.buildGroupDAO().update(gr);
			} else {
				throw new DuplicatedValueException(InternalErrorMsgs.VALOR_DUPLICADO
						.msg("nome"));
			}
		}
		return group;
	}

	/**
	 * Function used to retrieve a group of system via a identifier received as
	 * parameter.<br>
	 * 
	 * @param id
	 *            The identifier used to retrieve a group of system.<br>
	 * @return The group with the identifier passed as parameter.<br>
	 */
	public Group getGroup(Integer id) {
		List<Group> group = DAOFactory.DEFAULT.buildGroupDAO().findById(id);
		if (group.isEmpty()) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("grupo"));
		}
		return group.get(0);
	}

	/**
	 * Function used to retrieve all groups of system.<br>
	 * 
	 * @return The group list of system.<br>
	 */
	public List<Group> listGroups() {
		return DAOFactory.DEFAULT.buildGroupDAO().findAll();
	}

	/**
	 * Procedure used to delete a group of system.<br>
	 * 
	 * @param group
	 *            The group to be deleted.<br>
	 * @throws EasyCorrectionException
	 *             The exception to be launched in the removal of group.<br>
	 */
	public void deleteGroup(Group group) throws EasyCorrectionException {
		Group g = getGroup(group.getGroupId());
		g = (Group) SwapperAtributosReflect.swapObject(g, group, Group.class);
		DAOFactory.DEFAULT.buildGroupDAO().delete(g);
	}

	/**
	 * Function used to retrieve a group in the system, that receive a name as
	 * parameter.<br>
	 * 
	 * @param name
	 *            The name of group to be consulted.<br>
	 * @return The group with the name passed as parameter.<br>
	 */
	public Group getGroupByName(String name) {
		List<Group> list = DAOFactory.DEFAULT.buildGroupDAO().findByName(name);
		if (list.isEmpty()) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("grupo"));
		}
		return list.get(0);
	}

	/**
	 * It checks if there is a group with the given name
	 * 
	 * @param name
	 * @return true, if there is, or false, otherwise
	 */
	private boolean containsGroupByName(String name) {
		return (DAOFactory.DEFAULT.buildGroupDAO().findByName(name).isEmpty());
	}

	/*
	 * PERMISSION
	 */

	/**
	 * Function used to save a permission list in the system Easy Lab
	 * Correction. <br>
	 * 
	 * @param permissions
	 *            The permission list which will be saved.<br>
	 * @return The permission list save in the system.<br>
	 */
	public List<Permission> savePermissions(List<Permission> permissions)
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

	public List<Permission> updatePermissions(List<Permission> permissions)
			throws EasyCorrectionException {
		Permission p = new Permission();
		List<Permission> list = new LinkedList<Permission>();
		for (Permission permission : permissions) {
			if (!easyCorrectionUtil.isNull(permission)
					&& !easyCorrectionUtil.isNull(permission.getPermissionId())) {
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

	/**
	 * Function that saves a list of permissions for a group.<br>
	 * 
	 * @param g
	 *            The group that saved the list of permissions.<br>
	 * @param list
	 *            The list of permissions that saved.<br>
	 * @return The list of permissions save for a group.<br>
	 */
	public List<Permission> saveGroupPermission(Group g, List<MenuFunction> list) {
		List<Permission> permissaoDoGrupoBanco = DAOFactory.DEFAULT
				.buildPermissionDAO().findByGroupId(g.getGroupId());
		List<Permission> newList = new LinkedList<Permission>();
		// criando a lista de Permissao
		for (MenuFunction f : list) {
			Permission p = new Permission();
			p.setMenuFunction(f);
			p.setGroup(g);
			newList.add(p);
		}
		// gravando as novas permissoes
		for (Permission addPermission : newList) {
			if (!containsFunction(permissaoDoGrupoBanco, addPermission
					.getMenuFunction())) {
				Integer id = DAOFactory.DEFAULT.buildPermissionDAO().save(
						addPermission);
				addPermission.setPermissionId(id);
			} else {
				List<Permission> anotherP = DAOFactory.DEFAULT
						.buildPermissionDAO()
						.findByGroupAndFunction(g.getGroupId(),
								addPermission.getMenuFunction().getFunctionId());
				addPermission = anotherP.get(0); // eh garantido que a lista
				// não
				// é vazia
			}
		}
		// removendo as permissoes não passada na lista
		for (Permission delPermissao : permissaoDoGrupoBanco) {
			if (!containsFunction(newList, delPermissao.getMenuFunction())) {
				DAOFactory.DEFAULT.buildPermissionDAO().delete(delPermissao);
			}
		}

		return newList;
	}

	/**
	 * Function that retrieves a permission via a identifier passed as
	 * parameter.<br>
	 * 
	 * @param id
	 *            The identifier used to retrieves the permission.<br>
	 * @return The permission with a identifier passed as parameter.<br>
	 */
	public Permission getPermission(Integer id) {
		List<Permission> permissions = DAOFactory.DEFAULT.buildPermissionDAO()
				.findById(id);
		if (permissions.isEmpty()) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("permissao"));
		}
		return permissions.get(0);
	}

	/**
	 * Function used to verify the permissions of user whose identifier is
	 * passed as parameter.<br>
	 * 
	 * @param userId
	 *            The user identifier.<br>
	 * @return The list of permissions of user whose identifier is passed as
	 *         parameter.<br>
	 */
	public List<MenuFunction> verifyPermissions(Integer userId) {
		List<MenuFunction> functionsList = new LinkedList<MenuFunction>();
		List<UserGroup> GUlist = DAOFactory.DEFAULT.buildUserGroupDAO()
				.findByUserId(userId);
		if (!GUlist.isEmpty()) {
			for (UserGroup gU : GUlist) {
				List<Permission> pList = DAOFactory.DEFAULT
						.buildPermissionDAO().findByGroupId(
								gU.getGroup().getGroupId());
				if (!pList.isEmpty()) {
					for (Permission p : pList) {
						if (!isFunctionRepeated(functionsList, p
								.getMenuFunction())) {
							functionsList.add(p.getMenuFunction());
						}
					}
				}
			}
		}
		return functionsList;
	}

	/**
	 * Function used to consult the permissions of group whose identifier is
	 * passed as parameter.<br>
	 * 
	 * @param idGrupo
	 *            The group identifier which the permissions will be consulted.<br>
	 * @return The list of permissions of group whose identifier was passed as
	 *         parameter.<br>
	 */
	public List<Permission> consultPermissionsByGroup(Integer idGrupo) {
		return DAOFactory.DEFAULT.buildPermissionDAO().findByGroupId(idGrupo);
	}

	/**
	 * Function which verifies if a function exists in the system.<br>
	 * 
	 * @param list
	 *            The list of functions of system.<br>
	 * @param function
	 *            The function to be verified if they already exist in the
	 *            system.<br>
	 * @return A boolean: true - if already, false - otherwise.<br>
	 */
	private boolean isFunctionRepeated(List<MenuFunction> list,
			MenuFunction function) {
		for (MenuFunction f : list) {
			if (f.equals(function)) {
				return true;
			}
		}
		return false;
	}

}
