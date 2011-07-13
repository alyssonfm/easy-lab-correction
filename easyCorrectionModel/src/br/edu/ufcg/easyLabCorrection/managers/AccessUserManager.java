package br.edu.ufcg.easyLabCorrection.managers;

import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.AuthenticationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.MD5Generator;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing of acess of the users in the 
 * system Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class AccessUserManager extends Manager {

	/*
	 * Attributes private of the class.<br>
	 */

	/**
	 * Constructor default of the class, creates a new object 
	 * AccessUserManager.<br>
	 */
	public AccessUserManager() {
		super();
	}

	/*
	 * USER GROUP
	 */
	/**
	 * Function that retrieve an user group of the system by its identifier.<br>
	 * @param id The identifier of the user group who want to retrieve.<br>
	 * @return The user group whose identifier corresponds at the identifier 
	 * passed as parameter.<br>
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

	/**
	 * Function used to save a new user group in database of the system.<br>
	 * @param userGroup The user group to be saved in the system.<br>
	 * @return The user group save in the system.<br>
	 * @throws EasyCorrectionException Exception can be thrown in an 
	 * attempt to save a new user group in the system.<br>
	 */
	public UserGroup saveUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {
		UserGroup ug = new UserGroup();
		if (!easyCorrectionUtil.isNull(userGroup)) {
			try {
				ug = updateUserGroup(userGroup);
			} catch (ObjectNotFoundException e) {
				ug = createUserGroup(userGroup);
			}
		}
		return ug;
	}
	
	private UserGroup createUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {
		Integer id = DAOFactory.DEFAULT.buildUserGroupDAO().save(
				userGroup);
		userGroup.setUserGroupId(id);
		return userGroup;
	}
	
	private UserGroup updateUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {
		UserGroup ugs = new UserGroup();
		ugs = getUserGroupByGroupAndUser(userGroup.getGroup()
				.getGroupId(), userGroup.getUser().getUserId());
		ugs = (UserGroup) SwapperAtributosReflect.swapObject(ugs,
				userGroup, UserGroup.class);
		DAOFactory.DEFAULT.buildUserGroupDAO().update(ugs);
		return userGroup;
	}

	/**
	 * Function used to list all user groups of the system.<br>
	 * @return A list of all user groups of the system.<br>
	 */
	public List<UserGroup> listUserGroups() {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findAllUserGroup();
	}

	/**
	 * Function used to list user groups receiving a group
	 *  name as parameter.<br>
	 * @param groupName The string corresponding at the name 
	 * of the group.<br>
	 * @return A list of user groups whose group name corresponds 
	 * at the group name passed as parameter.<br>
	 */
	public List<UserGroup> listUserGroupsByGroup(String groupName) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(groupName);
	}

	/**
	 * Function used to retrieve a user by its identifier.<br>
	 * @param id The user identifier used in the recovery.<br>
	 * @return The user whose identifier corresponds at the
	 *  identifier passed as parameter.<br>
	 */
	public User getUser(Integer id) {
		List<User> users = DAOFactory.DEFAULT.buildUserDAO().findById(id);
		if (users.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return users.get(0);
	}

	/**
	 * Function used to retrieve a user of the system by its login.<br>
	 * @param login The user login used to retrieve a user of the system.<br>
	 * @return The user whose login corresponds at the login 
	 * passed as parameter.<br>
	 */
	public User getUserByLogin(String login) {
		List<User> users = DAOFactory.DEFAULT.buildUserDAO().findByLogin(login);
		if (users.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("usuario"));
		}
		return users.get(0);
	}

	/**
	 * Function used to retrieve a user group of the system by its 
	 * group and user.<br>
	 * @param groupId The group identifier used to retrieve 
	 * the user group.<br>
	 * @param userId The user identifier used to retrieve 
	 * the user group.<br>
	 * @return The user group whose identifiers of user and group correspond 
	 * at the identifiers passed as parameter.<br>
	 */
	private UserGroup getUserGroupByGroupAndUser(Integer groupId, Integer userId) {
		List<UserGroup> list = DAOFactory.DEFAULT.buildUserGroupDAO()
				.findByUserAndGroup(groupId, userId);
		if (list.isEmpty()) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("grupo usuario"));
		}
		return list.get(0);
	}

	/**
	 * Function used to retrieve the user groups of the system by user 
	 * identifier.<br>
	 * @param userId The user identifier used to retrieve the 
	 * user groups.<br>
	 * @return A list of all user groups whose user identifier 
	 * corresponds at the identifier passed as parameter.<br> 
	 */
	public List<UserGroup> getUserGroupByUser(Integer userId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByUserId(userId);
	}

	/*
	 * USER
	 */
	
	/**
	 * Function used to consult a user by its login.<br>
	 * @param login The login of the user who want to consult.<br>
	 * @return The user corresponding at the login passed
	 * as parameter.<br>
	 */
	public User retrieveUserByLogin(String login) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO().findByLogin(login);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * Private Method.<br>
	 */
	public User getUserByEmail(String email) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO().findByEmail(email);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	public UserGroup createUser(UserGroup userGroup){
		Integer id = DAOFactory.DEFAULT.buildUserDAO().save(
				userGroup.getUser());
		userGroup.getUser().setUserId(id);
		return userGroup;
	}
	
	public UserGroup updateUser(UserGroup userGroup, User user) throws EasyCorrectionException{
		user = (User) SwapperAtributosReflect.swapObject(user,
				userGroup.getUser(), User.class);
		DAOFactory.DEFAULT.buildUserDAO().update(user);
		userGroup.getUser().setUserId(user.getUserId());
		return userGroup;
	}

	/**
	 * Function used to update a user of the system.<br>
	 * @param user The user who want to update in the system.<br>
	 * @return The user updated.<br>
	 * @throws EasyCorrectionException Exception can be thrown in an 
	 * attempt to update user in the system.<br>
	 */
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

	/**
	 * Procedure used to delete a user of the system.<br>
	 * @param userGroup The user group that contains the 
	 * user to be deleted.<br>
	 * @throws EasyCorrectionException Exception can be thrown in an 
	 * attempt to delete user in the system.<br>
	 */
	public void deleteUser(UserGroup userGroup, List<Assignment> assigns) throws EasyCorrectionException {

		if (userGroup.getGroup().getName().equals("Aluno")
				&& assigns.size() > 0) {
			throw new EasyCorrectionException(MsgErros.USUARIO_ALOCADO
					.msg("O GrupoUsuario"));
		}

		if (easyCorrectionUtil.isNull(userGroup)) {
			throw new EasyCorrectionException(MsgErros.OBJ_NOT_FOUND
					.msg("O GrupoUsuario"));
		}

		// Remove User Group
		UserGroup ug = getUserGroup(userGroup.getUserGroupId());
		ug = (UserGroup) SwapperAtributosReflect.swapObject(ug, userGroup,
				UserGroup.class);
		DAOFactory.DEFAULT.buildUserGroupDAO().delete(ug);

		// Remove User
		User u = getUser(userGroup.getUser().getUserId());
		u = (User) SwapperAtributosReflect.swapObject(u, userGroup.getUser(),
				User.class);
		DAOFactory.DEFAULT.buildUserDAO().delete(u);
	}

	/**
	 * Function used to list all users of the system 
	 * Easy Lab Correction.<br>
	 * @return A list with all users of the system.<br>
	 */
	public List<User> listUsers() {
		return DAOFactory.DEFAULT.buildUserDAO().findAll();
	}

	/**
	 * Function used to verify the login and the password 
	 * of the user of the system.<br>
	 * @param user The user to be verified its login and 
	 * password.<br>
	 * @return The user checked with login and password.<br>
	 */
	public User verifyLoginAndPassword(User user) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO()
				.findByLoginAndPassword(user.getLogin(), user.getPassword());
		if (list.isEmpty()) {
			throw new AuthenticationException(MsgErros.AUTENTICACAO.msg());
		}
		return list.get(0);

	}

	/**
	 * Function used to consult users of the system by group 
	 * identifier.<br> 
	 * @param groupId The group identifier used to consult
	 * the user in the system.<br>
	 * @return A list of user groups whose group identifier 
	 * correspond at the group identifier passed as parameter.<br>
	 */
	public List<UserGroup> consultUserByGroup(Integer groupId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(groupId);
	}

	/**
	 * Function used to change the password of the user of the 
	 * system.<br>
	 * @param user The user who want to change the password.<br>
	 * @param newPassword The new password of the user.<br>
	 * @return The user with password modified.<br>
	 */
	public User changePassword(User user, String newPassword) {
		// Generetes the md5 of the password.<br>
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
	
	/**
	 * Function used to change the password of the user of the 
	 * system.<br>
	 * @param user The user who want to change the password.<br>
	 * @param currentPassword The current password of the user.<br>
	 * @param newPassword The new password of the user.<br>
	 * @return The user with password modified.<br>
	 */
	public User changePasswordAFM(User user, String currentPassword, String newPassword) {
		// Generetes the md5 of the password.<br>
		String password = MD5Generator.md5(newPassword);
		User bdUser = getUserByLogin(user.getLogin());

		if (!easyCorrectionUtil.isNull(bdUser)
				&& bdUser.getPassword().equals(currentPassword)) {
			bdUser.setPassword(password);
			DAOFactory.DEFAULT.buildUserDAO().update(bdUser);
		} else {
			throw new ObjectNotFoundException(MsgErros.AUTENTICACAO.msg());
		}
		return bdUser;
	}

}
