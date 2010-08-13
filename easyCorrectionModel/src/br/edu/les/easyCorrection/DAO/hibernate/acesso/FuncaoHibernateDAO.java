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
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class FuncaoHibernateDAO extends
		AbstractHibernateDAO<Funcao, Integer>  {

	public FuncaoHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public List<Funcao> findById(Integer id) {
		List <Funcao> lista = findByCriteria(Restrictions.eq("id", id));
		return lista;
	}
	
	public List<Funcao> findByNome(String nome) {
		List <Funcao> lista = findByCriteria(Restrictions.eq("nome", nome));
		return lista;
	}
	
	
	public List<Funcao> findByNomeERotulo(String nome, String rotulo) {
		 SimpleExpression criteria1 = Restrictions.eq("nome", nome);
		 SimpleExpression criteria2 = Restrictions.eq("rotulo", rotulo);
		 LogicalExpression criteria = Restrictions.or(criteria1, criteria2);
		 List <Funcao> lista = findByCriteria(criteria);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List <Funcao> findByMenu(Integer idMenu){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("menu.idMenu", idMenu));
		crit.addOrder(Order.desc("rotulo"));
		List <Funcao> lista = crit.list();
		instaciaLista(lista);
		return lista;
	}
	
	@Override
	public void instaciaLista(List<Funcao> lista) {
		
		for (Funcao f: lista) {
			
			Menu menu = MyPersistenceLayer.deproxy(f.getMenu(),
					Menu.class);
			f.setMenu(menu);
		}
		HibernateUtil.closeSession();
		
	}

}
