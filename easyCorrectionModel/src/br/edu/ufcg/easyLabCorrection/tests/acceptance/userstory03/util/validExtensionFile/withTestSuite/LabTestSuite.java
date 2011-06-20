package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory03.util.validExtensionFile.withTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={
		TesteClasseA.class,
		TesteClasseB.class
	}
	)
public class LabTestSuite {
}