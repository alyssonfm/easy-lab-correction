package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.UsuarioHibernateDAO;
import br.edu.les.easyCorrection.exceptions.EmptyFieldException;
import br.edu.les.easyCorrection.exceptions.ConstraintViolationException;
import br.edu.les.easyCorrection.pojo.avaliacoes.Chat;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

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
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Chat> instanciaLista(List<Chat> lista) {
		try {
			for (Chat c : lista) {
				c = instanciaChat(c);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Chat instanciaChat(Chat c) throws EmptyFieldException{
		c.setEquipe(EquipeHibernateDAO.instanciaEquipe(c.getEquipe()));
		c.setRoteiro(RoteiroHibernateDAO.instanciaRoteiro(c.getRoteiro()));
		c.setUsuarioDestino(UsuarioHibernateDAO.instanciaUsuario(c.getUsuarioDestino()));
		c.setUsuarioOrigem(UsuarioHibernateDAO.instanciaUsuario(c.getUsuarioOrigem()));
		c = MyPersistenceLayer.deproxy(c, Chat.class);
		return c;
	}

}
