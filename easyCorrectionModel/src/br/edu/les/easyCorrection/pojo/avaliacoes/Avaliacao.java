package br.edu.les.easyCorrection.pojo.avaliacoes;

import java.util.Date;

import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;

public class Avaliacao {
	
	private Integer id;
	private Submissao submissao;
	private double notaAutomatica;
	private double notaCorrecao;
	private String resultadoExecucaoTestes;
	private double penalidade;
	private Date dataAvaliacao;
	private Usuario corretor;
	private boolean corrigido;

	public Avaliacao(Integer id, 
			Submissao submissao, 
			double notaAutomatica,
			double notaCorrecao, 
			String resuladoExecucaoTestes,
			double penalidade,
			Date dataAvaliacao,
			Usuario corretor) {
		
		
		this.id = id;
		this.submissao = submissao;
		this.notaAutomatica = notaAutomatica;
		this.notaCorrecao = notaCorrecao;
		this.resultadoExecucaoTestes = resuladoExecucaoTestes;
		this.penalidade = penalidade;
		this.dataAvaliacao = dataAvaliacao;
		this.corretor = corretor;
	}
	
	public Avaliacao() {
		super();
	}
	
	public boolean isCorrigido() {
		return corrigido;
	}

	public void setCorrigido(boolean corrigido) {
		this.corrigido = corrigido;
	}

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
	
	public String getResultadoExecucaoTestes() {
		return resultadoExecucaoTestes;
	}

	public void setResultadoExecucaoTestes(String resultadoExecucaoTestes) {
		this.resultadoExecucaoTestes = resultadoExecucaoTestes;
	}

	public double getPenalidade() {
		return penalidade;
	}
	public void setPenalidade(double penalidade) {
		this.penalidade = penalidade;
	}
	public Usuario getCorretor() {
		return corretor;
	}
	public void setCorretor(Usuario corretor) {
		this.corretor = corretor;
	}
	
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((corretor == null) ? 0 : corretor.hashCode());
		result = prime * result
				+ ((dataAvaliacao == null) ? 0 : dataAvaliacao.hashCode());
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
				+ ((resultadoExecucaoTestes == null) ? 0
						: resultadoExecucaoTestes.hashCode());
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
		if (corretor == null) {
			if (other.corretor != null)
				return false;
		} else if (!corretor.equals(other.corretor))
			return false;
		if (dataAvaliacao == null) {
			if (other.dataAvaliacao != null)
				return false;
		} else if (!dataAvaliacao.equals(other.dataAvaliacao))
			return false;
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
		if (resultadoExecucaoTestes == null) {
			if (other.resultadoExecucaoTestes != null)
				return false;
		} else if (!resultadoExecucaoTestes
				.equals(other.resultadoExecucaoTestes))
			return false;
		if (submissao == null) {
			if (other.submissao != null)
				return false;
		} else if (!submissao.equals(other.submissao))
			return false;
		return true;
	}

	
}
