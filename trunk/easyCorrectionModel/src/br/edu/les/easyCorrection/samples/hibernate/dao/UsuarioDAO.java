package br.edu.les.easyCorrection.samples.hibernate.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;

public class UsuarioDAO{
	
	private SessionFactory factory;
	
	public UsuarioDAO() throws Exception{
		factory = new Configuration().addClass(Usuario.class).buildSessionFactory();
		
	}     
	
	public void UsInserir(Usuario us) throws Exception {
		Session session = factory.openSession();
		session.save(us);
		session.flush();
		session.close();
	}
	
	public void UsAlterar(Usuario us) throws Exception {
		Session session = factory.openSession();
		session.update(us);
		session.flush();
		session.close();
	}
	public void UsExcluir(Usuario us) throws Exception {
		Session session = factory.openSession();
		session.delete(us);
		session.flush();
		session.close();
	}
}