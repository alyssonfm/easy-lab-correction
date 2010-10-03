package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.pojo.sistema.Roteiro;

public class GerenciadorRoteiros {

	public Periodo getPeriodo(int periodoId){
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}
	
	public Roteiro getRoteiro(int roteiroId){
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(roteiroId);
	}

	/*
	 * Duvidas rever o mais rapido possivel
	 */
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp) {
		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}

	public void excluirRoteiro(Roteiro roteiro){
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}
	
	
}
