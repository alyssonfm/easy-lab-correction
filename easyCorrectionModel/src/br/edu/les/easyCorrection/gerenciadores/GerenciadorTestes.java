package br.edu.les.easyCorrection.gerenciadores;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.textui.TestRunner;

import org.junit.runner.JUnitCore;

import br.edu.les.easyCorrection.exceptions.ExecucaoTestesException;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.servlet.ServletUpload;

public class GerenciadorTestes extends Gerenciador {

	private String resultadoErro = "";
	private boolean erroCompilacao = false;

	public GerenciadorTestes() {
		super();
	}

	public TestResult executarTestes(String diretorioTestes,
			String arquivoDeTeste, String diretorioInterface,
			String arquivoDeInterface, String diretorioSource,
			String arquivoSource) throws ExecucaoTestesException {

		String diretorioLib = (ServletUpload.local + "/").replace("/",
				File.separator);

		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		URLClassLoader cl;
		Class<?> testClass;

		JUnit4TestAdapter testAdapter;
		TestResult result;

		OutputStream out = new OutputStream() {
			public void write(int b) throws IOException {
				resultadoErro += (char) b;
			}
		};

		OutputStream erro = new OutputStream() {
			public void write(int b) throws IOException {
				erroCompilacao = true;
				resultadoErro += (char) b;
			}
		};

		try {
			javaCompiler.run(null, out, erro, "-sourcepath", diretorioSource
					+ ";" + diretorioInterface + ";" + diretorioTestes,
					"-classpath", diretorioLib + "junit.jar",
					diretorioInterface + arquivoDeInterface, diretorioSource
							+ arquivoSource, diretorioTestes + arquivoDeTeste,
					"-d", diretorioSource); // diret�rio de sa�da dos .class

			cl = new URLClassLoader(new URL[] { new File(diretorioSource)
					.toURI().toURL() }, JUnitCore.class.getClassLoader());
			testClass = cl.loadClass(arquivoDeTeste.substring(0, arquivoDeTeste
					.length() - 5));

			testAdapter = new JUnit4TestAdapter(testClass);
			result = TestRunner.run(testAdapter);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ExecucaoTestesException(e.getMessage());
		}

		if (erroCompilacao) {
			erroCompilacao = false;
			System.err.println(erro);
			throw new ExecucaoTestesException(erro.toString());
		}
		
		return result;
	}

	public String getSaidaDosTestes(TestResult result,
			EquipeHasUsuarioHasRoteiro equipeUsuarioRoteiro) {

		String relatorio = "";

		int quantTestesRodados = result.runCount();
		int erros = result.errorCount();
		int porcAcertos = ((quantTestesRodados - erros) * 100)
				/ quantTestesRodados;

		relatorio = "Relat�rio de Avalia��o: \n\n"
				+ "Total de Testes: "
				+ quantTestesRodados
				+ "\n"
				+ "Total de Erros: "
				+ erros
				+ "\n"
				+ "Porcentagem de Acertos: "
				+ porcAcertos
				+ " %\n"
				+ "Nota dos Testes Autom�ticos: "
				+ (porcAcertos * equipeUsuarioRoteiro.getRoteiro()
						.getPorcentagemTestesAutomaticos()) / 1000
				+ "\n\nConsole:\n";

		if (result.wasSuccessful()) {
			relatorio += "SUCESSO!";

		} else {
			Enumeration<TestFailure> failures = result.errors();
			for (int i = 0; i < result.errorCount(); i++) {
				relatorio += failures.nextElement().toString() + "\n";
			}
		}
		return relatorio;
	}

}
