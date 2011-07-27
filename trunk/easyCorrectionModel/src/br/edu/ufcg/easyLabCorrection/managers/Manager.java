package br.edu.ufcg.easyLabCorrection.managers;

import java.util.List;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.pojo.system.SystemStage;
import br.edu.ufcg.easyLabCorrection.util.Constants;

/**
 * Abstract super class of managers, which is extended by the 
 * managers of the specific system Easy Lab Correction.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public abstract class Manager {

	/**
	 * Function used to retrieve a stage of year, receives an identifier 
	 * of stage as parameter.<br>
	 * 
	 * @param periodId The identifier of stage to be used in the recovery 
	 * of the stage.<br>
	 * 
	 * @return The stage corresponding at the identifier received as parameter.<br>
	 */
	public SystemStage getPeriod(int periodId) {
		return DAOFactory.DEFAULT.buildSystemStage().getById(periodId);
	}

	/**
	 * Function used to retrieve the current stage.<br>
	 * @return The stage current.<br>
	 */
	public List<SystemStage> getCurrentPeriod() {
		return DAOFactory.DEFAULT.buildSystemStage().findAll();
	}

	/**
	 * Procedure used to restart the database of system, receives a script 
	 * as parameter.<br>
	 * @param script The script to be used to restart the database.<br>
	 */
	public void rebootDataBase(){
		try{
			HibernateUtil.executeSQL(Constants.bdBackupFile);
		} catch(Exception e){
			System.out.println("Erro no script do banco.");
		}
	}
	
}
