package br.edu.ufcg.easyLabCorrection.managers;

import java.io.File;
import java.util.List;

import junit.framework.TestResult;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.SubmissionException;
import br.edu.ufcg.easyLabCorrection.exceptions.TestExecutionException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing of submissions in the system Easy 
 * Lab Correction.<br> 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class SubmissionManager {

	private TestManager testManager;

	/**
	 * Constructor default of class.<br>
	 */
	public SubmissionManager() {
		super();
		testManager = new TestManager();
	}

	/**
	 * Function used to retrieve the number of submission that is 
	 * being held for a certain time, receives a submission as 
	 * parameter.<br>
	 * @param submission The submission that is being held.<br>
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
	 * Function used to retrieve all submissions performed in a specific assignment 
	 * for a  certain time, receiving as parameters the team and the assignment.<br>
	 * @param assign The assignment used in the recovery of submissions.<br>
	 * @param team The team used in the recovery of submissions.<br>
	 * @return All submissions whose assignment and team corresponds at the 
	 * assignment and team passed as parameter.<br>
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
	 * @param tua The TeamHasUserHasAssignment used to recovery a number 
	 * of submission.<br>
	 * @return The number of submission whose TeamHasUserHasAssignment corresponds 
	 * at the TeamHasUserHasAssignment passed as parameter.<br>
	 */
	public Integer getSubmissionNumberByTUA(TeamHasUserHasAssignment tua) {
		return getSubmissionsByAssignmentAndTeam(tua.getAssignment(),
				tua.getTeam()).size();
	}

	/**
	 * Function used to performs a submission in an assignment.<br>
	 * @param submission The submission to be performed in the assignment 
	 * passed as parameter.<br>
	 * @param assignment The assignment in which the submission 
	 * will be held.<br>
	 * @return The submission performed.<br>
	 * @throws EasyCorrectionException An exception may be thrown in making 
	 * the submission.<br>
	 */
	public Submission submitAssignment(Submission submission,
			Assignment assignment) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(submission)) {
			if (!easyCorrectionUtil.isNull(assignment)) {
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
							MsgErros.NUMERO_MAXIMO_SUBMISSOES_EXCEDIDO
									.msg(submission
											.getTeamHasUserHasAssignment()
											.getAssignment().getName()));
				}
			} else {
				throw new EasyCorrectionException(
						MsgErros.TEMPO_MAXIMO_SUBMISSOES_EXCEDIDO
								.msg(submission.getTeamHasUserHasAssignment()
										.getAssignment().getName()));

			}
		}
		return submission;
	}

	/*
	 * BEGIN OF PRIVATE METHODS
	 */
	private String firstOccurrence(String[] fileList) {
		try {
			String fileName = "";
			for (int i = 0; i < fileList.length; i++) {
				fileName = fileList[i];
				if (fileName
						.substring(fileName.length() - 4, fileName.length())
						.equals("java")) {
					return fileName;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	private String zipFirstOccurrence(String[] fileList) {
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
	/*
	 * END OF PRIVATE METHODS 
	 */
	
	// TODO: It is not being used!
	// private String[] compilerParameters(String libDirectory,
	// String sourceDirectory, String interfaceDirectory,
	// String testsDirectory, String[] sourceFileList,
	// String[] interfaceFileList, String[] testsFileList) {
	//
	// List<String> params = new ArrayList<String>();
	// params.add("-sourcepath");
	// params.add(sourceDirectory + ";" + interfaceDirectory + ";"
	// + testsDirectory);
	// params.add("-classpath");
	// params.add(libDirectory + "junit.jar");
	// String fileName = "";
	// for (int i = 0; i < sourceFileList.length; i++) {
	// fileName = sourceFileList[i];
	// if (fileName.substring(fileName.length() - 4, fileName.length())
	// .equals("java")) {
	// params.add(sourceDirectory + fileName);
	// }
	// }
	// for (int i = 0; i < interfaceFileList.length; i++) {
	// fileName = interfaceFileList[i];
	// if (fileName.substring(fileName.length() - 4, fileName.length())
	// .equals("java")) {
	// params.add(interfaceDirectory + fileName);
	// }
	// }
	// for (int i = 0; i < testsFileList.length; i++) {
	// fileName = testsFileList[i];
	// if (fileName.substring(fileName.length() - 4, fileName.length())
	// .equals("java")) {
	// params.add(testsDirectory + fileName);
	// }
	// }
	// params.add("-d");
	// params.add(sourceDirectory);
	// return params.toArray(new String[params.size()]);
	// }

	/*
	 * TODO: Move to System
	 * 
	 * Idea: The compiler filenames will be build on the System and the
	 * TestManager will receive the filename completely build
	 */
	
	/**
	 * Function used to run automatic tests in the submission performed.<br>
	 * @param submission The submission which will run in automatic testing.<br>
	 * @return A string containing the result of running the tests.<br> 
	 * @throws EasyCorrectionException Exception can be thrown in the execution 
	 * of automated tests.<br>
	 */
	public String runAutomaticTests(Submission submission)
			throws EasyCorrectionException {

		if (submission.getTeamHasUserHasAssignment().getAssignment()
				.getAutomaticTestsPercentage() > 0) {
			String testsDirectory = ServletUpload.local
					+ submission.getTeamHasUserHasAssignment().getAssignment()
							.getTestsDirectory().replace("/", File.separator);
			String interfaceDirectory = ServletUpload.local
					+ submission.getTeamHasUserHasAssignment().getAssignment()
							.getInterfaceDirectory().replace("/",
									File.separator);
			String sourceDirectory = ServletUpload.local
					+ submission.getUrl().replace("/", File.separator);

			File testsDir = new File(testsDirectory);
			String testFile = firstOccurrence(testsDir.list());
			File interfaceDir = new File(interfaceDirectory);
			String interfaceFile = firstOccurrence(interfaceDir.list());
			File sourceDir = new File(sourceDirectory);
			String sourceFile = firstOccurrence(sourceDir.list());

			TestResult testResult;

			try {
				testResult = testManager.executeTests(testsDirectory, testFile,
						interfaceDirectory, interfaceFile, sourceDirectory,
						sourceFile);
			} catch (TestExecutionException e) {
				deleteSubmission(submission);
				return e.getMessage();
			}

			return testManager.getTestsExecutionOutput(testResult, submission);
		} else {
			String result = "Este roteiro não possui testes automáticos.";
			testManager.saveAssessment(submission, 0, result);
			return "Resultado: " + result;
		}
	}

	/**
	 * Funtion used to retrieve a submission by a submission identifier 
	 * received as parameter.<br>
	 * @param submissionId The identifier of submission who want to 
	 * retrieve.<br>
	 * @return The submission whose identifier corresponds at the 
	 * identifier passed as parameter.<br>
	 */
	public Submission getSubmission(int submissionId) {
		return DAOFactory.DEFAULT.buildSubmissionDAO().getById(submissionId);
	}

	/**
	 * Procedure used to delete a submission of the system, receives a 
	 * submission as parameter.<br>
	 * @param sub The submission who want to delete.<br>
	 * @throws EasyCorrectionException Exception that can be launched in an 
	 * attempt to delete a submission system.<br>
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

	/**
	 * Function used to retrieve the filename of interface of the assignment 
	 * received as parameter.<br>
	 * @param assignment The assignment who want to retrieve the interface 
	 * filename.<br>
	 * @return The string corresponding at the filename of interface of 
	 * assignment passed as parameter.<br>
	 */
	public String getInterfaceFileName(Assignment assignment) {
		String interfaceDirectory = ServletUpload.local
				+ assignment.getInterfaceDirectory().replace("/",
						File.separator);
		File interfaceDir = new File(interfaceDirectory);
		return firstOccurrence(interfaceDir.list());
	}

	/**
	 * Function used to retrieve the filename of tests of the assignment 
	 * received as parameter.<br>
	 * @param assignment The assignment who want to retrieve the tests 
	 * filename.<br>
	 * @return The string corresponding at the filename of tests of 
	 * assignment passed as parameter.<br>
	 */
	public String getTestsFileName(Assignment assignment) {
		String testsDirectory = ServletUpload.local
				+ assignment.getTestsDirectory().replace("/", File.separator);
		File testsDir = new File(testsDirectory);
		return zipFirstOccurrence(testsDir.list());
	}

	/**
	 * Function used to retrieve the filename of source code of the assignment 
	 * received as parameter.<br>
	 * @param assignment The assignment who want to retrieve the source code 
	 * filename.<br>
	 * @return The string corresponding at the filename of source code of 
	 * assignment passed as parameter.<br>
	 */
	public String getSourceFileName(Submission submission) {
		String sourceDirectory = ServletUpload.local
				+ submission.getUrl().replace("/", File.separator);
		File sourceDir = new File(sourceDirectory);
		return zipFirstOccurrence(sourceDir.list());
	}

}
