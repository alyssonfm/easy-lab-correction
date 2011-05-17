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
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Function;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class FunctionHibernateDAO extends
		AbstractHibernateDAO<Function, Integer>  {

	public FunctionHibernateDAO(Session s) {
		super(s);
	}

	public List<Function> findById(Integer id) {
		List <Function> list = findByCriteria(Restrictions.eq("id", id));
		instantiatesList(list);
		return list;
	}
	
	public List<Function> findByName(String name) {
		List <Function> list = findByCriteria(Restrictions.eq("nome", name));
		instantiatesList(list);
		return list;
	}
	
	
	public List<Function> findByNameAndLabel(String name, String label) {
		 SimpleExpression criteria1 = Restrictions.eq("nome", name);
		 SimpleExpression criteria2 = Restrictions.eq("rotulo", label);
		 LogicalExpression criteria = Restrictions.or(criteria1, criteria2);
		 List <Function> list = findByCriteria(criteria);
		 instantiatesList(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List <Function> findByMenu(Integer idMenu){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("menu.idMenu", idMenu));
		crit.addOrder(Order.desc("rotulo"));
		List <Function> list = crit.list();
		instantiatesList(list);
		return list;
	}
	
	@Override
	public List<Function> instantiatesList(List<Function> list) {
		try {
			for (Function f : list) {
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
	
	public static Function instantiatesFunction(Function f) throws EmptyFieldException{
		f.setMenu(MenuHibernateDAO.instantiatesMenu(f.getMenu()));
		f = MyPersistenceLayer.deproxy(f, Function.class);
		return f;
	}

}
