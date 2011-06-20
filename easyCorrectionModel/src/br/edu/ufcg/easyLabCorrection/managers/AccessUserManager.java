package br.edu.ufcg.easyLabCorrection.managers;

import java.util.List;


import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.AuthenticationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.system.System;
import br.edu.ufcg.easyLabCorrection.util.MD5Generator;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class AccessUserManager extends Manager {

	private AssignmentManager assignmentManager;
	private System system; 

	public AccessUserManager() {
		super();
		assignmentManager = new AssignmentManager();
		//system = new System();
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

	// TODO Refatorar. Dividir em update e passar parte das responsabilidade
	// para System
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
					system.createTeamForIncomingAluno(userGroup);
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

		List<Assignment> assigns = assignmentManager.getAssignments();
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

	public User verifyLoginAndPassword(User user) {
		List<User> list = DAOFactory.DEFAULT.buildUserDAO()
				.findByLoginAndPassword(user.getLogin(), user.getPassword());
		if (list.isEmpty()) {
			throw new AuthenticationException(MsgErros.AUTENTICACAO.msg());
		}
		return list.get(0);

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
