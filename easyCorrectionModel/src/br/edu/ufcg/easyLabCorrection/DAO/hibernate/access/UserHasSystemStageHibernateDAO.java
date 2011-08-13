package br.edu.ufcg.easyLabCorrection.DAO.hibernate.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.MyPersistenceLayer;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.SystemStageHibernateDAO;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserHasSystemStage;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserHasSystemStageHibernateDAO extends
		AbstractHibernateDAO<UserHasSystemStage, Integer>  {

	public UserHasSystemStageHibernateDAO(Session s) {
		super(s);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserHasSystemStage> findByUserId(Integer userId) {
		Query q = getSession().createQuery("from UserHasSystemStage where user.userId = :userId ");
		q.setParameter("userId", userId);
		q.setCacheable(true);
		List <UserHasSystemStage> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserHasSystemStage> findBySystemStageId(Integer systemStageId) {
		Query q = getSession().createQuery("from UserHasSystemStage where systemStage.id  = :systemStageId ");
		q.setParameter("systemStageId", systemStageId);
		q.setCacheable(true);
		List <UserHasSystemStage> list = q.list();
		instantiatesList(list);
		return list;
	}

	@Override
	public List<UserHasSystemStage> instantiatesList(List<UserHasSystemStage> list) {
		try {
			for (UserHasSystemStage uss : list) {
				uss = instantiateUserHasSystemStage(uss);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static UserHasSystemStage instantiateUserHasSystemStage(UserHasSystemStage uss) throws EmptyFieldException{
		uss.setSystemStage(SystemStageHibernateDAO.instantiatesSystemStage(uss.getSystemStage()));
		uss.setUser(UserHibernateDAO.instantiatesUser(uss.getUser()));
		uss = MyPersistenceLayer.deproxy(uss, UserHasSystemStage.class);
		return uss;
	}


	
	

}
