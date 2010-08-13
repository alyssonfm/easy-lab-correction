package br.edu.les.easyCorrection.DAO.hibernate.acesso;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.pojo.acesso.Funcao;
import br.edu.les.easyCorrection.pojo.acesso.Grupo;
import br.edu.les.easyCorrection.pojo.acesso.Menu;
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
	


	@Override
	public void instaciaLista(List<Permissao> lista) {
		for (Permissao p: lista) {
			
			Grupo grupo = MyPersistenceLayer.deproxy(p.getGrupo(),
					Grupo.class);
			p.setGrupo(grupo);
			
			Funcao funcao = MyPersistenceLayer.deproxy(p.getFuncao(),
					Funcao.class);
			p.setFuncao(funcao);
			
			Menu menu = MyPersistenceLayer.deproxy(p.getFuncao().getMenu(), 
					Menu.class);
			p.getFuncao().setMenu(menu);
			
		}
		HibernateUtil.closeSession();
	}

	@SuppressWarnings("unchecked")
	public List<Permissao>  findByGrupoAndFuncao(Integer idGrupo, Integer idFuncao) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("grupo.idGrupo", idGrupo));
		crit.add(Restrictions.eq("funcao.idFuncao", idFuncao));
        List<Permissao> lista = crit.list();
        instaciaLista(lista);
        return  lista;
	}

	
	

}
