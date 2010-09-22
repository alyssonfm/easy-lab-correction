package br.edu.les.easyCorrection.tests.acceptance.userstory02;

import java.util.ArrayList;
import java.util.List;

import br.edu.les.easyCorrection.tests.acceptance.Constants;
import br.edu.les.easyCorrection.tests.acceptance.extra.FacadeTestAcceptance;

import easyaccept.EasyAcceptFacade;

public class TesteRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        List<String> files = new ArrayList<String>();
        files.add(Constants.dirTestsAcceptance + "us2_cadastro.eas");
        FacadeTestAcceptance testFacade = new FacadeTestAcceptance();
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(testFacade, files);
        eaFacade.executeTests();
        System.out.println(eaFacade.getCompleteResults());
	}

}