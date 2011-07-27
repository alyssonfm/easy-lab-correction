package br.edu.ufcg.easyLabCorrection.DAO.hibernate.system;

import java.util.List;

import org.hibernate.Session;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.MyPersistenceLayer;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.access.UserHibernateDAO;
import br.edu.ufcg.easyLabCorrection.exceptions.ConstraintViolationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EmptyFieldException;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Chat;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class ChatHibernateDAO extends
		AbstractHibernateDAO<Chat, Integer>  {

	public ChatHibernateDAO(Session s) {
		super(s);
	}

	@Override
	public List<Chat> instantiatesList(List<Chat> list) {
		try {
			for (Chat c : list) {
				c = instantiatesChat(c);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	public static Chat instantiatesChat(Chat c) throws EmptyFieldException{
		c.setTeam(TeamHibernateDAO.instantiatesTeam(c.getTeam()));
		c.setAssignment(AssignmentHibernateDAO.instantiatesAssignment(c.getAssignment()));
		c.setReceptor(UserHibernateDAO.instantiatesUser(c.getReceptor()));
		c.setSender(UserHibernateDAO.instantiatesUser(c.getSender()));
		c = MyPersistenceLayer.deproxy(c, Chat.class);
		return c;
	}

}
