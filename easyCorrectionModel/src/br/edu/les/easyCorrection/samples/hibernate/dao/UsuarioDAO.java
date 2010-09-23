package br.edu.les.easyCorrection.samples.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import br.edu.les.easyCorrection.DAO.hibernate.AbstractHibernateDAO;
import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;

public class UsuarioDAO extends AbstractHibernateDAO<UsuarioTeste, Integer>{
	
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
	public void instaciaLista(List<UsuarioTeste> lista) {
		//DAOFactory.DEFAULT.buildUsuarioTestDAO().instaciaLista(us);
	}
}