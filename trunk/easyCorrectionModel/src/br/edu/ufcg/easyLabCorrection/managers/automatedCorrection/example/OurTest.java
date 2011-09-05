package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.example;

import java.util.ArrayList;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.OutputComparison;

public class OurTest {

	public static void main(String[] args) throws EasyCorrectionException {

		OutputComparison output = new OutputComparison("", "input.txt");
		ArrayList<Boolean> testVerdict = output.compareOutput();
		
		for (int i = 0; i < testVerdict.size(); i++) {
			System.out.println("TC_" + i + ": " + testVerdict.get(i));
		}
	}

}
