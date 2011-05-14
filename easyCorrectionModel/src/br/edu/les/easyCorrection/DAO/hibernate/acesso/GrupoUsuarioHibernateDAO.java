package br.edu.les.easyCorrection.DAO.hibernate.acesso;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.EmptyFieldException;
import br.edu.les.easyCorrection.exceptions.ViolacaoConstraintException;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class GrupoUsuarioHibernateDAO extends
		AbstractHibernateDAO<GrupoUsuario, Integer>  {

	public GrupoUsuarioHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public List<GrupoUsuario> findById(Integer id) {
		List<GrupoUsuario> lista = findByCriteria(Restrictions.eq("id", id));
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> findByGrupo(Integer idGrupo) {
		Query q = getSession().createQuery("from GrupoUsuario where grupo.idGrupo = :idGrupo");
		q.setParameter("idGrupo",idGrupo);
		q.setCacheable(true);
		List <GrupoUsuario> lista = q.list();
		instanciaLista(lista);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> findByGrupo(String nome) {
		Query q = getSession().createQuery("from GrupoUsuario where grupo.nome like :nome");
		q.setParameter("nome",nome);
		q.setCacheable(true);
		List <GrupoUsuario> lista = q.list();
		instanciaLista(lista);
		return lista;
	}

	
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> findByUsuarioId(Integer idUsuario) {
		Query q = getSession().createQuery("from GrupoUsuario where usuario.idUsuario = :idUsuario");
		q.setParameter("idUsuario",idUsuario);
		q.setCacheable(true);
		List <GrupoUsuario> lista = q.list();
		instanciaLista(lista);
		return lista;
	}
	
	
	public List<GrupoUsuario> findByUsuarioEGrupo(Integer idGrupo, Integer idUsuario) {
		SimpleExpression criteria1 = Restrictions.eq("usuario.idUsuario", idUsuario);
		SimpleExpression criteria2 = Restrictions.eq("grupo.idGrupo", idGrupo);
		List<GrupoUsuario> lista = findByCriteria(criteria1, criteria2);
		return lista;
	}
	
	public List <GrupoUsuario> findAllGrupoUsuario(){
		List <GrupoUsuario> lista = findByCriteria();
		return lista;
	}
	
	

	@Override
	public List<GrupoUsuario> instanciaLista(List<GrupoUsuario> lista) {
		try {
			for (GrupoUsuario gu : lista) {
				gu = instanciaGrupoUsuario(gu);
			}	
		} catch (EmptyFieldException e) {
			throw new ViolacaoConstraintException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static GrupoUsuario instanciaGrupoUsuario(GrupoUsuario gu) throws EmptyFieldException{
		gu.setGrupo(GrupoHibernateDAO.instanciaGrupo(gu.getGrupo()));
		gu.setUsuario(UsuarioHibernateDAO.instanciaUsuario(gu.getUsuario()));
		gu= MyPersistenceLayer.deproxy(gu, GrupoUsuario.class);
		return gu;
	}


	
	

}
