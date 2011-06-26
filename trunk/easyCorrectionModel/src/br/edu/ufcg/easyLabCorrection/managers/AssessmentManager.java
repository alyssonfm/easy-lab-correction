package br.edu.ufcg.easyLabCorrection.managers;

import java.util.ArrayList;
import java.util.List;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing of assessments i nthe system 
 * Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class AssessmentManager extends Manager {
	
	/**
	 * Constructor default of class, creates a new object AssessmentManager.<br>
	 */
	public AssessmentManager() {
		super();
	}

	/**
	 * Function used to retrieve all correctors of the system.<br>
	 * @return A list of all correctors of the system.<br>
	 */
	public List<User> getCorrectors() {
		List<UserGroup> ugCorrectors;
		ugCorrectors = DAOFactory.DEFAULT.buildUserGroupDAO().findByGroup(2);
		ugCorrectors.addAll(DAOFactory.DEFAULT.buildUserGroupDAO()
				.findByGroup(3));

		ArrayList<User> correctorsUsers = new ArrayList<User>();
		for (UserGroup gu : ugCorrectors) {
			correctorsUsers.add(gu.getUser());
		}
		return correctorsUsers;
	}

	/**
	 * Function used to retrieve all assessments without corrector assigned
	 *  receiving an assignment identifier as parameter.<br>
	 * @param assignmentId The assignment identifier who want to retrieve
	 * the assessment without corrector assigned.<br>
	 * @return A list of all assessments without corrector.<br>
	 */
	public List<Assessment> getAssignmentWithoutCorrectors(int assignmentId) {
		return this.getAssignmentWithCorrectors(assignmentId, 0);
	}

	/**
	 * Function used to retrieve all assessments with corrector assigned receiving 
	 * an assignment and corrector identifiers as parameter.<br>
	 * @param assignmentId The assignment identifier used in the recovery 
	 * of assessments.<br>
	 * @param correctorId The corrector identifier used in the recovery of 
	 * assessments.<br>
	 * @return A list of all assessments with corrector whose corrector 
	 * and assingment identifiers corresponds at the identifiers passed 
	 * as parameter.<br>
	 */
	public List<Assessment> getAssignmentWithCorrectors(int assignmentId,
			int correctorId) {

		if (correctorId <= 0) {
			return DAOFactory.DEFAULT.buildAssessmentDAO()
					.findByAssignmentWithOutCorrector(assignmentId);
		} else {
			return DAOFactory.DEFAULT.buildAssessmentDAO()
					.findByAssignmentWithCorrector(assignmentId, correctorId);
		}
	}
	
	/**
	 * Function used to retrieve an assessment by its assignment and team.<br>
	 * @param assignmentId The assignment identifier used in the recovery.<br>
	 * @param teamId The team identifier used in the recovery.<br>
	 * @return The assessment whose team and assignment identifiers 
	 * corresponds at the identifiers passed as parameter.<br>
	 */
	public Assessment getAssessmentByAssignmentAndTeam(int assignmentId, int teamId){
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO().findByAssignmentAndTeam(assignmentId, teamId);
		if(list.isEmpty()){
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND.msg("avaliacao"));
		}
		return list.get(0);
	}
	
	/**
	 * Function used to retrieve all assessments whose assign and corrector 
	 * corresponds at the assign and corrector was passed as parameter.<br>
	 * @param assign The assignment who want to retrieve the assessments.<br>
	 * @param us The corrector who want to retrieve the assessments.<br>
	 * @return A list of assessments whose assign and corrector 
	 * corresponds at the assign and corrector was passed as parameter.<br>
	 */
	public List<Assessment> getAssessmentByAssignmentAndCorrector(Assignment assign, Integer us) {
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO()
				.findByTeamAndAssignmentByCorrector(assign.getId(), us);
		return list;
	} 
	
	/**
	 * Function used to save an assessment in the database of the system.<br>
	 * @param assessment The assessment who want to save.<br>
	 * @return The assessment save in the system.<br>
	 * @throws EasyCorrectionException Exception can be thrown in an 
	 * attempt to save the assessment in the system.
	 */
	public Assessment saveAssessment(Assessment assessment) throws EasyCorrectionException{
		try{
			Assessment assess = getAssessmentByAssignmentAndTeam(assessment.getSubmission().getTeamHasUserHasAssignment().getAssignment().getId(), 
					assessment.getSubmission().getTeamHasUserHasAssignment().getTeam().getId());
			assess.setCorrectionGrade(calculateCorrectorGrade(assessment.getCorrectionGrade(), 
					assessment.getSubmission().getTeamHasUserHasAssignment().getAssignment().getAutomaticTestsPercentage()));
			assess.setCorrected(assessment.isCorrected());
			return updateAssessment(assess);
		}
		catch(Exception e){
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND.msg("avaliacao"));
		}
	}
	
	private double calculateCorrectorGrade(double grossGrade, double correctionPercentage){
		return (grossGrade * (100 - correctionPercentage)) / 100;
	}
	
	/**
	 * Function used to update an assessment in the database of the system.<br>
	 * @param assessment The assessment who want to update.<br>
	 * @return The assessment update in the system.<br>
	 * @throws EasyCorrectionException Exception can be thrown in an 
	 * attempt to update the assessment in the system.
	 */
	public Assessment updateAssessment(Assessment assessment) throws EasyCorrectionException{
		
		DAOFactory.DEFAULT.buildAssessmentDAO().update(assessment);
		assessment.setId(assessment.getId());
		return assessment;
	}
	
	/**
	 * Function used to creates a new assessment in the system.<br>
	 * @param assessment The assessment to be created.<br>
	 * @return The assessment that was created.<br>
	 */
	public Assessment createAssessment(Assessment assessment){
		
		int id = DAOFactory.DEFAULT.buildAssessmentDAO().save(assessment);
		assessment.setId(id);
		return assessment;
	}
	
	/**
	 * Procedure used to delete an assessment of the system receiving an 
	 * assessment as parameter.<br>
	 * @param assessment The assessment to be deleted.<br>
	 * @throws EasyCorrectionException Exception can be thrown in an 
	 * attempt to delete the assessment in the system.
	 */
	public void deleteAssessment(Assessment assessment) throws EasyCorrectionException{
		Assessment assess = DAOFactory.DEFAULT.buildAssessmentDAO().getById(assessment.getId());
		assess= (Assessment) SwapperAtributosReflect.swapObject(assess, assessment,
				Assessment.class);
		DAOFactory.DEFAULT.buildAssessmentDAO().delete(assess);
	}
	
	/**
	 * Function used to retrieve the assessments of the system that receives a 
	 * submission identifier as parameter.<br>
	 * @param submissionId The submission identifier used in the recovery.<br>
	 * @return A list of assessments whose submission identifier corresponds 
	 * at the submission identifier passed as parameter.<br>
	 */
	public List<Assessment> getAssessmentBySubmission(int submissionId){
		return DAOFactory.DEFAULT.buildAssessmentDAO().findBySubmission(submissionId);
		
	}

	/**
	 * Function used to retrieve the assessments of the system that receives an 
	 * assignment as parameter.<br>
	 * @param assignment The assignment used in the recovery.<br>
	 * @return A list of assessments whose assignment corresponds 
	 * at the assignment passed as parameter.<br>
	 */
	public List<Assessment> getAssessmentByAssignment(Assignment assignment) {
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO()
			.findByAssignment(assignment.getId());
		return list;
	}

	/**
	 * Function used to retrieve the assessments of the system that receives
	 * a team identifier and an assignment identifier as parameter.<br>
	 * @param teamId The team identifier used in the recovery.<br>
	 * @param assignmentId The assignment identifier used in the recovery.<br>
	 * @return A list of all assessments whose team and assignment identifiers 
	 * corresponds at the identifiers passed as parameter.<br>
	 */
	public List<Assessment> getAssessmentByTeamAndAssignment(Integer teamId,
			Integer assignmentId) {
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO()
		.findByTeamAndAssignment(teamId, assignmentId);
	return list;
	}
	
	/**
	 * Function used to save an assessment in the database of the system.<br>
	 * @param submission The submission who want to save the assessment.<br>
	 * @param automaticTestsGrade The value of automatic tests.<br> 
	 * @param automaticTestsResult The result of automatic tests.<br> 
	 * @return The assessment save in the system.<br>
	 * @throws EasyCorrectionException 
	 */
	public Assessment setAssessment(Submission submission, double automaticTestsGrade, String automaticTestsResult) throws EasyCorrectionException{
		try{
			Assessment assess = getAssessmentByAssignmentAndTeam(submission.getTeamHasUserHasAssignment().getAssignment().getId(), 
					submission.getTeamHasUserHasAssignment().getTeam().getId());
			assess.setSubmission(submission);
			assess.setAutomaticGrade(automaticTestsGrade);
			assess.setAssessmentDate(easyCorrectionUtil.getDataNow());
			assess.setTestsExecutionResult(automaticTestsResult);
			return updateAssessment(assess);
		}
		catch (ObjectNotFoundException e) {
			Assessment assess = new Assessment(0, 
					submission, 
					automaticTestsGrade, 
					0.0, 
					automaticTestsResult, 
					0.0, 
					easyCorrectionUtil.getDataNow(),
					null);
			return createAssessment(assess);
		}
	}

}
