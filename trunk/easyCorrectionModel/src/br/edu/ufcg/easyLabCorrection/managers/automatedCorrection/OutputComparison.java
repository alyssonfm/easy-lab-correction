package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import java.io.File;

import java.io.PrintStream;
import java.util.ArrayList;

import br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.example.Main;
import br.edu.ufcg.easyLabCorrection.util.Constants;

/**
 * This class will execute the solution submitted by the students and compare
 * the output string with the expected output given by the tutor. <br>
 * <br>
 * Requirements to use this module: <br>
 * <br>
 * * The solution shall have a Main.java file in the root directory; <br>
 * * The Main.java file shall contain a main method to receive the input from
 * the args parameter and shall show the result of the computation in the
 * standard output <br>
 * * The input and output txt files shall not contain accents
 */
public class OutputComparison {

	/**
	 * This is the most important function of the module. Its job is execute the
	 * given mainSolution (Main.java file) with the test data strings from the
	 * given input file, then compare each result with the expected output
	 * strings from the given output file
	 * 
	 * @param mainSolution
	 *            - directory where the Main.java file was compiled. I THINK
	 *            THIS WILL BE CHANGED!
	 * @param inputFile
	 *            - the .txt file with the test cases
	 * @param outputFile
	 *            - the .txt file with all the expected outputs
	 * @return an ArrayList with a boolean result for each test case
	 */
	public ArrayList<Boolean> compareOutput(File mainSolution, File inputFile,
			File outputFile) {

		ArrayList<String> testSuite = readTestCasesFromFile(inputFile);
		ArrayList<String> expectedOutput = readExpectedOutputsFromFile(outputFile);
		ArrayList<Boolean> testVerdicts = new ArrayList<Boolean>(testSuite
				.size());

		PrintStream stdout = System.out;
		OurOutputStream ourOutputStream = new OurOutputStream();
		
		System.setOut(new PrintStream(ourOutputStream));

		for (int i = 0; i < testSuite.size(); i++) {
			// Solution Execution
			Main.main(testSuite.get(i).split(Constants.TEST_DATA_SEPARATOR));

			// Result comparison
			testVerdicts.set(i, compareActualAndExpectedOutputs(ourOutputStream
					.toString(), expectedOutput.get(i)));

			// Stream cleaning
			ourOutputStream.flushOurStream();
		}

		System.setOut(stdout);
		
		return testVerdicts;
	}

	/**
	 * It compares two strings for equality.<br>
	 * <br>
	 * OBS.:This method could be re-implemented at any moment, depending on the
	 * client requirements.
	 * 
	 * @param string
	 *            to compare
	 * @param string2
	 *            to compare
	 * @return a boolean with the result
	 */
	private boolean compareActualAndExpectedOutputs(String string,
			String string2) {
		return string.equals(string2);
	}

	/**
	 * It returns the total amount of test cases in a single input file
	 * 
	 * @param inputFile
	 *            - a txt file with the test data for each test case separated
	 *            by lines
	 * @return the total amount of test cases in a single file
	 */
	public int getTestCasesNumber(File inputFile) {
		// I THINK IT WILL NOT BE USED
		return readTestCasesFromFile(inputFile).size();
	}

	/**
	 * It reads the file and splits it into expected output strings
	 * 
	 * @param f
	 *            - the complete output file
	 * @return an ArrayList of the expected output strings
	 */
	private ArrayList<String> readExpectedOutputsFromFile(File f) {
		return readStringsFromFile(f, Constants.EXPECTED_OUTPUT_SEPARATOR);
	}

	/**
	 * It reads the input file and splits it into test case strings
	 * 
	 * @param f
	 *            - the complete input file
	 * @return an ArrayList with the test case strings
	 */
	private ArrayList<String> readTestCasesFromFile(File f) {
		return readStringsFromFile(f, Constants.TEST_DATA_SEPARATOR);
	}

	/**
	 * A private function that reads a txt file and splits its content by a
	 * given stringSeparator string
	 * 
	 * @param f
	 *            - the file with the content
	 * @param stringSeparator
	 *            - the string separator to split the data
	 * @return an ArrayList with the splitted file data
	 */
	private ArrayList<String> readStringsFromFile(File f, String stringSeparator) {
		// TODO
		return null;
	}

}
