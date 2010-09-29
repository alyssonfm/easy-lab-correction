package br.edu.les.easyCorrection.pojo.sistema;

public class Avaliacao {
	
	private Integer id;
	private Submissao submissao;
	private double notaAutomatica;
	private double notaCorrecao;
	private String resuladoExecucaoTestes;
	private double penalidade;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Submissao getSubmissao() {
		return submissao;
	}
	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
	}
	public double getNotaAutomatica() {
		return notaAutomatica;
	}
	public void setNotaAutomatica(double notaAutomatica) {
		this.notaAutomatica = notaAutomatica;
	}
	public double getNotaCorrecao() {
		return notaCorrecao;
	}
	public void setNotaCorrecao(double notaCorrecao) {
		this.notaCorrecao = notaCorrecao;
	}
	public String getResuladoExecucaoTestes() {
		return resuladoExecucaoTestes;
	}
	public void setResuladoExecucaoTestes(String resuladoExecucaoTestes) {
		this.resuladoExecucaoTestes = resuladoExecucaoTestes;
	}
	public double getPenalidade() {
		return penalidade;
	}
	public void setPenalidade(double penalidade) {
		this.penalidade = penalidade;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(notaAutomatica);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(notaCorrecao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(penalidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((resuladoExecucaoTestes == null) ? 0
						: resuladoExecucaoTestes.hashCode());
		result = prime * result
				+ ((submissao == null) ? 0 : submissao.hashCode());
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
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(notaAutomatica) != Double
				.doubleToLongBits(other.notaAutomatica))
			return false;
		if (Double.doubleToLongBits(notaCorrecao) != Double
				.doubleToLongBits(other.notaCorrecao))
			return false;
		if (Double.doubleToLongBits(penalidade) != Double
				.doubleToLongBits(other.penalidade))
			return false;
		if (resuladoExecucaoTestes == null) {
			if (other.resuladoExecucaoTestes != null)
				return false;
		} else if (!resuladoExecucaoTestes.equals(other.resuladoExecucaoTestes))
			return false;
		if (submissao == null) {
			if (other.submissao != null)
				return false;
		} else if (!submissao.equals(other.submissao))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	

}
