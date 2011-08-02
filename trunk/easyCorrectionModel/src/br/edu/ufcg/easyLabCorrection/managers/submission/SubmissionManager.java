package br.edu.ufcg.easyLabCorrection.managers.submission;

import java.io.File;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.SubmissionException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;
import br.edu.ufcg.easyLabCorrection.util.ExternalErrorMsgs;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing of submissions in the system Easy Lab
 * Correction.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class SubmissionManager {

	/**
	 * Constructor default of class.<br>
	 */
	public SubmissionManager() {
		super();
	}

	/**
	 * Function used to retrieve the number of submission that is being held for
	 * a certain time, receives a submission as parameter.<br>
	 * 
	 * @param submission
	 *            The submission that is being held.<br>
	 * @return An integer, the number of submission.<br>
	 */
	public Integer getSubmissionNumber(Submission submission) {
		List<Submission> list = DAOFactory.DEFAULT.buildSubmissionDAO()
				.findByTeamAndAssignment(
						submission.getTeamHasUserHasAssignment().getTeam()
								.getId(),
						submission.getTeamHasUserHasAssignment()
								.getAssignment().getId());
		return list.size();
	}

	/**
	 * Function used to retrieve all submissions performed in a specific
	 * assignment for a certain time, receiving as parameters the team and the
	 * assignment.<br>
	 * 
	 * @param assign
	 *            The assignment used in the recovery of submissions.<br>
	 * @param team
	 *            The team used in the recovery of submissions.<br>
	 * @return All submissions whose assignment and team corresponds at the
	 *         assignment and team passed as parameter.<br>
	 */
	public List<Submission> getSubmissionsByAssignmentAndTeam(
			Assignment assign, Team team) {
		List<Submission> list = DAOFactory.DEFAULT.buildSubmissionDAO()
				.findByTeamAndAssignment(team.getId(), assign.getId());
		return list;
	}

	/**
	 * Function used to retrieve the number of submission by a
	 * TeamHasUserHasAssignment.<br>
	 * 
	 * @param tua
	 *            The TeamHasUserHasAssignment used to recovery a number of
	 *            submission.<br>
	 * @return The number of submission whose TeamHasUserHasAssignment
	 *         corresponds at the TeamHasUserHasAssignment passed as parameter.<br>
	 */
	public Integer getSubmissionNumberByTUA(TeamHasUserHasAssignment tua) {
		return getSubmissionsByAssignmentAndTeam(tua.getAssignment(),
				tua.getTeam()).size();
	}

	/**
	 * Function used to performs a submission in an assignment.<br>
	 * 
	 * @param submission
	 *            The submission to be performed in the assignment passed as
	 *            parameter.<br>
	 * @param assignment
	 *            The assignment in which the submission will be held.<br>
	 * @return The submission performed.<br>
	 * @throws EasyCorrectionException
	 *             An exception may be thrown in making the submission.<br>
	 */
	public Submission submitAssignment(Submission submission,
			Assignment assignment) throws EasyCorrectionException {
		if (submission != null) {
			if (assignment != null) {
				if (getSubmissionNumber(submission) < (submission
						.getTeamHasUserHasAssignment().getAssignment()
						.getSendMaxNumber())) {

					submission.setSubmissionDate(easyCorrectionUtil
							.getDataNow());
					Integer id = DAOFactory.DEFAULT.buildSubmissionDAO().save(
							submission);
					submission.setId(id);
				} else {
					throw new EasyCorrectionException(
							ExternalErrorMsgs.SUBMISSION_LIMIT_ALREADY_REACHED
									.msg());
				}
			} else {
				throw new EasyCorrectionException(
						ExternalErrorMsgs.SUBMISSION_DEADLINE_ALREADY_FINISHED
								.msg());
			}
		}
		return submission;
	}

	/*
	 * BEGIN OF PRIVATE METHODS This method is used to provide the environment,
	 * test and source downloads.
	 */
	private String getZipFile(String[] fileList) {
		try {
			String fileName = "";
			for (int i = 0; i < fileList.length; i++) {
				fileName = fileList[i];
				if (fileName
						.substring(fileName.length() - 3, fileName.length())
						.equals("zip")) {
					return fileName;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Funtion used to retrieve a submission by a submission identifier received
	 * as parameter.<br>
	 * 
	 * @param submissionId
	 *            The identifier of submission who want to retrieve.<br>
	 * @return The submission whose identifier corresponds at the identifier
	 *         passed as parameter.<br>
	 */
	public Submission getSubmission(int submissionId) {
		return DAOFactory.DEFAULT.buildSubmissionDAO().getById(submissionId);
	}

	/**
	 * Procedure used to delete a submission of the system, receives a
	 * submission as parameter.<br>
	 * 
	 * @param sub
	 *            The submission who want to delete.<br>
	 * @throws EasyCorrectionException
	 *             Exception that can be launched in an attempt to delete a
	 *             submission system.<br>
	 */
	public void deleteSubmission(Submission sub) throws EasyCorrectionException {
		if (sub == null) {
			throw new SubmissionException("Submissão inexistente!");
		}
		Submission submission = getSubmission(sub.getId());
		submission = (Submission) SwapperAtributosReflect.swapObject(
				submission, sub, Submission.class);
		DAOFactory.DEFAULT.buildSubmissionDAO().delete(submission);
	}

	public void deleteAllSubmissionsByAssignment(Integer assignmentId) {
		DAOFactory.DEFAULT.buildSubmissionDAO()
				.deleteAllSubmissionsByAssignment(assignmentId);
	}

	public void deleteAllSubmissionsByUserId(Integer userId) {
		DAOFactory.DEFAULT.buildSubmissionDAO().deleteAllSubmissionsByUserId(
				userId);
	}

	/**
	 * Function used to retrieve the filename of interface of the assignment
	 * received as parameter.<br>
	 * 
	 * @param assignment
	 *            The assignment who want to retrieve the interface filename.<br>
	 * @return The string corresponding at the filename of interface of
	 *         assignment passed as parameter.<br>
	 */
	public String getEnvironmentFileName(Assignment assignment) {
		String environmentDirectory = ServletUpload.local
				+ assignment.getInterfaceDirectory().replace("/",
						File.separator);
		File interfaceDir = new File(environmentDirectory);
		return getZipFile(interfaceDir.list());
	}

	/**
	 * Function used to retrieve the filename of tests of the assignment
	 * received as parameter.<br>
	 * 
	 * @param assignment
	 *            The assignment who want to retrieve the tests filename.<br>
	 * @return The string corresponding at the filename of tests of assignment
	 *         passed as parameter.<br>
	 */
	public String getTestsFileName(Assignment assignment) {
		String testsDirectory = ServletUpload.local
				+ assignment.getTestsDirectory().replace("/", File.separator);
		File testsDir = new File(testsDirectory);
		return getZipFile(testsDir.list());
	}

	/**
	 * Function used to retrieve the filename of source code of the assignment
	 * received as parameter.<br>
	 * 
	 * @param assignment
	 *            The assignment who want to retrieve the source code filename.<br>
	 * @return The string corresponding at the filename of source code of
	 *         assignment passed as parameter.<br>
	 */
	public String getSourceFileName(Submission submission) {
		String sourceDirectory = ServletUpload.local
				+ submission.getUrl().replace("/", File.separator);
		File sourceDir = new File(sourceDirectory);
		return getZipFile(sourceDir.list());
	}

}
