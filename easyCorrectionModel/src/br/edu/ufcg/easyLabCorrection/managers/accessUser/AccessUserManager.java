package br.edu.ufcg.easyLabCorrection.managers.accessUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.AuthenticationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.managers.Manager;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.system.SystemStage;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.ErrorMsgs;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;

/**
 * Class responsible for managing of access of the users in the system Easy Lab
 * Correction.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class AccessUserManager extends Manager {

	/*
	 * Attributes private of the class.<br>
	 */

	/**
	 * Constructor default of the class, creates a new object AccessUserManager.<br>
	 */
	public AccessUserManager() {
		super();
	}

	/*
	 * USER GROUP
	 */
	/**
	 * Function that retrieve an user group of the system by its identifier.<br>
	 * 
	 * @param id
	 *            The identifier of the user group who want to retrieve.<br>
	 * @return The user group whose identifier corresponds at the identifier
	 *         passed as parameter.<br>
	 */
	public UserGroup getUserGroup(Integer id) {
		List<UserGroup> userGroup = DAOFactory.DEFAULT.buildUserGroupDAO()
				.findById(id);
		if (userGroup.isEmpty()) {
			throw new ObjectNotFoundException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("UserGroup"));
		}
		return userGroup.get(0);
	}

	/**
	 * Function used to save a new user group in database of the system.<br>
	 * 
	 * @param userGroup
	 *            The user group to be saved in the system.<br>
	 * @return The user group save in the system.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to save a new user
	 *             group in the system.<br>
	 */
	public UserGroup createUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {

		if (userGroup != null) {
			Integer id = DAOFactory.DEFAULT.buildUserGroupDAO().save(userGroup);
			userGroup.setUserGroupId(id);
		}else{
			throw new EasyCorrectionException(ErrorMsgs.NULL_OBJECT.msg("UserGroup"));
		}
	
		return userGroup;
	}

	/**
	 * Function used to list all user groups of the system.<br>
	 * 
	 * @return A list of all user groups of the system.<br>
	 */
	public List<UserGroup> listUserGroups() {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findAllUserGroup();
	}

	/**
	 * Function used to list user groups receiving a group name as parameter.<br>
	 * 
	 * @param groupName
	 *            The string corresponding at the name of the group.<br>
	 * @return A list of user groups whose group name corresponds at the group
	 *         name passed as parameter.<br>
	 */
	public List<UserGroup> listUserGroupsByGroup(String groupName) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(groupName);
	}

	/**
	 * Function used to retrieve a user by its identifier.<br>
	 * 
	 * @param id
	 *            The user identifier used in the recovery.<br>
	 * @return The user whose identifier corresponds at the identifier passed as
	 *         parameter.<br>
	 */
	public User getUser(Integer id) {
		List<User> users = DAOFactory.DEFAULT.buildUserDAO().findById(id);
		if (users.isEmpty()) {
			throw new ObjectNotFoundException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("User"));
		}
		return users.get(0);
	}

	/**
	 * Function used to retrieve a user of the system by its login.<br>
	 * 
	 * @param login
	 *            The user login used to retrieve a user of the system.<br>
	 * @return The user whose login corresponds at the login passed as
	 *         parameter.<br>
	 */
	public User getUserByLogin(String login) {
		List<User> users = DAOFactory.DEFAULT.buildUserDAO().findByLogin(login);
		if (users.isEmpty()) {
			throw new ObjectNotFoundException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("User"));
		}
		return users.get(0);
	}

	/**
	 * Function used to retrieve a user group of the system by its group and
	 * user.<br>
	 * 
	 * @param groupId
	 *            The group identifier used to retrieve the user group.<br>
	 * @param userId
	 *            The user identifier used to retrieve the user group.<br>
	 * @return The user group whose identifiers of user and group correspond at
	 *         the identifiers passed as parameter.<br>
	 */
	// Private Method that not been used.
	/*
	 * private UserGroup getUserGroupByGroupAndUser(Integer groupId, Integer
	 * userId) { List<UserGroup> list = DAOFactory.DEFAULT.buildUserGroupDAO()
	 * .findByUserAndGroup(groupId, userId); if (list.isEmpty()) { throw new
	 * ObjectNotFoundException(ErrorMsgs.OBJ_NOT_FOUND .msg("UserGroup")); }
	 * return list.get(0); }
	 */

	/**
	 * Function used to retrieve the user groups of the system by user
	 * identifier.<br>
	 * 
	 * @param userId
	 *            The user identifier used to retrieve the user groups.<br>
	 * @return A list of all user groups whose user identifier corresponds at
	 *         the identifier passed as parameter.<br>
	 */
	public List<UserGroup> getUserGroupByUser(Integer userId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByUserId(userId);
	}

	/*
	 * USER
	 */

	/**
	 * Function used to consult a user by its login.<br>
	 * 
	 * @param login
	 *            The login of the user who want to consult.<br>
	 * @return The user corresponding at the login passed as parameter.<br>
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

	public UserGroup createUser(UserGroup userGroup) {
		Integer id = DAOFactory.DEFAULT.buildUserDAO()
				.save(userGroup.getUser());
		userGroup.getUser().setUserId(id);
		return userGroup;
	}

	public UserGroup updateUser(UserGroup userGroup, User user)
			throws EasyCorrectionException {
		user = (User) SwapperAtributosReflect.swapObject(user, userGroup
				.getUser(), User.class);
		DAOFactory.DEFAULT.buildUserDAO().update(user);
		userGroup.getUser().setUserId(user.getUserId());
		return userGroup;
	}

	/**
	 * Function used to update a user of the system.<br>
	 * 
	 * @param user
	 *            The user who want to update in the system.<br>
	 * @return The user updated.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to update user in the
	 *             system.<br>
	 */
	public User updateUser(User user) throws EasyCorrectionException {

		if (user == null) {
			throw new EasyCorrectionException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("User"));
		}
		// Generates the password's MD5.
		String password = MD5Generator.md5(user.getPassword());
		user.setPassword(password);

		User us = getUser(user.getUserId());
		us = (User) SwapperAtributosReflect.swapObject(us, user, User.class);
		DAOFactory.DEFAULT.buildUserDAO().update(us);
		return us;
	}

	/**
	 * Procedure used to delete a user of the system.<br>
	 * 
	 * @param userGroup
	 *            The user group that contains the user to be deleted.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to delete user in the
	 *             system.<br>
	 */
	public void deleteUser(UserGroup userGroup) throws EasyCorrectionException {

		if (userGroup == null) {
			throw new EasyCorrectionException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("UserGroup"));
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
	 * Function used to list all users of the system Easy Lab Correction.<br>
	 * 
	 * @return A list with all users of the system.<br>
	 */
	public List<User> listUsers() {
		return DAOFactory.DEFAULT.buildUserDAO().findAll();
	}

	/**
	 * Function used to verify the login and the password of the user of the
	 * system.<br>
	 * 
	 * @param user
	 *            The user to be verified its login and password.<br>
	 * @return The user checked with login and password.<br>
	 */
	public User verifyLoginAndPassword(User user) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO()
				.findByLoginAndPassword(user.getLogin(), user.getPassword());
		if (list.isEmpty()) {
			throw new AuthenticationException(ErrorMsgs.INVALID_AUTHENTICATION
					.msg());
		}
		return list.get(0);

	}

	/**
	 * Function used to consult users of the system by group identifier.<br>
	 * 
	 * @param groupId
	 *            The group identifier used to consult the user in the system.<br>
	 * @return A list of user groups whose group identifier correspond at the
	 *         group identifier passed as parameter.<br>
	 */
	public List<UserGroup> listUsersByGroup(Integer groupId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(groupId);
	}

	/**
	 * Function used to change the password of the user of the system.<br>
	 * 
	 * @param user
	 *            The user who want to change the password.<br>
	 * @param newPassword
	 *            The new password of the user.<br>
	 * @return The user with password modified.<br>
	 */
	public User changePassword(User user, String newPassword) {
		// Generates the md5 of the password.<br>
		String password = MD5Generator.md5(newPassword);
		User bdUser = getUserByLogin(user.getLogin());

		if (bdUser != null
				&& bdUser.getPassword().equals(
						MD5Generator.md5(user.getPassword()))) {
			bdUser.setPassword(password);
			DAOFactory.DEFAULT.buildUserDAO().update(bdUser);
		} else {
			throw new ObjectNotFoundException(ErrorMsgs.INVALID_AUTHENTICATION
					.msg());
		}
		return bdUser;
	}

	/**
	 * Function used to list all users of the system Easy Lab Correction.<br>
	 * 
	 * @return A list with all users of the system.<br>
	 */
	public List<UserGroup> getUserGroupByStage(Integer stageId) {
		return DAOFactory.DEFAULT.buildUserGroupDAO().findByStage(stageId);
	}

	/**
	 * Function used to creates users in the system ELC from a CSV file.<br>
	 * 
	 * @param fileName
	 *            The path for the CSV file.<br>
	 * @param g
	 *            The group which you want to create users.<br>
	 * @return The list of UserGroups from users created.<br>
	 * @throws IOException
	 *             Exception that can be launched in an attempt to open the file
	 *             whose path is passed as parameter.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to validate users to be
	 *             registered.<br>
	 */
	public ArrayList<UserGroup> createUsersFromCsvFile(String fileName, Group g, SystemStage systemStage)
			throws IOException, EasyCorrectionException {
		CSVFileFilter csv = new CSVFileFilter();
		ArrayList<ArrayList<String>> listUsers = csv.checkCsvFile(fileName);
		ArrayList<UserGroup> ug = new ArrayList<UserGroup>();

		for (int i = 0; i < listUsers.size(); i++) {
			User user = new User();
			user.setUserId(new Integer(0));
			user.setLogin(listUsers.get(i).get(0));
			user.setName(listUsers.get(i).get(1));
			user.setEmail(listUsers.get(i).get(2));
			user.setPeriod(systemStage);
			String password = PasswordGenerator.generatePassword(6, user
					.getLogin());
			password = MD5Generator.md5(password);
			user.setPassword(password);
			if (validateUser(user)) {
				UserGroup userGroup = new UserGroup();
				userGroup.setUserGroupId(new Integer(0));
				userGroup.setGroup(g);
				userGroup.setUser(user);
				ug.add(userGroup);
			} else {
				throw new EasyCorrectionException(ErrorMsgs.CSV_INVALID_USER
						.msg(String.valueOf(i)));
			}
		}
		return ug;
	}

	/**
	 * Function used to validate a user in the system ELC.<br>
	 * 
	 * @param user
	 *            The user that whether to validate the system.<br>
	 * @return The boolean value indicating the validity of user.<br>
	 */
	private boolean validateUser(User user) {
		Pattern patEmail = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher pesqEmail = patEmail.matcher(user.getEmail());

		Pattern patName = Pattern.compile("[A-Za-z]+\\s[A-Za-z]+\\s[A-Za-z]+");
		Matcher pesqName = patName.matcher(user.getName());

		Pattern patLogin = Pattern.compile("[a-zA-Z0-9]+");
		Matcher pesqLogin = patLogin.matcher(user.getLogin());

		if (pesqName.matches() && pesqEmail.matches() && pesqLogin.matches()) {
			return true;
		} else {
			return false;
		}
	}

}
