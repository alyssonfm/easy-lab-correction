package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.example;

import java.io.File;
import java.util.ArrayList;

import br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.OutputComparison;

public class OurTest {

	public static void main(String[] args) {

		OutputComparison output = new OutputComparison();
		ArrayList<Boolean> testVerdict = output.compareOutput(new File(""), new File("input.txt"),
				new File("output.txt"));
		
		for (int i = 0; i < testVerdict.size(); i++) {
			System.out.println("TC " + i + testVerdict.get(i));
		}
	}

}
