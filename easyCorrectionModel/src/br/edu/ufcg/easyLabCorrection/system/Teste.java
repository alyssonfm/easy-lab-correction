package br.edu.ufcg.easyLabCorrection.system;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import br.edu.ufcg.easyLabCorrection.exceptions.CompilationException;
import br.edu.ufcg.easyLabCorrection.util.SubmissionFileFilter;

public class Teste {
	
	public static void main(String[] args) throws Throwable {
		
		/*
		String sourceFile = "C:/GRADUAÇÃO/Workspace[Projeto I]/easyCorrectionModel/";
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
	    List<File> sourceFileList = new ArrayList<File>();
	    sourceFileList.add(new File(sourceFile));
	    Iterable<? extends JavaFileObject> compilationUnits = fileManager
	        .getJavaFileObjectsFromFiles(sourceFileList);
	    CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
	    boolean result = task.call();
	    if (result) {
	    	System.out.println("Compilation was successful");
	    } else {
	    	System.out.println("Compilation failed");
	    }
	    fileManager.close();*/
	    
	    SubmissionFileFilter pv = new SubmissionFileFilter();
		ArrayList<String> list = pv.visitAllDirsAndFiles(
				new File("C:/DESENVOLVIMENTO/easyCorrectionBackend/src/"));
		
		ArrayList<String> pathList = new ArrayList<String>();
		ArrayList<String> sourceFileList = new ArrayList<String>();
		for (String sourcePath: list){
			String path = sourcePath.substring(0, sourcePath.lastIndexOf("\\") + 1);
			if(!pathList.contains(path)){
				pathList.add(path);
			}
			sourceFileList.add(sourcePath);
		}
		
		ArrayList<String> listLib = pv.visitAllDirsAndFilesFindingJars(
				new File("C:/DESENVOLVIMENTO/easyCorrectionBackend/WebContent/WEB-INF/lib"));
		ArrayList<String> libFileList = new ArrayList<String>();
		for (String libPath: listLib){
			libFileList.add(libPath);
		}
		
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		
		try {
			String sourceDir = Teste.getDirectories(pathList);
			String libDir = Teste.getDirectories(libFileList);
			String sourceFileListed = Teste.getSourceFiles(sourceFileList);
			String[] arguments = new String[6 + sourceFileList.size()];
			
			arguments[0] = "-sourcepath";
			arguments[1] = sourceDir;
			arguments[2] = "-classpath";
			arguments[3] = libDir;
			int i = 4;
			for (String st: sourceFileList){
				arguments[i] = st;
				i++;
			}
			arguments[i] = "-d";
			arguments[i+1] = "C:/book/";
			
			javaCompiler.run(null, null, null, arguments);
			/*
			javaCompiler.run(null, null, null, "-sourcepath", sourceDir,
					"-classpath", libDir, sourceFileListed,
					"-d", "C:/book/");*/ // diretório de saída dos .class

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	    
	    /*
		Facade facade = new Facade();
		//facade.reinicializaBancoDeDados();
		//System.out.println(facade.getRoteirosLiberados());
	
		facade.getUsuario(1);
		System.out.println(facade.getUsuario(1));
		*/
		//Submissao sub = facade.getSubmissao(51);
		//facade.excluirSubmissao(sub);
		//easyCorrectionUtil.realizaDumpBD();
	}
	
	public static String getSourceFiles(ArrayList<String> pathList){
		String paths = "";
		for(String path: pathList){
			paths += path + ",";
		}
		return paths.substring(0, paths.length()-1);
	}
	
	public static String getDirectories(ArrayList<String> pathList){
		String paths = "";
		for(String path: pathList){
			paths += path + ";";
		}
		return paths;
	}

}

