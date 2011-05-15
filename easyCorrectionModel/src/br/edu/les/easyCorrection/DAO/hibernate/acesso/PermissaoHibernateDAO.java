package br.edu.les.easyCorrection.DAO.hibernate.acesso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.exceptions.EmptyFieldException;
import br.edu.les.easyCorrection.exceptions.ConstraintViolationException;
import br.edu.les.easyCorrection.pojo.acesso.Permissao;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class PermissaoHibernateDAO extends
		AbstractHibernateDAO<Permissao, Integer>  {

	public PermissaoHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public List<Permissao> findById(Integer id) {
		List<Permissao> lista = findByCriteria(Restrictions.eq("id", id));
		return lista;
	}
	
	public List<Permissao> findByTitulo(Integer id) {
		List <Permissao> lista = findByCriteria(Restrictions.eq("id", id));
		return lista;
	}
	
	public List<Permissao> findByIdGrupo(Integer idGrupo) {
		List <Permissao> lista = findByCriteria(Restrictions.eq("grupo.id", idGrupo));
		return lista;
	}
	
	public List<Permissao> findAllPermissao() {
		List <Permissao> lista = findByCriteria();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Permissao>  findByGrupoAndFuncao(Integer idGrupo, Integer idFuncao) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("grupo.idGrupo", idGrupo));
		crit.add(Restrictions.eq("funcao.idFuncao", idFuncao));
        List<Permissao> lista = crit.list();
        instanciaLista(lista);
        return  lista;
	}

	@Override
	public List<Permissao> instanciaLista(List<Permissao> lista) {
		try {
			for (Permissao p : lista) {
				p = instanciaPermissao(p);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Permissao instanciaPermissao(Permissao p) throws EmptyFieldException{
		p.setFuncao(FuncaoHibernateDAO.instanciaFuncao(p.getFuncao()));
		p.setGrupo(GrupoHibernateDAO.instanciaGrupo(p.getGrupo()));
		p = MyPersistenceLayer.deproxy(p, Permissao.class);
		
		return p;
	}


	

	
	

}
