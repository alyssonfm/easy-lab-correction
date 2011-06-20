package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory04;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.tests.acceptance.Constants;
import easyaccept.EasyAcceptFacade;

public class TesteRunnerUS04 {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		EasyAcceptFacade eaFacade;

		// Teste US04
		files.add(Constants.dirAcceptanceTestsUS4
				+ "us04_CriacaoEquipes.eas");
		FacadeAcceptanceTestUS04 testFacadeUS04 = new FacadeAcceptanceTestUS04();
		eaFacade = new EasyAcceptFacade(testFacadeUS04, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}
}