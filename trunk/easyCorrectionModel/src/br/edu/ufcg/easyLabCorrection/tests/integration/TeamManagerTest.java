package br.edu.ufcg.easyLabCorrection.tests.integration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.managers.TeamManager;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
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
		teamOK = new Team(idOK, nameOK);

		userOK = new User("login", "User OK", "123456", "user@gmail.com");

		assignOK = new Assignment();
		assignOK.setId(idOK);
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

		tuaOK = new TeamHasUserHasAssignment(idOK, teamOK, assignOK, userOK);
	}
	
	@BeforeClass
	public void restartDatabase() {
		facade.rebootsDataBase();
	}

	@Test
	public void testTeamBadParameters() {

		try {
			team.getTeam(1); // EXCEPTION
		} catch (ObjectNotFoundException e) {
		}
		try {
			team.getTeamByName("TeamOK"); // EXCEPTION
		} catch (ObjectNotFoundException e) {
		}

		List<Team> list = team.getTeams(); // EMPTY
		Assert.assertEquals(list.size(), 0);

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
		try {
			team.getTeam(-1); // EXCEPTION
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			team.getTeam(0); // EXCEPTION
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			team.getTeamByName(""); // EXCEPTION
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}
		try {
			team.getTeamByName(null); // EXCEPTION
			Assert.assertTrue(false);
		} catch (ObjectNotFoundException e) {
		}

		List<Team> list2 = team.getTeams(); // EMPTY
		Assert.assertNotSame(list2.size(), 0);

		/*
		 * UPDATE
		 */

		Team t4 = new Team(idOK, null);
		Team t5 = new Team(idOK, "");

		try {
			team.saveTeam(t4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		try {
			team.saveTeam(t5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		/*
		 * DELETE - Teams are never deleted
		 */

	}

	@Test
	public void testTUABadParameters() {

		Assert.assertEquals(0, team.getNumberOfAllocatedTeams(assignOK)); // EMPTY
		Assert.assertNull(team.getTeamHasUserHasAssignmentByAssignment(idOK)); // NULL
		Assert.assertNull(team
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				idOK, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				idOK, idOK)); // NULL
		List<TeamHasUserHasAssignment> list = team
				.getTeamHasUserHasAssignments();

		Assert.assertEquals(0, list.size());

		/*
		 * CREATE
		 */

		TeamHasUserHasAssignment tua1 = new TeamHasUserHasAssignment(null,
				teamOK, assignOK, userOK);
		TeamHasUserHasAssignment tua2 = new TeamHasUserHasAssignment(-1,
				teamOK, assignOK, userOK);
		TeamHasUserHasAssignment tua3 = new TeamHasUserHasAssignment(idOK,
				null, assignOK, userOK);
		TeamHasUserHasAssignment tua4 = new TeamHasUserHasAssignment(idOK,
				teamOK, null, userOK);
		TeamHasUserHasAssignment tua5 = new TeamHasUserHasAssignment(idOK,
				teamOK, assignOK, null);

		try {
			team.saveTeamHasUserHasAssignment(tua1);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeamHasUserHasAssignment(tua2);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeamHasUserHasAssignment(tua3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeamHasUserHasAssignment(tua4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeamHasUserHasAssignment(tua5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}

		try {
			team.saveTeamHasUserHasAssignment(tuaOK);
		} catch (EasyCorrectionException e) {
			Assert.assertTrue(false);
		}

		// AllocateTeamForUser

		List<Team> teamsOK = new ArrayList<Team>();
		teamsOK.add(teamOK);
		List<UserGroup> usersOK = new ArrayList<UserGroup>();
		usersOK.add(new UserGroup(idOK, new Group(idOK, "GroupOK"), userOK));
		
		try {
			team.allocateTeamsForUsers(null, teamsOK, usersOK);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.allocateTeamsForUsers(assignOK, null, usersOK);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.allocateTeamsForUsers(assignOK, teamsOK, null);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		//team.allocateTeamsForUsers(assignOK, teamsOK, usersOK); -- it is already allocated

		/*
		 * RETRIEVE
		 */

		Assert.assertNotSame(0, team.getNumberOfAllocatedTeams(null)); // EMPTY
		Assert.assertNotSame(1, team.getNumberOfAllocatedTeams(assignOK)); // NOT
		// EMPTY

		Assert.assertNull(team.getTeamHasUserHasAssignmentByAssignment(null)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByAssignment(-1)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByAssignment(0)); // NULL
		Assert
				.assertNotNull(team
						.getTeamHasUserHasAssignmentByAssignment(idOK)); // NOT
		// NULL

		Assert.assertNull(team
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(null)); // NULL
		Assert.assertNull(team
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(-1)); // NULL
		Assert.assertNull(team
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(0)); // NULL
		Assert.assertNotNull(team
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(idOK)); // NOT
		// NULL

		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				null, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				-1, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				0, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				idOK, null)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				idOK, -1)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByTeamAndAssignment(
				idOK, 0)); // NULL
		Assert.assertNotNull(team
				.getTeamHasUserHasAssignmentByTeamAndAssignment(idOK, idOK)); // NOT
		// NULL

		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				null, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				-1, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				0, idOK)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				idOK, null)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				idOK, -1)); // NULL
		Assert.assertNull(team.getTeamHasUserHasAssignmentByUserAndAssignment(
				idOK, 0)); // NULL
		Assert.assertNotNull(team
				.getTeamHasUserHasAssignmentByUserAndAssignment(idOK, idOK)); // NOT
																				// NULL

		List<TeamHasUserHasAssignment> list2 = team
				.getTeamHasUserHasAssignments();

		Assert.assertNotSame(0, list2.size());

		/*
		 * UPDATE
		 */

		try {
			team.saveTeamHasUserHasAssignment(tua3);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeamHasUserHasAssignment(tua4);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		try {
			team.saveTeamHasUserHasAssignment(tua5);
			Assert.assertTrue(false);
		} catch (EasyCorrectionException e) {
		}
		
		/*
		 * DELETE - TeamHasUserHasAssignment cannot be deleted
		 */

	}

}
