package br.edu.les.easyCorrection.DAO.hibernate;

import br.edu.les.easyCorrection.DAO.hibernate.acesso.FuncaoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.GrupoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.GrupoUsuarioHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.MenuHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.PermissaoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.UsuarioHibernateDAO;
import br.edu.les.easyCorrection.samples.hibernate.dao.UsuarioDAO;

/**
 * Generated at Fri Jan 30 09:30:06 GMT-03:00 2009
 *
 * @see http://www.hibernate.org/328.html
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 */

public abstract class DAOFactory {

	private static final DAOFactory HIBERNATE = new HibernateDAOFactory();

	public static final DAOFactory DEFAULT = HIBERNATE;
	
    /**
     * Factory method for instantiation of concrete factories.
     */
    @SuppressWarnings("unchecked")
	public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }
    
	public abstract MenuHibernateDAO buildMenuDAO();
	
	public abstract GrupoHibernateDAO buildGrupoDAO();
	
	public abstract UsuarioHibernateDAO buildUsuarioDAO();
	
	public abstract GrupoUsuarioHibernateDAO buildGrupoUsuarioDAO();
	
	public abstract FuncaoHibernateDAO buildFuncaoDAO();
	
	public abstract PermissaoHibernateDAO buildPermissaoDAO();
	
	//Utilizado Para o teste de Augusto
	public abstract UsuarioDAO buildUsuarioTesteDAO();
		
}
