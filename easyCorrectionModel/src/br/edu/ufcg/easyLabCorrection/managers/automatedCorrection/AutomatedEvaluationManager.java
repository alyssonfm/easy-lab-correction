package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import junit.framework.TestResult;
import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.exceptions.TestExecutionException;
import br.edu.ufcg.easyLabCorrection.managers.Manager;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;

public class AutomatedEvaluationManager extends Manager {

	private TestExecution test;

	public AutomatedEvaluationManager() {
		super();
		test = new TestExecution();
	}

	/*
	 * Test Execution
	 */

	public TestResult runAutomaticTests(Submission submission,
			String sourceDirectory, String testsDirectory)
			throws EasyCorrectionException {

		return test.runAutomaticTests(submission, sourceDirectory,
				testsDirectory);
	}

	public TestResult executeTests(String sourceDirectory, String testsDirectory)
			throws TestExecutionException {
		return test.executeTests(sourceDirectory, testsDirectory);
	}

	public Object[] getTestsExecutionOutput(TestResult result,
			Submission submission) {
		return test.getTestsExecutionOutput(result, submission);
	}
}
