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
import br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.servlet.ServletUpload;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorTestes extends Gerenciador {

	private String resultadoErro = "";
	private boolean erroCompilacao = false;
	
	private GerenciadorAvaliacoes gerenciadorAvaliacoes;

	public GerenciadorTestes() {
		super();
		gerenciadorAvaliacoes = new GerenciadorAvaliacoes();
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
					"-d", diretorioSource); // diretório de saída dos .class

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
			Submissao submissao) {
		
		EquipeHasUsuarioHasRoteiro eur = submissao.getEquipeHasUsuarioHasRoteiro();
		String relatorio = "";

		int quantTestesRodados = result.runCount();
		int erros = result.errorCount();
		int porcAcertos = ((quantTestesRodados - erros) * 100)
				/ quantTestesRodados;
		double notaTestesAutomaticos = (porcAcertos * eur.getRoteiro()
				.getPorcentagemTestesAutomaticos()) / 1000;
		
		relatorio = "Relatório de Avaliação: \n\n"
				+ "Total de Testes: "
				+ quantTestesRodados
				+ "\n"
				+ "Total de Erros: "
				+ erros
				+ "\n"
				+ "Porcentagem de Acertos: "
				+ porcAcertos
				+ " %\n"
				+ "Nota dos Testes Automáticos: "
				+ notaTestesAutomaticos
				+ "\n\nConsole:\n";

		if (result.wasSuccessful()) {
			relatorio += "SUCESSO!";
		} else {
			Enumeration<TestFailure> failures = result.errors();
			for (int i = 0; i < result.errorCount(); i++) {
				relatorio += failures.nextElement().toString() + "\n";
			}
		}
		salvaAvaliacao(submissao, notaTestesAutomaticos, relatorio);
		return relatorio;
	}
	
	public Avaliacao salvaAvaliacao(Submissao submissao, double notaTestesAutomaticos, String resultadoTestesAutomaticos){
		try{
			Avaliacao aval = gerenciadorAvaliacoes.getAvaliacaoPorRoteiroEquipe(submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro().getId(), 
					submissao.getEquipeHasUsuarioHasRoteiro().getEquipe().getId());
			aval.setSubmissao(submissao);
			aval.setNotaAutomatica(notaTestesAutomaticos);
			aval.setDataAvaliacao(easyCorrectionUtil.getDataNow());
			aval.setResultadoExecucaoTestes(resultadoTestesAutomaticos);
			return gerenciadorAvaliacoes.editarAvaliacao(aval);
		}
		catch (Exception e) {
			
			Avaliacao aval = new Avaliacao(0, 
					submissao, 
					notaTestesAutomaticos, 
					0.0, 
					resultadoTestesAutomaticos, 
					0.0, 
					easyCorrectionUtil.getDataNow(),
					null);
			return gerenciadorAvaliacoes.cadastrarAvaliacao(aval);
		}	
		
	}

}
