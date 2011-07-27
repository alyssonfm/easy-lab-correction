package br.edu.ufcg.easyLabCorrection.DAO.hibernate.access;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.MyPersistenceLayer;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.system.SystemStageHibernateDAO;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class UserHibernateDAO extends
		AbstractHibernateDAO<User, Integer>  {

	public UserHibernateDAO(Session s) {
		super(s);
	}

	public List<User> findById(Integer id) {
		return findByCriteria(Restrictions.eq("userId", id));
	}
	
	public List<User> findByLabel(String name) {
		return findByCriteria(Restrictions.eq("name", name));
	}
	
	public List <User> findByLogin(String login){
		return findByCriteria(Restrictions.eq("login", login));
	}
	
	public List <User> findByEmail(String email){
		return findByCriteria(Restrictions.eq("email", email));
	}
	
	public List<User> findByLoginAndPassword(String login, String password) {
		SimpleExpression criteria = Restrictions.eq("password", password);
		SimpleExpression criteria2 = Restrictions.eq("login", login);
		return findByCriteria(criteria,criteria2);
	}

	@Override
	public List<User> instantiatesList(List<User> list) {
		try {
			for (User u : list) {
				u = instantiatesUser(u);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static User instantiatesUser(User u) throws EmptyFieldException{
		u.setPeriod(SystemStageHibernateDAO.instantiatesSystemStage(u.getPeriod()));
		u = MyPersistenceLayer.deproxy(u, User.class);
		return u;
	}


	
	

}
