package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;

public abstract class Gerenciador {


	public Periodo getPeriodo(int periodoId) {
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}

	public List<Periodo> getPeriodoAtual() {
		return DAOFactory.DEFAULT.buildPeriodoDAO().findAll();
	}


	public void limpaBancoDeDados(){
		/*
		 * Remove tudo!
		 */
	}
	
	public void inicializaBancoDeDados(){
		/*
		 * Inicializa com:
		 * 
		 * usu�rios: n�s 4 
		 * grupos: admin, prof, mon e aluno
		 * permiss�es: admin v� tudo (s� isso por enquanto)
		 * per�odo: 2010.2
		 */
	}
	
}
