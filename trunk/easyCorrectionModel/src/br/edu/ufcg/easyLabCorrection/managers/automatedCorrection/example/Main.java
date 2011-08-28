package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.example;


public class Main {

	public static void main(String[] args) {
		MySolution s = new MySolution();
		int a = Integer.valueOf(args[0]);
		int b = Integer.valueOf(args[1]);
		System.out.println(s.sum(a, b));
	}
	
}
