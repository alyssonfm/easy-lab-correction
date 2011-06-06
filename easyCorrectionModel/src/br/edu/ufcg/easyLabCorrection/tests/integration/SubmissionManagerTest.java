package br.edu.ufcg.easyLabCorrection.tests.integration;

import java.util.Date;

import org.junit.BeforeClass;

import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.system.Facade;


public class SubmissionManagerTest {
	
	private Facade facade;
	
	private Integer idOK;
	private Date submissionDateOK;
	private TeamHasUserHasAssignment teamHasUserHasAssignmentOK;
	private String urlOK;
	private String statusOK;
	
	public SubmissionManagerTest() {
		
		facade = new Facade();
		
	}
	
	@BeforeClass
	public void reinicializaBD() {
		facade.reinicializaBancoDeDados();
	}

}
