package br.edu.les.easyCorrection.pojo.roteiros;

import java.util.Date;

public class Submissao {

	private Integer id;
	private Date dataSubmissao;
	private EquipeHasUsuarioHasRoteiro equipeHasUsuarioHasRoteiro;
	private String url;
	private String estado;
	
	
	public Submissao(Integer id, 
			Date dataSubmissao,
			EquipeHasUsuarioHasRoteiro equipeHasUsuarioHasRoteiro, 
			String url,
			String estado) {
		super();
		this.id = id;
		this.dataSubmissao = dataSubmissao;
		this.equipeHasUsuarioHasRoteiro = equipeHasUsuarioHasRoteiro;
		this.url = url;
		this.estado = estado;
	}
	
	public Submissao() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getDataSubmissao() {
		return dataSubmissao;
	}
	public void setDataSubmissao(Date dataSubmissao) {
		this.dataSubmissao = dataSubmissao;
	}
	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiro() {
		return equipeHasUsuarioHasRoteiro;
	}
	public void setEquipeHasUsuarioHasRoteiro(
			EquipeHasUsuarioHasRoteiro equipeHasUsuarioHasRoteiro) {
		this.equipeHasUsuarioHasRoteiro = equipeHasUsuarioHasRoteiro;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataSubmissao == null) ? 0 : dataSubmissao.hashCode());
		result = prime
				* result
				+ ((equipeHasUsuarioHasRoteiro == null) ? 0
						: equipeHasUsuarioHasRoteiro.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submissao other = (Submissao) obj;
		if (dataSubmissao == null) {
			if (other.dataSubmissao != null)
				return false;
		} else if (!dataSubmissao.equals(other.dataSubmissao))
			return false;
		if (equipeHasUsuarioHasRoteiro == null) {
			if (other.equipeHasUsuarioHasRoteiro != null)
				return false;
		} else if (!equipeHasUsuarioHasRoteiro
				.equals(other.equipeHasUsuarioHasRoteiro))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	
	
}
