package br.edu.les.easyCorrection.pojo.roteiros;

import br.edu.les.easyCorrection.pojo.acesso.Usuario;

public class EquipeHasUsuarioHasRoteiro {
	
	private ChaveCompostaTernariaInteger id;
	private Equipe equipe;
	private Roteiro roteiro;
	private Usuario usuario;
	
	public EquipeHasUsuarioHasRoteiro() {
		super();
	}
	
	public EquipeHasUsuarioHasRoteiro(Equipe equipe, Roteiro roteiro, Usuario usuario) {
		setId(new ChaveCompostaTernariaInteger(equipe.getId(), usuario.getIdUsuario(), roteiro.getId()));
	}
	
	
	public ChaveCompostaTernariaInteger getId() {
		return id;
	}
	public void setId(ChaveCompostaTernariaInteger id) {
		this.id = id;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public Roteiro getRoteiro() {
		return roteiro;
	}
	public void setRoteiro(Roteiro roteiro) {
		this.roteiro = roteiro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((roteiro == null) ? 0 : roteiro.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		EquipeHasUsuarioHasRoteiro other = (EquipeHasUsuarioHasRoteiro) obj;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roteiro == null) {
			if (other.roteiro != null)
				return false;
		} else if (!roteiro.equals(other.roteiro))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
