package br.edu.ufcg.easyLabCorrection.system;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Function;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Group;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Menu;
import br.edu.ufcg.easyLabCorrection.pojo.permission.Permission;
import br.edu.ufcg.easyLabCorrection.pojo.system.Stage;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.PasswordGenerator;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class Facade {

	private System system;
	static Logger log = Logger.getLogger("br.ufcg.edu.facade");

	public Facade() {
		system = new System();

	}

	public void reinicializaBancoDeDados() {
		system.rebootsDataBase("backupEasyCorrection");
	}

	/*******************************************************************
	 * Controle de Acesso
	 * **********************************************************************
	 * 
	 * @throws Throwable
	 */
	public Function getFuncao(Integer id) throws Throwable {
		try {
			return system.getFunction(id);
		} catch (Throwable e) {
			log.error("getFunction()", e);
			throw e;
		}
	}

	public Group getGrupo(Integer id) throws Throwable {
		try {
			return system.getGroup(id);
		} catch (Throwable e) {
			log.error("getGroup()", e);
			throw e;
		}
	}

	public UserGroup getGrupoUsuario(Integer id) throws Throwable {
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

	public Permission getPermissao(Integer id) throws Throwable {
		try {
			return system.getPermission(id);
		} catch (Throwable e) {
			log.error("getPermission()", e);
			throw e;
		}
	}

	public User getUsuario(Integer id) throws Throwable {
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

	public Function saveFunction(Function function) throws Throwable {
		try {
			return system.saveFunction(function);
		} catch (Throwable e) {
			log.error("saveFunction()", e);
			throw e;
		}
	}

	public Group saveGroup(Group group) throws Throwable {
		try {
			return system.saveGroup(group);
		} catch (Throwable e) {
			log.error("saveGroup()", e);
			throw e;
		}
	}

	public UserGroup cadastrarGrupoUsuario(UserGroup userGroup)
			throws Throwable {
		try {
			return system.saveUserGroup(userGroup);
		} catch (Throwable e) {
			log.error("saveUserGroup()", e);
			throw e;
		}
	}

	public Menu saveMenu(Menu menu) throws Throwable {
		try {
			return system.saveMenu(menu);
		} catch (Throwable e) {
			log.error("saveMenu()", e);
			throw e;
		}
	}

	public List<Permission> cadastrarPermissao(List<Permission> permissions)
			throws Throwable {
		try {
			return system.savePermissions(permissions);
		} catch (Throwable e) {
			log.error("savePermission()", e);
			throw e;
		}
	}

	public UserGroup saveUser(UserGroup userGroup)
			throws Throwable {
		try {
			return system.saveUser(userGroup);
		} catch (Throwable e) {
			log.error("saveUser()", e);
			throw e;
		}
	}

	public List<Function> validateUser(User user) throws Throwable {
		try {
			return system.validateUser(user);
		} catch (Throwable e) {
			log.error("validateUser()", e);
			throw e;
		}
	}

	public List<User> listarUsuarios() throws Throwable {
		try {
			return system.listUsers();
		} catch (Throwable e) {
			log.error("listUsers()", e);
			throw e;
		}
	}

	public List<Group> groupList() throws Throwable {
		try {
			return system.groupList();
		} catch (Throwable e) {
			log.error("groupList()", e);
			throw e;
		}
	}

	public List<Function> functionList() throws Throwable {
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

	public void removeFunction(Function function) throws Throwable {
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
			log.error("deleteUser()", e);
			throw e;
		}
	}

	public List<UserGroup> listarGrupoUsuarios() throws Throwable {
		try {
			return system.listUserGroups();
		} catch (Throwable e) {
			log.error("listUserGroups()", e);
			throw e;
		}
	}

	public List<UserGroup> listarGrupoUsuariosPorGrupo(String groupName)
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
			return system.retrievePermissionsByGroup(groupId);
		} catch (Throwable e) {
			log.error("retrievePermissionsByGroup()", e);
			throw e;
		}
	}

	public List<Permission> saveGroupPermission(Group g, List<Function> list)
			throws Throwable {
		try {
			return system.saveGroupPermission(g, list);
		} catch (Throwable e) {
			log.error("saveGroupPermission()", e);
			throw e;
		}
	}

	public List<Menu> listarMenusOrdenados() throws Throwable {
		try {
			return system.listOrderMenus();
		} catch (Throwable e) {
			log.error("listOrderMenus()", e);
			throw e;
		}
	}

	public List<Function> getFunctionByMenu(Integer menuId) throws Throwable {
		try {
			return system.consultFunctionByMenu(menuId);
		} catch (Throwable e) {
			log.error("consultFunctionByMenu()", e);
			throw e;
		}
	}

	public Group getGrupoPorNome(String name) throws Throwable {
		try {
			return system.getGroupByName(name);
		} catch (Throwable e) {
			log.error("getGroupByName()", e);
			throw e;
		}
	}

	public User atualizarUsuario(User user) throws Throwable {
		try {
			return system.updateUser(user);
		} catch (Throwable e) {
			log.error("updateUser()", e);
			throw e;
		}
	}

	public User consultarUsuarioPorLogin(String login) throws Throwable {
		try {
			return system.consultUserByLogin(login);
		} catch (Throwable e) {
			log.error("consultUserByLogin()", e);
			throw e;
		}
	}

	public List<UserGroup> getUserByGroup(Integer groupId)
			throws Throwable {
		try {
			return system.getUserByGroup(groupId);
		} catch (Throwable e) {
			log.error("consultUserByGroup()", e);
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
			log.error("gerarSenha()", e);
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

	public Stage getPeriodo(int periodId) throws Throwable {
		try {
			return system.getPeriod(periodId);
		} catch (Throwable e) {
			log.error("getPeriod()", e);
			throw e;
		}
	}

	public List<Stage> getPeriodoAtual() throws Throwable {
		try {
			return system.getCurrentPeriod();
		} catch (Throwable e) {
			log.error("getCurrentPeriod()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Roteiros
	 * **********************************************************************
	 */

	public Assignment cadastrarRoteiro(Assignment assignment) throws Throwable {
		try {
			return system.saveAssignment(assignment);
		} catch (Throwable e) {
			log.error("saveAssignment()", e);
			throw e;
		}
	}

	public Assignment editarRoteiro(Assignment assignment) throws Throwable {
		try {
			return system.updateAssignment(assignment);
		} catch (Throwable e) {
			log.error("updateAssignment()", e);
			throw e;
		}
	}

	public Assignment getRoteiro(int assignmentId) throws Throwable {
		try {
			return system.getAssignment(assignmentId);
		} catch (Throwable e) {
			log.error("getAssignment()", e);
			throw e;
		}
	}

	public List<Assignment> listarRoteiros() throws Throwable {
		try {
			return system.listAssignments();
		} catch (Throwable e) {
			log.error("listAssignments()", e);
			throw e;
		}
	}

	public void excluirRoteiro(Assignment assignment) throws Throwable {
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

	public Team cadastrarEquipe(Team team) throws Throwable {
		try {
			return system.saveTeam(team);
		} catch (Throwable e) {
			log.error("saveTeam()", e);
			throw e;
		}
	}

	public Team getEquipe(int id) throws Throwable {
		try {
			return system.getTeam(id);
		} catch (Throwable e) {
			log.error("getTeam()", e);
			throw e;
		}
	}

	public Team getEquipePorNome(String teamName) throws Throwable {
		try {
			return system.getTeamByName(teamName);
		} catch (Throwable e) {
			log.error("getTeamByName()", e);
			throw e;
		}
	}

	public List<Team> getEquipes() throws Throwable {
		try {
			return system.getTeams();
		} catch (Throwable e) {
			log.error("getTeams()", e);
			throw e;
		}
	}

	public List<TeamHasUserHasAssignment> getEquipeHasUsuarioHasRoteiros()
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

	public int getEquipeAlocadas(Integer assignmentId) throws Throwable {
		try {
			return system.getAllocatedTeams(assignmentId);
		} catch (Throwable e) {
			log.error("getAllocatedTeams()", e);
			throw e;
		}
	}

	public TeamHasUserHasAssignment cadastraEquipeHasUsuarioHasRoteiro(
			TeamHasUserHasAssignment tua) throws Throwable {
		try {
			return system.saveTeamHasUserHasAssignment(tua);
		} catch (Throwable e) {
			log.error("saveTeamHasUserHasAssignment()", e);
			throw e;
		}
	}

	public TeamHasUserHasAssignment mudarEquipe(TeamHasUserHasAssignment tua)
			throws Throwable {
		try {
			return system.changeTeam(tua);
		} catch (Throwable e) {
			log.error("changeTeam()", e);
			throw e;
		}
	}

	public String getInterfaceFileName(Assignment assignment) throws Throwable {
		try {
			return system.getInterfaceFileName(assignment);
		} catch (Throwable e) {
			log.error("getInterfaceFileName()", e);
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

	public String getNomeArquivoCodigo(Submission submission) throws Throwable {
		try {
			return system.getSourceFileName(submission);
		} catch (Throwable e) {
			log.error("getSourceFileName()", e);
			throw e;
		}
	}

	public Submission submeteRoteiro(Submission submission) throws Throwable {
		try {
			return system.submitAssignment(submission);
		} catch (Throwable e) {
			log.error("submitAssignment()", e);
			throw e;
		}
	}

	public String rodarTestesAutomaticos(Submission submission) throws Throwable {
		try {
			return system.runAutomaticTests(submission);
		} catch (Throwable e) {
			log.error("runAutomaticTests()", e);
			throw e;
		}
	}

	public Submission getSubmissao(int submissaoId) throws Throwable {
		try {
			return system.getSubmission(submissaoId);
		} catch (Throwable e) {
			log.error("getSubmission()", e);
			throw e;
		}
	}

	public void excluirSubmissao(Submission sub) throws Throwable {
		try {
			system.deleteSubmission(sub);
		} catch (Throwable e) {
			log.error("deleteSubmission()", e);
			throw e;
		}
	}

	public Integer getNumeroSubmissoes(Submission submission) throws Throwable {
		try {
			return system.submissionNumber(submission);
		} catch (Throwable e) {
			log.error("submissionNumber()", e);
			throw e;
		}
	}

	public Integer getNumeroSubmissoesPorEUR(TeamHasUserHasAssignment tua) throws Throwable {
		try {
			return system.getSubmissionNumberByTua(tua);
		} catch (Throwable e) {
			log.error("getSubmissionNumberByTua()", e);
			throw e;
		}
	}

	public List<Assessment> getAvaliacaoPorRoteiroEquipePorCorretor(Assignment assignment, 
			Integer us) throws Throwable {
		try {
			return system.getAssessmentByAssignmentAndCorrector(assignment, us);
		} catch (Throwable e) {
			log.error("getAssessmentByAssignmentAndCorrector()", e);
			throw e;
		}
	}

	public Submission getUltimaSubmissaoPorRoteiroEquipe(Assignment assignment,
			Team team) throws Throwable {
		try {
			return system.getLastSubmissionByAssignmentAndTeam(assignment, team);
		} catch (Throwable e) {
			log.error("getLastSubmissionByAssignmentAndTeam()", e);
			throw e;
		}
	}

	/*******************************************************************
	 * Facade Avaliacoes
	 * **********************************************************************
	 */

	/**
	 * Retorna todas as equipes
	 */
	public List<TeamHasUserHasAssignment> getEquipeHasUsuarioHasRoteiroPorRoteiro(
			Integer assignmentId, Integer correctorId) throws Throwable {
		try {
			return system.getTeamHasUserHasAssignmentByAssignment(assignmentId);
		} catch (Throwable e) {
			log.error("getTemHasUserHasAssignmentByAssignment()", e);
			throw e;
		}
	}

	public List<TeamHasUserHasAssignment> getEquipeHasUsuarioHasRoteiroPorRoteiroAgrupadoPorEquipe(
			Integer assignmentId) throws Throwable {
		try {
			return system.getTeamHasUserHasAssignmentByAssignmentGroupByTeam(assignmentId);
		} catch (Throwable e) {
			log.error("getTeamHasUserHasAssignmentByAssignmentGroupByTeam()",	e);
			throw e;
		}
	}

	public List<Assignment> getRoteirosFechados() throws Throwable {
		try {
			return system.getClosedAssignments();
		} catch (Throwable e) {
			log.error("getClosedAssignments()", e);
			throw e;
		}
	}

	/**
	 * Retorna os usuarios que sao considerados professores, ou seja, checa se
	 * no grupoUsuario o id_grupo eh 2 ou 3 (ver script do banco para maiores
	 * informacoes)
	 * 
	 */
	public List<User> getCorretores() throws Throwable {
		try {
			return system.getCorrectors();
		} catch (Throwable e) {
			log.error("getCorrectors()", e);
			throw e;
		}
	}

	public List<Assessment> getAvaliacoesDoRoteiroSemCorretor(int assignmentId) throws Throwable {
		try {
			return system.getAssignmentWithOutCorrectorsAssessments(assignmentId);
		} catch (Throwable e) {
			log.error("getAssignmentWithOutCorrectorsAssessments()", e);
			throw e;
		}
	}

	public List<Assessment> getAvaliacoesDoRoteiroComCorretor(int assignmentId, int correctorId) throws Throwable {
		try {
			return system.getAssignmentWithCorrectorsAssessments(assignmentId, correctorId);
		} catch (Throwable e) {
			log.error("getAssignmentWithCorrectorsAssessments()", e);
			throw e;
		}
	}
	
	public Assessment salvarAvaliacao(Assessment assessment) throws Throwable{
		try{
			return system.saveAssessment(assessment);
		} catch (Throwable e) {
			log.error("saveAssessment()", e);
			throw e;
		}
	}
	
	public Assessment alocaCorretor(Assessment assessment) throws Throwable{
		try{
			return system.allocateCorrector(assessment);
		} catch (Throwable e) {
			log.error("allocateCorrector()", e);
			throw e;
		}
	}
	
	
	public void excluirAvaliacao(Assessment assessment) throws Throwable{
		try{
			system.deleteAssessment(assessment);
		} catch (Throwable e) {
			log.error("deleteAssessment()", e);
			throw e;
		}
	}
	
	public List<Assessment> getAvaliacaoPorSubmissao(int submissionId) throws Throwable{
		try{
			return system.getAssessmentBySubmission(submissionId);
		}catch (Throwable e) {
			log.error("getAssessmentBySubmission()", e);
			throw e;
		}
	}
	
	public List<Assessment> getAvaliacoesPorRoteiro(Assignment assignment) throws Throwable{
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

}
