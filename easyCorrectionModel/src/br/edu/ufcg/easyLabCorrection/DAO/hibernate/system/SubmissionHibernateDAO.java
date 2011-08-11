package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.MyPersistenceLayer;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;

/**
 * <p>
 * Hibernate DAO layer for Agendas
 * </p>
 * <p>
 * Generated at Fri Jan 30 09:30:05 GMT-03:00 2009
 * </p>
 * 
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class SubmissionHibernateDAO extends
		AbstractHibernateDAO<Submission, Integer> {

	public SubmissionHibernateDAO(Session s) {
		super(s);
	}

	@SuppressWarnings("unchecked")
	public List<Submission> findByTeamAndAssignment(Integer teamId,
			Integer assignmentId) {
		Query q = getSession()
				.createQuery(
						"from Submission where teamHasUserHasAssignment.team.id = :teamId "
								+ "and teamHasUserHasAssignment.assignment.id = :assignmentId");
		q.setParameter("teamId", teamId);
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		List<Submission> list = q.list();
		return instantiatesList(list);
	}

	public void deleteAllSubmissionsByStage(Integer stageId) {
		HibernateUtil.beginTransaction();
		Query q = getSession()
				.createSQLQuery(
						"DELETE FROM submissao WHERE equipe_has_usuario_has_roteiro_id IN ( "
								+ " SELECT eur.id FROM equipe_has_usuario_has_roteiro eur "
								+ " JOIN roteiro r ON (r.id = eur.roteiro_id) WHERE r.stage.id = :stageId)");
		q.setParameter("stageId", stageId);
		q.executeUpdate();
		HibernateUtil.commitTransactionCloseSession();
	}

	public void deleteAllSubmissionsByAssignment(Integer assignmentId) {
		HibernateUtil.beginTransaction();
		Query q = getSession()
				.createSQLQuery(
						"DELETE FROM submissao WHERE equipe_has_usuario_has_roteiro_id IN ( "
								+ " SELECT eur.id FROM equipe_has_usuario_has_roteiro eur "
								+ " JOIN roteiro r ON (r.id = eur.roteiro_id) WHERE r.id = :assignmentId)");
		q.setParameter("assignmentId", assignmentId);
		q.executeUpdate();
		HibernateUtil.commitTransactionCloseSession();
	}

	public void deleteAllSubmissionsByUserId(Integer userId) {
		HibernateUtil.beginTransaction();
		Query q = getSession()
				.createSQLQuery(
						"DELETE FROM submissao WHERE equipe_has_usuario_has_roteiro_id  IN ( "
								+ " SELECT eur.id FROM equipe_has_usuario_has_roteiro eur "
								+ " JOIN usuario u ON (u.id = eur.usuario_id) WHERE u.id = :userId) ");
		q.setParameter("userId", userId);
		q.executeUpdate();
		HibernateUtil.commitTransactionCloseSession();
	}

	@Override
	public List<Submission> instantiatesList(List<Submission> list) {
		try {
			for (Submission s : list) {
				s = instantiatesSubmission(s);
			}
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}

	public static Submission instantiatesSubmission(Submission s)
			throws EmptyFieldException {
		s.setTeamHasUserHasAssignment(TeamHasUserHasAssignmentHibernateDAO
				.instantiatesTeamHasUserHasAssignment(s
						.getTeamHasUserHasAssignment()));
		s = MyPersistenceLayer.deproxy(s, Submission.class);
		return s;
	}

	public String countSubmissionsByAssignmentId(int assignmentId) {
		String count = "";
		Query q = getSession().createQuery(
				"SELECT count(*) FROM roteiro r, "
						+ "submissao s, equipe_has_usuario_has_roteiro e "
						+ "where r.id = e.roteiro_id and "
						+ "s.equipe_has_usuario_has_roteiro_id = e.id "
						+ "and r.id = :assignmentId");
		q.setParameter("assignmentId", assignmentId);
		q.setCacheable(true);
		count = q.getQueryString();
		return count;
	}

}
