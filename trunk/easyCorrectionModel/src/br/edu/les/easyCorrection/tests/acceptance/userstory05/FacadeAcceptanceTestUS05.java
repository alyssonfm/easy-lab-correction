package br.edu.les.easyCorrection.tests.acceptance.userstory05;

import java.util.Calendar;

import br.edu.les.easyCorrection.exceptions.ExclusaoRoteiroException;
import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.tests.acceptance.userstory04.FacadeAcceptanceTestUS04;

public class FacadeAcceptanceTestUS05 extends FacadeAcceptanceTestUS04 {

	public FacadeAcceptanceTestUS05() {
		super();
	}

	/**
	 * Após o envio do anexo com a solução do ROteiro, será criada uma linha na
	 * tabela do BD como um log da submissão. A URL passada como parâmetro deve
	 * ter a seguinte estrutura:
	 * periodo<semestre>/submissoes/roteiro<roteiroId>/<nomeEquipe>/
	 * @throws Throwable 
	 */
	public int criarSubmissao(int roteiroId, int equipeId, int grupousuarioId,
			String url) throws Throwable {

		Submissao sub = new Submissao();
		sub.setDataSubmissao(Calendar.getInstance().getTime());

		EquipeHasUsuarioHasRoteiro eur = new EquipeHasUsuarioHasRoteiro();
		Equipe equipe = getEquipe(equipeId);
		Roteiro roteiro = getRoteiro(roteiroId);
		Usuario usuario = getGrupoUsuario(grupousuarioId).getUsuario();
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

	
}
