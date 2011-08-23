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
	}

	/*
	 * Test Execution
	 */

	public TestResult runAutomaticTests(String sourceDirectory, long timeLimit)
			throws EasyCorrectionException {
		TestResult tr = new TestResult();
		test = new TestExecution(sourceDirectory);
		test.start();
		try {
			Thread.sleep(timeLimit);
			tr = test.getResult();
			if(tr != null){
				return tr;
			}
			else{
				throw new TestExecutionException("Execution Time Limit Exceeded! Your code should run in " +
						timeLimit + " ms.");
			}
		} catch (InterruptedException e) {
			throw new TestExecutionException("Execution Time Limit Exceeded! Your code should run in " +
					timeLimit + " ms.");
		}
	}
	
	public void interruptExecution() {
		test.interrupt();
	}

	public Object[] getTestsExecutionOutput(TestResult result,
			Submission submission) {
		return test.getTestsExecutionOutput(result, submission);
	}
	
}
