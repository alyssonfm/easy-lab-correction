package br.edu.les.easyCorrection.DAO.hibernate.acesso;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.EmptyFieldException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class GrupoHibernateDAO extends
		AbstractHibernateDAO<Grupo, Integer>  {

	public GrupoHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public List<Grupo> findById(Integer id) {
		return findByCriteria(Restrictions.eq("id", id));
	}
	
	public List<Grupo> findByNome(String nome) {
		return findByCriteria(Restrictions.eq("nome", nome));
	}

	@Override
	public List<Grupo> instanciaLista(List<Grupo> lista) {
		try {
			for (Grupo g : lista) {
				g = instanciaGrupo(g);
			}	
		} catch (EmptyFieldException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Grupo instanciaGrupo(Grupo g) throws EmptyFieldException{
		g = MyPersistenceLayer.deproxy(g, Grupo.class);
		return g;
	}

	

	
	

}
