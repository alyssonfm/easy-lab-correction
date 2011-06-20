package br.edu.ufcg.easyLabCorrection.managers;

import java.io.IOException;
import java.io.OutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import br.edu.ufcg.easyLabCorrection.exceptions.CompilationException;

public class CompilationManager extends Manager{

	private String errorResult;
	private boolean compilationError;

	public CompilationManager() {
		super();
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
	
	public void setCompilationError(boolean compilationError) {
		this.compilationError = compilationError;
	}

	public String getErrorResult() {
		return errorResult;
	}

	public boolean isCompilationError() {
		return compilationError;
	}
	
}
