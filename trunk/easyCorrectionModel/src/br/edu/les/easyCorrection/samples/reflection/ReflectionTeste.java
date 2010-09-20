package br.edu.les.easyCorrection.samples.reflection;

import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
   
public class ReflectionTeste {
	
	public static String caminhoClasseTestada = "br.edu.les.easyCorrection.samples.reflection.Classe1"; 

	public static void main(String args[]) {
		
		String caminhoClasseTeste = "br.edu.les.easyCorrection.samples.reflection.TesteUnit";
		
		try{
			Result r = org.junit.runner.JUnitCore.runClasses(Class.forName(caminhoClasseTeste));
			List <Failure> failures = r.getFailures();

			for ( Failure f : failures ) {
				System.out.println( f.getTestHeader() );
				System.out.println( f.getMessage() );
			}
		}
		catch (Throwable e) {
			System.err.println(e);
		}
	}
} 
