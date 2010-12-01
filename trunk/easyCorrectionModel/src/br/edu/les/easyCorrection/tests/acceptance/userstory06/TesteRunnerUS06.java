package br.edu.les.easyCorrection.tests.acceptance.userstory06;

import java.util.ArrayList;
import java.util.List;

import br.edu.les.easyCorrection.tests.acceptance.Constants;
import easyaccept.EasyAcceptFacade;

public class TesteRunnerUS06 {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		EasyAcceptFacade eaFacade;

		// Teste US06
		files.add(Constants.dirAcceptanceTestsUS6
				+ "us06_SubmissaoRoteiros.eas");
		FacadeAcceptanceTestUS06 testFacadeUS06 = new FacadeAcceptanceTestUS06();
		eaFacade = new EasyAcceptFacade(testFacadeUS06, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}
}
