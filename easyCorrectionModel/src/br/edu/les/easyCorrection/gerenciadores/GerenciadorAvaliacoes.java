package br.edu.les.easyCorrection.gerenciadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorAvaliacoes extends Gerenciador {

	public GerenciadorAvaliacoes() {
		super();
	}

	/**
	 * Esse metodo retorna a lista de equipes (por roteiro) que nao tem
	 * corretores ainda
	 * 
	 * @param idRoteiro
	 * @return
	 */
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiro(
			Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiroGroupByEquipe(idRoteiro);
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiroAgrupadoPorEquipe(
			Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiroGroupByEquipe(idRoteiro);
	}

	/**
	 * Esse metodo retorna a lista de equipes (por roteiro) que jah possuem
	 * corretores
	 * 
	 * @param idRoteiro
	 * @param idCorretor
	 * @return
	 */
	public List<Equipe> getEquipeHasUsuarioHasRoteiroPorRoteiroDoCorretor(
			Integer idRoteiro, Integer idCorretor) {
		return null;
		// TODO
	}

	public List<Roteiro> getRoteirosFechados() {
		Date dataAtual = easyCorrectionUtil.getDataNow();
		return DAOFactory.DEFAULT.buildRoteiroDAO().findByRoteiroLiberado(
				dataAtual);
	}

	public List<Usuario> getCorretores() {
		List<GrupoUsuario> corretoresGU;

		corretoresGU = DAOFactory.DEFAULT.buildGrupoUsuarioDAO().findByGrupo(2);
		corretoresGU.addAll(DAOFactory.DEFAULT.buildGrupoUsuarioDAO()
				.findByGrupo(3));

		ArrayList<Usuario> corretoresUsuarios = new ArrayList<Usuario>();
		for (GrupoUsuario gu : corretoresGU) {
			corretoresUsuarios.add(gu.getUsuario());
		}
		return corretoresUsuarios;
	}

	public List<Avaliacao> getAvaliacoesDoRoteiroSemCorretor(int roteiroId) {
		return this.getAvaliacoesDoRoteiroComCorretor(roteiroId, 0);
	}

	public List<Avaliacao> getAvaliacoesDoRoteiroComCorretor(int roteiroId,
			int corretorId) {

		if (corretorId <= 0) {
			return DAOFactory.DEFAULT.buildAvaliacaoDAO()
					.findByRoteiroSemCorretor(roteiroId);
		} else {
			return DAOFactory.DEFAULT.buildAvaliacaoDAO()
					.findByRoteiroComCorretor(roteiroId, corretorId);
		}
	}

}
