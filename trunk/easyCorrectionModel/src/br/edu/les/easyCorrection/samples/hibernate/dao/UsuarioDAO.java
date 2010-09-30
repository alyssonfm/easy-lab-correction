package br.edu.les.easyCorrection.samples.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.samples.hibernate.pojo.UsuarioTeste;

public class UsuarioDAO extends AbstractHibernateDAO<UsuarioTeste, String>{
	
	public UsuarioDAO(Session s){
		super(s);	
	}     
	
	public void UsInserir(UsuarioTeste us) throws Exception {
		DAOFactory.DEFAULT.buildUsuarioTesteDAO().save(us);
	}
	
	public void UsAlterar(UsuarioTeste us) throws Exception {
		DAOFactory.DEFAULT.buildUsuarioTesteDAO().update(us);
	}
	public void UsExcluir(UsuarioTeste us) throws Exception {
		DAOFactory.DEFAULT.buildUsuarioTesteDAO().delete(us);
	}

	@Override
	public List<UsuarioTeste> instanciaLista(List<UsuarioTeste> lista) {
		// TODO Auto-generated method stub
		return null;
	}

}