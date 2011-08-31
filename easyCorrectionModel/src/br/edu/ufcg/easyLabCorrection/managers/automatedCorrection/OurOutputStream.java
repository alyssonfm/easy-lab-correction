package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import java.io.IOException;
import java.io.OutputStream;

public class OurOutputStream extends OutputStream {

	private StringBuilder stringBuilder;

	public OurOutputStream() {
		flushOurStream();
	}

	public void flushOurStream() {
		stringBuilder = new StringBuilder();
	}
	
	public void write(int b) throws IOException {
		this.stringBuilder.append((char) b);
	}

	public String toString() {
		return this.stringBuilder.toString();
	}
}