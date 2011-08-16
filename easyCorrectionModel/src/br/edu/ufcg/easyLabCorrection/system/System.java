package br.edu.ufcg.easyLabCorrection.system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import junit.framework.TestResult;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.HibernateUtil;
import br.edu.ufcg.easyLabCorrection.exceptions.AssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.CompilationException;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.managers.accessPermission.AccessPermissionManager;
import br.edu.ufcg.easyLabCorrection.managers.accessUser.AccessUserManager;
import br.edu.ufcg.easyLabCorrection.managers.accessUser.MD5Generator;
import br.edu.ufcg.easyLabCorrection.managers.assessment.AssessmentManager;
import br.edu.ufcg.easyLabCorrection.managers.assignment.AssignmentManager;
import br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager;
import br.edu.ufcg.easyLabCorrection.managers.compilation.CompilationManager;
import br.edu.ufcg.easyLabCorrection.managers.stage.StageManager;
import br.edu.ufcg.easyLabCorrection.managers.submission.SubmissionManager;
import br.edu.ufcg.easyLabCorrection.managers.team.TeamManager;
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
import br.edu.ufcg.easyLabCorrection.servlet.EmailSender;
import br.edu.ufcg.easyLabCorrection.servlet.SendMailServlet;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;
import br.edu.ufcg.easyLabCorrection.util.ErrorMsgs;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class System {

	private AccessPermissionManager accessPermissionManager;
	private AccessUserManager accessUserManager;
	private AssignmentManager assignmentManager;
	private SubmissionManager submissionManager;
	private AssessmentManager assessmentManager;
	private CompilationManager compilationManager;
	private TeamManager teamManager;
	private AutomatedEvaluationManager correctionManager;
	private StageManager stageManager;

	public System() {
		accessPermissionManager = new AccessPermissionManager();
		accessUserManager = new AccessUserManager();
		assignmentManager = new AssignmentManager();
		submissionManager = new SubmissionManager();
		assessmentManager = new AssessmentManager();
		compilationManager = new CompilationManager();
		teamManager = new TeamManager();
		correctionManager = new AutomatedEvaluationManager();
		stageManager = new StageManager();
	}

	public void rebootDataBase() {
		accessPermissionManager.rebootDataBase();
	}
	
	public void setSystemStage(int systemStage){
		HibernateUtil.setCurrentStageId(systemStage);
	}

	public void deleteMenu(Menu menu) throws EasyCorrectionException {
		accessPermissionManager.deleteMenu(menu);
	}

	public void deleteFunction(MenuFunction function)
			throws EasyCorrectionException {
		accessPermissionManager.deleteFunction(function);
	}

	public void deleteGroup(Group group) throws EasyCorrectionException {
		accessPermissionManager.deleteGroup(group);
	}

	public void deleteUser(UserGroup userGroup) throws EasyCorrectionException {
		
		assessmentManager.deleteAllAssessmentsByUserId(userGroup.getUser().getUserId());
		submissionManager.deleteAllSubmissionsByUserId(userGroup.getUser().getUserId());
		teamManager.deleteAllTeamHasUserHasAssignmentByUserId(userGroup.getUser().getUserId());
		deallocateAll(assessmentManager.getAssessmentByCorrector(userGroup.getUser().getUserId()));
		accessUserManager.deleteUser(userGroup);
		
	}
	
	private void deallocateAll(List<Assessment> AssessmentList) throws EasyCorrectionException{
		for (Assessment assessment : AssessmentList) {
			assessment.setCorrector(null);
			allocateCorrector(assessment);
		}
	}

	/******************************************** Controle de Acesso EasyCorrection *********************************************/

	public MenuFunction getFunction(Integer id) {
		return accessPermissionManager.getFunction(id);
	}

	public Group getGroup(Integer id) {
		return accessPermissionManager.getGroup(id);
	}

	public UserGroup getUserGroup(Integer id) {
		return accessUserManager.getUserGroup(id);
	}

	public Menu getMenu(Integer id) {
		return accessPermissionManager.getMenu(id);
	}

	public Permission getPermission(Integer id) {
		return accessPermissionManager.getPermission(id);
	}

	public User getUser(Integer id) {
		return accessUserManager.getUser(id);
	}

	public User getUserByLogin(String login) {
		return accessUserManager.getUserByLogin(login);
	}

	public MenuFunction createFunction(MenuFunction function)
			throws EasyCorrectionException {
		return accessPermissionManager.createFunction(function);
	}

	public Group createGroup(Group group) throws EasyCorrectionException {
		return accessPermissionManager.createGroup(group);
	}

	public UserGroup createUserGroup(UserGroup userGroup)
			throws EasyCorrectionException {
		return accessUserManager.createUserGroup(userGroup);
	}

	/**
	 * Function used to save a new user in the database of the system.<br>
	 * 
	 * @param userGroup
	 *            The user group that contains the user to be saved.<br>
	 * @return The user save in the system.<br>
	 * @throws EasyCorrectionException
	 *             Exception can be thrown in an attempt to save a new User in
	 *             the system.<br>
	 */

	public UserGroup createUser(UserGroup userGroup)
			throws EasyCorrectionException {

		User u = new User();
		User us = new User();
		User use = new User();

		if (userGroup == null) {
			throw new EasyCorrectionException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("The UserGroup"));
		}
		if (userGroup.getUser() == null) {
			throw new EasyCorrectionException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("The User"));
		}
		if (userGroup.getGroup() == null) {
			throw new EasyCorrectionException(ErrorMsgs.OBJ_NOT_FOUND
					.msg("The Group"));
		}

		if (userGroup.getUser() != null) {
			if (!userGroup.getUser().getUserId().equals(new Integer(0))) {
				u = getUser(userGroup.getUser().getUserId());
				if (!u.getPassword().equals(userGroup.getUser().getPassword())) {
					// Generates password MD5
					String password = MD5Generator.md5(userGroup.getUser()
							.getPassword());
					userGroup.getUser().setPassword(password);
				}
			} else {
				// Generates password MD5
				String password = "";
				if (userGroup.getUser().getPassword().equals("")) {
					password = MD5Generator.md5(userGroup.getUser().getLogin());
				} else {
					password = MD5Generator.md5(userGroup.getUser()
							.getPassword());
				}
				userGroup.getUser().setPassword(password);
			}
			// Create User
			u = retrieveUserByLogin(userGroup.getUser().getLogin());
			use = accessUserManager.getUserByEmail(userGroup.getUser()
					.getEmail());

			// If login do not exist and user id is null
			if (u == null && use == null) {
				try {
					us = getUser(userGroup.getUser().getUserId());
					userGroup = accessUserManager.updateUser(userGroup, us);
				} catch (ObjectNotFoundException e) {
					userGroup = accessUserManager.createUser(userGroup);
					createTeamForIncomingStudent(userGroup);
				}
				// Create User UserGroup
				accessUserManager.createUserGroup(userGroup);
			} else {
				try {
					us = getUser(userGroup.getUser().getUserId());
					if (u != null) {
						if (!userGroup.getUser().getUserId().equals(
								u.getUserId())) {
							throw new ObjectNotFoundException(ErrorMsgs.REPEATED_USER_LOGIN.msg());
						}
					}
					if (use  != null) {
						if (!userGroup.getUser().getUserId().equals(
								use.getUserId())) {
							throw new ObjectNotFoundException(ErrorMsgs.REPEATED_USER_EMAIL.msg());
						}
					}
					userGroup = accessUserManager.updateUser(userGroup, us);
				} catch (ObjectNotFoundException e) {
					if (u != null) {
						throw new ObjectNotFoundException(ErrorMsgs.REPEATED_USER_LOGIN.msg());
					}
					if (use != null) {
						throw new ObjectNotFoundException(ErrorMsgs.REPEATED_USER_EMAIL.msg());
					}
				}
			}
		}
		return userGroup;
		// urn accessUserManager.saveUserGroup(userGroup);
	}

	public Menu createMenu(Menu menu) throws EasyCorrectionException {
		return accessPermissionManager.createMenu(menu);
	}

	public List<Permission> createPermissions(List<Permission> permissions)
			throws EasyCorrectionException {
		return accessPermissionManager.createPermissions(permissions);
	}

	public Group updateGroup(Group group) throws EasyCorrectionException {
		return accessPermissionManager.updateGroup(group);
	}

	public MenuFunction updateFunction(MenuFunction function)
			throws EasyCorrectionException {
		return accessPermissionManager.updateFunction(function);
	}

	public Menu updateMenu(Menu menu) throws EasyCorrectionException {
		return accessPermissionManager.updateMenu(menu);
	}

	public List<Permission> updatePermissions(List<Permission> permissions)
			throws EasyCorrectionException {
		return accessPermissionManager.updatePermissions(permissions);
	}

	public List<User> listUsers() {
		return accessUserManager.listUsers();
	}

	public List<Group> listGroups() {
		return accessPermissionManager.listGroups();
	}

	public List<MenuFunction> listFunctions() {
		return accessPermissionManager.listFunctions();
	}

	public List<Menu> listMenus() {
		return accessPermissionManager.listMenus();
	}

	public List<MenuFunction> getFunctionsPerValidatedUser(User user) {

		List<MenuFunction> functions = new LinkedList<MenuFunction>();

		// Generate password MD5
		String password = MD5Generator.md5(user.getPassword());
		user.setPassword(password);

		// Checks if login and password is valid
		User u = accessUserManager.verifyLoginAndPassword(user);
		if (u != null) {
			// Checks the user permissions and returns a set of functions
			functions = accessPermissionManager
					.verifyPermissions(u.getUserId());
		}
		return functions;
	}

	public List<UserGroup> listUserGroups() {
		return accessUserManager.listUserGroups();
	}

	public List<UserGroup> listUserGroupsByGroup(String groupName) {
		return accessUserManager.listUserGroupsByGroup(groupName);
	}

	public List<Permission> listPermissionsByGroup(Integer groupId) {
		return accessPermissionManager.listPermissionsByGroup(groupId);
	}

	public List<Permission> createGroupPermission(Group g, List<MenuFunction> list)
			throws Throwable {
		return accessPermissionManager.createGroupPermission(g, list);
	}

	public List<Menu> listOrderMenus() {
		return accessPermissionManager.listOrderedMenus();
	}

	public List<MenuFunction> listFunctionByMenu(Integer idMenu) {
		return accessPermissionManager.listFunctionsByMenu(idMenu);
	}

	public Group getGroupByName(String name) {
		return accessPermissionManager.getGroupByName(name);
	}

	/*-------------------------------------- USUARIOS -------------------------------------------*/

	public User updateUser(User user) throws EasyCorrectionException {
		return accessUserManager.updateUser(user);
	}

	public User retrieveUserByLogin(String login) {
		return accessUserManager.retrieveUserByLogin(login);
	}

	public List<UserGroup> getUserByGroup(Integer groupId) {
		return accessUserManager.listUsersByGroup(groupId);
	}
	
	public List<UserGroup> getUsersByGroupAndStage(Integer systemStage, Integer groupId) {
		return accessUserManager.listUsersByGroupAndStage(systemStage, groupId);
	}
	
	public List<UserGroup> getUserGroupByUser(Integer userId) {
		return accessUserManager.getUserGroupByUser(userId);
	}
	
	public List<UserGroup> getUserGroupByUserIdAndCurrentStageId(Integer userId) {
		return accessUserManager.getUserGroupByUserIdAndCurrentStageId(userId);
	}

	public User changePassword(User user, String newPassword) {
		return accessUserManager.changePassword(user, newPassword);
	}

	public ArrayList<UserGroup> createUsersFromCsvFile(String path, Group group, SystemStage systemStage)
			throws IOException, EasyCorrectionException {
		
		ArrayList<UserGroup> ug = new ArrayList<UserGroup>();
		path = path.replace("/", File.separator);
		String uploadDir = ServletUpload.local + path;
		ug = accessUserManager.createUsersFromCsvFile(uploadDir, group, systemStage);
		for (int i = 0; i < ug.size(); i++) {
			createUser(ug.get(i));
			ug.get(i).getUser().setPassword("123456");
			sendEmail(ug.get(i));
		}
		return ug;
	}
	
	private void sendEmail(UserGroup ug){
		
		String assunto = "Welcome to EasyLabCorrection!";
		String contato = ug.getUser().getEmail();
		String mensagem = easyCorrectionUtil.getEmailMessage(ug);
		String emailInst = SendMailServlet.emailInst;
		
		try {
			EmailSender.sendMail(assunto, contato, mensagem, emailInst);
		} catch (ServletException e) {
		} catch (IOException e) {}
	}

	/******************************************** Controle de Criacao/Edicao de Roteiros EasyLabCorrection *********************************************/
	public SystemStage getStage(int periodId) {
		return assignmentManager.getPeriod(periodId);
	}

	public List<SystemStage> getCurrentStage() {
		return assignmentManager.getCurrentPeriod();
	}

	public Assignment getAssignment(int assignmentId) {
		return assignmentManager.getAssignment(assignmentId);
	}

	public Assignment createAssignment(Assignment tempAssignment)
			throws EasyCorrectionException {
		Assignment assign = assignmentManager.createAssignment(tempAssignment);
		List<Team> teams = teamManager.getTeams();
		List<UserGroup> users = accessUserManager
				.listUserGroupsByGroup("Student");
		teamManager.allocateTeamsForUsers(assign, teams, users);
		return assign;
	}

	public Assignment updateAssignment(Assignment tempAssignment)
			throws AssignmentException {
		return assignmentManager.updateAssignment(tempAssignment);
	}

	public void deleteAssignment(Assignment assignment)
			throws AssignmentException {
		assessmentManager.deleteAllAssessmentsByAssignment(assignment.getId());
		submissionManager.deleteAllSubmissionsByAssignment(assignment.getId());
		teamManager.deleteAllTeamHasUserHasAssignmentByAssignment(assignment.getId());
		assignmentManager.deleteAssignment(assignment);
	}

	public List<Assignment> listAssignments() {
		return assignmentManager.getAssignments();
	}

	public List<Assignment> getReleasedAssignments() {
		return assignmentManager.getReleasedAssignments();
	}

	/******************************************** Controle de Submissão de Roteiros EasyLabCorrection *********************************************/

	public TeamHasUserHasAssignment getTeamHasUserHasAssignmentByUserAndAssignment(
			Integer idUsuario, Integer idRoteiro) {
		return teamManager.getTeamHasUserHasAssignmentByUserAndAssignment(
				idUsuario, idRoteiro);
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

	public TeamHasUserHasAssignment createTeamHasUserHasAssignment(
			TeamHasUserHasAssignment tua) throws EasyCorrectionException {
		return teamManager.saveTeamHasUserHasAssignment(tua);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByTeamAndAssignment(
			Integer teamId, Integer assignmentId) {
		return teamManager.getTeamHasUserHasAssignmentByTeamAndAssignment(
				teamId, assignmentId);
	}

	public List<Assessment> getAssessmentByAssignmentAndCorrector(
			Assignment assignment, Integer us) {
		return assessmentManager.getAssessmentByAssignmentAndCorrector(
				assignment, us);
	}

	public Submission getLastSubmissionByAssignmentAndTeam(
			Assignment assignment, Team team) {
		List<Submission> submissionList = submissionManager
				.getSubmissionsByAssignmentAndTeam(assignment, team);
		return submissionList.get(submissionList.size() - 1);
	}

	public Submission submitAssignment(Submission submission)
			throws EasyCorrectionException {
		Assignment assignment = assignmentManager
				.getReleasedAssignment(submission.getTeamHasUserHasAssignment()
						.getAssignment().getId());
		return submissionManager.submitAssignment(submission, assignment);
	}

	public String processSubmission(Submission submission)
			throws EasyCorrectionException {

		TestResult testResult;
		String result = "";

		String testsDirectory = ServletUpload.local
				+ submission.getTeamHasUserHasAssignment().getAssignment()
						.getTestsDirectory().replace("/", File.separator);
		String sourceDirectory = ServletUpload.local
				+ submission.getUrl().replace("/", File.separator);
		String libDirectory = (ServletUpload.local + "/").replace("/",
				File.separator);
		
		if (submission.getTeamHasUserHasAssignment().getAssignment()
				.getAssignmentType().getCompilation()){
			
			// Compilation
			try {
				compilationUnit(sourceDirectory, testsDirectory, libDirectory);
				
				//Remover (Apenas para teste) -------
				/*double automaticTestsGrade = 10;
				result = "COMPILATION: OK.";
				assessmentManager.setAssessment(submission,
						automaticTestsGrade, result);*/
				//-----------------------------------
			} catch (CompilationException compilationError) {
				return "COMPILATION ERROR: \n" + compilationError.getMessage();
			}
		}
		if (submission.getTeamHasUserHasAssignment().getAssignment()
			.getAssignmentType().getTestExecution()){
			if (submission.getTeamHasUserHasAssignment().getAssignment()
				.getAutomaticTestsPercentage() > 0) {

				// Running Tests
				testResult = correctionManager.runAutomaticTests(submission,
						sourceDirectory, testsDirectory);
	
				if (testResult != null) {
					Object[] answer = correctionManager.getTestsExecutionOutput(
							testResult, submission);
					double automaticTestsGrade = (Double) answer[0];
					result = (String) answer[1];
					assessmentManager.setAssessment(submission,
							automaticTestsGrade, result);
				} else {
					submissionManager.deleteSubmission(submission);
				}
			} else {
				result = "This assessment will not run automatic tests.";
				assessmentManager.setAssessment(submission, 0, result);
				return "Result: " + result;
			}
		}
		if (submission.getTeamHasUserHasAssignment().getAssignment()
				.getAssignmentType().getOutputComparison()){
			//TODO
		}
		return result;
	}

	// This method should map the compilers.
	public void compilationUnit(String sourceDirectory, String testsDirectory,
			String libDirectory) throws CompilationException {
		try {
			compilationManager.runJavaCompiler(sourceDirectory, testsDirectory,
					libDirectory);
		} catch (CompilationException e) {
			compilationManager.setCompilationError(false);
			throw new CompilationException(deleteDirectory(compilationManager
					.getErrorResult(), testsDirectory, sourceDirectory,
					libDirectory));
		}
	}

	private String deleteDirectory(String errorResult, String td, String sd,
			String id) {
		String result = errorResult.replace(td, "");
		result = result.replace(sd, "");
		result = result.replace(id, "");
		return result;
	}

	public int getAllocatedTeams(Integer assignmentId) {
		if (assignmentId == null || assignmentId < 1) {
			throw new ObjectNotFoundException(
					ErrorMsgs.EMPTY_QUERY_RESULT.msg(""));
		}
		Assignment assignment = assignmentManager.getAssignment(assignmentId);
		return teamManager.getNumberOfAllocatedTeams(assignment);
	}

	public TeamHasUserHasAssignment changeTeam(TeamHasUserHasAssignment tua)
			throws EasyCorrectionException {
		UserGroup ug = getUserGroupByUser(tua.getUser().getUserId()).get(0);
		if (!ug.getGroup().getName().equals("Student")) {
			throw new EasyCorrectionException(
					"Change of team not held! Student inexistent.");
		}
		return teamManager.changeTeam(tua);
	}

	public Submission getSubmission(int submissionId) {
		return submissionManager.getSubmission(submissionId);
	}

	public void deleteSubmission(Submission sub) throws EasyCorrectionException {
		submissionManager.deleteSubmission(sub);
	}

	public Team createTeam(Team t) throws EasyCorrectionException {
		return teamManager.createTeam(t);
	}

	public Integer submissionNumber(Submission submission) {
		return submissionManager.getSubmissionNumber(submission);
	}

	public Integer getSubmissionNumberByTua(TeamHasUserHasAssignment tua) {
		return submissionManager.getSubmissionNumberByTUA(tua);
	}

	public String getEnvironmentFileName(Assignment assignment) {
		return submissionManager.getEnvironmentFileName(assignment);
	}

	public String getTestsFileName(Assignment assignment) {
		return submissionManager.getTestsFileName(assignment);
	}

	public String getSourceFileName(Submission submission) {
		return submissionManager.getSourceFileName(submission);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignment(
			Integer assignmentId) {
		return teamManager
				.getTeamHasUserHasAssignmentByAssignment(assignmentId);
	}

	public List<TeamHasUserHasAssignment> getTeamHasUserHasAssignmentByAssignmentGroupByTeam(
			Integer assignmentId) {
		return teamManager
				.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(assignmentId);
	}

	public List<Assignment> getClosedAssignments() {
		return assignmentManager.getClosedAssignments();
	}

	public List<User> getCorrectors() {
		return assessmentManager.getCorrectors();
	}

	public List<Assessment> getAssignmentWithOutCorrectorsAssessments(
			int assignmentId) {
		return assessmentManager.getAssignmentWithoutCorrectors(assignmentId);
	}

	public List<Assessment> getAssignmentWithCorrectorsAssessments(
			int assignmentId, int correctorId) {
		return assessmentManager.getAssignmentWithCorrectors(assignmentId,
				correctorId);
	}

	public Assessment createAssessment(Assessment assessment)
			throws EasyCorrectionException {
		return assessmentManager.saveAssessment(assessment);
	}

	public void deleteAssessment(Assessment assessment)
			throws EasyCorrectionException {
		assessmentManager.deleteAssessment(assessment);
	}

	public List<Assessment> getAssessmentBySubmission(int submissionId) {
		return assessmentManager.getAssessmentBySubmission(submissionId);
	}

	public Assessment allocateCorrector(Assessment assessement)
			throws EasyCorrectionException {
		return assessmentManager.updateAssessment(assessement);
	}

	public List<Assessment> getAssessmentsByAssignment(Assignment assignment) {
		List<Assessment> completeList = new ArrayList<Assessment>();
		List<Assessment> lista = assessmentManager
				.getAssessmentByAssignment(assignment);
		for (Assessment assessment : lista) {
			Team te = assessment.getSubmission().getTeamHasUserHasAssignment()
					.getTeam();
			List<TeamHasUserHasAssignment> listaTUA = getTeamHasUserHasAssignmentByTeamAndAssignment(
					te.getId(), assignment.getId());
			for (TeamHasUserHasAssignment tua : listaTUA) {
				Submission sub = new Submission(assessment.getSubmission()
						.getId(), assessment.getSubmission()
						.getSubmissionDate(), tua, assessment.getSubmission()
						.getStatus(), assessment.getTestsExecutionResult());
				Assessment assessAux = new Assessment(assessment.getId(), sub,
						assessment.getAutomaticGrade(), assessment
								.getCorrectionGrade(), assessment
								.getTestsExecutionResult(), assessment
								.getPenalty(), assessment.getAssessmentDate(),
						assessment.getCorrector());
				completeList.add(assessAux);
			}
		}
		return completeList;
	}

	public List<Assessment> getAssessmentByTeamAndAssignment(Integer team,
			Integer assignment) {
		return assessmentManager.getAssessmentByTeamAndAssignment(team,
				assignment);
	}

	/*
	 * ADDED AFTER THE REFACTORING PHASE
	 */

	// It came from AccessUserPermission
	public void createTeamForIncomingStudent(UserGroup userGroup)
			throws EasyCorrectionException {
		Team team = new Team();
		if (userGroup.getGroup().getName().equalsIgnoreCase("Student")) {
			List<UserGroup> gu = DAOFactory.DEFAULT.buildUserGroupDAO()
					.findByGroup("Student");
			List<Team> teams = teamManager.getTeams();
			int userNumber = gu.size() + 1;
			if (userNumber > teams.size()) {
				for (int i = 0; i < 5; i++) {
					teams = teamManager.getTeams();
					if (teams.isEmpty()) {
						team.setName("Team 1");
					} else {
						int index = teams.get(teams.size() - 1).getId() + 1;
						team.setName("Team " + index);
					}
					Team t = teamManager.createTeam(team);
					if (i == 0) {
						allocateUserToTeam(userGroup.getUser(), t);
					}
				}
			} else {
				allocateUserToTeam(userGroup.getUser());
			}
		}
	}

	// It came from AccessUserPermission
	private void allocateUserToTeam(User us, Team te)
			throws EasyCorrectionException {
		List<Assignment> assigns = assignmentManager.getAssignments();
		for (Assignment roteiro : assigns) {
			TeamHasUserHasAssignment tua = new TeamHasUserHasAssignment();
			tua.setTeam(te);
			tua.setUser(us);
			tua.setAssignment(roteiro);
			teamManager.saveTeamHasUserHasAssignment(tua);
		}
	}

	// It came from AccessUserPermission
	private void allocateUserToTeam(User us) throws EasyCorrectionException {
		List<Assignment> assigns = assignmentManager.getAssignments();
		for (Assignment assignment : assigns) {
			List<TeamHasUserHasAssignment> eurs = teamManager
					.getTeamHasUserHasAssignmentByAssignment(assignment.getId());
			Team eq = teamManager.getTeams().get(0);
			if (eurs.size() > 0) {
				int novoId = eurs.get(eurs.size() - 1).getTeam().getId() + 1;
				eq = teamManager.getTeam(novoId);
			}
			TeamHasUserHasAssignment eur = new TeamHasUserHasAssignment();
			eur.setTeam(eq);
			eur.setUser(us);
			eur.setAssignment(assignment);
			teamManager.saveTeamHasUserHasAssignment(eur);
		}
	}

	public List<AssignmentType> listAssignmentType() {
		return assignmentManager.listAssignmentType();
	}
	
	public List<SystemStage> systemStageList() {
		return stageManager.systemStageList();
	}

	public SystemStage createSystemStage(SystemStage stage) throws EasyCorrectionException{
		return stageManager.createSystemStage(stage);
	}
	
	public SystemStage updateSystemStage(SystemStage stage) throws EasyCorrectionException{
		return stageManager.updateSystemStage(stage);
	}
	
	public void deleteSystemStage(SystemStage stage) throws EasyCorrectionException {
		stageManager.deleteSystemStage(stage);
	}
	
	public void deleteForcedSystemStage(SystemStage stage) throws EasyCorrectionException {
		assessmentManager.deleteAllAssessmentsByStage(stage.getId());
		submissionManager.deleteAllSubmissionsByStage(stage.getId());
		teamManager.deleteAllTeamHasUserHasAssignmentByStage(stage.getId());
		assignmentManager.deleteAssignmentByStage(stage.getId());
		deleteForcedUsersByStage(stage.getId());
		stageManager.deleteSystemStage(stage);
	}
	
	private void deleteForcedUsersByStage(Integer stageId) throws EasyCorrectionException{
		List<UserGroup> userGroupList = accessUserManager.getUserGroupByStage(stageId);
		for (UserGroup userGroup : userGroupList) {
			deleteUser(userGroup);
		}
	}
	
	public List<Assignment> getAssignmentByCourse(String course) {
		return assignmentManager.getAssignmentByCourse(course);
	}

	public String getAssignmentStatus(int assign) {
		return assignmentManager.getAssignmentStatus(assign);
	}

	public String countSubmissionsByAssignmentId(int assignmentId) {
		return submissionManager.countSubmissionsByAssignmentId(assignmentId);
	}
	
}