package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class SubmissionHibernateDAO extends
		AbstractHibernateDAO<Submission, Integer>  {

	public SubmissionHibernateDAO(Session s) {
		super(s);
	}
	
	@SuppressWarnings("unchecked")
	public List<Submission> findByTeamAndAssignment(Integer teamId, Integer assignmentId) {
		Query q = getSession().createQuery("from Submission where teamHasUserHasAssignment.team.teamId = :teamId " +
				"and teamHasUserHasAssignment.assignment.assignmentId = :assignmentId");
		q.setParameter("teamId",teamId);
		q.setParameter("assignmentId",assignmentId);
		q.setCacheable(true);
		List <Submission> list = q.list();
		return instantiatesList(list);
	}
	
	@Override
	public List<Submission> instantiatesList(List<Submission> list) {
		try {
			for (Submission s : list) {
				s = instantiatesSubmission(s);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Submission instantiatesSubmission(Submission s) throws EmptyFieldException{
		s.setTeamHasUserHasAssignment(TeamHasUserHasAssignmentHibernateDAO.instantiatesTeamHasUserHasAssignment(s.getTeamHasUserHasAssignment()));
		s = MyPersistenceLayer.deproxy(s, Submission.class);
		return s;
	}

}
