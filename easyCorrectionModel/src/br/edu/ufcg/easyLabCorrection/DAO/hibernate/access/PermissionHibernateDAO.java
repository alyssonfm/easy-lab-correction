package br.edu.ufcg.easyLabCorrection.DAO.hibernate.access;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PermissionHibernateDAO extends
		AbstractHibernateDAO<Permission, Integer>  {

	public PermissionHibernateDAO(Session s) {
		super(s);
	}
	
	public List<Permission> findById(Integer id) {
		List<Permission> list = findByCriteria(Restrictions.eq("id", id));
		return list;
	}
	
	public List<Permission> findByLabel(Integer id) {
		List <Permission> list = findByCriteria(Restrictions.eq("id", id));
		return list;
	}
	
	public List<Permission> findByGroupId(Integer groupId) {
		List <Permission> list = findByCriteria(Restrictions.eq("grupo.id", groupId));
		return list;
	}
	
	public List<Permission> findAllPermission() {
		List <Permission> list = findByCriteria();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Permission> findByGroupAndFunction(Integer groupId, Integer functionId) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("grupo.idGrupo", groupId));
		crit.add(Restrictions.eq("funcao.idFuncao", functionId));
        List<Permission> list = crit.list();
        instantiatesList(list);
        return list;
	}

	@Override
	public List<Permission> instantiatesList(List<Permission> list) {
		try {
			for (Permission p : list) {
				p = instantiatesPermission(p);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Permission instantiatesPermission(Permission p) throws EmptyFieldException{
		p.setFunction(FunctionHibernateDAO.instantiatesFunction(p.getFunction()));
		p.setGroup(GroupHibernateDAO.instanciaGroup(p.getGroup()));
		p = MyPersistenceLayer.deproxy(p, Permission.class);
		return p;
	}


	

	
	

}
