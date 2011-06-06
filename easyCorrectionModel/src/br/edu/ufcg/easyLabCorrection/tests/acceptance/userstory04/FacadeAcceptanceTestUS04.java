package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory04;

import br.edu.ufcg.easyLabCorrection.exceptions.ObjectNotFoundException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory03.FacadeTestUS3Acceptance;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;

public class FacadeAcceptanceTestUS04 extends FacadeTestUS3Acceptance {

	public FacadeAcceptanceTestUS04() {
		super();
	}

	// ******************************************* SUBMISSOES
	// *****************************************

	/*
	 * todo getListaRoteiros getEquipes getUsuarios
	 * criaEquipeParaRoteiroComUsuario (com o m�ximo de integrantes)
	 * removerEquipeParaRoteiroComUsuario (remove se est� em alguma)
	 */

	/**
	 * Apenas a l�gica sabe como instanciar uma nova equipe
	 * @throws Throwable 
	 */
	public int criaEquipe() throws Throwable {
		Team e = new Team();
		Team equipeFinal = facadeSistema.cadastrarEquipe(e);
		return equipeFinal.getId();
	}

	/**
	 * Retorna a quantidade total de equipes no BD
	 * 
	 * @return int
	 * @throws Throwable
	 */
	public int getQuantidadeTotalEquipes() throws Throwable {
		return facadeSistema.getEquipes().size();
	}

	/**
	 * Retorna a quantidade de equipes alocadas para usu�rios em um determinado
	 * roteiro. Alocada significa que possui ao menos um usu�rio dentro dela
	 * 
	 * @param roteiroId
	 *            - id do roteiro no BD
	 * @return int com quantidade de equipes alocadas
	 * @throws Throwable
	 */
	public int getQuantidadeEquipesAlocadas(Integer roteiroId) throws Throwable {
		return facadeSistema.getEquipeAlocadas(roteiroId);
	}

	/**
	 * Retorna a quantidade de alunos em deteminada equipe para determinado
	 * roteiro, ao ver o tamanho da lista retornada no m�todo da facade do
	 * Sistema getAlunosPorEquipe
	 * 
	 * @param roteiroId
	 * @param equipeId
	 * @return int com a quantidade de alunos
	 * @throws Throwable 
	 */
	public int getQuantidadeAlunosPorEquipe(int equipeId, int roteiroId) throws Throwable {
		return facadeSistema.getEquipeHasUsuarioHasRoteiroPorEquipeERoteiro(equipeId, roteiroId).size();
	}

	/**
	 * Pesquisa a quantidade de alunos no sistema utilizando um m�todo mais
	 * gen�rico getUsuariosPorGrupo (int)
	 * 
	 * @return int com a quantidade de alunos do sistema
	 * @throws Throwable 
	 */
	public int getQuantidadeTotalAlunos() throws Throwable {
		return facadeSistema.listarGrupoUsuariosPorGrupo("Aluno").size();
	}

	/**
	 * Permite a mudan�a de equipe de um usu�rio. O usu�rio dever� sair da
	 * equipeDeSaida na qual est� alocado e ir para a equipeDeEntrada, caso
	 * obedeca a especifica��o de mudan�a de equipe. Maiores detalhes ver link
	 * abaixo.
	 * 
	 * @param usuarioId
	 * @param equipeDeSaidaID
	 * @param equipeDeEntradaID
	 * @param roteiroId
	 *            - roteiro ao qual a equipe faz parte
	 * @return id da nova equipe
	 * @throws Throwable 
	 * 
	 * @link 
	 *       https://sites.google.com/site/easylabcorrection/planejamento/planos-
	 *       de-iteracoes/iteracao-3/-plano-de-testes-de-aceitacao---us04
	 */
	public int mudarEquipe(int roteiroId, int equipeDeEntradaId, int grupoUsuarioId) throws Throwable {
		TeamHasUserHasAssignment teamHasUserHasAssignment = new TeamHasUserHasAssignment();
		try{
			getTeam(equipeDeEntradaId);
		}
		catch(Exception e){
			throw new ObjectNotFoundException(MsgErros.EQUIPE_INEXISTENTE
					.msg(""));
		}
		teamHasUserHasAssignment.setTeam(getTeam(equipeDeEntradaId));
		teamHasUserHasAssignment.setUser(getGrupoUsuario(grupoUsuarioId).getUser());
		teamHasUserHasAssignment.setAssignment(getRoteiro(roteiroId));
		return facadeSistema.mudarEquipe(teamHasUserHasAssignment).getTeam().getId();
	}

	
	/*
	 * UTIL
	 */
	public int getEquipeIDPorNome(String nome) throws Throwable {
		return facadeSistema.getEquipePorNome(nome).getId();
	}
	
	public Team getTeam(int idEquipe) throws Throwable {
		return facadeSistema.getEquipe(idEquipe);
	}

	public Submission getSubmissao(int submissaoId) throws Throwable{
		return facadeSistema.getSubmissao(submissaoId);
	}
	
	public int getAlunoGrupoID() {
		return 4;
	}
	
	public int somaECompara(int antes, int soma, int depois){
		return ((antes + soma) == depois)? 1 : 0;
	}

}
