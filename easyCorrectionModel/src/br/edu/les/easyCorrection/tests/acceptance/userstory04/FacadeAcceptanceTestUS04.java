package br.edu.les.easyCorrection.tests.acceptance.userstory04;

import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.tests.acceptance.userstory03.FacadeTestUS3Acceptance;

public class FacadeAcceptanceTestUS04 extends FacadeTestUS3Acceptance {

	private Facade facadeSistema;

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
	 */
	public int criaEquipe() {
		// return facadeSistema.criaEquipe();
		return 0;
	}

	/**
	 * Retorna a quantidade total de equipes no BD
	 * 
	 * @return int
	 */
	public int getQuantidadeTotalEquipes() {
		// return facadeSistema.getTodasEquipes().size();
		return 5;
	}

	/**
	 * Retorna a quantidade de equipes alocadas para usu�rios em um determinado
	 * roteiro. Alocada significa que possui ao menos um usu�rio dentro dela
	 * 
	 * @param roteiroId
	 *            - id do roteiro no BD
	 * @return int com quantidade de equipes alocadas
	 */
	public int getQuantidadeEquipesAlocadas(int roteiroId) {
		// return facadeSistema.getEquipesAlocadas(roteiroId).size();
		return 0;
	}

	/**
	 * Retorna a quantidade de alunos em deteminada equipe para determinado
	 * roteiro, ao ver o tamanho da lista retornada no m�todo da facade do
	 * Sistema getAlunosPorEquipe
	 * 
	 * @param roteiroId
	 * @param equipeId
	 * @return int com a quantidade de alunos
	 */
	public int getQuantidadeAlunosPorEquipe(int roteiroId, int equipeId) {
		// return facadeSistema.getAlunosPorEquipe(roteiroId, equipeId).size();
		return 0;
	}

	/**
	 * Pesquisa a quantidade de alunos no sistema utilizando um m�todo mais
	 * gen�rico getUsuariosPorGrupo (int)
	 * 
	 * @return int com a quantidade de alunos do sistema
	 */
	public int getQuantidadeTotalAlunos() {
		//return facadeSistema.getUsuariosPorGrupo(4).size();
		return 0;
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
	 * 
	 * @link 
	 *       https://sites.google.com/site/easylabcorrection/planejamento/planos-
	 *       de-iteracoes/iteracao-3/-plano-de-testes-de-aceitacao---us04
	 */
	public int mudarEquipe(int roteiroId, int equipeDeSaidaId,
			int equipeDeEntradaId, int grupoUsuarioId) {
		// return facadeSistema.mudarEquipe(roteiroId, equipeDeSaidaId,
		// equipeDeEntradaId, grupoUsuarioId);
		return 0;
	}

	/**
	 * Ap�s o envio do anexo com a solu��o do ROteiro, ser� criada uma linha na
	 * tabela do BD como um log da submiss�o. A URL passada como par�metro deve
	 * ter a seguinte estrutura:
	 * periodo<semestre>/submissoes/roteiro<roteiroId>/<nomeEquipe>/
	 */
	public int criarSubmissao(int roteiroId, int equipeId, int grupoUsuarioId,
			String url) {
		// return facadeSistema.criarSubmissao(roteiroId, equipeId,
		// grupoUsuarioId, url);
		return 0;
	}

	public int excluirSubmissao (int submissaoId){
//		return facadeSistema.excluirSubmissao(submissaoId);
		return 0;
	}
	/*
	 * UTIL
	 */

	public Equipe getEquipe(int equipeId) {
		return facadeSistema.getEquipe(equipeId);
	}

	public int getAlunoGrupoID() {
		return 4;
	}

}
