package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import java.io.IOException;
import java.io.OutputStream;

public class OurOutputStream extends OutputStream {

	private StringBuilder string;

	public OurOutputStream() {
		flushOurStream();
	}

	public void flushOurStream() {
		string = new StringBuilder();
	}
	
	public void write(int b) throws IOException {
		this.string.append((char) b);
	}

	public String toString() {
		return this.string.toString();
	}
}