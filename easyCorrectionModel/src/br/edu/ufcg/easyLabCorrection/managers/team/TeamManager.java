package br.edu.ufcg.easyLabCorrection.managers.team;

import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.managers.Manager;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.InternalErrorMsgs;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;

/**
 * Class responsible for managing of teams in the system Easy Lab Correction.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class TeamManager extends Manager {

	/**
	 * Constructor default of class, creates a new object TeamManager.<br>
	 */
	public TeamManager() {
		super();
	}

	/**
	 * Function used to save a team in database of the system Easy Lab
	 * Correction.<br>
	 * 
	 * @param t
	 *            The team to be saved.<br>
	 * @return The team that was saved.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to save the team in the
	 *             database system.<br>
	 */
	public Team saveTeam(Team t) throws EasyCorrectionException {
		if (t != null) {
			List<Team> teams = getTeams();
			if (teams.isEmpty()) {
				t.setName("Team 1");
				Integer id = DAOFactory.DEFAULT.buildTeamDAO().save(t);
				t.setId(id);
			} else {
				int ultimoNumero = Integer.parseInt(teams.get(teams.size() - 1)
						.getName().split(" ")[1]) + 1;
				List<Team> lista = DAOFactory.DEFAULT.buildTeamDAO()
						.findByName(t.getName());
				if (lista.isEmpty()) {
					t.setName("Team " + ultimoNumero);
					Integer id = DAOFactory.DEFAULT.buildTeamDAO().save(t);
					t.setId(id);
				}
			}
		}
		return t;
	}

	/**
	 * Function used to retrieve a team by its identifier.<br>
	 * 
	 * @param id
	 *            The identifier of team who want to retrieve.<br>
	 * @return The team whose identifier corresponds at the identifier passed as
	 *         parameter.<br>
	 */
	public Team getTeam(int id) {
		Team team = DAOFactory.DEFAULT.buildTeamDAO().getById(id);
		if (team == null) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("Team"));
		}
		return team;
	}

	/**
	 * Function used to retrieve all teams of the system Easy Lab Correction.<br>
	 * 
	 * @return A list of all teams of the system.<br>
	 */
	public List<Team> getTeams() {
		return DAOFactory.DEFAULT.buildTeamDAO().findAll();
	}

	/**
	 * Function used to retrieve a team by its name.<br>
	 * 
	 * @param name
	 *            The name of team who want to retrieve.<br>
	 * @return The team whose name corresponds at the name passed as parameter.<br>
	 */
	public Team getTeamByName(String name) {
		List<Team> Teams = DAOFactory.DEFAULT.buildTeamDAO().findByName(name);
		Team te = Teams.get(0);
		if (te == null) {
			throw new ObjectNotFoundException(InternalErrorMsgs.OBJ_NOT_FOUND
					.msg("Team"));
		}
		return te;
	}

	/*
	 * TEAM HAS USER HAS ASSIGNMENT
	 */

	private boolean isTeamFull(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {
		List<TeamHasUserHasAssignment> list = getTeamHasUserHasAssignmentByTeamAndAssignment(
				tua.getTeam().getId(), tua.getAssignment().getId());
		if (list.size() >= tua.getAssignment().getParticipantsMaxNumber()) {
			throw new EasyCorrectionException(
					"Esta equipe ja contem o numero maximo de participantes. Por favor cadastre-se em outra equipe.");
		}
		return true;
	}

	/**
	 * Procedure used to allocate teams for the users of the system Easy Lab
	 * Correction.<br>
	 * 
	 * @param assign
	 *            The assignment for which you want to make the allocation.<br>
	 * @param teams
	 *            The list of all teams of the system.<br>
	 * @param users
	 *            The list of users of the system.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in the allocation of teams to users.<br>
	 */
	public void allocateTeamsForUsers(Assignment assign, List<Team> teams,
			List<UserGroup> users) throws EasyCorrectionException {
		if (users.size() != 0) {
			for (int i = 0; i < users.size(); i++) {
				TeamHasUserHasAssignment tua = new TeamHasUserHasAssignment();
				tua.setAssignment(assign);
				tua.setTeam(teams.get(i));
				tua.setUser(users.get(i).getUser());
				saveTeamHasUserHasAssignment(tua);
			}
		}
	}

	/**
	 * Function used to retrieve the number of teams allocated for the
	 * assignment passed as parameter.<br>
	 * 
	 * @param assignment
	 *            The assignment who want to retrieve the number of allocated
	 *            teams.<br>
	 * @return The number of teams allocated for the assignment passed as
	 *         parameter.<br>
	 */
	public int getNumberOfAllocatedTeams(Assignment assignment) {
		if (assignment == null) {
			throw new ObjectNotFoundException(
					"Nao ha equipes alocadas pois o roteiro ainda nao foi criado!");
		}
		List<TeamHasUserHasAssignment> list = DAOFactory.DEFAULT
				.buildTeamHasUserHasAssignmentDAO().findByAssignment(
						assignment.getId());
		return list.size();
	}

	/**
	 * Function used to change the team of the user in a given assignment.<br>
	 * 
	 * @param tua
	 *            The team of the user of assignment who want to change.<br>
	 * @return The team of the user of assignment changed.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to change the team a
	 *             user in a assignment.
	 */
	public TeamHasUserHasAssignment changeTeam(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {

		if (tua.getAssignment().getParticipantsMaxNumber() == 1) {
			throw new ObjectNotFoundException(
					"Nenhuma equipe pode ser modificada. O Roteiro eh individual!");
		}
		if (tua.getAssignment().getParticipantsMaxNumber() == getTeamHasUserHasAssignmentByTeamAndAssignment(
				tua.getTeam().getId(), tua.getAssignment().getId()).size()) {

			throw new ObjectNotFoundException(
					"Nao foi possivel mudar de equipe! Limite de integrantes da "
							+ tua.getTeam().getName()
							+ " ja alcancado (maximo de "
							+ tua.getAssignment().getParticipantsMaxNumber()
									.toString() + " integrante(s) por equipe).");
		}
		try {
			getTeam(tua.getTeam().getId());
		} catch (Exception e) {
			throw new ObjectNotFoundException(
					"Mudanca nao realizada! Equipe inexistente.");
		}

		TeamHasUserHasAssignment teamUserAssignment = getTeamHasUserHasAssignmentByUserAndAssignment(
				tua.getUser().getUserId(), tua.getAssignment().getId());
		if (teamUserAssignment != null) {
			deleteTeamHasUserHasAssignment(teamUserAssignment);
			teamUserAssignment = saveTeamHasUserHasAssignment(tua);
		} else {
			teamUserAssignment = saveTeamHasUserHasAssignment(tua);
		}
		return teamUserAssignment;
	}

	/**
	 * Function used to retrieve the team of the user of the assignment by its
	 * user identifier and assignment identifier.<br>
	 * 
	 * @param userId
	 *            The user identifier used to retrieve the team.<br>
	 * @param assignmentId
	 *            The assignment identifier used to retrieve the team.<br>
	 * @return The team of the user of assignment whose user identifier and
	 *         assignment identifier corresponds at the identifiers passed as
	 *         parameter.<br>
	 */
	public TeamHasUserHasAssignment getTeamHasUserHasAssignmentByUserAndAssignment(
			Integer userId, Integer assignmentId) {
		List<TeamHasUserHasAssignment> list = DAOFactory.DEFAULT
				.buildTeamHasUserHasAssignmentDAO().findByUserAndAssignment(
						userId, assignmentId);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * Function used to retrieve a list of team of the user of assignment
	 * receiving as parameter the team identifier and the assignment identifier.<br>
	 * 
	 * @param teamId
	 *            The team identifier used in the recovery.<br>
	 * @param assignmentId
	 *            the assignment identifier used in the recovery.<br>
	 * @return A list of team of the user of assignment whose team and
	 *         assignment identifiers corresponds at the identifiers passed as
	 *         parameter.<br>
	 */
	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByTeamAndAssignment(
			Integer teamId, Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByTeamAndAssignment(teamId, assignmentId);
	}

	/**
	 * Function used to retrieve a list of team of the user of assignment
	 * receiving as parameter the assignment identifier.<br>
	 * 
	 * @param assignmentId
	 *            the assignment identifier used in the recovery.<br>
	 * @return A list of team of the user of assignment whose assignment
	 *         identifier corresponds at the assignment identifier passed as
	 *         parameter.<br>
	 */
	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignment(
			Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByAssignment(assignmentId);
	}

	/**
	 * Function used to retrieve a list of team of the user of assignment
	 * receiving as parameter the assignment identifier.<br>
	 * 
	 * @param assignmentId
	 *            the assignment identifier used in the recovery.<br>
	 * @return A list of team of the user of assignment whose assignment
	 *         identifier corresponds at the assignment identifier passed as
	 *         parameter grouped by team.<br>
	 */
	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignmentGroupByTeam(
			Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByAssignmentGroupByTeam(assignmentId);
	}

	/**
	 * Function used to retrieve all teams of the users of assignments of the
	 * system Easy Lab Correction.<br>
	 * 
	 * @return A list of all teams of the users of assignments of the system.<br>
	 */
	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignments() {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO().findAll();
	}

	/**
	 * Function used to save a team of the user of assignment in the system Easy
	 * Lab Correction.<br>
	 * 
	 * @param tua
	 *            The team of the user of assignment who want to save.<br>
	 * @return The team of the user of assignment save.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to save the team of the
	 *             user of assignment.<br>
	 */
	public TeamHasUserHasAssignment saveTeamHasUserHasAssignment(
			TeamHasUserHasAssignment tua) throws EasyCorrectionException {
		if (tua != null) {
			isTeamFull(tua);
			Integer id = DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
					.save(tua);
			tua.setId(id);
		}
		return tua;
	}

	private void deleteTeamHasUserHasAssignment(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {
		TeamHasUserHasAssignment localTua = getTeamHasUserHasAssignmentByUserAndAssignment(
				tua.getUser().getUserId(), tua.getAssignment().getId());
		localTua = (TeamHasUserHasAssignment) SwapperAtributosReflect
				.swapObject(localTua, tua, TeamHasUserHasAssignment.class);
		DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO().delete(localTua);
	}

	public void deleteAllTeamHasUserHasAssignmentByAssignment(Integer assignmentId) {
		DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO().deleteAllTeamHasUserHasAssignmentByAssignment(assignmentId);
	}

	public void deleteAllTeamHasUserHasAssignmentByUserId(Integer userId) {
		DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO().deleteAllTeamHasUserHasAssignmentByUserId(userId);
		
	}
	
}
