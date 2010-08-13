package br.edu.les.easyCorrection.util;

import java.io.Serializable;

public class EnderecoContato implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idEnderecoContato;
	/**
	 * Attribute endereco.
	 */
	private String rua;
	
	/**
	 * Attribute endereco.
	 */
	private String numero;
	
	private String complemento;

	/**
	 * Attribute bairro.
	 */
	private String bairro;

	/**
	 * Attribute cep.
	 */
	private String cep;

	/**
	 * Attribute cidade.
	 */
	private String cidade;

	/**
	 * Attribute estado.
	 */
	private String estado;

	
	private String fone1;
	
	private String fone2;
	
	private String celular;
	
	private String email1;
	
	private String email2;
	
	
	/**
	 * @param endereco
	 * @param bairro
	 * @param cep
	 * @param cidade
	 * @param estado
	 */
	public EnderecoContato(String endereco, String bairro, String cep, String cidade,
			String estado) {
		this.rua = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	/**
	 *
	 */
	public EnderecoContato() {	}

	/**
	 * @return the endereco
	 */
	public String getRua() {
		return rua;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setRua(String endereco) {

		this.rua = endereco;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	
	
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}



	public Integer getIdEnderecoContato() {
		return idEnderecoContato;
	}

	public void setIdEnderecoContato(Integer idEnderecoContato) {
		this.idEnderecoContato = idEnderecoContato;
	}

	
	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	
	public String toString(){
		return "["+getRua()+", "+getBairro()+", "+getCidade()+", "+getEstado()+", "+getCep()+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((email1 == null) ? 0 : email1.hashCode());
		result = prime * result + ((email2 == null) ? 0 : email2.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fone1 == null) ? 0 : fone1.hashCode());
		result = prime * result + ((fone2 == null) ? 0 : fone2.hashCode());
		result = prime
				* result
				+ ((idEnderecoContato == null) ? 0 : idEnderecoContato
						.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EnderecoContato)) {
			return false;
		}
		EnderecoContato other = (EnderecoContato) obj;
		if (bairro == null) {
			if (other.bairro != null) {
				return false;
			}
		} else if (!bairro.equals(other.bairro)) {
			return false;
		}
		if (celular == null) {
			if (other.celular != null) {
				return false;
			}
		} else if (!celular.equals(other.celular)) {
			return false;
		}
		if (cep == null) {
			if (other.cep != null) {
				return false;
			}
		} else if (!cep.equals(other.cep)) {
			return false;
		}
		if (cidade == null) {
			if (other.cidade != null) {
				return false;
			}
		} else if (!cidade.equals(other.cidade)) {
			return false;
		}
		if (complemento == null) {
			if (other.complemento != null) {
				return false;
			}
		} else if (!complemento.equals(other.complemento)) {
			return false;
		}
		if (email1 == null) {
			if (other.email1 != null) {
				return false;
			}
		} else if (!email1.equals(other.email1)) {
			return false;
		}
		if (email2 == null) {
			if (other.email2 != null) {
				return false;
			}
		} else if (!email2.equals(other.email2)) {
			return false;
		}
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
			return false;
		}
		if (fone1 == null) {
			if (other.fone1 != null) {
				return false;
			}
		} else if (!fone1.equals(other.fone1)) {
			return false;
		}
		if (fone2 == null) {
			if (other.fone2 != null) {
				return false;
			}
		} else if (!fone2.equals(other.fone2)) {
			return false;
		}
		if (idEnderecoContato == null) {
			if (other.idEnderecoContato != null) {
				return false;
			}
		} else if (!idEnderecoContato.equals(other.idEnderecoContato)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		if (rua == null) {
			if (other.rua != null) {
				return false;
			}
		} else if (!rua.equals(other.rua)) {
			return false;
		}
		return true;
	}

	
	
}
