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
import br.edu.ufcg.easyLabCorrection.exceptions.TestExecutionException;
import br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.servlet.ServletUpload;
import br.edu.ufcg.easyLabCorrection.util.Constants;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

/**
 * Class responsible for managing of tests in the system 
 * Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class TestManager extends Manager {
	
	/*
	 * Attributes private of class.<br>
	 */
	private AssessmentManager assessmentManager;
	private CompilationManager compilationManager;

	/**
	 * Constructor default of class, creates a new object TestManager.<br>
	 */
	public TestManager() {
		super();
		assessmentManager = new AssessmentManager();
		compilationManager = new CompilationManager();
	}

	/**
	 * Function used to execute the tests of system, receives as parameter
	 * the path of the tests directory, of the interface directory, 
	 * of the source code directory, of the test file, of the interface 
	 * file and of the source code file.<br>
	 * @param testsDirectory The path of tests directory.<br>
	 * @param testFile The path of test file.<br>
	 * @param interfaceDirectory The path of interface directory.<br>
	 * @param interfaceFile The path of interface file.<br>
	 * @param sourceDirectory The path of source code directory.<br>
	 * @param sourceFile The path of source code file.<br>
	 * @return The test result of execution.<br>
	 * @throws TestExecutionException Exception can be thrown during 
	 * the execution of tests.<br>
	 */
	public TestResult executeTests(String testsDirectory,
			String testFile, String interfaceDirectory,
			String interfaceFile, String sourceDirectory,
			String sourceFile) throws TestExecutionException {

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
			
			testClass = cl.loadClass(testFile.substring(0, testFile.length() - 5));

			testAdapter = new JUnit4TestAdapter(testClass);
			result = TestRunner.run(testAdapter);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new TestExecutionException(e.getMessage());
		}

		if (compilationManager.isCompilationError()) {
			compilationManager.setCompilationError(false);
			throw new TestExecutionException(deleteDirectory(compilationManager.getErrorResult(), testsDirectory, sourceDirectory, interfaceDirectory));
		}
		
		return result;
	}
	
	/**
	 * Function used to execute the tests of system, receives as parameter
	 * the path of the tests directory, and of the source code directory.<br>
	 * @param sourceDirectory The path of source code directory.<br>
	 * @param testsDirectory The path of tests directory.<br>
	 * @return The test result of execution.<br>
	 * @throws TestExecutionException Exception can be thrown during 
	 * the execution of tests.<br>
	 */
	public TestResult executeTests2(String sourceDirectory,
			String testsDirectory) throws TestExecutionException {

		String libDirectory = "D:/TEMP/lib/";
		
		//String libDirectory = (ServletUpload.local + "/").replace("/",
				//File.separator);

		JUnit4TestAdapter testAdapter;
		TestResult result;
		URLClassLoader cl;
		Class<?> testClass;

		try {
			
			compilationManager.runJavaCompiler2(sourceDirectory,
					testsDirectory, 
					libDirectory);

			cl = new URLClassLoader(new URL[] { new File(sourceDirectory)
					.toURI().toURL() }, JUnitCore.class.getClassLoader());
			
			testClass = cl.loadClass(Constants.mainTest);

			testAdapter = new JUnit4TestAdapter(testClass);
			result = TestRunner.run(testAdapter);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new TestExecutionException(e.getMessage());
		}

		if (compilationManager.isCompilationError()) {
			compilationManager.setCompilationError(false);
			throw new TestExecutionException(deleteDirectory(compilationManager.getErrorResult(), testsDirectory, sourceDirectory, libDirectory));
		}
		
		return result;
	}
	
	private String deleteDirectory(String errorResult, String td, String sd, String id){
		String result = errorResult.replace(td, "");
		result = result.replace(sd, "");
		result = result.replace(id, "");
		return result;
	}

	/**
	 * Function used to retrieve the output of tests execution, receives a test 
	 * result and a submission as parameter.<br>
	 * @param result The test result of execution of submission who want to 
	 * retrieve the output.<br>  
	 * @param submission The submission who want to retrieve the output.<br>  
	 * @return The string corresponding at the tests execution of the submission
	 * received as parameter.<br>
	 */
	public String getTestsExecutionOutput(TestResult result,
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
	/**
	 * Function used to save an assessment in the database of the system.<br>
	 * @param submission The submission who want to save the assessment.<br>
	 * @param automaticTestsGrade The value of automatic tests.<br> 
	 * @param automaticTestsResult The result of automatic tests.<br> 
	 * @return The assessment save in the system.<br>
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
