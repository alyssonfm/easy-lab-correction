package br.edu.ufcg.easyLabCorrection.DAO.hibernate.access;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.MyPersistenceLayer;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class MenuHibernateDAO extends
		AbstractHibernateDAO<Menu, Integer>  {

	public MenuHibernateDAO(Session s) {
		super(s);
	}

	/**
	 * Find Agenda by dataInicio
	 */
	public List<Menu> findByLabel(String label) {
		return findByCriteria(Restrictions.eq("label", label));
	}
	
	public List<Menu> findById(Integer id) {
		return findByCriteria(Restrictions.eq("menuId", id));
	}
	
	public List<Menu> findByNameAndLabel(String name, String label) {
		SimpleExpression criteria1 = Restrictions.eq("name", name);
		SimpleExpression criteria2 = Restrictions.eq("label", label);
		LogicalExpression criteria = Restrictions.or(criteria1, criteria2);
		return findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> findAllByOrder(){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.addOrder(Order.asc("label"));
		List <Menu> list = crit.list();
		instantiatesList(list);
		return list;
	}

	@Override
	public List<Menu> instantiatesList(List<Menu> list) {
		try {
			for (Menu m : list) {
				m = instantiatesMenu(m);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Menu instantiatesMenu(Menu m) throws EmptyFieldException{
		m = MyPersistenceLayer.deproxy(m, Menu.class);
		return m;
	}
	
}
