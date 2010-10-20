package br.edu.les.easyCorrection.gerenciadores;

import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ObjetoNaoEncontradoException;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.util.MsgErros;
import br.edu.les.easyCorrection.util.SwapperAtributosReflect;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class GerenciadorSubmissoes extends GerenciadorRoteiros{
	
	private GerenciadorRoteiros gerenciadorRoteiros;
	
	public GerenciadorSubmissoes(){
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
	
	public Equipe getEquipe(int id){
		Equipe equipe = DAOFactory.DEFAULT.buildEquipeDAO().getById(id);
		if(easyCorrectionUtil.isNull(equipe)){
			throw new ObjetoNaoEncontradoException(MsgErros.OBJ_NOT_FOUND.msg("equipe"));
		}
		return equipe;
	}
	
	public List<EquipeHasUsuarioHasRoteiro> getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(Integer idEquipe, Integer idRoteiro){
		return DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().findByEquipeERoteiro(idEquipe, idRoteiro); 
	}
	
	public void verificaSeUsuarioEstaCadastrado(EquipeHasUsuarioHasRoteiro eur) throws EasyCorrectionException{
		List<EquipeHasUsuarioHasRoteiro> lista = getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(eur.getEquipe().getId(), eur.getRoteiro().getId());
		boolean tem = false;
		boolean equipeIgual = false;
		for(EquipeHasUsuarioHasRoteiro equr:lista){
			if(equr.getUsuario().getIdUsuario().equals(eur.getUsuario().getIdUsuario())){
				tem = true;
				if(equr.getEquipe().getId().equals(eur.getEquipe().getId())){
					equipeIgual = true;
				}
			}
		}
		if(tem){
			if(equipeIgual){
				excluiEquipeHasRoteiroHasUsuario(eur);
			}
		}else{
			cadastraEquipeHasUsuarioHasRoteiro(eur);
		}
	}
	
	public int getEquipeAlocadas(Integer idRoteiro){
		if(!easyCorrectionUtil.isNull(idRoteiro)){
			throw new ObjetoNaoEncontradoException(MsgErros.ROTEIRO_INEXISTENTE.msg(""));
		}
		List<EquipeHasUsuarioHasRoteiro> lista = DAOFactory.DEFAULT.buildEquipeHasUsuarioHasRoteiroDAO().findByRoteiro(idRoteiro);
		for(EquipeHasUsuarioHasRoteiro equr:lista){
			if(!equr.getRoteiro().getId().equals(gerenciadorRoteiros.getRoteirosLiberado(equr.getRoteiro().getId()).getId())){
				throw new ObjetoNaoEncontradoException(MsgErros.ROTEIRO_NAO_LIBERADO.msg(equr.getRoteiro().getNome()));
			}
		}
		return lista.size();

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
