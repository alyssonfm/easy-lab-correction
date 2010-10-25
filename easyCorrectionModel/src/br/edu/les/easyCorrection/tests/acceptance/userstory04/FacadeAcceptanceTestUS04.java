package br.edu.les.easyCorrection.tests.acceptance.userstory04;

import java.util.Calendar;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.tests.acceptance.userstory03.FacadeTestUS3Acceptance;

public class FacadeAcceptanceTestUS04 extends FacadeTestUS3Acceptance {

	private Facade facadeSistema;

	public FacadeAcceptanceTestUS04() {
		super();
		facadeSistema = new Facade();
	}

	// ******************************************* SUBMISSOES
	// *****************************************

	/*
	 * todo getListaRoteiros getEquipes getUsuarios
	 * criaEquipeParaRoteiroComUsuario (com o máximo de integrantes)
	 * removerEquipeParaRoteiroComUsuario (remove se está em alguma)
	 */

	/**
	 * Apenas a lógica sabe como instanciar uma nova equipe
	 * @throws EasyCorrectionException 
	 */
	public int criaEquipe() throws EasyCorrectionException {
		Equipe e = new Equipe();
		Equipe equipeFinal = facadeSistema.cadastrarEquipe(e);
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
	 * Retorna a quantidade de equipes alocadas para usuários em um determinado
	 * roteiro. Alocada significa que possui ao menos um usuário dentro dela
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
	 * roteiro, ao ver o tamanho da lista retornada no método da facade do
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
	 * Pesquisa a quantidade de alunos no sistema utilizando um método mais
	 * genérico getUsuariosPorGrupo (int)
	 * 
	 * @return int com a quantidade de alunos do sistema
	 * @throws Throwable 
	 */
	public int getQuantidadeTotalAlunos() throws Throwable {
		return facadeSistema.listarUsuarios().size();
	}

	/**
	 * Permite a mudança de equipe de um usuário. O usuário deverá sair da
	 * equipeDeSaida na qual está alocado e ir para a equipeDeEntrada, caso
	 * obedeca a especificação de mudança de equipe. Maiores detalhes ver link
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
	public EquipeHasUsuarioHasRoteiro mudarEquipe(int roteiroId, int equipeDeEntradaId, int grupoUsuarioId) throws Throwable {
		EquipeHasUsuarioHasRoteiro equipeUsuarioRoteiro = new EquipeHasUsuarioHasRoteiro();
		equipeUsuarioRoteiro.setEquipe(getEquipe(equipeDeEntradaId));
		equipeUsuarioRoteiro.setUsuario(getGrupoUsuario(grupoUsuarioId).getUsuario());
		equipeUsuarioRoteiro.setRoteiro(getRoteiro(roteiroId));
		return facadeSistema.mudarEquipe(equipeUsuarioRoteiro);
	}

	/**
	 * Após o envio do anexo com a solução do ROteiro, será criada uma linha na
	 * tabela do BD como um log da submissão. A URL passada como parâmetro deve
	 * ter a seguinte estrutura:
	 * periodo<semestre>/submissoes/roteiro<roteiroId>/<nomeEquipe>/
	 * @throws Throwable 
	 */
	public int criarSubmissao(int roteiroId, int equipeId, int usuarioId,
			String url) throws Throwable {

		Submissao sub = new Submissao();
		sub.setDataSubmissao(Calendar.getInstance().getTime());

		EquipeHasUsuarioHasRoteiro eur = new EquipeHasUsuarioHasRoteiro();
		Equipe equipe = getEquipe(equipeId);
		Roteiro roteiro = getRoteiro(roteiroId);
		Usuario usuario = getUsuario(usuarioId);
		eur.setEquipe(equipe);
		eur.setRoteiro(roteiro);
		eur.setUsuario(usuario);
		eur.setId(0);
		sub.setEquipeHasUsuarioHasRoteiro(eur);

		sub.setId(0);
		sub.setUrl(url);

		Submissao resultado = facadeSistema.submeteRoteiro(sub);
		return resultado.getId();
	}

	public void excluirSubmissao(int submissaoId) throws ExclusaoRoteiroException {
		Submissao sub = getSubmissao(submissaoId);
		facadeSistema.excluirSubmissao(sub);;
	}

	/*
	 * UTIL
	 */

	public Equipe getEquipePorNome(String nome) throws Throwable {
		return facadeSistema.getEquipePorNome(nome);
	}

	public Submissao getSubmissao(int submissaoId){
		return facadeSistema.getSubmissao(submissaoId);
	}
	
	public int getAlunoGrupoID() {
		return 4;
	}
	
	public int somaECompara(int antes, int soma, int depois){
		return ((antes + soma) == depois)? 1 : 0;
	}

}
