package br.edu.ufcg.easyLabCorrection.managers;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.textui.TestRunner;

import org.junit.runner.JUnitCore;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.TestExecutionException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.util.CompilationFileFilter;
import br.edu.ufcg.easyLabCorrection.util.Constants;

/**
 * Class responsible for managing of tests in the system 
 * Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class TestManager extends Manager {

	/**
	 * Constructor default of class, creates a new object TestManager.<br>
	 */
	public TestManager() {
		super();
	}
	
	/**
	 * Function used to run automatic tests in the submission performed.<br>
	 * @param submission The submission which will run in automatic testing.<br>
	 * @return A string containing the result of running the tests.<br> 
	 * @throws EasyCorrectionException Exception can be thrown in the execution 
	 * of automated tests.<br>
	 */
	public TestResult runAutomaticTests(Submission submission, String sourceDirectory,
			String testsDirectory)
			throws EasyCorrectionException {

		TestResult testResult;
		try {
			testResult = executeTests(sourceDirectory, testsDirectory);
		} catch (TestExecutionException e) {
			return null;
		}
		return testResult;
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
	public TestResult executeTests(String sourceDirectory,
			String testsDirectory) throws TestExecutionException {

		JUnit4TestAdapter testAdapter;
		TestResult result;
		URLClassLoader cl;
		Class<?> testClass;

		try {
			CompilationFileFilter pv = new CompilationFileFilter();
			//Gets the names of all java files inside sourceDirectory
			ArrayList<String> listSource = pv.visitAllDirsAndFiles(new File(sourceDirectory));
			
			if (listSource.size() == 0){
				cl = new URLClassLoader(new URL[] { new File(sourceDirectory)
						.toURI().toURL() }, JUnitCore.class.getClassLoader());
				testClass = cl.loadClass(Constants.mainTest);
			}else{
				String pathFile = listSource.get(0);
				String path = pathFile.substring(0, pathFile.lastIndexOf("\\") + 1);
				String lastPath = path.substring(0, pathFile.lastIndexOf("\\"));
				lastPath = lastPath.substring(lastPath.lastIndexOf("\\") + 1, lastPath.length());
				cl = new URLClassLoader(new URL[] { 
						new File(path)
						.toURI().toURL() }, JUnitCore.class.getClassLoader());
				testClass = cl.loadClass(lastPath + "." + Constants.mainTest);
			}
			testAdapter = new JUnit4TestAdapter(testClass);
			result = TestRunner.run(testAdapter);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new TestExecutionException(e.getMessage());
		}
		
		return result;
	}
	
	/*
	 * PRIVATE METHODS.
	 */
	private URL[] mountSourceDirectories(ArrayList<String> listSource) throws MalformedURLException{
		ArrayList<String> pathList = new ArrayList<String>();
		for (String sourcePath: listSource){
			String path = sourcePath.substring(0, sourcePath.lastIndexOf("\\") + 1);
			if(!pathList.contains(path)){
				pathList.add(path);
			}
		}
		URL[] urlList = new URL[pathList.size()];
		for (int i = 0; i < pathList.size(); i++){
			urlList[i] = new File(pathList.get(i)).toURI().toURL();
		}
		return urlList;
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
		Object[] answer = new Object[2];
		answer[0] = automaticTestsGrade;
		answer[1] = report;
		return answer;
	}

}
