package br.edu.les.easyCorrection.util;

import java.io.Serializable;

public class Departamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribute departamento.
	 */
	private String nomeDepartamento;
	
	/**
	 * Attribute centro.
	 */
	private String centro;
	
	/**
	 * Attribute campus.
	 */
	private String campus;

	

	/**
	 * <p> 
	 * </p>
	 * @return departamento
	 */
	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	/**
	 * @param departamento new value for departamento 
	 */
	public void setNomeDepartamento(String departamento) {
		this.nomeDepartamento = departamento;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return centro
	 */
	public String getCentro() {
		return centro;
	}

	/**
	 * @param centro new value for centro 
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return campus
	 */
	public String getCampus() {
		return campus;
	}

	/**
	 * @param campus new value for campus 
	 */
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	
}
