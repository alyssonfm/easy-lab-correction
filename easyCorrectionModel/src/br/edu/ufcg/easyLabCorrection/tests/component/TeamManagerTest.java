package br.edu.ufcg.easyLabCorrection.tests.component;

import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.TeamManager;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.system.Facade;

/**
 * This test class is going to test the TeamManager, that has the following
 * roles: Team CRUD and TeamHasUserHasAssignment CRUD
 */
public class TeamManagerTest {

	private Facade facade;
	private TeamManager team;

	private int idOK;
	private String nameOK;
	private Team teamOK;
	private User userOK;
	private Assignment assignOK;
	private TeamHasUserHasAssignment tuaOK;
	
	public TeamManagerTest() {
		team = new TeamManager();
		
		idOK = 1;
		nameOK = "nameOK";
		teamOK = new Team(1, nameOK);
		
		userOK = new User("1", "User OK", "123456", "user@gmail.com");
		
		assignOK = new Assignment();
		assignOK.setId(1);
		assignOK.setStage(new Stage(1, "2011.1"));
		assignOK.setName("AssignOK");
		assignOK.setDescription("DescOK");
		Calendar testCalendar = Calendar.getInstance();
		testCalendar.add(Calendar.DAY_OF_YEAR, 7);
		assignOK.setReleaseDate(testCalendar.getTime());
		testCalendar.add(Calendar.DAY_OF_YEAR, 7);
		assignOK.setDeliveryDate(testCalendar.getTime());
		testCalendar.add(Calendar.DAY_OF_YEAR, 7);
		assignOK.setDiscussionDate(testCalendar.getTime());
		assignOK.setParticipantsMaxNumber(1);
		assignOK.setSendMaxNumber(1);
		assignOK.setPenaltyPerDaysLate(1.0);
		assignOK.setAutomaticTestsPercentage(0.5);
		assignOK.setTestTimeLimit(10000);
		assignOK.setInterfaceDirectory("OK");
		assignOK.setTestsDirectory("OK");
				
		tuaOK = new TeamHasUserHasAssignment(1, teamOK, assignOK, userOK);
	}

	@BeforeClass
	public void restartDatabase() {
		facade.reinicializaBancoDeDados();
	}

	@Test
	public void testTeamBadParameters(){
		
		Team tNULL = team.getTeam(-1); // EXCEPTION
		Team tNULL2 = team.getTeam(0); // EXCEPTION
		Team tNULL3 = team.getTeamByName("TeamOK"); // EXCEPTION 
		List<Team> list = team.getTeams(); // EMPTY
		
		
		
		/*
		 * CREATE
		 */
		
		Team t1 = new Team(-1, nameOK);
		Team t2 = new Team(idOK, "");
		Team t3 = new Team(idOK, null);
		Team tOK = new Team(-1, nameOK);
		
		try {
			team.saveTeam(t1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeam(t2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeam(t3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		
		
		try {
			team.saveTeam(tOK);
		} catch (EasyCorrectionException e) {
			Assert.assertTrue(false);
		}
		
		/*
		 * RETRIEVE
		 */
		
		
		
		/*
		 * UPDATE
		 */
		
		
		
		/*
		 * DELETE
		 */
		
	}
	
}
