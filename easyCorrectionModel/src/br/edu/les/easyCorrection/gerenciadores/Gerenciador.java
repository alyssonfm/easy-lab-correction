package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.DAO.hibernate.HibernateUtil;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;

public abstract class Gerenciador {


	public Periodo getPeriodo(int periodoId) {
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}

	public List<Periodo> getPeriodoAtual() {
		return DAOFactory.DEFAULT.buildPeriodoDAO().findAll();
	}

	public void reinicializaBancoDeDados(String script){
		try{
			HibernateUtil.executeSQL(script);
		} catch(Exception e){
			System.out.println("Erro no script do banco.");
		}
	}
	
}
