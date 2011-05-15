package br.edu.les.easyCorrection.DAO.hibernate.sistema;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.EmptyFieldException;
import br.edu.les.easyCorrection.exceptions.ConstraintViolationException;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class EquipeHibernateDAO extends
		AbstractHibernateDAO<Equipe, Integer>  {

	public EquipeHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipe> findByNome(String nome) {
		Query q = getSession().createQuery("from Equipe where nome = :nome");
		q.setParameter("nome",nome);
		q.setCacheable(true);
		List <Equipe> lista = q.list();
		return instanciaLista(lista);
	}

	@Override
	public List<Equipe> instanciaLista(List<Equipe> lista) {
		try {
			for (Equipe e : lista) {
				e = instanciaEquipe(e);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Equipe instanciaEquipe(Equipe e) throws EmptyFieldException{
		e = MyPersistenceLayer.deproxy(e, Equipe.class);
		
		return e;
	}

}
