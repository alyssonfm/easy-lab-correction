package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.lang.reflect.Method;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.util.Constants;
import br.edu.ufcg.easyLabCorrection.util.SecuritySupport;

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
public class OutputComparison extends Thread{
	
	private String sourceDirectory;
	private String inOutFilesDirectory;
	private String result;
	private Object pass;
	private SecuritySupport secSupport;
	
	
	/**
	 * Constructor default of class, creates a new object OutputComparison.<br>
	 */
	public OutputComparison(String sourceDirectory, String inOutFilesDirectory) {
		super();
		pass = new Object();
		secSupport = new SecuritySupport(pass);
		this.inOutFilesDirectory = inOutFilesDirectory; 
		this.sourceDirectory = sourceDirectory;
	}
	
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
	 * @throws EasyCorrectionException 
	 */
	public ArrayList<Boolean> compareOutput(){
		
		try{
			File inputFile = new File(this.inOutFilesDirectory + "entrada.txt");
			File outputFile = new File(this.inOutFilesDirectory + "saida.txt");
	
			ArrayList<String> testSuite = readTestCasesFromFile(inputFile);
			ArrayList<String> expectedOutput = readExpectedOutputsFromFile(outputFile);
			ArrayList<Boolean> testVerdicts = new ArrayList<Boolean>(testSuite
					.size());
	
			PrintStream stdout = System.out;
			OurOutputStream ourOutputStream = new OurOutputStream();
	
			System.setOut(new PrintStream(ourOutputStream));
			
			URLClassLoader cl;
			Class<?> testClass;

			try {
				TestExecutionFileFilter tv = new TestExecutionFileFilter();
				//Gets the names of all java files inside sourceDirectory
				ArrayList<String> listSource = tv.visitAllDirsAndFiles(new File(sourceDirectory));
				if (listSource.size() != 0){
					String pathFile = tv.findMainClass();
					String path = pathFile.substring(sourceDirectory.length(), 
						pathFile.lastIndexOf(File.separator) + 1).replace(File.separator, ".");
					cl = new URLClassLoader(new URL[] { new File(sourceDirectory)
						.toURI().toURL() });
					testClass = cl.loadClass(path + Constants.mainClass);
					
					Class<?> getArg1[] = { (new String[1]).getClass() };
				    Method m = testClass.getMethod("main", getArg1);
				    
				    for (int i = 0; i < testSuite.size(); i++) {
						// Solution Execution
						String[] arg1 = testSuite.get(i).split(Constants.TEST_DATA_SEPARATOR);
						Object args[] = {arg1};
						m.invoke(null, args);
			
						// Result comparison
						testVerdicts.add(i, compareActualAndExpectedOutputs(ourOutputStream
								.toString(), expectedOutput.get(i)));
			
						// Stream cleaning
						ourOutputStream.flushOurStream();
					}
			
					System.setOut(stdout);
					setResult("OK!");
					return testVerdicts;
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
				setResult(null);
			}
		}
		catch(EasyCorrectionException ece){
			return new ArrayList<Boolean>();
		}
		return new ArrayList<Boolean>();
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
	 * It reads the file and splits it into expected output strings
	 * 
	 * @param f
	 *            - the complete output file
	 * @return an ArrayList of the expected output strings
	 * @throws EasyCorrectionException 
	 */
	private ArrayList<String> readExpectedOutputsFromFile(File f) throws EasyCorrectionException {
		return readStringsFromFile(f, Constants.EXPECTED_OUTPUT_SEPARATOR);
	}

	/**
	 * It reads the input file and splits it into test case strings
	 * 
	 * @param f
	 *            - the complete input file
	 * @return an ArrayList with the test case strings
	 * @throws EasyCorrectionException 
	 */
	private ArrayList<String> readTestCasesFromFile(File f) throws EasyCorrectionException {
		return readStringsFromFile(f, Constants.TEST_CASE_SEPARATOR);
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
	 * @throws EasyCorrectionException
	 */
	private ArrayList<String> readStringsFromFile(File f, String stringSeparator)
			throws EasyCorrectionException {
		ArrayList<String> contentOfFile = new ArrayList<String>();
		byte[] buffer = new byte[(int) f.length()];
		BufferedInputStream stream = null;
		try {
			stream = new BufferedInputStream(new FileInputStream(f));
			stream.read(buffer);
		} catch (IOException e) {
			throw new EasyCorrectionException("The file " + f.getName()
					+ " could not be read during the Output Comparison!");
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				throw new EasyCorrectionException("The file " + f.getName()
						+ " could not be closed during the Output Comparison!");
			}
		}

		for (String string : new String(buffer).split(stringSeparator)) {
			contentOfFile.add(string);
		}

		return contentOfFile;
	}
	
	public void run() {
		SecurityManager old = System.getSecurityManager();
		System.setSecurityManager(secSupport);
		compareOutput();
		System.setSecurityManager(old);
		secSupport.disable(pass);
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

}
