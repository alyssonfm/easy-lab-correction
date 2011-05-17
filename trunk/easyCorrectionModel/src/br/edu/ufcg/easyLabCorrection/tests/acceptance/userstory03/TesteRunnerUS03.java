package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory03;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.tests.acceptance.Constants;
import easyaccept.EasyAcceptFacade;

public class TesteRunnerUS03 {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		EasyAcceptFacade eaFacade;

		files.add(Constants.dirAcceptanceTestsUS3 + "us3_CriacaoRoteiros.eas");
		files.add(Constants.dirAcceptanceTestsUS3 + "us3_EdicaoRoteiros.eas");
		FacadeTestUS3Acceptance testFacadeUS3 = new FacadeTestUS3Acceptance();
		eaFacade = new EasyAcceptFacade(testFacadeUS3, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}
}
