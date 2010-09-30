package br.edu.les.easyCorrection.DAO.hibernate;

import br.edu.les.easyCorrection.DAO.hibernate.acesso.FuncaoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.GrupoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.GrupoUsuarioHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.MenuHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.PermissaoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.acesso.UsuarioHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.AvaliacaoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.ChatHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.EquipeHasUsuarioHasRoteiroHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.EquipeHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.PeriodoHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.RoteiroHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.sistema.SubmissaoHibernateDAO;
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

	@Override
	public AvaliacaoHibernateDAO buildAvaliacaoDAO() {
		return new AvaliacaoHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public ChatHibernateDAO buildChatDAO() {
		return new ChatHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public EquipeHibernateDAO buildEquipeDAO() {
		return new EquipeHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public EquipeHasUsuarioHasRoteiroHibernateDAO buildEquipeHasUsuarioHasRoteiroDAO() {
		return new EquipeHasUsuarioHasRoteiroHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public PeriodoHibernateDAO buildPeriodoDAO() {
		return new PeriodoHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public RoteiroHibernateDAO buildRoteiroDAO() {
		return new RoteiroHibernateDAO(HibernateUtil.getSession());
	}

	@Override
	public SubmissaoHibernateDAO buildSubmissaoDAO() {
		return new SubmissaoHibernateDAO(HibernateUtil.getSession());
	}
}
