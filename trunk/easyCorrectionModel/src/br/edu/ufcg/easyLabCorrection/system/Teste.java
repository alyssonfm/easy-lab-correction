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
import br.edu.ufcg.easyLabCorrection.managers.CompilationManager;
import br.edu.ufcg.easyLabCorrection.managers.TestManager;
import br.edu.ufcg.easyLabCorrection.util.CompilationFileFilter;

public class Teste {
	
	public static void main(String[] args) throws Throwable {
	    
		CompilationManager cm = new CompilationManager();
		TestManager tm = new TestManager();
		tm.executeTests("D:/TEMP/codigo/", "D:/TEMP/testes/");
		//cm.runJavaCompiler2(, , "D:/TEMP/lib/");
	    
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

}

