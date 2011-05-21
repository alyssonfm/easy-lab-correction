package br.edu.ufcg.easyLabCorrection.managers;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;

public class AssessmentManager extends Manager {
	
	public AssessmentManager() {
		super();
	}

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

	public List<Assessment> getAssignmentWithoutCorrectors(int assignmentId) {
		return this.getAssignmentWithCorrectors(assignmentId, 0);
	}

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
	
	public Assessment getAssessmentByAssignmentAndTeam(int assignmentId, int teamId){
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO().findByAssignmentAndTeam(assignmentId, teamId);
		if(list.isEmpty()){
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND.msg("avaliacao"));
		}
		return list.get(0);
	}
	
	public List<Assessment> getAssessmentByAssignmentAndCorrector(Assignment assign, Integer us) {
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO()
				.findByTeamAndAssignmentByCorrector(assign.getId(), us);
		return list;
	} 
	
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
	
	public Assessment updateAssessment(Assessment assessment) throws EasyCorrectionException{
		
		DAOFactory.DEFAULT.buildAssessmentDAO().update(assessment);
		assessment.setId(assessment.getId());
		return assessment;
	}
	
	public Assessment createAssessment(Assessment assessment){
		
		int id = DAOFactory.DEFAULT.buildAssessmentDAO().save(assessment);
		assessment.setId(id);
		return assessment;
	}
	
	public void deleteAssessment(Assessment assessment) throws EasyCorrectionException{
		Assessment assess = DAOFactory.DEFAULT.buildAssessmentDAO().getById(assessment.getId());
		assess= (Assessment) SwapperAtributosReflect.swapObject(assess, assessment,
				Assessment.class);
		DAOFactory.DEFAULT.buildAssessmentDAO().delete(assess);
	}
	
	public List<Assessment> getAssessmentBySubmission(int submissionId){
		return DAOFactory.DEFAULT.buildAssessmentDAO().findBySubmission(submissionId);
		
	}

	public List<Assessment> getAssessmentByAssignment(Assignment assignment) {
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO()
			.findByAssignment(assignment.getId());
		return list;
	}

	public List<Assessment> getAssessmentByTeamAndAssignment(Integer teamId,
			Integer assignmentId) {
		List<Assessment> list = DAOFactory.DEFAULT.buildAssessmentDAO()
		.findByTeamAndAssignment(teamId, assignmentId);
	return list;
	}



}
