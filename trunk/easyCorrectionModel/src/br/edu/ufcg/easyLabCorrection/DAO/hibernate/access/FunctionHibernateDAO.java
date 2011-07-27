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
import br.edu.ufcg.easyLabCorrection.pojo.permission.MenuFunction;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class FunctionHibernateDAO extends
		AbstractHibernateDAO<MenuFunction, Integer>  {

	public FunctionHibernateDAO(Session s) {
		super(s);
	}

	public List<MenuFunction> findById(Integer id) {
		List <MenuFunction> list = findByCriteria(Restrictions.eq("functionId", id));
		instantiatesList(list);
		return list;
	}
	
	public List<MenuFunction> findByName(String name) {
		List <MenuFunction> list = findByCriteria(Restrictions.eq("name", name));
		instantiatesList(list);
		return list;
	}
	
	
	public List<MenuFunction> findByNameAndLabel(String name, String label) {
		 SimpleExpression criteria1 = Restrictions.eq("name", name);
		 SimpleExpression criteria2 = Restrictions.eq("label", label);
		 LogicalExpression criteria = Restrictions.or(criteria1, criteria2);
		 List <MenuFunction> list = findByCriteria(criteria);
		 instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List <MenuFunction> findByMenu(Integer idMenu){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("menu.menuId", idMenu));
		crit.addOrder(Order.desc("label"));
		List <MenuFunction> list = crit.list();
		instantiatesList(list);
		return list;
	}
	
	@Override
	public List<MenuFunction> instantiatesList(List<MenuFunction> list) {
		try {
			for (MenuFunction f : list) {
				f = instantiatesFunction(f);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static MenuFunction instantiatesFunction(MenuFunction f) throws EmptyFieldException{
		f.setMenu(MenuHibernateDAO.instantiatesMenu(f.getMenu()));
		f = MyPersistenceLayer.deproxy(f, MenuFunction.class);
		return f;
	}

}
