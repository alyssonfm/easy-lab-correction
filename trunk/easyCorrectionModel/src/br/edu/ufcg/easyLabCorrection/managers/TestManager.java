package br.edu.ufcg.easyLabCorrection.managers;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.textui.TestRunner;
import org.junit.runner.JUnitCore;
import br.edu.ufcg.easyLabCorrection.exceptions.ExecutionTestsException;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class TestManager extends Manager {
	
	private AssessmentManager assessmentManager;
	private CompilationManager compilationManager;

	public TestManager() {
		super();
		assessmentManager = new AssessmentManager();
		compilationManager = new CompilationManager();
	}

	public TestResult executeTests(String testsDirectory,
			String testFile, String interfaceDirectory,
			String interfaceFile, String sourceDirectory,
			String sourceFile) throws ExecutionTestsException {

		String libDirectory = (ServletUpload.local + "/").replace("/",
				File.separator);


		JUnit4TestAdapter testAdapter;
		TestResult result;
		URLClassLoader cl;
		Class<?> testClass;

		try {
			
			compilationManager.runJavaCompiler(sourceDirectory, 
					interfaceDirectory, 
					testsDirectory, 
					libDirectory, 
					interfaceFile, 
					sourceFile, 
					testFile);

			cl = new URLClassLoader(new URL[] { new File(sourceDirectory)
					.toURI().toURL() }, JUnitCore.class.getClassLoader());
			testClass = cl.loadClass(testFile.substring(0, testFile
					.length() - 5));

			testAdapter = new JUnit4TestAdapter(testClass);
			result = TestRunner.run(testAdapter);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ExecutionTestsException(e.getMessage());
		}

		if (compilationManager.isCompilationError()) {
			compilationManager.setCompilationError(false);
			throw new ExecutionTestsException(deleteDirectory(compilationManager.getErrorResult(), testsDirectory, sourceDirectory, interfaceDirectory));
		}
		
		return result;
	}
	
	private String deleteDirectory(String errorResult, String td, String sd, String id){
		String result = errorResult.replace(td, "");
		result = result.replace(sd, "");
		result = result.replace(id, "");
		return result;
	}

	public String getTestsExecutionOut(TestResult result,
			Submission submission) {
		
		TeamHasUserHasAssignment tua = submission.getTeamHasUserHasAssignment();
		String report = "";

		int testsRunnedNumber = result.runCount();
		int errors = result.errorCount();
		int itemsPercent = ((testsRunnedNumber - errors) * 100)
				/ testsRunnedNumber;
		double automaticTestsGrade = (itemsPercent * tua.getAssignment()
				.getAutomaticTestsPercentage()) / 1000;
		
		report = "Relatório de Avaliação: \n\n"
				+ "Total de Testes: "
				+ testsRunnedNumber
				+ "\n"
				+ "Total de Erros: "
				+ errors
				+ "\n"
				+ "Porcentagem de Acertos: "
				+ itemsPercent
				+ " %\n"
				+ "Nota dos Testes Automáticos: "
				+ automaticTestsGrade
				+ "\n\nConsole:\n";

		if (result.wasSuccessful()) {
			report += "SUCESSO!";
		} else {
			Enumeration<TestFailure> failures = result.errors();
			for (int i = 0; i < result.errorCount(); i++) {
				report += failures.nextElement().trace() + "\n";
			}
		}
		saveAssessment(submission, automaticTestsGrade, report);
		return report;
	}
	
	/* TODO: to System
	 * 
	 * The idea is to reuse the other saveAssessment there is on the AssessmentManager
	 */
	public Assessment saveAssessment(Submission submission, double automaticTestsGrade, String automaticTestsResult){
		try{
			Assessment assess = assessmentManager.getAssessmentByAssignmentAndTeam(submission.getTeamHasUserHasAssignment().getAssignment().getId(), 
					submission.getTeamHasUserHasAssignment().getTeam().getId());
			assess.setSubmission(submission);
			assess.setAutomaticGrade(automaticTestsGrade);
			assess.setAssessmentDate(easyCorrectionUtil.getDataNow());
			assess.setTestsExecutionResult(automaticTestsResult);
			return assessmentManager.updateAssessment(assess);
		}
		// TODO: What kind of exception?
		catch (Exception e) {
			
			Assessment assess = new Assessment(0, 
					submission, 
					automaticTestsGrade, 
					0.0, 
					automaticTestsResult, 
					0.0, 
					easyCorrectionUtil.getDataNow(),
					null);
			return assessmentManager.createAssessment(assess);
		}	
		
	}

}
