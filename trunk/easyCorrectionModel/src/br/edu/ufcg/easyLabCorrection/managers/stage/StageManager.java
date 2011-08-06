package br.edu.ufcg.easyLabCorrection.managers.stage;

import java.util.List;


import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.system.SystemStage;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.ErrorMsgs;

public class StageManager {
	
	
	public List<SystemStage> listSystemStage() {
		return DAOFactory.DEFAULT.buildSystemStage().findAll();
	}
	
	public SystemStage getStage(Integer id) {
		SystemStage stage = DAOFactory.DEFAULT.buildSystemStage().getById(id);
		if (stage == null) {
			throw new ObjectNotFoundException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("período"));
		}
		return stage;
	}
	
	public SystemStage createSystemStage(SystemStage stage)
		throws EasyCorrectionException {
		Integer id = DAOFactory.DEFAULT.buildSystemStage().save(stage);
		stage.setId(id);
		return stage;
	}

	public SystemStage updateSystemStage(SystemStage stage)
		throws EasyCorrectionException {
		SystemStage stg = new SystemStage();
		stg = getStage(stage.getId());
		stg = (SystemStage) SwapperAtributosReflect.swapObject(stg,
		stage, SystemStage.class);
		DAOFactory.DEFAULT.buildSystemStage().update(stg);
		return stage;
	}
	
	public void deleteStage(SystemStage stage) throws EasyCorrectionException {

		SystemStage stg = getStage(stage.getId());
		stg = (SystemStage) SwapperAtributosReflect.swapObject(stg, stage,
				SystemStage.class);
		DAOFactory.DEFAULT.buildSystemStage().delete(stg);
	}

}
