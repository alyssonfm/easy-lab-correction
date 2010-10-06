package br.edu.les.easyCorrection.tests.acceptance;

import java.util.ArrayList;
import java.util.List;

import br.edu.les.easyCorrection.tests.acceptance.userstory03.FacadeTestUS3Acceptance;

import easyaccept.EasyAcceptFacade;

public class TesteRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		EasyAcceptFacade eaFacade;

		// Teste US02
		// files.add(Constants.dirTestsAcceptanceUS2 + "us2_cadastro.eas");
		// FacadeTestAcceptance testFacade = new FacadeTestAcceptance();
		// eaFacade.executeTests();
		// System.out.println(eaFacade.getCompleteResults());

		// Teste US03
		files.add(Constants.dirTestsAcceptanceUS3 + "us3_CriacaoRoteiros.eas");
		FacadeTestUS3Acceptance testFacadeUS3 = new FacadeTestUS3Acceptance();
		eaFacade = new EasyAcceptFacade(testFacadeUS3, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}

}
