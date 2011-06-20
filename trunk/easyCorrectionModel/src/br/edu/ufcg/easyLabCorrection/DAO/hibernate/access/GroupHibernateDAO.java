package br.edu.ufcg.easyLabCorrection.DAO.hibernate.access;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class GroupHibernateDAO extends
		AbstractHibernateDAO<Group, Integer>  {

	public GroupHibernateDAO(Session s) {
		super(s);
	}

	public List<Group> findById(Integer id) {
		return findByCriteria(Restrictions.eq("id", id));
	}
	
	public List<Group> findByName(String name) {
		return findByCriteria(Restrictions.eq("nome", name));
	}

	@Override
	public List<Group> instantiatesList(List<Group> list) {
		try {
			for (Group g : list) {
				g = instanciaGroup(g);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Group instanciaGroup(Group g) throws EmptyFieldException{
		g = MyPersistenceLayer.deproxy(g, Group.class);
		return g;
	}

}
