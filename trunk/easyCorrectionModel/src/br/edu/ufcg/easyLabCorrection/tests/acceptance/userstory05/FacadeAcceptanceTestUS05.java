package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory05;

import java.util.Calendar;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission;
import br.edu.ufcg.easyLabCorrection.pojo.team.Team;
import br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment;
import br.edu.ufcg.easyLabCorrection.pojo.user.User;
import br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory04.FacadeAcceptanceTestUS04;

public class FacadeAcceptanceTestUS05 extends FacadeAcceptanceTestUS04 {

	public FacadeAcceptanceTestUS05() {
		super();
	}

	/**
	 * Ap�s o envio do anexo com a solu��o do ROteiro, ser� criada uma linha na
	 * tabela do BD como um log da submiss�o. A URL passada como par�metro deve
	 * ter a seguinte estrutura:
	 * periodo<semestre>/submissoes/roteiro<roteiroId>/<nomeEquipe>/
	 * @throws Throwable 
	 */
	public int criarSubmissao(int roteiroId, int equipeId, int grupousuarioId,
			String url) throws Throwable {

		Submission sub = new Submission();
		sub.setSubmissionDate(Calendar.getInstance().getTime());

		TeamHasUserHasAssignment tua = new TeamHasUserHasAssignment();
		Team equipe = getTeam(equipeId);
		Assignment roteiro = getRoteiro(roteiroId);
		User usuario = getGrupoUsuario(grupousuarioId).getUser();
		tua.setTeam(equipe);
		tua.setAssignment(roteiro);
		tua.setUser(usuario);
		tua.setId(0);
		sub.setTeamHasUserHasAssignment(tua);

		sub.setId(0);
		sub.setUrl(url);

		Submission resultado = facadeSistema.submeteRoteiro(sub);
		return resultado.getId();
	}

	public void excluirSubmissao(int submissaoId) throws Throwable {
		Submission sub = getSubmissao(submissaoId);
		facadeSistema.excluirSubmissao(sub);;
	}

	
}
