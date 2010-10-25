package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.exceptions.ObjetoNaoEncontradoException;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorSubmissoes {

	private GerenciadorRoteiros gerenciadorRoteiros;

	public GerenciadorSubmissoes() {
		super();
		gerenciadorRoteiros = new GerenciadorRoteiros();
	}

	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
			Integer idUsuario, Integer idRoteiro) {
		List<EquipeHasUsuarioHasRoteiro> lista = DAOFactory.DEFAULT
				.buildEquipeHasUsuarioHasRoteiroDAO().findByUsuarioERoteiro(
						idUsuario, idRoteiro);
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	public Equipe getEquipe(int id) {
		Equipe equipe = DAOFactory.DEFAULT.buildEquipeDAO().getById(id);
		if (easyCorrectionUtil.isNull(equipe)) {
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND
					.msg("equipe"));
		}
		return equipe;
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
			Integer idEquipe, Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByEquipeERoteiro(idEquipe, idRoteiro);
	}

	public EquipeHasUsuarioHasRoteiro mudarEquipe(EquipeHasUsuarioHasRoteiro eur)
			throws EasyCorrectionException {

		EquipeHasUsuarioHasRoteiro equipeUsuarioRoteiro = getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
				eur.getUsuario().getIdUsuario(), eur.getRoteiro().getId());
		if (!easyCorrectionUtil.isNull(equipeUsuarioRoteiro)) {
			excluiEquipeHasRoteiroHasUsuario(equipeUsuarioRoteiro);
			equipeUsuarioRoteiro = cadastraEquipeHasUsuarioHasRoteiro(eur);
		} else {
			equipeUsuarioRoteiro = cadastraEquipeHasUsuarioHasRoteiro(eur);

		}
		return equipeUsuarioRoteiro;
	}

	public int getEquipeAlocadas(Integer idRoteiro){
		if (easyCorrectionUtil.isNull(idRoteiro)) {
			throw new ObjetoNaoEncontradoException(MsgErros.ROTEIRO_INEXISTENTE
					.msg(""));
		}
		
		Roteiro roteiro = gerenciadorRoteiros.getRoteiro(idRoteiro);
		if ( roteiro == null){
			throw new ObjetoNaoEncontradoException(MsgErros.ROTEIRO_INEXISTENTE
					.msg(""));
		}
		
		List<EquipeHasUsuarioHasRoteiro> lista = DAOFactory.DEFAULT
				.buildEquipeHasUsuarioHasRoteiroDAO().findByRoteiro(idRoteiro);

		if (lista.isEmpty()){
			throw new ObjetoNaoEncontradoException(
					MsgErros.ROTEIRO_NAO_LIBERADO.msg(roteiro.getNome()));	
		}
		
		for (EquipeHasUsuarioHasRoteiro equr : lista) {
			if (!equr.getRoteiro().getId().equals(gerenciadorRoteiros.getRoteiroLiberado(equr.getRoteiro().getId()).getId())) {
				throw new ObjetoNaoEncontradoException(
						MsgErros.ROTEIRO_NAO_LIBERADO.msg(equr.getRoteiro().getNome()));
			}
		}
		return lista.size();
	}

	public List<Equipe> getEquipes() {
		return DAOFactory.DEFAULT.buildEquipeDAO().findAll();
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiros() {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findAll();
	}

	public Integer numeroSubmissoes(Submissao submissao) {
		List<Submissao> lista = DAOFactory.DEFAULT.buildSubmissaoDAO()
				.findByEquipeERoteiro(
						submissao.getEquipeHasUsuarioHasRoteiro().getEquipe()
								.getId(),
						submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro()
								.getId());
		return lista.size();
	}

	public Submissao submeteRoteiro(Submissao submissao) {
		if (!easyCorrectionUtil.isNull(submissao)) {
			if (numeroSubmissoes(submissao) < (submissao
					.getEquipeHasUsuarioHasRoteiro().getRoteiro()
					.getNumeroMaximoEnvios())) {
				submissao.setDataSubmissao(easyCorrectionUtil.getDataNow());
				Integer id = DAOFactory.DEFAULT.buildSubmissaoDAO().save(
						submissao);
				submissao.setId(id);
			}
		}
		return submissao;
	}

	public void verificaSeEquipePossuiMaximoParticipantes(
			EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException {
		List<EquipeHasUsuarioHasRoteiro> lista = getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
				equr.getEquipe().getId(), equr.getRoteiro().getId());
		if (lista.size() >= equr.getRoteiro().getNumeroMaximoParticipantes()) {
			throw new EasyCorrectionException(
					MsgErros.NUMERO_MAXIMO_PARTICIPANTES.msg(""));
		}

	}

	public EquipeHasUsuarioHasRoteiro cadastraEquipeHasUsuarioHasRoteiro(
			EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(equr)) {
			verificaSeEquipePossuiMaximoParticipantes(equr);
			Integer id = DAOFactory.DEFAULT
					.buildEquipeHasUsuarioHasRoteiroDAO().save(equr);
			equr.setId(id);
		}
		return equr;
	}

	public Equipe cadastraEquipe(Equipe e) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(e)) {
			List<Equipe> equipes = getEquipes();
			if (equipes.isEmpty()) {
				e.setNome("Equipe 1");
				Integer id = DAOFactory.DEFAULT.buildEquipeDAO().save(e);
				e.setId(id);
			} else {
				int ultimoNumero = Integer.parseInt(equipes.get(
						equipes.size() - 1).getNome().split(" ")[1]) + 1;

				// TODO: essa comparacao com e.getNome() nao faz sentido pois o
				// nome eh gerado aqui, na logica, ela deveria estar depois de
				// e.setNome() abaixo
				List<Equipe> lista = DAOFactory.DEFAULT.buildEquipeDAO()
						.findByNome(e.getNome());
				if (lista.isEmpty()) {
					e.setNome("Equipe " + ultimoNumero);
					Integer id = DAOFactory.DEFAULT.buildEquipeDAO().save(e);
					e.setId(id);
				}
			}
		}
		return e;
	}

	public void excluiEquipeHasRoteiroHasUsuario(EquipeHasUsuarioHasRoteiro equr)
			throws EasyCorrectionException {
		EquipeHasUsuarioHasRoteiro e = getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(
				equr.getUsuario().getIdUsuario(), equr.getRoteiro().getId());
		e = (EquipeHasUsuarioHasRoteiro) SwapperAtributosReflect.swapObject(e,
				equr, EquipeHasUsuarioHasRoteiro.class);
		DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().delete(e);
	}

	public Submissao getSubmissao(int submissaoId) {
		return DAOFactory.DEFAULT.buildSubmissaoDAO().getById(submissaoId);
	}

	public void excluirSubmissao(Submissao sub) throws ExclusaoRoteiroException {
		if (sub == null) {
			throw new ExclusaoRoteiroException("Submissao inexistente!");
		}
		DAOFactory.DEFAULT.buildSubmissaoDAO().delete(sub);
	}

}
