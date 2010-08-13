package br.edu.les.easyCorrection.util;

import java.io.Serializable;
import java.util.Date;

public class CarteiraIdentidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribute identidade.
	 */
	private String numeroRG;
	
	/**
	 * Attribute orgaoEmissor.
	 */
	private String orgaoEmissor;
	
	/**
	 * Attribute uf.
	 */
	private String ufEmissao;
	
	private Date dataEmissao; 


	/**
	 * <p> 
	 * </p>
	 * @return identidade
	 */
	public String getNumeroRG() {
		return numeroRG;
	}

	/**
	 * @param identidade new value for identidade 
	 */
	public void setNumeroRG(String identidade) {
		this.numeroRG = identidade;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return orgaoEmissor
	 */
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	/**
	 * @param orgaoEmissor new value for orgaoEmissor 
	 */
	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}
	
	
	
	public String getUfEmissao() {
		return ufEmissao;
	}

	public void setUfEmissao(String ufEmissao) {
		this.ufEmissao = ufEmissao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	
}
