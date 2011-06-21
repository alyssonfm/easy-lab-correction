package br.edu.ufcg.easyLabCorrection.managers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import br.edu.ufcg.easyLabCorrection.exceptions.CompilationException;
import br.edu.ufcg.easyLabCorrection.util.SubmissionFileFilter;

public class CompilationManager extends Manager{

	private String errorResult;
	private boolean compilationError;
	private ArrayList<String> pathList;
	private ArrayList<String> sourceFileList;
	private ArrayList<String> pathTestList;
	private ArrayList<String> sourceTestFileList;
	private ArrayList<String> libFileList;

	public CompilationManager() {
		super();
		pathList = new ArrayList<String>();
		sourceFileList = new ArrayList<String>();
		pathTestList = new ArrayList<String>();
		sourceTestFileList = new ArrayList<String>();
		libFileList = new ArrayList<String>();
	}
	
	public void runJavaCompiler(String sourceDirectory, 
			String interfaceDirectory, 
			String testsDirectory,
			String libDirectory,
			String interfaceFile,
			String sourceFile,
			String testFile) throws CompilationException{
		
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

		try {
			javaCompiler.run(null, out, error, "-sourcepath", sourceDirectory
					+ ";" + interfaceDirectory + ";" + testsDirectory,
					"-classpath", libDirectory + "junit.jar",
					interfaceDirectory + interfaceFile, sourceDirectory
							+ sourceFile, testsDirectory + testFile,
					"-d", sourceDirectory); // diretório de saída dos .class

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		if (compilationError) {
			throw new CompilationException("Compilation Error!");
		}
	}
	
	public void runJavaCompiler2(String sourceDirectory, String testDirectory, String libDirectory) throws CompilationException{
		
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
		
		SubmissionFileFilter pv = new SubmissionFileFilter();
		
		//Gets the names of all java files inside sourceDirectory
		ArrayList<String> listSource = pv.visitAllDirsAndFiles(new File(sourceDirectory));
		mountSourceDirectories(listSource);
		
		//Gets the names of all java test files inside sourceDirectory
		ArrayList<String> listTestSource = pv.visitAllDirsAndFiles(new File(testDirectory));
		mountTestDirectories(listTestSource);
		
		//Gets the names of all jar files inside libDirectory
		ArrayList<String> listLib = pv.visitAllDirsAndFilesFindingJars(new File(sourceDirectory));
		mountLibDirectories(listLib);

		try {
			String sourceDir = concatDirectories(pathList);
			String testSourceDir = concatDirectories(pathTestList);
			String libDir = concatDirectories(libFileList);
			libDir += libDirectory + "junit.jar;";
			
			String[] arguments = mountCompilerParameters(sourceDir, testSourceDir, libDir, sourceDirectory);
			javaCompiler.run(null, out, error, arguments);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		if (compilationError) {
			throw new CompilationException("Compilation Error!");
		}
	}
	
	public void setCompilationError(boolean compilationError) {
		this.compilationError = compilationError;
	}

	public String getErrorResult() {
		return errorResult;
	}

	public boolean isCompilationError() {
		return compilationError;
	}
	
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
