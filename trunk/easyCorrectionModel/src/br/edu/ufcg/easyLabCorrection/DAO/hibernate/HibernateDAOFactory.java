package br.edu.ufcg.easyLabCorrection.DAO.hibernate;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.FunctionHibernateDAO;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.GroupHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.MenuHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.PermissionHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserGroupHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.AssessmentHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.AssignmentHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.AssignmentTypeHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.ChatHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.SubmissionHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.SystemStageHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.TeamHasUserHasAssignmentHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.TeamHibernateDAO;

/**
 * Generated at Fri Jan 30 09:30:06 GMT-03:00 2009
 *
 * @see http://www.hibernate.org/43.html
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 */
public class HibernateDAOFactory extends DAOFactory {

	public MenuHibernateDAO buildMenuDAO() {
		return new MenuHibernateDAO(HibernateUtil.getSession());
	}
	
	public GroupHibernateDAO buildGroupDAO() {
		return new GroupHibernateDAO(HibernateUtil.getSession());
	}
	
	public UserHibernateDAO buildUserDAO() {
		return new UserHibernateDAO(HibernateUtil.getSession());
	}
	
	public UserGroupHibernateDAO buildUserGroupDAO() {
		return new UserGroupHibernateDAO(HibernateUtil.getSession());
	}
	
	public FunctionHibernateDAO buildFunctionDAO() {
		return new FunctionHibernateDAO(HibernateUtil.getSession());
	}
	
	public PermissionHibernateDAO buildPermissionDAO() {
		return new PermissionHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public AssessmentHibernateDAO buildAssessmentDAO() {
		return new AssessmentHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public ChatHibernateDAO buildChatDAO() {
		return new ChatHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public TeamHibernateDAO buildTeamDAO() {
		return new TeamHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public TeamHasUserHasAssignmentHibernateDAO buildTeamHasUserHasAssignmentDAO() {
		return new TeamHasUserHasAssignmentHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public SystemStageHibernateDAO buildSystemStage() {
		return new SystemStageHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public AssignmentHibernateDAO buildAssignmentDAO() {
		return new AssignmentHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public SubmissionHibernateDAO buildSubmissionDAO() {
		return new SubmissionHibernateDAO(HibernateUtil.getSession());
	}
	
	@Override
	public AssignmentTypeHibernateDAO buildAssignmentTypeDAO() {
		return new AssignmentTypeHibernateDAO(HibernateUtil.getSession());
	}
	
}
