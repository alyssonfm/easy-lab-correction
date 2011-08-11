package br.edu.ufcg.easyLabCorrection.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.accessUser.PasswordGenerator;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.AssignmentType;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.MenuFunction;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.pojo.system.SystemStage;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class Facade {

	private System system;
	static Logger log = Logger.getLogger("br.ufcg.edu.facade");

	public Facade() {
		system = new System();
	}

	public void rebootDataBase() {
		system.rebootDataBase();
	}
	
	public void setSystemStage(int systemStage){
		system.setSystemStage(systemStage);
	}

	/*******************************************************************
	 * Controle de Acesso
	 * **********************************************************************
	 * 
	 * @throws Throwable
	 */
	public MenuFunction getFunction(Integer id) throws Throwable {
		try {
			return system.getFunction(id);
		} catch (Throwable e) {
			log.error("getFunction()", e);
			throw e;
		}
	}

	public Group getGroup(Integer id) throws Throwable {
		try {
			return system.getGroup(id);
		} catch (Throwable e) {
			log.error("getGroup()", e);
			throw e;
		}
	}

	public UserGroup getUserGroup(Integer id) throws Throwable {
		try {
			return system.getUserGroup(id);
		} catch (Throwable e) {
			log.error("getUserGroup()", e);
			throw e;
		}
	}

	public Menu getMenu(Integer id) throws Throwable {
		try {
			return system.getMenu(id);
		} catch (Throwable e) {
			log.error("getMenu()", e);
			throw e;
		}
	}

	public Permission getPermission(Integer id) throws Throwable {
		try {
			return system.getPermission(id);
		} catch (Throwable e) {
			log.error("getPermission()", e);
			throw e;
		}
	}

	public User getUser(Integer id) throws Throwable {
		try {
			return system.getUser(id);
		} catch (Throwable e) {
			log.error("getUser()", e);
			throw e;
		}
	}

	public User getUserByLogin(String login) throws Throwable {
		try {
			return system.getUserByLogin(login);
		} catch (Throwable e) {
			log.error("getUserByLogin()", e);
			throw e;
		}
	}

	public MenuFunction saveFunction(MenuFunction function) throws Throwable {
		try {
			return system.createFunction(function);
		} catch (Throwable e) {
			log.error("saveFunction()", e);
			throw e;
		}
	}

	public Group saveGroup(Group group) throws Throwable {
		try {
			return system.createGroup(group);
		} catch (Throwable e) {
			log.error("saveGroup()", e);
			throw e;
		}
	}

	public UserGroup saveUserGroup(UserGroup userGroup)
			throws Throwable {
		try {
			return system.createUserGroup(userGroup);
		} catch (Throwable e) {
			log.error("saveUserGroup()", e);
			throw e;
		}
	}

	public Menu saveMenu(Menu menu) throws Throwable {
		try {
			return system.createMenu(menu);
		} catch (Throwable e) {
			log.error("saveMenu()", e);
			throw e;
		}
	}

	public List<Permission> savePermissions(List<Permission> permissions)
			throws Throwable {
		try {
			return system.createPermissions(permissions);
		} catch (Throwable e) {
			log.error("savePermissions()", e);
			throw e;
		}
	}

	public UserGroup saveUser(UserGroup userGroup)
			throws Throwable {
		try {
			return system.createUser(userGroup);
		} catch (Throwable e) {
			log.error("saveUser()", e);
			throw e;
		}
	}
	
	public ArrayList<UserGroup> saveUsersFromCsvFile(String path, Group group, SystemStage systemStage) 
		throws IOException, EasyCorrectionException {
		try {
			return system.createUsersFromCsvFile(path, group, systemStage);
		} catch (IOException e) {
			log.error("saveUsersFromCsvFile", e);
			throw e;
		} catch (EasyCorrectionException e) {
			log.error("saveUsersFromCsvFile", e);
			throw e;
		}
	}

	public List<MenuFunction> validateUser(User user) throws Throwable {
		try {
			return system.getFunctionsPerValidatedUser(user);
		} catch (Throwable e) {
			log.error("validateUser()", e);
			throw e;
		}
	}

	public List<User> listUsers() throws Throwable {
		try {
			return system.listUsers();
		} catch (Throwable e) {
			log.error("listUsers()", e);
			throw e;
		}
	}

	public List<Group> groupList() throws Throwable {
		try {
			return system.listGroups();
		} catch (Throwable e) {
			log.error("groupList()", e);
			throw e;
		}
	}

	public List<MenuFunction> functionList() throws Throwable {
		try {
			return system.listFunctions();
		} catch (Throwable e) {
			log.error("listFunctions()", e);
			throw e;
		}
	}

	public List<Menu> menuList() throws Throwable {
		try {
			return system.listMenus();
		} catch (Throwable e) {
			log.error("listMenus()", e);
			throw e;
		}
	}

	public void removeMenu(Menu menu) throws Throwable {
		try {
			system.deleteMenu(menu);
		} catch (Throwable e) {
			log.error("deleteMenu()", e);
			throw e;
		}
	}

	public void removeFunction(MenuFunction function) throws Throwable {
		try {
			system.deleteFunction(function);
		} catch (Throwable e) {
			log.error("deleteFunction()", e);
			throw e;
		}
	}

	public void removeGroup(Group group) throws Throwable {
		try {
			system.deleteGroup(group);
		} catch (Throwable e) {
			log.error("deleteGroup()", e);
			throw e;
		}
	}

	public void removeUserGroup(UserGroup userGroup) throws Throwable {
		try {
			system.deleteUser(userGroup);
		} catch (Throwable e) {
			log.error("deleteUserGroup()", e);
			throw e;
		}
	}

	public List<UserGroup> listUserGroups() throws Throwable {
		try {
			return system.listUserGroups();
		} catch (Throwable e) {
			log.error("listUserGroups()", e);
			throw e;
		}
	}

	public List<UserGroup> listUserGroupsByGroup(String groupName)
			throws Throwable {
		try {
			return system.listUserGroupsByGroup(groupName);
		} catch (Throwable e) {
			log.error("listUserGroupsByGroup()", e);
			throw e;
		}
	}

	public List<Permission> retrievePermissionsByGroup(Integer groupId)
			throws Throwable {
		try {
			return system.listPermissionsByGroup(groupId);
		} catch (Throwable e) {
			log.error("retrievePermissionsByGroup()", e);
			throw e;
		}
	}

	public List<Permission> saveGroupPermission(Group g, List<MenuFunction> list)
			throws Throwable {
		try {
			return system.createGroupPermission(g, list);
		} catch (Throwable e) {
			log.error("saveGroupPermission()", e);
			throw e;
		}
	}

	public List<Menu> listOrderMenus() throws Throwable {
		try {
			return system.listOrderMenus();
		} catch (Throwable e) {
			log.error("listOrderMenus()", e);
			throw e;
		}
	}

	public List<MenuFunction> getFunctionByMenu(Integer menuId) throws Throwable {
		try {
			return system.listFunctionByMenu(menuId);
		} catch (Throwable e) {
			log.error("consultFunctionByMenu()", e);
			throw e;
		}
	}

	public Group getGroupByName(String name) throws Throwable {
		try {
			return system.getGroupByName(name);
		} catch (Throwable e) {
			log.error("getGroupByName()", e);
			throw e;
		}
	}

	public User updateUser(User user) throws Throwable {
		try {
			return system.updateUser(user);
		} catch (Throwable e) {
			log.error("updateUser()", e);
			throw e;
		}
	}
	
	public Group updateGroup(Group group) throws Throwable {
		try {
			return system.updateGroup(group);
		} catch (Throwable e) {
			log.error("updateGroup()", e);
			throw e;
		}
	}

	public MenuFunction updateFunction(MenuFunction function) throws Throwable {
		try {
			return system.updateFunction(function);
		} catch (Throwable e) {
			log.error("updateFunction()", e);
			throw e;
		}
	}

	public Menu updateMenu(Menu menu) throws Throwable {
		try {
			return system.updateMenu(menu);
		} catch (Throwable e) {
			log.error("updateMenu()", e);
			throw e;
		}
	}

	public List<Permission> updatePermissions(List<Permission> permissions) throws Throwable {
		try {
			return system.updatePermissions(permissions);
		} catch (Throwable e) {
			log.error("updatePermissions()", e);
			throw e;
		}
	}

	public User retrieveUserByLogin(String login) throws Throwable {
		try {
			return system.retrieveUserByLogin(login);
		} catch (Throwable e) {
			log.error("retrieveUserByLogin()", e);
			throw e;
		}
	}

	public List<UserGroup> getUserByGroup(Integer groupId)
			throws Throwable {
		try {
			return system.getUserByGroup(groupId);
		} catch (Throwable e) {
			log.error("getUserByGroup()", e);
			throw e;
		}
	}
	
	public List<UserGroup> getUsersByGroupAndStage(Integer systemStage, Integer groupId)
			throws Throwable {
		try {
			return system.getUsersByGroupAndStage(systemStage, groupId);
		} catch (Throwable e) {
			log.error("getUsersByGroupAndStage()", e);
			throw e;
		}
	}
	
	public List<UserGroup> getUserGroupByUser(Integer userId)
			throws Throwable {
		try {
			return system.getUserGroupByUser(userId);
		} catch (Throwable e) {
			log.error("getUserGroupByUser()", e);
			throw e;
		}
	}

	public Date getDataNow() throws Throwable {
		try {
			return easyCorrectionUtil.getDataNow();
		} catch (Throwable e) {
			log.error("getDataNow()", e);
			throw e;
		}
	}

	public String generatePassword(int digitsNumber, String userName)
			throws Throwable {
		try {
			return PasswordGenerator.generatePassword(digitsNumber, userName);
		} catch (Throwable e) {
			log.error("generatePassword()", e);
			throw e;
		}
	}

	public User changePassword(User user, String newPassword)
			throws Throwable {
		try {
			return system.changePassword(user, newPassword);
		} catch (Throwable e) {
			log.error("changePassword()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Sistema (em geral)
	 * **********************************************************************
	 */

	public SystemStage getStage(int stageId) throws Throwable {
		try {
			return system.getStage(stageId);
		} catch (Throwable e) {
			log.error("getStage()", e);
			throw e;
		}
	}

	public List<SystemStage> getCurrentStage() throws Throwable {
		try {
			return system.getCurrentStage();
		} catch (Throwable e) {
			log.error("getCurrentStage()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Roteiros
	 * **********************************************************************
	 */

	public Assignment saveAssignment(Assignment assignment) throws Throwable {
		try {
			return system.createAssignment(assignment);
		} catch (Throwable e) {
			log.error("saveAssignment()", e);
			throw e;
		}
	}

	public Assignment updateAssignment(Assignment assignment) throws Throwable {
		try {
			return system.updateAssignment(assignment);
		} catch (Throwable e) {
			log.error("updateAssignment()", e);
			throw e;
		}
	}

	public Assignment getAssignment(int assignmentId) throws Throwable {
		try {
			return system.getAssignment(assignmentId);
		} catch (Throwable e) {
			log.error("getAssignment()", e);
			throw e;
		}
	}

	public List<Assignment> listAssignments() throws Throwable {
		try {
			return system.listAssignments();
		} catch (Throwable e) {
			log.error("listAssignments()", e);
			throw e;
		}
	}

	public void deleteAssignment(Assignment assignment) throws Throwable {
		try {
			system.deleteAssignment(assignment);
		} catch (Throwable e) {
			log.error("deleteAssignment()", e);
			throw e;
		}
	}

	public List<Assignment> getReleasedAssignments() throws Throwable {
		try {
			return system.getReleasedAssignments();
		} catch (Throwable e) {
			log.error("getReleasedAssignments()", e);
			throw e;
		}
	}
	
	public List<Assignment> getAssignmentByCourse(String course) throws Throwable {
		try {
			return system.getAssignmentByCourse(course);
		} catch (Throwable e) {
			log.error("getAssignmentByCourse()", e);
			throw e;
		}
	}
	
	public String getAssignmentStatus(int assign) throws Throwable{
		try {
			return system.getAssignmentStatus(assign);		
		} catch (Throwable e) {
			log.error("getAssignmentStatus()", e);
			throw e;
		}		
	}
	
	/*******************************************************************
	 * Facade Submissoes
	 * **********************************************************************
	 */

	public TeamHasUserHasAssignment getTeamHasUserHasAssignmentByUserAndAssignment(
			Integer userId, Integer assignmentId) throws Throwable {
		try {
			return system.getTeamHasUserHasAssignmentByUserAndAssignment(
					userId, assignmentId);
		} catch (Throwable e) {
			log.error("getTeamHasUserHasAssignmentByUserAndAssignment()", e);
			throw e;
		}
	}

	public Team saveTeam(Team team) throws Throwable {
		try {
			return system.createTeam(team);
		} catch (Throwable e) {
			log.error("saveTeam()", e);
			throw e;
		}
	}

	public Team getTeam(int id) throws Throwable {
		try {
			return system.getTeam(id);
		} catch (Throwable e) {
			log.error("getTeam()", e);
			throw e;
		}
	}

	public Team getTeamByName(String teamName) throws Throwable {
		try {
			return system.getTeamByName(teamName);
		} catch (Throwable e) {
			log.error("getTeamByName()", e);
			throw e;
		}
	}

	public List<Team> getTeams() throws Throwable {
		try {
			return system.getTeams();
		} catch (Throwable e) {
			log.error("getTeams()", e);
			throw e;
		}
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignments()
			throws Throwable {
		try {
			return system.getTeamHasUserHasAssignments();
		} catch (Throwable e) {
			log.error("getTeamHasUserHasAssignments()", e);
			throw e;
		}
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByTeamAndAssignment(
			Integer teamId, Integer assignmentId) throws Throwable {
		try {
			return system.getTeamHasUserHasAssignmentByTeamAndAssignment(
					teamId, assignmentId);
		} catch (Throwable e) {
			log.error("getTeamHasUserHasAssignmentByTeamAndAssignment()", e);
			throw e;
		}
	}

	public int getAllocatedTeams(Integer assignmentId) throws Throwable {
		try {
			return system.getAllocatedTeams(assignmentId);
		} catch (Throwable e) {
			log.error("getAllocatedTeams()", e);
			throw e;
		}
	}

	public TeamHasUserHasAssignment saveTeamHasUserHasAssignment(
			TeamHasUserHasAssignment tua) throws Throwable {
		try {
			return system.createTeamHasUserHasAssignment(tua);
		} catch (Throwable e) {
			log.error("saveTeamHasUserHasAssignment()", e);
			throw e;
		}
	}

	public TeamHasUserHasAssignment changeTeam(TeamHasUserHasAssignment tua)
			throws Throwable {
		try {
			return system.changeTeam(tua);
		} catch (Throwable e) {
			log.error("changeTeam()", e);
			throw e;
		}
	}

	public String getEnvironmentFileName(Assignment assignment) throws Throwable {
		try {
			return system.getEnvironmentFileName(assignment);
		} catch (Throwable e) {
			log.error("getEnvironmentFileName()", e);
			throw e;
		}
		
	}

	public String getTestsFileName(Assignment assignment) throws Throwable {
		try {
			return system.getTestsFileName(assignment);
		} catch (Throwable e) {
			log.error("getTestsFileName()", e);
			throw e;
		}
	}

	public String getSourceFileName(Submission submission) throws Throwable {
		try {
			return system.getSourceFileName(submission);
		} catch (Throwable e) {
			log.error("getSourceFileName()", e);
			throw e;
		}
	}

	public Submission submitAssignment(Submission submission) throws Throwable {
		try {
			return system.submitAssignment(submission);
		} catch (Throwable e) {
			log.error("submitAssignment()", e);
			throw e;
		}
	}

	public String runAutomaticTests(Submission submission) throws Throwable {
		try {
			return system.runAutomaticTests(submission);
		} catch (Throwable e) {
			log.error("runAutomaticTests()", e);
			throw e;
		}
	}

	public Submission getSubmission(int submissaoId) throws Throwable {
		try {
			return system.getSubmission(submissaoId);
		} catch (Throwable e) {
			log.error("getSubmission()", e);
			throw e;
		}
	}

	public void deleteSubmission(Submission sub) throws Throwable {
		try {
			system.deleteSubmission(sub);
		} catch (Throwable e) {
			log.error("deleteSubmission()", e);
			throw e;
		}
	}

	public Integer submissionNumber(Submission submission) throws Throwable {
		try {
			return system.submissionNumber(submission);
		} catch (Throwable e) {
			log.error("submissionNumber()", e);
			throw e;
		}
	}

	public Integer getSubmissionNumberByTua(TeamHasUserHasAssignment tua) throws Throwable {
		try {
			return system.getSubmissionNumberByTua(tua);
		} catch (Throwable e) {
			log.error("getSubmissionNumberByTua()", e);
			throw e;
		}
	}

	public List<Assessment> getAssessmentByAssignmentAndCorrector(Assignment assignment, 
			Integer us) throws Throwable {
		try {
			return system.getAssessmentByAssignmentAndCorrector(assignment, us);
		} catch (Throwable e) {
			log.error("getAssessmentByAssignmentAndCorrector()", e);
			throw e;
		}
	}

	public Submission getLastSubmissionByAssignmentAndTeam(Assignment assignment,
			Team team) throws Throwable {
		try {
			return system.getLastSubmissionByAssignmentAndTeam(assignment, team);
		} catch (Throwable e) {
			log.error("getLastSubmissionByAssignmentAndTeam()", e);
			throw e;
		}
	}
	
	public String countSubmissionsByAssignmentId(int assignmentId) throws Throwable{
		try {
			return system.countSubmissionsByAssignmentId(assignmentId);
		} catch (Throwable e) {
			log.error("countSubmissionsByAssignmentId()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Assignments
	 * **********************************************************************
	 */

	/**
	 * Return all teams
	 */
	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignment(
			Integer assignmentId, Integer correctorId) throws Throwable {
		try {
			return system.getTeamHasUserHasAssignmentByAssignment(assignmentId);
		} catch (Throwable e) {
			log.error("getTemHasUserHasAssignmentByAssignment()", e);
			throw e;
		}
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignmentGroupByTeam(
			Integer assignmentId) throws Throwable {
		try {
			return system.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(assignmentId);
		} catch (Throwable e) {
			log.error("getTeamHasUserHasAssignmentByAssignmentGroupByTeam()",	e);
			throw e;
		}
	}

	public List<Assignment> getClosedAssignments() throws Throwable {
		try {
			return system.getClosedAssignments();
		} catch (Throwable e) {
			log.error("getClosedAssignments()", e);
			throw e;
		}
	}

	/**
	 * Return the users that be considered instructors - check if
	 * in userGroup the id_grupo is 2 or 3.
	 * 
	 */
	public List<User> getCorrectors() throws Throwable {
		try {
			return system.getCorrectors();
		} catch (Throwable e) {
			log.error("getCorrectors()", e);
			throw e;
		}
	}

	public List<Assessment> getAssignmentWithOutCorrectorsAssessments(int assignmentId) throws Throwable {
		try {
			return system.getAssignmentWithOutCorrectorsAssessments(assignmentId);
		} catch (Throwable e) {
			log.error("getAssignmentWithOutCorrectorsAssessments()", e);
			throw e;
		}
	}

	public List<Assessment> getAssignmentWithCorrectorsAssessments(int assignmentId, int correctorId) throws Throwable {
		try {
			return system.getAssignmentWithCorrectorsAssessments(assignmentId, correctorId);
		} catch (Throwable e) {
			log.error("getAssignmentWithCorrectorsAssessments()", e);
			throw e;
		}
	}
	
	public Assessment saveAssessment(Assessment assessment) throws Throwable{
		try{
			return system.createAssessment(assessment);
		} catch (Throwable e) {
			log.error("saveAssessment()", e);
			throw e;
		}
	}
	
	public Assessment allocateCorrector(Assessment assessment) throws Throwable{
		try{
			return system.allocateCorrector(assessment);
		} catch (Throwable e) {
			log.error("allocateCorrector()", e);
			throw e;
		}
	}
	
	
	public void deleteAssessment(Assessment assessment) throws Throwable{
		try{
			system.deleteAssessment(assessment);
		} catch (Throwable e) {
			log.error("deleteAssessment()", e);
			throw e;
		}
	}
	
	public List<Assessment> getAssessmentBySubmission(int submissionId) throws Throwable{
		try{
			return system.getAssessmentBySubmission(submissionId);
		}catch (Throwable e) {
			log.error("getAssessmentBySubmission()", e);
			throw e;
		}
	}
	
	public List<Assessment> getAssessmentsByAssignment(Assignment assignment) throws Throwable{
		try{
			return system.getAssessmentsByAssignment(assignment);
		}catch (Throwable e) {
			log.error("getAssessmentsByAssignment()", e);
			throw e;
		}
	}
	
	public List<Assessment> getAssessmentsByTeamAndAssignment(Integer teamId, Integer assignmentId) throws Throwable{
		try{
			return system.getAssessmentByTeamAndAssignment(teamId, assignmentId);
		}catch (Throwable e) {
			log.error("getAssessmentByTeamAndAssignment()", e);
			throw e;
		}
	}
	
	public List<AssignmentType> listAssignmentType() throws Throwable{
		try{
			return system.listAssignmentType();
		}catch (Throwable e) {
			log.error("listAssignmentType()", e);
			throw e;
		}
	}
	
	
	public List<SystemStage> systemStageList() throws Throwable {
		try{
			return system.systemStageList();
		}catch (Throwable e) {
			log.error("systemStageList()", e);
			throw e;
		}
	}

	public SystemStage createSystemStage(SystemStage stage) throws Throwable{
		try{
			return system.createSystemStage(stage);
		}catch (Throwable e) {
			log.error("createSystemStage()", e);
			throw e;
		}
	}
	
	public SystemStage updateSystemStage(SystemStage stage) throws Throwable{
		try{
			return system.updateSystemStage(stage);
		}catch (Throwable e) {
			log.error("updateSystemStage()", e);
			throw e;
		}
	}
	
	public void deleteSystemStage(SystemStage stage) throws Throwable {
		try{
			system.deleteSystemStage(stage);
		}catch (Throwable e) {
			log.error("deleteSystemStage()", e);
			throw e;
		}
	}
	
}
