package br.edu.ufcg.easyLabCorrection.DAO.hibernate.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.MyPersistenceLayer;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserGroupHibernateDAO extends
		AbstractHibernateDAO<UserGroup, Integer>  {

	public UserGroupHibernateDAO(Session s) {
		super(s);
	}

	public List<UserGroup> findById(Integer id) {
		List<UserGroup> list = findByCriteria(Restrictions.eq("id", id));
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserGroup> findByGroup(Integer groupId) {
		Query q = getSession().createQuery("from UserGroup where group.groupId = :groupId " +
				"and user.period.id = :stageId");
		q.setParameter("groupId", groupId);
		q.setParameter("stageId", HibernateUtil.getCurrentStageId());
		q.setCacheable(true);
		List <UserGroup> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroup> findByGroup(String name) {
		Query q = getSession().createQuery("from UserGroup where group.name like :name " +
				"and user.period.id = :stageId");
		q.setParameter("name",name);
		q.setParameter("stageId", HibernateUtil.getCurrentStageId());
		q.setCacheable(true);
		List <UserGroup> list = q.list();
		instantiatesList(list);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserGroup> findByUserId(Integer userId) {
		Query q = getSession().createQuery("from UserGroup where user.userId = :userId");
		q.setParameter("userId",userId);
		q.setCacheable(true);
		List <UserGroup> list = q.list();
		instantiatesList(list);
		return list;
	}
	
	public List<UserGroup> findByUserAndGroup(Integer groupId, Integer userId) {
		SimpleExpression criteria1 = Restrictions.eq("user.userId", userId);
		SimpleExpression criteria2 = Restrictions.eq("group.groupId", groupId);
		List<UserGroup> list = findByCriteria(criteria1, criteria2);
		return list;
	}
	
	public List <UserGroup> findAllUserGroup(){
		List <UserGroup> list = findByCriteria();
		return list;
	}

	@Override
	public List<UserGroup> instantiatesList(List<UserGroup> list) {
		try {
			for (UserGroup gu : list) {
				gu = instantiateUserGroup(gu);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static UserGroup instantiateUserGroup(UserGroup gu) throws EmptyFieldException{
		gu.setGroup(GroupHibernateDAO.instanciaGroup(gu.getGroup()));
		gu.setUser(UserHibernateDAO.instantiatesUser(gu.getUser()));
		gu = MyPersistenceLayer.deproxy(gu, UserGroup.class);
		return gu;
	}


	
	

}
