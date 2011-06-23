package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

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
public class AssignmentHibernateDAO extends AbstractHibernateDAO<Assignment, Integer> {

	public AssignmentHibernateDAO(Session s) {
		super(s);
	}

	@SuppressWarnings("unchecked")
	public List<Assignment> findByReleasedAssignments(Date currentDate) {
		Query q = getSession()
				.createQuery(
						"from Assignment where releaseDate <= :currentDate and deliveryDate > :currentDate");
		q.setParameter("currentDate", currentDate);
		q.setCacheable(true);
		List<Assignment> list = q.list();
		return instantiatesList(list);
	}

	/**
	 * Consideramos que as datas selecionadas tem seus relogios zerados. Ou
	 * seja, o data XX-YY-ZZZZ tem o relogio 00:00:00 e isso tem impacto direto
	 * na definicao de igualdade. Assim, o momento atual da data de hoje serah
	 * sempre maior do que a data de hoje que estah no banco.
	 * 
	 * @param currentDate
	 * @param assignmentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Assignment> findByReleasedAssignments(Date currentDate, Integer assignmentId) {
		Query q = getSession()
				.createQuery(
						"from Assignment where releaseDate <= :currentDate and " +
						"deliveryDate > :currentDate and " +
						"id = :idAssignment");
		q.setParameter("currentDate", currentDate);
		q.setParameter("idAssignment", assignmentId);
		q.setCacheable(true);
		List<Assignment> list = q.list();
		return instantiatesList(list);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Retorna os Assignments que estao fechados, ou seja, a data atual deve ser pelo menos 1 dia apos a data de entrega
	 */
	public List<Assignment> findByClosedAssignments(Date currentDate) {
		Query q = getSession().createQuery(
				"from Assignment where releaseDate <= :currentDate");
		q.setParameter("currentDate", currentDate);
		q.setCacheable(true);
		List<Assignment> list = q.list();
		return instantiatesList(list);
	}

	@Override
	public List<Assignment> instantiatesList(List<Assignment> list) {
		try {
			for (Assignment a : list) {
				a = instantiatesAssignment(a);
			}
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}

	public static Assignment instantiatesAssignment(Assignment a)
			throws EmptyFieldException {
		a.setStage(SystemStageHibernateDAO.instantiatesSystemStage(a.getStage()));
		a.setAssignmentType(AssignmentTypeHibernateDAO.instantiatesAssignmentType(a.getAssignmentType()));
		a = MyPersistenceLayer.deproxy(a, Assignment.class);
		return a;
	}

}
