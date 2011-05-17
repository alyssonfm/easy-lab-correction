package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.tests.acceptance.Constants;
import easyaccept.EasyAcceptFacade;

public class TesteRunner {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		EasyAcceptFacade eaFacade;

		// Teste US02
		files.add(Constants.dirAcceptanceTestsUS2 + "us2_cadastro.eas");
		FacadeAcceptanceTestUS02 testFacade = new FacadeAcceptanceTestUS02();
		eaFacade = new EasyAcceptFacade(testFacade, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());

	}
}
