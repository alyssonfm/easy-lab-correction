package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory05;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.tests.acceptance.Constants;
import easyaccept.EasyAcceptFacade;

public class TesteRunnerUS05 {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		EasyAcceptFacade eaFacade;

		// Teste US05
		files.add(Constants.dirAcceptanceTestsUS5
				+ "us05_SubmissaoRoteiros.eas");
		FacadeAcceptanceTestUS05 testFacadeUS05 = new FacadeAcceptanceTestUS05();
		eaFacade = new EasyAcceptFacade(testFacadeUS05, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}
}