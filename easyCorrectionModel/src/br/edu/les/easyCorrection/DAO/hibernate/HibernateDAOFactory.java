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
 * @see http://www.hibernate.org/43.html
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 */
public class HibernateDAOFactory extends DAOFactory {

	public MenuHibernateDAO buildMenuDAO() {
		return new MenuHibernateDAO(HibernateUtil.getSession());
	}
	
	public GrupoHibernateDAO buildGrupoDAO() {
		return new GrupoHibernateDAO(HibernateUtil.getSession());
	}
	
	public UsuarioHibernateDAO buildUsuarioDAO() {
		return new UsuarioHibernateDAO(HibernateUtil.getSession());
	}
	
	public GrupoUsuarioHibernateDAO buildGrupoUsuarioDAO() {
		return new GrupoUsuarioHibernateDAO(HibernateUtil.getSession());
	}
	
	public FuncaoHibernateDAO buildFuncaoDAO() {
		return new FuncaoHibernateDAO(HibernateUtil.getSession());
	}
	
	public PermissaoHibernateDAO buildPermissaoDAO() {
		return new PermissaoHibernateDAO(HibernateUtil.getSession());
	}

	//Utilizado Para o teste de Augusto
	public UsuarioDAO buildUsuarioTesteDAO() {
		return new UsuarioDAO(HibernateUtil.getSession());
	}
}
