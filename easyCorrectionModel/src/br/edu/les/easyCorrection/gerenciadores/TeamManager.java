package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ObjectNotFoundException;
import br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class TeamManager extends Gerenciador{
	
	private GerenciadorRoteiros gerenciadorRoteiros;

	public TeamManager() {
		super();
		gerenciadorRoteiros = new GerenciadorRoteiros();
	}
	
	public Equipe getEquipePorNome(String nome) {
		List<Equipe> equipes = DAOFactory.DEFAULT.buildEquipeDAO().findByNome(
				nome);
		Equipe eq = equipes.get(0);
		if (easyCorrectionUtil.isNull(eq)) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND
					.msg("equipe"));
		}
		return eq;
	}
	
	public Equipe getEquipe(int id) {
		Equipe equipe = DAOFactory.DEFAULT.buildEquipeDAO().getById(id);
		if (easyCorrectionUtil.isNull(equipe)) {
			throw new ObjectNotFoundException(MsgErros.OBJ_NOT_FOUND.msg("equipe"));
		}
		return equipe;
	}
	
	public EquipeHasUsuarioHasRoteiro mudarEquipe(EquipeHasUsuarioHasRoteiro eur)
		throws EasyCorrectionException {
	
		if (eur.getRoteiro().getNumeroMaximoParticipantes() == 1) {
			throw new ObjectNotFoundException(MsgErros.ROTEIRO_INDIVIDUAL
					.msg(""));
		}
		if (eur.getRoteiro().getNumeroMaximoParticipantes() == getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
				eur.getEquipe().getId(), eur.getRoteiro().getId()).size()) {
			String[] params = { eur.getEquipe().getNome(),
					eur.getRoteiro().getNumeroMaximoParticipantes().toString() };
			throw new ObjectNotFoundException(
					MsgErros.EQUIPE_HAS_ROTEIRO_COMPLETA.msg(params));
		}
		try {
			getEquipe(eur.getEquipe().getId());
		} catch (Exception e) {
			throw new ObjectNotFoundException(MsgErros.EQUIPE_INEXISTENTE.msg(""));
		}
		
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

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(
			Integer idEquipe, Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByEquipeERoteiro(idEquipe, idRoteiro);
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorRoteiro(
			Integer idRoteiro) {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findByRoteiro(idRoteiro);
	}

	public int getEquipeAlocadas(Integer idRoteiro) {
		if (easyCorrectionUtil.isNull(idRoteiro) || idRoteiro < 1) {
			throw new ObjectNotFoundException(
					MsgErros.ID_ROTEIRO_INEXISTENTE.msg(""));
		}

		Roteiro roteiro = gerenciadorRoteiros.getRoteiro(idRoteiro);
		if (roteiro == null) {
			throw new ObjectNotFoundException(MsgErros.ROTEIRO_INEXISTENTE
					.msg(""));
		}

		List<EquipeHasUsuarioHasRoteiro> lista = DAOFactory.DEFAULT
				.buildEquipeHasUsuarioHasRoteiroDAO().findByRoteiro(idRoteiro);
		return lista.size();
	}

	public List<Equipe> getEquipes() {
		return DAOFactory.DEFAULT.buildEquipeDAO().findAll();
	}

	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiros() {
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO()
				.findAll();
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
	
	public void alocaEquipesParaAlunos(Roteiro rot, List<Equipe> equipes,
			List<GrupoUsuario> alunos) throws EasyCorrectionException {
		if (alunos.size() != 0){
			for (int i = 0; i < alunos.size(); i++) {
				EquipeHasUsuarioHasRoteiro eur = new EquipeHasUsuarioHasRoteiro();
				eur.setRoteiro(rot);
				eur.setEquipe(equipes.get(i));
				eur.setUsuario(alunos.get(i).getUsuario());
				cadastraEquipeHasUsuarioHasRoteiro(eur);
			}
		}
	}
}
