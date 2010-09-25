package br.edu.les.easyCorrection.samples.hibernate.dao;

import java.io.Serializable;

public class UsuarioTeste implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int usCod;
	private String usSenha;
	private String usNome;
	private String usEmail;

	public UsuarioTeste() {
		super();
	}		 

	public UsuarioTeste(int usCod, String usSenha, String usNome, String usEmail) {
		this.usCod = usCod;
		this.usSenha = usSenha;
		this.usNome = usNome;
		this.usEmail = usEmail;
	}

	public int getUsCod() {
		return usCod;
	}

	public void setUsCod(int usCod) {
		this.usCod = usCod;
	}

	public String getUsSenha() {
		return usSenha;
	}

	public void setUsSenha(String usSenha) {
		this.usSenha = usSenha;
	}

	public String getUsNome() {
		return usNome;
	}

	public void setUsNome(String usNome) {
		this.usNome = usNome;
	}

	public String getUsEmail() {
		return usEmail;
	}

	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

}