package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.system.SystemStage;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class SystemStageHibernateDAO extends
		AbstractHibernateDAO<SystemStage, Integer>  {

	public SystemStageHibernateDAO(Session s) {
		super(s);
	}

	@Override
	public List<SystemStage> instantiatesList(List<SystemStage> list) {
		try {
			for (SystemStage p : list) {
				p = instantiatesSystemStage(p);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static SystemStage instantiatesSystemStage(SystemStage p) throws EmptyFieldException{
		p = MyPersistenceLayer.deproxy(p, SystemStage.class);
		return p;
	}

}
