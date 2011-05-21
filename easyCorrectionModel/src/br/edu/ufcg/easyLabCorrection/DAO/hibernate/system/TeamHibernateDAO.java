package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class TeamHibernateDAO extends
		AbstractHibernateDAO<Team, Integer>  {

	public TeamHibernateDAO(Session s) {
		super(s);
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> findByName(String name) {
		Query q = getSession().createQuery("from Equipe where nome = :nome");
		q.setParameter("nome",name);
		q.setCacheable(true);
		List <Team> list = q.list();
		return instantiatesList(list);
	}

	@Override
	public List<Team> instantiatesList(List<Team> list) {
		try {
			for (Team t : list) {
				t = instantiatesTeam(t);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Team instantiatesTeam(Team t) throws EmptyFieldException{
		t = MyPersistenceLayer.deproxy(t, Team.class);
		return t;
	}

}
