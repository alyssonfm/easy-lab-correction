package br.edu.ufcg.easyLabCorrection.managers;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestResult;
import br.edu.ufcg.easyLabCorrection.DAO.hibernate.DAOFactory;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.ExclusionAssignmentException;
import br.edu.ufcg.easyLabCorrection.exceptions.ExecutionTestsException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;
import br.edu.ufcg.easyLabCorrection.util.SwapperAtributosReflect;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class SubmissionManager {

	private TestManager testManager;

	public SubmissionManager() {
		super();
		testManager = new TestManager();
	}

	public Integer getSubmissionNumber(Submission submission) {
		List<Submission> list = DAOFactory.DEFAULT.buildSubmissionDAO()
				.findByTeamAndAssignment(
						submission.getTeamHasUserHasAssignment().getTeam()
								.getId(),
						submission.getTeamHasUserHasAssignment()
								.getAssignment().getId());
		return list.size();
	}

	public List<Submission> getSubmissionsByAssignmentAndTeam(
			Assignment assign, Team team) {
		List<Submission> list = DAOFactory.DEFAULT.buildSubmissionDAO()
				.findByTeamAndAssignment(team.getId(), assign.getId());
		return list;
	}

	public Integer getSubmissionNumberByTUA(TeamHasUserHasAssignment tua) {
		return getSubmissionsByAssignmentAndTeam(tua.getAssignment(),
				tua.getTeam()).size();
	}

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

	// TODO: It is not being used!
	private String[] compilerParameters(String libDirectory,
			String sourceDirectory, String interfaceDirectory,
			String testsDirectory, String[] sourceFileList,
			String[] interfaceFileList, String[] testsFileList) {

		List<String> params = new ArrayList<String>();
		params.add("-sourcepath");
		params.add(sourceDirectory + ";" + interfaceDirectory + ";"
				+ testsDirectory);
		params.add("-classpath");
		params.add(libDirectory + "junit.jar");
		String fileName = "";
		for (int i = 0; i < sourceFileList.length; i++) {
			fileName = sourceFileList[i];
			if (fileName.substring(fileName.length() - 4, fileName.length())
					.equals("java")) {
				params.add(sourceDirectory + fileName);
			}
		}
		for (int i = 0; i < interfaceFileList.length; i++) {
			fileName = interfaceFileList[i];
			if (fileName.substring(fileName.length() - 4, fileName.length())
					.equals("java")) {
				params.add(interfaceDirectory + fileName);
			}
		}
		for (int i = 0; i < testsFileList.length; i++) {
			fileName = testsFileList[i];
			if (fileName.substring(fileName.length() - 4, fileName.length())
					.equals("java")) {
				params.add(testsDirectory + fileName);
			}
		}
		params.add("-d");
		params.add(sourceDirectory);
		return params.toArray(new String[params.size()]);
	}

	/*
	 * TODO: Move to System
	 * 
	 * Idea: The compiler filenames will be build on the System and the
	 * TestManager will receive the filename completely build
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
			} catch (ExecutionTestsException e) {
				deleteSubmission(submission);
				return e.getMessage();
			}

			return testManager.getTestsExecutionOut(testResult, submission);
		} else {
			String result = "Este roteiro não possui testes automáticos.";
			testManager.saveAssessment(submission, 0, result);
			return "Resultado: " + result;
		}
	}

	public Submission getSubmission(int submissionId) {
		return DAOFactory.DEFAULT.buildSubmissionDAO().getById(submissionId);
	}

	public void deleteSubmission(Submission sub) throws EasyCorrectionException {
		if (sub == null) {
			throw new ExclusionAssignmentException("Submissão inexistente!");
		}
		Submission submission = getSubmission(sub.getId());
		submission = (Submission) SwapperAtributosReflect.swapObject(
				submission, sub, Submission.class);
		DAOFactory.DEFAULT.buildSubmissionDAO().delete(submission);
	}

	public String getInterfaceFileName(Assignment assignment) {
		String interfaceDirectory = ServletUpload.local
				+ assignment.getInterfaceDirectory().replace("/",
						File.separator);
		File interfaceDir = new File(interfaceDirectory);
		return firstOccurrence(interfaceDir.list());
	}

	public String getTestsFileName(Assignment assignment) {
		String testsDirectory = ServletUpload.local
				+ assignment.getTestsDirectory().replace("/", File.separator);
		File testsDir = new File(testsDirectory);
		return zipFirstOccurrence(testsDir.list());
	}

	public String getSourceFileName(Submission submission) {
		String sourceDirectory = ServletUpload.local
				+ submission.getUrl().replace("/", File.separator);
		File sourceDir = new File(sourceDirectory);
		return zipFirstOccurrence(sourceDir.list());
	}

}
