package br.edu.les.easyCorrection.DAO.hibernate.acesso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.pojo.acesso.Menu;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find Agenda by dataInicio
	 */
	public List<Menu> findByTitulo(String titulo) {
		return findByCriteria(Restrictions.eq("titulo", titulo));
	}
	
	public List<Menu> findById(Integer id) {
		return findByCriteria(Restrictions.eq("id", id));
	}
	
	public List<Menu> findByNomeERotulo(String nome, String rotulo) {
		SimpleExpression criteria1 = Restrictions.eq("nome", nome);
		SimpleExpression criteria2 = Restrictions.eq("rotulo", rotulo);
		LogicalExpression criteria = Restrictions.or(criteria1, criteria2);
		return findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> findAllOrdenado(){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.addOrder(Order.asc("rotulo"));
		List <Menu> lista = crit.list();
		return lista;
	}

	@Override
	public void instaciaLista(List<Menu> lista) {
		// TODO Auto-generated method stub
		HibernateUtil.closeSession();
	} 
	
}
