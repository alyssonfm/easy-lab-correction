package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserHibernateDAO;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class TeamHasUserHasAssignmentHibernateDAO extends
		AbstractHibernateDAO<TeamHasUserHasAssignment, Integer>  {

	public TeamHasUserHasAssignmentHibernateDAO(Session s) {
		super(s);
	}
	
	@SuppressWarnings("unchecked")
	public List<TeamHasUserHasAssignment> findByUserAndAssignment(Integer userId, Integer assignmentId) {
		Query q = getSession().createQuery("from TeamHasUserHasAssignment where user.userId = :userId " +
				"and assignment.id = :assignmentId");
		q.setParameter("userId", userId);
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <TeamHasUserHasAssignment> list = q.list();
		return instantiatesList(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<TeamHasUserHasAssignment> findByTeamAndAssignment(Integer teamId, Integer assignmentId) {
		Query q = getSession().createQuery("from TeamHasUserHasAssignment where team.id = :teamId " +
				"and assignment.id = :assignmentId");
		q.setParameter("teamId",teamId);
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <TeamHasUserHasAssignment> list = q.list();
		return instantiatesList(list);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TeamHasUserHasAssignment> findByAssignment(Integer assignmentId) {
		Query q = getSession().createQuery("from TeamHasUserHasAssignment where assignment.id = :assignmentId");
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <TeamHasUserHasAssignment> list = q.list();
		return instantiatesList(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<TeamHasUserHasAssignment> findByAssignmentGroupByTeam(Integer assignmentId) {
		Query q = getSession().createQuery("from TeamHasUserHasAssignment where assignment.id = :assignmentId GROUP BY team.id");
		q.setParameter("assignmentId",assignmentId);
		q.setCacheable(true);
		List <TeamHasUserHasAssignment> list = q.list();
		return instantiatesList(list);
	}

	@Override
	public List<TeamHasUserHasAssignment> instantiatesList(List<TeamHasUserHasAssignment> list) {
		try {
			for (TeamHasUserHasAssignment tua : list) {
				tua = instantiatesTeamHasUserHasAssignment(tua);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static TeamHasUserHasAssignment instantiatesTeamHasUserHasAssignment(TeamHasUserHasAssignment tua) throws EmptyFieldException{
		tua.setTeam(TeamHibernateDAO.instantiatesTeam(tua.getTeam()));
		tua.setAssignment(AssignmentHibernateDAO.instantiatesAssignment(tua.getAssignment()));
		tua.setUser(UserHibernateDAO.instantiatesUser(tua.getUser()));
		tua = MyPersistenceLayer.deproxy(tua, TeamHasUserHasAssignment.class);
		return tua;
	}

}
