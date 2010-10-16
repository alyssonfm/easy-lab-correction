package br.edu.les.easyCorrection.tests.acceptance.userstory04;

import java.util.Calendar;
import java.util.List;

import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.pojo.roteiros.ChaveCompostaTernariaInteger;
import br.edu.les.easyCorrection.pojo.roteiros.Equipe;
import br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.roteiros.Submissao;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.tests.acceptance.userstory03.FacadeTestUS3Acceptance;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class FacadeAcceptanceTestUS04 extends FacadeTestUS3Acceptance {

	private Facade facadeSistema;

	public FacadeAcceptanceTestUS04() {
		super();
	}

	// ******************************************* SUBMISSOES
	// *****************************************

	public int criaEquipeParaRoteiro(int[] userIds, int equipeId,
			int roteiroId, String nomeEquipe) {

		Equipe equipeTemp = new Equipe();

		equipeTemp.setId(0);
		equipeTemp.setNome(nomeEquipe);

		EquipeHasUsuarioHasRoteiro equipeHasUsuarioHasRoteiro = new EquipeHasUsuarioHasRoteiro();
		ChaveCompostaTernariaInteger chaveComposta = new ChaveCompostaTernariaInteger();
		chaveComposta.setKey1(userIds[0]);
		chaveComposta.setKey2(equipeId);
		chaveComposta.setKey3(roteiroId);
		equipeHasUsuarioHasRoteiro.setId(chaveComposta);
		equipeHasUsuarioHasRoteiro.setEquipe(equipeTemp);
		equipeHasUsuarioHasRoteiro.setRoteiro(getRoteiro(roteiroId));
		equipeHasUsuarioHasRoteiro.setUsuario(getUsuario(userIds[0]));

		Equipe equipeCriada = facadeSistema.criaEquipeParaRoteiro(equipeTemp,
				equipeHasUsuarioHasRoteiro);
		return equipeCriada.getId();
	}

	public int submeteSolucaoParaRoteiro(int equipeHasUsuarioHasRoteiroId) {

		Submissao submissaoTemp = new Submissao();
		submissaoTemp.setId(0);
		submissaoTemp.setDataSubmissao(Calendar.getInstance().getTime());
		submissaoTemp.setEstado("ESTADO");
		submissaoTemp.setUrl("URL");
		submissaoTemp
				.setEquipeHasUsuarioHasRoteiro(getEquipeHasUsuarioHasRoteiro(equipeHasUsuarioHasRoteiroId));

		Submissao submissaoRealizada = facadeSistema
				.submeteSolucaoRoteiro(submissaoTemp);

		return submissaoRealizada.getId();
	}

	public EquipeHasUsuarioHasRoteiro getEquipeHasUsuarioHasRoteiro(
			int equipeHasUsuarioHasRoteiroId) {

		return facadeSistema
				.getEquipeHasUsuarioHasRoteiro(equipeHasUsuarioHasRoteiroId);
	}

	public Equipe getEquipe(int equipeId) {
		return facadeSistema.getEquipe(equipeId);
	}

}
