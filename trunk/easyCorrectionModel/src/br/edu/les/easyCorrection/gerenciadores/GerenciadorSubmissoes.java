package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorSubmissoes extends GerenciadorRoteiros{

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
	
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(Integer idEquipe, Integer idRoteiro){
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().findByEquipeERoteiro(idEquipe, idRoteiro); 
	}
	
	public List<Equipe> getEquipes(){
		return DAOFactory.DEFAULT.buildEquipeDAO().findAll();
	}
	
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiros(){
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().findAll();
	}

	
	public Integer numeroSubmissoes(Submissao submissao) {
		List<Submissao> lista = DAOFactory.DEFAULT.buildSubmissaoDAO().findByEquipeERoteiro(submissao.getEquipeHasUsuarioHasRoteiro().getEquipe().getId(),
		submissao.getEquipeHasUsuarioHasRoteiro().getRoteiro().getId());
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
	
	
	public void verificaSeEquipePossuiMaximoParticipantes(EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException{
		List<EquipeHasUsuarioHasRoteiro> lista = getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(equr.getEquipe().getId(), equr.getRoteiro().getId());
		if(lista.size() >= equr.getRoteiro().getNumeroMaximoParticipantes()){
			throw new EasyCorrectionException(MsgErros.NUMERO_MAXIMO_PARTICIPANTES.msg(""));
		}
		
	}
	public EquipeHasUsuarioHasRoteiro cadastraEquipeHasUsuarioHasRoteiro(EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException {
		if (!easyCorrectionUtil.isNull(equr)) {
			verificaSeEquipePossuiMaximoParticipantes(equr);
			Integer id = DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().save(equr);
			equr.setId(id);
		}
		return equr;
	} 
	
	public void excluiEquipeHasRoteiroHasUsuario(EquipeHasUsuarioHasRoteiro equr) throws EasyCorrectionException{
		EquipeHasUsuarioHasRoteiro e = getEquipeHasUsuarioHasRoteiroPorUsuarioERoteiro(equr.getUsuario().getIdUsuario(), equr.getRoteiro().getId());
		e= (EquipeHasUsuarioHasRoteiro) SwapperAtributosReflect.swapObject(e, equr, EquipeHasUsuarioHasRoteiro.class);
		DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().delete(e);
	}
	
}
