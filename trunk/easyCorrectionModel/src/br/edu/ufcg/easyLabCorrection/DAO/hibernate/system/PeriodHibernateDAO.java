package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PeriodHibernateDAO extends
		AbstractHibernateDAO<Stage, Integer>  {

	public PeriodHibernateDAO(Session s) {
		super(s);
	}

	@Override
	public List<Stage> instantiatesList(List<Stage> list) {
		try {
			for (Stage p : list) {
				p = instantiatesPeriod(p);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Stage instantiatesPeriod(Stage p) throws EmptyFieldException{
		p = MyPersistenceLayer.deproxy(p, Stage.class);
		return p;
	}

}
