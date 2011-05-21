package br.edu.ufcg.easyLabCorrection.managers;

import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class TeamManager extends Manager {

	public TeamManager() {
		super();
	}

	public Team saveTeam(Team t) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(t)) {
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

	public Team getTeam(int id) {
		Team team = DAOFactory.DEFAULT.buildTeamDAO().getById(id);
		if (easyCorrectionUtil.isNull(team)) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("Team"));
		}
		return team;
	}

	public List<Team> getTeams() {
		return DAOFactory.DEFAULT.buildTeamDAO().findAll();
	}

	public Team getTeamByName(String name) {
		List<Team> Teams = DAOFactory.DEFAULT.buildTeamDAO().findByName(name);
		Team te = Teams.get(0);
		if (easyCorrectionUtil.isNull(te)) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("Team"));
		}
		return te;
	}

	public int getAllocatedTeams(Assignment assignment) {
		if (assignment == null) {
			throw new ObjectNotFoundException(MsgErros.ROTEIRO_INEXISTENTE
					.msg(""));
		}
		List<TeamHasUserHasAssignment> list = DAOFactory.DEFAULT
				.buildTeamHasUserHasAssignmentDAO().findByAssignment(
						assignment.getId());
		return list.size();
	}

	private boolean isTeamFull(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {
		List<TeamHasUserHasAssignment> list = getTeamHasUserHasAssignmentByTeamAndAssignment(
				tua.getTeam().getId(), tua.getAssignment().getId());
		if (list.size() >= tua.getAssignment().getParticipantsMaxNumber()) {
			throw new EasyCorrectionException(
					MsgErros.NUMERO_MAXIMO_PARTICIPANTES.msg(""));
		}
		return true;
	}

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

	public TeamHasUserHasAssignment changeTeam(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {

		if (tua.getAssignment().getParticipantsMaxNumber() == 1) {
			throw new ObjectNotFoundException(MsgErros.ROTEIRO_INDIVIDUAL
					.msg(""));
		}
		if (tua.getAssignment().getParticipantsMaxNumber() == getTeamHasUserHasAssignmentByTeamAndAssignment(
				tua.getTeam().getId(), tua.getAssignment().getId()).size()) {
			String[] params = { tua.getTeam().getName(),
					tua.getAssignment().getParticipantsMaxNumber().toString() };
			throw new ObjectNotFoundException(
					MsgErros.EQUIPE_HAS_ROTEIRO_COMPLETA.msg(params));
		}
		try {
			getTeam(tua.getTeam().getId());
		} catch (Exception e) {
			throw new ObjectNotFoundException(MsgErros.EQUIPE_INEXISTENTE
					.msg(""));
		}

		TeamHasUserHasAssignment teamUserAssignment = getTeamHasUserHasAssignmentByUserAndAssignment(
				tua.getUser().getUserId(), tua.getAssignment().getId());
		if (!easyCorrectionUtil.isNull(teamUserAssignment)) {
			deleteTeamHasUserHasAssignment(teamUserAssignment);
			teamUserAssignment = saveTeamHasUserHasAssignment(tua);
		} else {
			teamUserAssignment = saveTeamHasUserHasAssignment(tua);
		}
		return teamUserAssignment;
	}

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

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByTeamAndAssignment(
			Integer teamId, Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByTeamAndAssignment(teamId, assignmentId);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignment(
			Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByAssignment(assignmentId);
	}

	public List<TeamHasUserHasAssignment> getTemHasUserHasAssignmentByAssignment(
			Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByAssignmentGroupByTeam(assignmentId);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignmentGroupByTeam(
			Integer assignmentId) {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO()
				.findByAssignmentGroupByTeam(assignmentId);
	}

	// TODO: It is not being used!
	private List<Team> getTeamHasUserHasAssignmentByCorrectorAssignment(
			Integer assignmentId, Integer correctorId) {
		return null;
		// TODO
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignments() {
		return DAOFactory.DEFAULT.buildTeamHasUserHasAssignmentDAO().findAll();
	}

	public TeamHasUserHasAssignment saveTeamHasUserHasAssignment(
			TeamHasUserHasAssignment tua) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(tua)) {
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
}
