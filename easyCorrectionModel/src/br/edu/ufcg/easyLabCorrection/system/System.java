package br.edu.ufcg.easyLabCorrection.system;

import java.util.ArrayList;
import java.util.List;
import br.edu.ufcg.easyLabCorrection.exceptions.CreateAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.EditingAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.ExclusionAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.exceptions.ReleasesAssignmentException;
import br.edu.ufcg.easyLabCorrection.managers.AccessPermissionManager;
import br.edu.ufcg.easyLabCorrection.managers.AssessmentManager;
import br.edu.ufcg.easyLabCorrection.managers.AssignmentManager;
import br.edu.ufcg.easyLabCorrection.managers.SubmissionManager;
import br.edu.ufcg.easyLabCorrection.managers.TeamManager;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Team;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Function;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.pojo.system.Period;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class System {

	private AccessPermissionManager accessPermissionManager;
	private AssignmentManager assignmentManager;
	private SubmissionManager submissionManager;
	private AssessmentManager assessmentManager;
	private TeamManager teamManager;

	public System() {
		accessPermissionManager = new AccessPermissionManager();
		assignmentManager = new AssignmentManager();
		submissionManager = new SubmissionManager();
		assessmentManager = new AssessmentManager();
		teamManager = new TeamManager();
	}

	public void rebootsDataBase(String script) {
		accessPermissionManager.reinicializaBancoDeDados(script);
	}

	public void deleteMenu(Menu menu) throws EasyCorrectionException {
		accessPermissionManager.deleteMenu(menu);
	}

	public void deleteFunction(Function function) throws EasyCorrectionException {
		accessPermissionManager.deleteFunction(function);
	}

	public void deleteGroup(Group group) throws EasyCorrectionException {
		accessPermissionManager.deleteGroup(group);
	}

	public void deleteUser(UserGroup userGroup)
			throws EasyCorrectionException {
		accessPermissionManager.deleteUser(userGroup);
	}

	/******************************************** Controle de Acesso EasyCorrection *********************************************/

	public Function getFunction(Integer id) {
		return accessPermissionManager.getFunction(id);
	}

	public Group getGroup(Integer id) {
		return accessPermissionManager.getGroup(id);
	}

	public UserGroup getUserGroup(Integer id) {
		return accessPermissionManager.getUserGroup(id);
	}

	public Menu getMenu(Integer id) {
		return accessPermissionManager.getMenu(id);
	}

	public Permission getPermission(Integer id) {
		return accessPermissionManager.getPermission(id);
	}

	public User getUser(Integer id) {
		return accessPermissionManager.getUser(id);
	}

	public User getUserByLogin(String login) {
		return accessPermissionManager.getUserByLogin(login);
	}

	public Function saveFunction(Function function) throws EasyCorrectionException {
		return accessPermissionManager.saveFunction(function);
	}

	public Group saveGroup(Group group) throws EasyCorrectionException {
		return accessPermissionManager.saveGroup(group);
	}

	public UserGroup saveUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {
		return accessPermissionManager.saveUserGroup(userGroup);
	}

	public Menu saveMenu(Menu menu) throws EasyCorrectionException {
		return accessPermissionManager.saveMenu(menu);
	}

	public List<Permission> savePermission(List<Permission> permissions)
			throws EasyCorrectionException {
		return accessPermissionManager.savePermission(permissions);
	}

	public UserGroup saveUser(UserGroup userGroup)
			throws EasyCorrectionException {
		return accessPermissionManager.saveUser(userGroup);
	}

	public List<User> listUsers() {
		return accessPermissionManager.listUsers();
	}

	public List<Group> listGroups() {
		return accessPermissionManager.listGroups();
	}

	public List<Function> listFunctions() {
		return accessPermissionManager.listFunctions();
	}

	public List<Menu> listMenus() {
		return accessPermissionManager.listMenus();
	}

	public List<Function> validateUser(User user) {
		return accessPermissionManager.validateUser(user);
	}

	public List<UserGroup> listUserGroups() {
		return accessPermissionManager.listUserGroups();
	}

	public List<UserGroup> listUserGroupsByGroup(String groupName) {
		return accessPermissionManager.listUserGroupsByGroup(groupName);
	}

	public List<Permission> consultPermissionsByGroup(Integer groupId) {
		return accessPermissionManager.consultPermissionsByGroup(groupId);
	}

	public List<Permission> saveGroupPermission(Group g, List<Function> list)
			throws Throwable {
		return accessPermissionManager.saveGroupPermission(g, list);
	}

	public List<Menu> listOrderMenus() {
		return accessPermissionManager.listOrderMenus();
	}

	public List<Function> consultFunctionByMenu(Integer idMenu) {
		return accessPermissionManager.consultFunctionByMenu(idMenu);
	}

	public Group getGroupByName(String name) {
		return accessPermissionManager.getGroupByName(name);
	}

	/*-------------------------------------- USUARIOS -------------------------------------------*/

	public User updateUser(User user)
			throws EasyCorrectionException {
		return accessPermissionManager.updateUser(user);
	}

	public User consultUserByLogin(String login) {
		return accessPermissionManager.consultUserByLogin(login);
	}

	public List<UserGroup> consultUserByGroup(Integer groupId) {
		return accessPermissionManager.consultUserByGroup(groupId);
	}

	public List<UserGroup> getUserGroupByUser(Integer userId) {
		return accessPermissionManager.getUserGroupByUser(userId);
	}

	public User changePassword(User user, String newPassword) {
		return accessPermissionManager.changePassword(user, newPassword);
	}

	/******************************************** Controle de Criacao/Edicao de Roteiros EasyLabCorrection *********************************************/
	public Period getPeriod(int periodId) {
		return assignmentManager.getPeriod(periodId);
	}

	public List<Period> getCurrentPeriod() {
		return assignmentManager.getCurrentPeriod();
	}

	public Assignment getAssignment(int assignmentId) {
		return assignmentManager.getAssignment(assignmentId);
	}

	public Assignment saveAssignment(Assignment tempAssignment)
			throws EasyCorrectionException {
		Assignment assign = assignmentManager.saveAssignment(tempAssignment);
		List<Team> teams = teamManager.getTeams();
		List<UserGroup> users = accessPermissionManager
				.listUserGroupsByGroup("Aluno");
		teamManager.allocateTeamsForUsers(assign, teams, users);
		return assign;
	}

	public Assignment updateAssignment(Assignment tempAssignment)
			throws EditingAssignmentException, CreateAssignmentException,
			ReleasesAssignmentException {
		return assignmentManager.updateAssignment(tempAssignment);
	}

	public void deleteAssignment(Assignment assignment) throws ExclusionAssignmentException {
		assignmentManager.deleteAssignment(assignment);
	}

	public List<Assignment> listAssignments() {
		return assignmentManager.listAssignments();
	}

	public List<Assignment> getReleasedAssignments() {
		return assignmentManager.getReleasedAssignments();
	}

	/******************************************** Controle de Submissão de Roteiros EasyLabCorrection *********************************************/

	public TeamHasUserHasAssignment getTeamHasUserHasAssignmentByUserAndAssignment(
			Integer idUsuario, Integer idRoteiro) {
		return teamManager
				.getTeamHasUserHasAssignmentByUserAndAssignment(idUsuario, idRoteiro);
	}

	public List<Team> getTeams() {
		return teamManager.getTeams();
	}

	public Team getTeam(int id) {
		return teamManager.getTeam(id);
	}

	public Team getTeamByName(String teamName) throws Throwable {
		return teamManager.getTeamByName(teamName);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignments() {
		return teamManager.getTeamHasUserHasAssignments();
	}

	public TeamHasUserHasAssignment saveTeamHasUserHasAssignment(
			TeamHasUserHasAssignment tua) throws EasyCorrectionException {
		return teamManager.saveTeamHasUserHasAssignment(tua);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByTeamAndAssignment(
			Integer teamId, Integer assignmentId) {
		return teamManager
				.getTeamHasUserHasAssignmentByTeamAndAssignment(teamId,
						assignmentId);
	}
	
	public List<Assessment> getAssessmentByAssignmentAndCorrector(Assignment assignment, Integer us) {
		return assessmentManager.getAssessmentByAssignmentAndCorrector(assignment, us);
	}
	
	public Submission getLastSubmissionByAssignmentAndTeam(Assignment assignment, Team team) {
		List<Submission> submissionList = submissionManager.getSubmissionsByAssignmentAndTeam(assignment, team);
		return submissionList.get(submissionList.size()-1);
	}

	public Submission submitAssignment(Submission submission)
			throws EasyCorrectionException {
		Assignment assignment = assignmentManager.getReleasedAssignments(submission.getTeamHasUserHasAssignment().getAssignment().getId());
		return submissionManager.submitAssignment(submission, assignment);
	}

	public String runAutomaticTests(Submission submission)
			throws EasyCorrectionException {
		return submissionManager.runAutomaticTests(submission);
	}

	public int getAllocatedTeams(Integer assignmentId) {
		if (easyCorrectionUtil.isNull(assignmentId) || assignmentId < 1) {
			throw new ObjectNotFoundException(
					MsgErros.ID_ROTEIRO_INEXISTENTE.msg(""));
		}
		Assignment assignment = assignmentManager.getAssignment(assignmentId);
		return teamManager.getAllocatedTeams(assignment);
	}

	public TeamHasUserHasAssignment changeTeam(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {
		UserGroup ug = getUserGroupByUser(
				tua.getUser().getUserId()).get(0);
		if (!ug.getGroup().getName().equals("Aluno")) {
			throw new EasyCorrectionException(MsgErros.ALUNO_INEXISTENTE
					.msg(""));
		}
		return teamManager.changeTeam(tua);
	}

	public Submission getSubmission(int submissionId) {
		return submissionManager.getSubmission(submissionId);
	}

	public void deleteSubmission(Submission sub) throws EasyCorrectionException {
		submissionManager.deleteSubmission(sub);
	}

	public Team saveTeam(Team t) throws EasyCorrectionException {
		return teamManager.saveTeam(t);
	}

	public Integer submissionNumber(Submission submission) {
		return submissionManager.submissionNumber(submission);
	}

	public Integer getSubmissionNumberByTua(TeamHasUserHasAssignment tua) {
		return submissionManager.getSubmissionNumberByTua(tua);
	}

	public String getInterfaceFileName(Assignment assignment) {
		return submissionManager.getInterfaceFileName(assignment);
	}

	public String getTestsFileName(Assignment assignment) {
		return submissionManager.getTestsFileName(assignment);
	}
	
	public String getSourceFileName(Submission submission) {
		return submissionManager.getSourceFileName(submission);
	}

	public List<TeamHasUserHasAssignment> getTemHasUserHasAssignmentByAssignment(
			Integer assignmentId) {
		return assessmentManager
				.getTemHasUserHasAssignmentByAssignment(assignmentId);
	}
	
	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignmentGroupByTeam(
			Integer assignmentId) {
		return assessmentManager
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(assignmentId);
	}

	public List<Assignment> getClosedAssignments() {
		return assessmentManager.getClosedAssignments();
	}

	public List<User> getCorrectors() {
		return assessmentManager.getCorrectors();
	}

	public List<Assessment> getAssignmentWithOutCorrectorsAssessments(int assignmentId) {
		return assessmentManager.getAssignmentWithOutCorrectorsAssessments(assignmentId);
	}

	public List<Assessment> getAssignmentWithCorrectorsAssessments(int assignmentId, int correctorId) {
		return assessmentManager.getAssignmentWithCorrectorsAssessments(assignmentId, correctorId);
	}
	
	public Assessment saveAssessment(Assessment assessment) throws EasyCorrectionException{
		return assessmentManager.saveAssessment(assessment);
	}
	
	public void deleteAssessment(Assessment assessment) throws EasyCorrectionException{
		assessmentManager.deleteAssessment(assessment);
	}
	
	public List<Assessment> getAssessmentBySubmission(int submissionId){
		return assessmentManager.getAssessmentBySubmission(submissionId);
	}

	public Assessment allocateCorrector(Assessment assessement) throws EasyCorrectionException {
		return assessmentManager.updateAssessment(assessement);
	}

	public List<Assessment> getAssessmentsByAssignment(Assignment assignment) {
		List<Assessment> completeList = new ArrayList<Assessment>();
		List<Assessment> lista = assessmentManager.getAssessmentByAssignment(assignment);
		for (Assessment assessment : lista) {
			Team te = assessment.getSubmission().getTeamHasUserHasAssignment().getTeam();
			List<TeamHasUserHasAssignment> listaTUA = getTeamHasUserHasAssignmentByTeamAndAssignment(te.getId(), assignment.getId());
			for (TeamHasUserHasAssignment tua : listaTUA) {
				Submission sub = new Submission(assessment.getSubmission().getId(), 
						assessment.getSubmission().getSubmissionDate(), 
						tua, 
						assessment.getSubmission().getStatus(), 
						assessment.getTestsExecutionResult());
				Assessment assessAux = new Assessment(assessment.getId(),
												sub,
												assessment.getAutomaticGrade(),
												assessment.getCorrectionGrade(),
												assessment.getTestsExecutionResult(),
												assessment.getPenalty(),
												assessment.getAssessmentDate(),
												assessment.getCorrector());
				completeList.add(assessAux);
			}
		}
		return completeList;
	}

	public List<Assessment> getAssessmentByTeamAndAssignment(Integer team,
			Integer assignment) {
		return assessmentManager.getAssessmentByTeamAndAssignment(team, assignment);
	}

}