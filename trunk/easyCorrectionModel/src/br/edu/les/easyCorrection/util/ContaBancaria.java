package br.edu.les.easyCorrection.util;

import java.io.Serializable;

public class ContaBancaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribute banco.
	 */
	private String nomeBanco;
	
	/**
	 * Attribute agencia.
	 */
	private String codigoAgencia;
	
	/**
	 * Attribute conta.
	 */
	private String numeroConta;
	
	
	public ContaBancaria() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param banco
	 * @param agencia
	 * @param conta
	 */
	public ContaBancaria(String banco, String agencia, String conta) {
		super();
		this.nomeBanco = banco;
		this.codigoAgencia = agencia;
		this.numeroConta = conta;
	}


	public String getNomeBanco() {
		return nomeBanco;
	}


	public void setNomeBanco(String banco) {
		this.nomeBanco = banco;
	}


	public String getCodigoAgencia() {
		return codigoAgencia;
	}


	public void setCodigoAgencia(String agencia) {
		this.codigoAgencia = agencia;
	}


	public String getNumeroConta() {
		return numeroConta;
	}


	public void setNumeroConta(String conta) {
		this.numeroConta = conta;
	}
	
	

}
