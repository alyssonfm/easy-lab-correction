package br.edu.ufcg.easyLabCorrection.managers.compilation;

import groovy.util.AntBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import br.edu.ufcg.easyLabCorrection.exceptions.CompilationException;
import br.edu.ufcg.easyLabCorrection.managers.Manager;
import br.edu.ufcg.easyLabCorrection.util.ErrorMsgs;

/**
 * Class responsible for managing of compilation in the system Easy 
 * Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 *
 */
public class CompilationManager extends Manager{

	/*
	 * Attributes private of class.<br>
	 */
	private String errorResult;
	private boolean compilationError;
	private ArrayList<String> pathList;
	private ArrayList<String> sourceFileList;
	private ArrayList<String> pathTestList;
	private ArrayList<String> sourceTestFileList;
	private ArrayList<String> libFileList;
	
	/**
	 * Constructor default of class, creates a new object CompilationManager.<br>
	 */
	public CompilationManager() {
		super();
		pathList = new ArrayList<String>();
		sourceFileList = new ArrayList<String>();
		pathTestList = new ArrayList<String>();
		sourceTestFileList = new ArrayList<String>();
		libFileList = new ArrayList<String>();
	}
	
	/**
	 * Procedure used to run the Java compiler, receiving as parameter
	 * the path of source code directory, of tests directory and of 
	 * library directory that will be used in the compilation.<br>
	 * @param sourceDirectory The path of source code directory.<br>
	 * @param testsDirectory The path of tests directory.<br>
	 * @param libDirectory The path of library directory.<br>
	 * @throws CompilationException Exception can be thrown 
	 * during the compilation.<br>
	 */
	public void runJavaCompiler(String sourceDirectory, String testDirectory, String libDirectory) throws CompilationException{
		
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		
		OutputStream out = new OutputStream() {
			public void write(int b) throws IOException {
				errorResult += (char) b;
			}
		};

		OutputStream error = new OutputStream() {
			public void write(int b) throws IOException {
				compilationError = true;
				errorResult += (char) b;
			}
		};
		
		CompilationFileFilter pv = new CompilationFileFilter();
		
		//Gets the names of all java files inside sourceDirectory
		ArrayList<String> listSource = pv.visitAllDirsAndFiles(new File(sourceDirectory));
		mountSourceDirectories(listSource);
		
		pv = new CompilationFileFilter();
		
		//Gets the names of all java test files inside sourceDirectory
		ArrayList<String> listTestSource = pv.visitAllDirsAndFiles(new File(testDirectory));
		mountTestDirectories(listTestSource);
		
		clearSourcePaths();
		
		//Gets the names of all jar files inside libDirectory
		ArrayList<String> listLib = pv.visitAllDirsAndFilesFindingJars(new File(sourceDirectory));
		mountLibDirectories(listLib);
		
		
		/*
		try {
			//generateTask(javaCompiler);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			String sourceDir = concatDirectories(pathList);
			String testSourceDir = concatDirectories(pathTestList);
			String libDir = concatDirectories(libFileList);
			libDir += libDirectory + "junit.jar;";
			
			String[] arguments = mountCompilerParameters(sourceDir, testSourceDir, libDir, sourceDirectory);
			javaCompiler.run(null, out, error, arguments);

		} catch (Exception e) {
			System.err.println(ErrorMsgs.INVALID_TEST_SUITE_NAME.msg());
			throw new CompilationException(ErrorMsgs.INVALID_TEST_SUITE_NAME.msg());
		}
		
		if (compilationError) {
			throw new CompilationException("Compilation Error!");
		}
	}
	
	/**
	 * Procedure used set the occurrence of an error of compilation.<br> 
	 * @param compilationError The boolean parameter that indicates 
	 * the occurrence of compilation error.<br>
	 */
	public void setCompilationError(boolean compilationError) {
		this.compilationError = compilationError;
	}

	/**
	 * Function used to retrieve the error result of compilation.<br>
	 * @return The error result of compilation.<br>
	 */
	public String getErrorResult() {
		return errorResult;
	}

	/**
	 * Function used to verify if the error occurred is 
	 * a compilation error.<br>
	 * @return A boolean value indicating the presence 
	 * or absence of a compilation error.<br>
	 */
	public boolean isCompilationError() {
		return compilationError;
	}
	
	private List<File> mountFileList(ArrayList<String> listResource){
		List<File> list = new ArrayList<File>();
		for (String str : listResource) {
			list.add(new File(str));
		} 
		return list;
	}
	
	/*
	 * PRIVATE METHODS.
	 */
	private void mountSourceDirectories(ArrayList<String> listSource){
		for (String sourcePath: listSource){
			String path = sourcePath.substring(0, sourcePath.lastIndexOf("\\") + 1);
			if(!pathList.contains(path)){
				pathList.add(path);
			}
			sourceFileList.add(sourcePath);
		}
	}
	
	private void mountTestDirectories(ArrayList<String> listTestSource){
		for (String sourcePath: listTestSource){
			String path = sourcePath.substring(0, sourcePath.lastIndexOf("\\") + 1);
			if(!pathTestList.contains(path)){
				pathTestList.add(path);
			}
			sourceTestFileList.add(sourcePath);
		}
	}
	
	private void mountLibDirectories(ArrayList<String> listLib){
		for (String libPath: listLib){
			libFileList.add(libPath);
		}
	}
	
	private void clearSourcePaths(){
		try{
			for (String testPath: sourceTestFileList){
				for (String sourcePath: sourceFileList){
					String st = testPath.substring(testPath.lastIndexOf("\\"), testPath.length());
					String sts = sourcePath.substring(sourcePath.lastIndexOf("\\"), sourcePath.length());
					if (st.equals(sts)){
						sourceFileList.remove(sourcePath);
						break;
					}
				}
			}
		}catch (Exception e) { }
	}
	
	private String[] mountCompilerParameters(String sourceDir, String testSourceDir, String libDir, String outDir){
		
		String[] arguments = new String[6 + sourceFileList.size() + sourceTestFileList.size()];
		arguments[0] = "-sourcepath";
		arguments[1] = sourceDir + testSourceDir;
		arguments[2] = "-classpath";
		arguments[3] = libDir;
		int i = 4;
		for (String st: sourceFileList){
			arguments[i] = st;
			i++;
		}
		for (String st: sourceTestFileList){
			arguments[i] = st;
			i++;
		}
		arguments[i] = "-d";
		i++;
		arguments[i] = outDir;
		return arguments;
	}
	
	private String concatDirectories(ArrayList<String> pathList){
		String paths = "";
		for(String path: pathList){
			paths += path + ";";
		}
		return paths;
	}
	
}
