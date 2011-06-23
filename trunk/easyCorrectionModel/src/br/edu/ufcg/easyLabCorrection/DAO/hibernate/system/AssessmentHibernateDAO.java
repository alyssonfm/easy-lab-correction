package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserHibernateDAO;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class AssessmentHibernateDAO extends
		AbstractHibernateDAO<Assessment, Integer>  {

	public AssessmentHibernateDAO(Session s) {
		super(s);
	}
	
	@SuppressWarnings("unchecked")
	public List<Assessment> findByAssignmentWithOutCorrector(int assignmentId) {
		Query q = getSession().createQuery("from Assessment where corrector.userId is null and " +
				"submission.teamHasUserHasAssignment.assignment.assignmentId = :assignmentId");
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Assessment> findByAssignmentAndTeam(int assignmentId, int teamId) {
		Query q = getSession().createQuery("from Assessment where submission.teamHasUserHasAssignment.assignment.assignmentId = :assignmentId " +
				" and submission.teamHasUserHasAssignment.team.teamId =:teamId");
		q.setParameter("teamId", teamId);
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Assessment> findBySubmission(int submissionId) {
		Query q = getSession().createQuery("from Assessment where submission.submissionId = :submissionId");
		q.setParameter("submissionId", submissionId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		instantiatesList(list);
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<Assessment> findByAssignmentWithCorrector(int assignmentId, int correctorId) {
		Query q = getSession().createQuery("from Assessment where corrector.userId = :correctorId " +
				"and submission.teamHasUserHasAssignment.assignment.assignmentId = :assignmentId");
		q.setParameter("assignmentId", assignmentId);
		q.setParameter("correctorId", correctorId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Assessment> findByTeamAndAssignmentByCorrector(Integer assignmentId, Integer correctorId) {
		Query q = getSession().createQuery("from Assessment where submission.teamHasUserHasAssignment.assignment.assignmentId = :assignmentId " +
				"and corrector.userId = :correctorId " +
				"GROUP BY submission.teamHasUserHasAssignment.team.teamId");
		q.setParameter("assignmentId",assignmentId);
		q.setParameter("correctorId",correctorId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		return instantiatesList(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<Assessment> findByAssignment(int assignmentId) {
		Query q = getSession().createQuery("from Assessment where submission.teamHasUserHasAssignment.assignment.assignmentId = :assignmentId");
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Assessment> findByTeamAndAssignment(Integer teamId, Integer assignmentId) {
		Query q = getSession().createQuery("from Assessment where submission.teamHasUserHasAssignment.assignment.assignmentId = :assignmentId" +
				" and submission.teamHasUserHasAssignment.team.teamId = :teamId");
		q.setParameter("teamId", teamId);
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List <Assessment> list = q.list();
		instantiatesList(list);
		return list;
	}

	@Override
	public List<Assessment> instantiatesList(List<Assessment> list) {
		try {
			for (Assessment a : list) {
				a = instantiatesAssessment(a);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Assessment instantiatesAssessment(Assessment a) throws EmptyFieldException{
		a.setSubmission(SubmissionHibernateDAO.instantiatesSubmission(a.getSubmission()));
		try{
			a.setCorrector(UserHibernateDAO.instantiatesUser(a.getCorrector()));
		}
		catch(Exception e){
			a.setCorrector(null);
		}
		a = MyPersistenceLayer.deproxy(a, Assessment.class);
		return a;
	}

}
