package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.textui.TestRunner;

import org.junit.runner.JUnitCore;

import br.edu.ufcg.easyLabCorrection.exceptions.TestExecutionException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.util.Constants;
import br.edu.ufcg.easyLabCorrection.util.SecuritySupport;

/**
 * Class responsible for managing of tests in the system 
 * Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class TestExecution extends Thread{
	
	private String sourceDirectory;
	private TestResult result;
	private Object pass = new Object();
	private SecuritySupport secSupport = new SecuritySupport(pass);

	/**
	 * Constructor default of class, creates a new object TestManager.<br>
	 */
	public TestExecution(String sourceDirectory) {
		super();
		this.sourceDirectory = sourceDirectory;
	}
	
	public TestResult getResult() {
		return result;
	}

	public void setResult(TestResult result) {
		this.result = result;
	}

	public Object getPass() {
		return pass;
	}

	public void setPass(Object pass) {
		this.pass = pass;
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
	public void executeTests(){
		
		JUnit4TestAdapter testAdapter;
		URLClassLoader cl;
		Class<?> testClass;

		try {
			TestExecutionFileFilter tv = new TestExecutionFileFilter();
			//Gets the names of all java files inside sourceDirectory
			ArrayList<String> listSource = tv.visitAllDirsAndFiles(new File(sourceDirectory));
			if (listSource.size() != 0){
				String pathFile = tv.findMainTest();
				String path = pathFile.substring(sourceDirectory.length(), 
					pathFile.lastIndexOf(File.separator) + 1).replace(File.separator, ".");
				cl = new URLClassLoader(new URL[] { new File(sourceDirectory)
					.toURI().toURL() }, JUnitCore.class.getClassLoader());
				testClass = cl.loadClass(path + Constants.mainTest);
				testAdapter = new JUnit4TestAdapter(testClass);
				result = TestRunner.run(testAdapter);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			result = null;
		}
	}

	public void run() {
		SecurityManager old = System.getSecurityManager();
		System.setSecurityManager(secSupport);
		executeTests();
		System.setSecurityManager(old);
		secSupport.disable(pass);
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
	public Object[] getTestsExecutionOutput(TestResult result,
			Submission submission) {
		
		TeamHasUserHasAssignment tua = submission.getTeamHasUserHasAssignment();
		String report = "";

		int testsRunnedNumber = result.runCount();
		int errors = result.errorCount();
		int itemsPercent = ((testsRunnedNumber - errors) * 100)
				/ testsRunnedNumber;
		double automaticTestsGrade = (itemsPercent * tua.getAssignment()
				.getAutomaticTestsPercentage()) / 1000;
		
		report = "Assessment Report: \n\n"
				+ "Total of Test Cases Executed = "
				+ testsRunnedNumber
				+ "\n"
				+ "Error Verdict(s) = "
				+ errors
				+ "\n"
//				+ "Failures Verdict(s) = "
//				+ result.failureCount()
//				+ "\n"
				+ "Success Verdict(s) = "
				+ errors
				+ "\n"
				+ "Success percentage = "
				+ itemsPercent
				+ " %\n"
				+ "Automatic Execution Grade: "
				+ automaticTestsGrade
				+ "\n\nConsole: \n";

		if (result.wasSuccessful()) {
			report += "SUCESS!";
		} else {
			Enumeration<TestFailure> failures = result.errors();
			for (int i = 0; i < result.errorCount(); i++) {
				report += failures.nextElement().trace() + "\n";
			}
		}
		Object[] answer = new Object[2];
		answer[0] = automaticTestsGrade;
		answer[1] = report;
		return answer;
	}

}
