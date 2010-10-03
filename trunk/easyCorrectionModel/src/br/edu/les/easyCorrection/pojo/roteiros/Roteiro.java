package br.edu.les.easyCorrection.pojo.roteiros;

import java.util.Date;

import br.edu.les.easyCorrection.pojo.sistema.Periodo;

public class Roteiro {
	
	private Integer id;
	private Periodo periodo;
	private String nome;
	private String descricao;
	private Date dataLiberacao;
	private Date dataFinalEntrega;
	private Date dataFinalDiscussao;
	private Integer numeroMaximoEnvios;
	private double penalidadeDiasAtraso;
	private double porcentagemTestesAutomaticos;
	private Integer tempoLimiteTestes;
	private String diretorioInterface;
	private String diretorioTestes;
	private String versaoInterface;
	private String versaoTestes;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataLiberacao() {
		return dataLiberacao;
	}
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}
	public Date getDataFinalEntrega() {
		return dataFinalEntrega;
	}
	public void setDataFinalEntrega(Date dataFinalEntrega) {
		this.dataFinalEntrega = dataFinalEntrega;
	}
	public Date getDataFinalDiscussao() {
		return dataFinalDiscussao;
	}
	public void setDataFinalDiscussao(Date dataFinalDiscussao) {
		this.dataFinalDiscussao = dataFinalDiscussao;
	}
	public Integer getNumeroMaximoEnvios() {
		return numeroMaximoEnvios;
	}
	public void setNumeroMaximoEnvios(Integer numeroMaximoEnvios) {
		this.numeroMaximoEnvios = numeroMaximoEnvios;
	}
	public double getPenalidadeDiasAtraso() {
		return penalidadeDiasAtraso;
	}
	public void setPenalidadeDiasAtraso(double penalidadeDiasAtraso) {
		this.penalidadeDiasAtraso = penalidadeDiasAtraso;
	}
	public double getPorcentagemTestesAutomaticos() {
		return porcentagemTestesAutomaticos;
	}
	public void setPorcentagemTestesAutomaticos(double porcentagemTestesAutomaticos) {
		this.porcentagemTestesAutomaticos = porcentagemTestesAutomaticos;
	}
	public Integer getTempoLimiteTestes() {
		return tempoLimiteTestes;
	}
	public void setTempoLimiteTestes(Integer tempoLimiteTestes) {
		this.tempoLimiteTestes = tempoLimiteTestes;
	}
	public String getDiretorioInterface() {
		return diretorioInterface;
	}
	public void setDiretorioInterface(String diretorioInterface) {
		this.diretorioInterface = diretorioInterface;
	}
	public String getDiretorioTestes() {
		return diretorioTestes;
	}
	public void setDiretorioTestes(String diretorioTestes) {
		this.diretorioTestes = diretorioTestes;
	}
	public String getVersaoInterface() {
		return versaoInterface;
	}
	public void setVersaoInterface(String versaoInterface) {
		this.versaoInterface = versaoInterface;
	}
	public String getVersaoTestes() {
		return versaoTestes;
	}
	public void setVersaoTestes(String versaoTestes) {
		this.versaoTestes = versaoTestes;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dataFinalDiscussao == null) ? 0 : dataFinalDiscussao
						.hashCode());
		result = prime
				* result
				+ ((dataFinalEntrega == null) ? 0 : dataFinalEntrega.hashCode());
		result = prime * result
				+ ((dataLiberacao == null) ? 0 : dataLiberacao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime
				* result
				+ ((diretorioInterface == null) ? 0 : diretorioInterface
						.hashCode());
		result = prime * result
				+ ((diretorioTestes == null) ? 0 : diretorioTestes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((numeroMaximoEnvios == null) ? 0 : numeroMaximoEnvios
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(penalidadeDiasAtraso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		temp = Double.doubleToLongBits(porcentagemTestesAutomaticos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((tempoLimiteTestes == null) ? 0 : tempoLimiteTestes
						.hashCode());
		result = prime * result
				+ ((versaoInterface == null) ? 0 : versaoInterface.hashCode());
		result = prime * result
				+ ((versaoTestes == null) ? 0 : versaoTestes.hashCode());
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
		Roteiro other = (Roteiro) obj;
		if (dataFinalDiscussao == null) {
			if (other.dataFinalDiscussao != null)
				return false;
		} else if (!dataFinalDiscussao.equals(other.dataFinalDiscussao))
			return false;
		if (dataFinalEntrega == null) {
			if (other.dataFinalEntrega != null)
				return false;
		} else if (!dataFinalEntrega.equals(other.dataFinalEntrega))
			return false;
		if (dataLiberacao == null) {
			if (other.dataLiberacao != null)
				return false;
		} else if (!dataLiberacao.equals(other.dataLiberacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diretorioInterface == null) {
			if (other.diretorioInterface != null)
				return false;
		} else if (!diretorioInterface.equals(other.diretorioInterface))
			return false;
		if (diretorioTestes == null) {
			if (other.diretorioTestes != null)
				return false;
		} else if (!diretorioTestes.equals(other.diretorioTestes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroMaximoEnvios == null) {
			if (other.numeroMaximoEnvios != null)
				return false;
		} else if (!numeroMaximoEnvios.equals(other.numeroMaximoEnvios))
			return false;
		if (Double.doubleToLongBits(penalidadeDiasAtraso) != Double
				.doubleToLongBits(other.penalidadeDiasAtraso))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (Double.doubleToLongBits(porcentagemTestesAutomaticos) != Double
				.doubleToLongBits(other.porcentagemTestesAutomaticos))
			return false;
		if (tempoLimiteTestes == null) {
			if (other.tempoLimiteTestes != null)
				return false;
		} else if (!tempoLimiteTestes.equals(other.tempoLimiteTestes))
			return false;
		if (versaoInterface == null) {
			if (other.versaoInterface != null)
				return false;
		} else if (!versaoInterface.equals(other.versaoInterface))
			return false;
		if (versaoTestes == null) {
			if (other.versaoTestes != null)
				return false;
		} else if (!versaoTestes.equals(other.versaoTestes))
			return false;
		return true;
	}
	
	
	
	
	
	

}
