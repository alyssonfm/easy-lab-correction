package br.edu.les.easyCorrection.gerenciadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorAvaliacoes extends Gerenciador {

	public GerenciadorAvaliacoes() {
		super();
	}

	/**
	 * Esse metodo retorna a lista de equipes (por roteiro) que nao tem corretores ainda   
	 * @param idRoteiro
	 * @return
	 */
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiro(
			Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiroECorretor(idRoteiro, 0);
	}
	
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiroAgrupadoPorEquipe(
			Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiroGroupByEquipe(idRoteiro);
	}

	/**
	 * Esse metodo retorna a lista de equipes (por roteiro) que jah possuem corretores
	 * @param idRoteiro
	 * @param idCorretor
	 * @return
	 */
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiroDoCorretor(
			Integer idRoteiro, Integer idCorretor) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiroECorretor(idRoteiro, idCorretor);
	}

	public List<Roteiro> getRoteirosFechados() {
		Date dataAtual = easyCorrectionUtil.getDataNow();
		List<Roteiro> roteirosFechados = DAOFactory.DEFAULT.buildRoteiroDAO()
				.findByRoteiroLiberado(dataAtual);
		return roteirosFechados;
	}

	public List<Usuario> getCorretores() {
		List<GrupoUsuario> professores = DAOFactory.DEFAULT
				.buildGrupoUsuarioDAO().findByGrupo(2);
		List<GrupoUsuario> monitores = DAOFactory.DEFAULT
				.buildGrupoUsuarioDAO().findByGrupo(3);
		List<GrupoUsuario> corretoresGrupo = professores;
		corretoresGrupo.addAll(monitores);
		ArrayList<Usuario> corretoresUsuarios = new ArrayList<Usuario>();
		for (GrupoUsuario gu : corretoresGrupo) {
			corretoresUsuarios.add(gu.getUsuario());
		}
		return corretoresUsuarios;
	}

}
