package br.edu.ufcg.easyLabCorrection.DAO.hibernate;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.FunctionHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.GroupHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.MenuHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.PermissionHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserGroupHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.AssessmentHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.AssignmentHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.ChatHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.PeriodHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.SubmissionHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.TeamHasUserHasAssignmentHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.TeamHibernateDAO;

/**
 * Generated at Fri Jan 30 09:30:06 GMT-03:00 2009
 *
 * @see http://www.hibernate.org/328.html
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 */

public abstract class DAOFactory {

	private static final DAOFactory HIBERNATE = new HibernateDAOFactory();

	public static final DAOFactory DEFAULT = HIBERNATE;
	
    /**
     * Factory method for instantiation of concrete factories.
     */
	@SuppressWarnings("unchecked")
	public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }
    
	public abstract MenuHibernateDAO buildMenuDAO();
	
	public abstract GroupHibernateDAO buildGroupDAO();
	
	public abstract UserHibernateDAO buildUserDAO();
	
	public abstract UserGroupHibernateDAO buildUserGroupDAO();
	
	public abstract FunctionHibernateDAO buildFunctionDAO();
	
	public abstract PermissionHibernateDAO buildPermissionDAO();
	
	public abstract PeriodHibernateDAO buildPeriodDAO();
	
	public abstract AssignmentHibernateDAO buildAssignmentDAO();
	
	public abstract ChatHibernateDAO buildChatDAO();
	
	public abstract TeamHibernateDAO buildTeamDAO();
	
	public abstract TeamHasUserHasAssignmentHibernateDAO buildTeamHasUserHasAssignmentDAO();
	
	public abstract AssessmentHibernateDAO buildAssessmentDAO();
	
	public abstract SubmissionHibernateDAO buildSubmissionDAO();
		
}
