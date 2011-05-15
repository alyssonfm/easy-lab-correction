package br.edu.les.easyCorrection.DAO.hibernate.acesso;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.PeriodoHibernateDAO;
import br.edu.les.easyCorrection.exceptions.EmptyFieldException;
import br.edu.les.easyCorrection.exceptions.ConstraintViolationException;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.util.MyPersistenceLayer;

/**
 * <p>Hibernate DAO layer for Agendas</p>
 * <p>Generated at Fri Jan 30 09:30:05 GMT-03:00 2009</p>
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public class UsuarioHibernateDAO extends
		AbstractHibernateDAO<Usuario, Integer>  {

	public UsuarioHibernateDAO(Session s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public List<Usuario> findById(Integer id) {
		return findByCriteria(Restrictions.eq("id", id));
	}
	
	
	public List<Usuario> findByTitulo(String nome) {
		return findByCriteria(Restrictions.eq("nome", nome));
	}
	
	public List <Usuario> findByLogin(String login){
		return findByCriteria(Restrictions.eq("login", login));
	}
	
	public List <Usuario> findByEmail(String email){
		return findByCriteria(Restrictions.eq("email", email));
	}
	
	public List<Usuario> findByLoginESenha(String login, String senha) {
		SimpleExpression criteria = Restrictions.eq("senha", senha);
		SimpleExpression criteria2 = Restrictions.eq("login", login);
		return findByCriteria(criteria,criteria2);
	}

	@Override
	public List<Usuario> instanciaLista(List<Usuario> lista) {
		try {
			for (Usuario u : lista) {
				u = instanciaUsuario(u);
			}	
		} catch (EmptyFieldException e) {
			throw new ConstraintViolationException(e.getMessage());
		}
		finally{
			HibernateUtil.closeSession();
		}
		return lista;
	}
	
	public static Usuario instanciaUsuario(Usuario u) throws EmptyFieldException{
		u.setPeriodo(PeriodoHibernateDAO.instanciaPeriodo(u.getPeriodo()));
		u = MyPersistenceLayer.deproxy(u, Usuario.class);
		return u;
	}


	
	

}
