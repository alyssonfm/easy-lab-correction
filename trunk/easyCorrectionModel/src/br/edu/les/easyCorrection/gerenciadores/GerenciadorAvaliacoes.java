package br.edu.les.easyCorrection.gerenciadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ObjetoNaoEncontradoException;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
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
		return DAOFactory.DEFAULT.buildRoteiroDAO().findByRoteiroFechado(
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
	
	public Avaliacao getAvaliacaoPorRoteiroEquipe(int idRoteiro, int idEquipe){
		List<Avaliacao> lista = DAOFactory.DEFAULT.buildAvaliacaoDAO().findByRoteiroEquipe(idRoteiro, idEquipe);
		if(lista.isEmpty()){
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND.msg("avaliacao"));
		}
		return lista.get(0);
	}
	
	public List<Avaliacao> getAvaliacaoPorRoteiroEquipeUnicos(Roteiro rot, Integer us) {
		List<Avaliacao> lista = DAOFactory.DEFAULT.buildAvaliacaoDAO()
				.findByEquipeERoteiroUnicos(rot.getId(), us);
		return lista;
	} 
	
	public Avaliacao salvarAvaliacao(Avaliacao avaliacao) throws EasyCorrectionException{
		try{
			Avaliacao aval = getAvaliacaoPorRoteiroEquipe(avaliacao.getSubmissao().getEquipeHasUsuarioHasRoteiro().getRoteiro().getId(), 
					avaliacao.getSubmissao().getEquipeHasUsuarioHasRoteiro().getEquipe().getId());
			aval.setNotaCorrecao(avaliacao.getNotaCorrecao());
			aval.setCorrigido(avaliacao.isCorrigido());
			return editarAvaliacao(aval);
		}
		catch(Exception e){
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND.msg("avaliacao"));
		}
	}
	
	public Avaliacao editarAvaliacao(Avaliacao avaliacao) throws EasyCorrectionException{
		
		DAOFactory.DEFAULT.buildAvaliacaoDAO().update(avaliacao);
		avaliacao.setId(avaliacao.getId());
		return avaliacao;
	}
	
	public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao){
		
		int id = DAOFactory.DEFAULT.buildAvaliacaoDAO().save(avaliacao);
		avaliacao.setId(id);
		return avaliacao;
	}
	
	public void excluirAvaliacao(Avaliacao avaliacao) throws EasyCorrectionException{
		Avaliacao aval = DAOFactory.DEFAULT.buildAvaliacaoDAO().getById(avaliacao.getId());
		aval= (Avaliacao) SwapperAtributosReflect.swapObject(aval, avaliacao,
				Avaliacao.class);
		DAOFactory.DEFAULT.buildAvaliacaoDAO().delete(aval);
	}
	
	public List<Avaliacao> getAvaliacaoPorSubmissao(int idSubmissao){
		return DAOFactory.DEFAULT.buildAvaliacaoDAO().findBySubmissao(idSubmissao);
		
	}

	public List<Avaliacao> getAvaliacoesPorRoteiro(Roteiro roteiro) {
		List<Avaliacao> lista = DAOFactory.DEFAULT.buildAvaliacaoDAO()
			.findByRoteiro(roteiro.getId());
		return lista;
	}



}
