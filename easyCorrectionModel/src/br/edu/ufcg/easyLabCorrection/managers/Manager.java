package br.edu.ufcg.easyLabCorrection.managers;

import java.util.List;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;

public abstract class Manager {


	public Stage getPeriod(int periodId) {
		return DAOFactory.DEFAULT.buildPeriodDAO().getById(periodId);
	}

	public List<Stage> getCurrentPeriod() {
		return DAOFactory.DEFAULT.buildPeriodDAO().findAll();
	}

	public void reinicializaBancoDeDados(String script){
		try{
			HibernateUtil.executeSQL(script);
		} catch(Exception e){
			System.out.println("Erro no script do banco.");
		}
	}
	
}
