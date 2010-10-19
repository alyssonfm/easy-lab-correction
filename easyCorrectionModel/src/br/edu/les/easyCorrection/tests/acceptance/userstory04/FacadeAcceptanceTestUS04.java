package br.edu.les.easyCorrection.tests.acceptance.userstory04;

import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
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
		return facadeSistema.criaEquipe();
	}

	/**
	 * Retorna a quantidade total de equipes no BD
	 * 
	 * @return int
	 */
	public int getQuantidadeEquipesTotal() {
		return facadeSistema.getTodasEquipes().size();
	}

	/**
	 * Retorna a quantidade de equipes alocadas para usu�rios em um determinado
	 * roteiro. Alocada significa que possui ao menos um usu�rio dentro dela
	 * 
	 * @param roteiroID
	 *            - id do roteiro no BD
	 * @return int com quantidade de equipes alocadas
	 */
	public int getQuantidadeEquipesAlocadas(int roteiroID) {
		return facadeSistema.getEquipesAlocadas(roteiroID).size();
	}

	/**
	 * Permite a mudan�a de equipe de um usu�rio. O usu�rio dever� sair da
	 * equipeDeSaida na qual est� alocado e ir para a equipeDeEntrada, caso
	 * obedeca a especifica��o de mudan�a de equipe. Maiores detalhes ver link
	 * abaixo.
	 * 
	 * @param usuarioID
	 * @param equipeDeSaidaID
	 * @param equipeDeEntradaID
	 * @param roteiroID
	 *            - roteiro ao qual a equipe faz parte
	 * @return id da nova equipe
	 * 
	 * @link 
	 *       https://sites.google.com/site/easylabcorrection/planejamento/planos-
	 *       de-iteracoes/iteracao-3/-plano-de-testes-de-aceitacao---us04
	 */
	public int mudarEquipe(int usuarioID, int equipeDeSaidaID,
			int equipeDeEntradaID, int roteiroID) {
		return facadeSistema.mudarEquipe(usuarioID, equipeDeSaidaID,
				equipeDeEntradaID, roteiroID);
	}

	/*
	 * url deve ser: periodo<semestre>/submissoes/roteiro<roteiroId>/<nomeEquipe>/
	 */

	public int setURLServidorParaSubmissao(int submissaoID, String novaURL) {

		Submissao submissaoAntiga = facadeSistema.getSubmissao(submissaoID);

		Submissao submissaoAtualizada = facadeSistema
				.setURLSubmissao(submissaoAntiga);

		return submissaoAtualizada.getId();
	}

	/*
	 * UTIL
	 */
	
	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiro(
			int equipeHasUsuarioHasRoteiroId) {

		return facadeSistema
				.getEquipeHasUsuarioHasRoteiro(equipeHasUsuarioHasRoteiroId);
	}

	public Equipe getEquipe(int equipeId) {
		return facadeSistema.getEquipe(equipeId);
	}
	
}
