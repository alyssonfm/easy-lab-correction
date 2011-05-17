package br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory03;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment;
import br.edu.ufcg.easyLabCorrection.system.Facade;
import br.edu.ufcg.easyLabCorrection.tests.acceptance.userstory02.FacadeAcceptanceTestUS02;
import br.edu.ufcg.easyLabCorrection.util.easyCorrectionUtil;

public class FacadeTestUS3Acceptance extends FacadeAcceptanceTestUS02 {

	public FacadeTestUS3Acceptance() {
		super();
	}

	// ******************************************* Cria��o de Roteiros
	// *****************************************

	public int cadastrarRoteiro(int periodoId, String nome, String descricao,
			String dataLiberacao, String dataFinalEntrega,
			String dataFinalDiscussao, int numeroMaximoEnvios,
			double penalidadeDiasAtraso, double porcentagemTestesAutomaticos,
			int tempoLimiteTestes, int numeroMaximoParticipantes,
			boolean bloqueado) throws Throwable {

		Assignment roteiroTemp = new Assignment();
		roteiroTemp.setPeriod(facadeSistema.getPeriodo(periodoId));
		roteiroTemp.setId(0);
		roteiroTemp.setName(nome);
		roteiroTemp.setDescription(descricao);
		roteiroTemp.setReleaseDate(translateData(dataLiberacao));
		roteiroTemp.setDeliveryDate(translateData(dataFinalEntrega));
		roteiroTemp.setDiscussionDate(translateData(dataFinalDiscussao));

//		roteiroTemp.setBloqueado(bloqueado);

		// doubleOnes
		roteiroTemp.setPenaltyLateDays(penalidadeDiasAtraso);
		roteiroTemp
				.setAutomaticTestsPercentage(porcentagemTestesAutomaticos);

		// convencoes
		if (numeroMaximoEnvios == -1) {
			roteiroTemp.setSendMaxNumber(null);
		} else {
			roteiroTemp.setSendMaxNumber(Integer
					.valueOf(numeroMaximoEnvios));
		}
		if (tempoLimiteTestes == -1) {
			roteiroTemp.setTestTimeLimit(null);
		} else {
			roteiroTemp
					.setTestTimeLimit(Integer.valueOf(tempoLimiteTestes));
		}
		if (numeroMaximoParticipantes == -1) {
			roteiroTemp.setParticipantsMaxNumber(null);
		} else {
			roteiroTemp.setParticipantsMaxNumber(Integer
					.valueOf(numeroMaximoParticipantes));
		}

		Assignment roteiroCriado = facadeSistema.cadastrarRoteiro(roteiroTemp);
		return roteiroCriado.getId();
	}

	// ******************************************* Edi��o de Roteiros
	// *****************************************

	// EasyAcceptOK
	public int editarRoteiro(int roteiroId, int periodoId, String nome,
			String descricao, String dataLiberacao, String dataFinalEntrega,
			String dataFinalDiscussao, int numeroMaximoEnvios,
			double penalidadeDiasAtraso, double porcentagemTestesAutomaticos,
			int tempoLimiteTestes, String diretorioInterface,
			String diretorioTestes, int numeroMaximoParticipantes,
			boolean isBloqueado) throws Throwable {

		Assignment roteiroTemp = facadeSistema.getRoteiro(roteiroId);
		roteiroTemp.setId(roteiroId);
		roteiroTemp.setPeriod(facadeSistema.getPeriodo(periodoId));
		roteiroTemp.setName(nome);
		roteiroTemp.setDescription(descricao);
		roteiroTemp.setReleaseDate(translateData(dataLiberacao));
		roteiroTemp.setDeliveryDate(translateData(dataFinalEntrega));
		roteiroTemp.setDiscussionDate(translateData(dataFinalDiscussao));

		roteiroTemp.setInterfaceDirectory(translateDirectory(
				diretorioInterface, roteiroTemp));
		roteiroTemp.setTestsDirectory(translateDirectory(diretorioTestes,
				roteiroTemp));
//		roteiroTemp.setBloqueado(isBloqueado);

		// doubleOnes
		roteiroTemp.setPenaltyLateDays(penalidadeDiasAtraso);
		roteiroTemp
				.setAutomaticTestsPercentage(porcentagemTestesAutomaticos);

		// convencoes
		if (numeroMaximoEnvios == -1) {
			roteiroTemp.setSendMaxNumber(null);
		} else {
			roteiroTemp.setSendMaxNumber(Integer
					.valueOf(numeroMaximoEnvios));
		}
		if (tempoLimiteTestes == -1) {
			roteiroTemp.setTestTimeLimit(null);
		} else {
			roteiroTemp
					.setTestTimeLimit(Integer.valueOf(tempoLimiteTestes));
		}
		if (numeroMaximoParticipantes == -1) {
			roteiroTemp.setParticipantsMaxNumber(null);
		} else {
			roteiroTemp.setParticipantsMaxNumber(Integer
					.valueOf(numeroMaximoParticipantes));
		}

		Assignment rotAtualizado = facadeSistema.editarRoteiro(roteiroTemp);
		return rotAtualizado.getId();
	}

	// EasyAcceptOK
//	public int liberarRoteiro(int idRoteiro) throws Throwable {
//		Roteiro roteiroTemp = facadeSistema.getRoteiro(idRoteiro);
//
//		Roteiro rotLiberado = facadeSistema.liberarRoteiro(roteiroTemp);
//		return rotLiberado.getId();
//	}

	// EasyAcceptOK
//	public int bloquearRoteiro(int roteiroId) throws Throwable {
//		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
//		Roteiro rotBloqueado = facadeSistema.bloquearRoteiro(roteiroTemp);
//		return rotBloqueado.getId();
//	}

	// EasyAcceptOK
//	public int desbloquearRoteiro(int roteiroId) throws Throwable {
//		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
//		Roteiro rotDesbloqueado = facadeSistema.desbloquearRoteiro(roteiroTemp);
//		return rotDesbloqueado.getId();
//	}

	// EasyAcceptOK
	public void excluirRoteiro(int idRoteiro) throws Throwable {
		facadeSistema.excluirRoteiro(facadeSistema.getRoteiro(idRoteiro));
	}

	// ******************************************* UTIL
	// *****************************************

	// EasyAcceptOK
	public Object getAtributoRoteiro(int id, String nomeAtributo)
			throws Throwable {
		Assignment objRoteiro = getRoteiro(id);
		if (nomeAtributo.equals("periodo")) {
			return easyCorrectionUtil.getAttribute(objRoteiro.getPeriod(),
					nomeAtributo, false);
		} else {
			return easyCorrectionUtil.getAttribute(objRoteiro, nomeAtributo,
					false);
		}
	}

	// EasyAcceptOK
	public Object getAtributoListaRoteiro(int id, String nomeAtributo)
			throws Throwable {
		List<Assignment> listaRoteiros = listarRoteiros();
		for (Assignment Roteiro : listaRoteiros) {
			if (Roteiro.getId() == id) {
				return easyCorrectionUtil.getAttribute(Roteiro, nomeAtributo,
						false);
			}
		}
		return "Roteiro Inexistente.";
	}

	// EasyAcceptOK
	public List<Assignment> listarRoteiros() throws Throwable {
		List<Assignment> listarRoteiros = facadeSistema.listarRoteiros();
		return listarRoteiros;
	}

	// EasyAcceptOK
	public Assignment getRoteiro(int idRoteiro) throws Throwable {
		return facadeSistema.getRoteiro(idRoteiro);
	}

	public Date translateData(String dataCodificada) {

		Calendar calendar = Calendar.getInstance();

		if (dataCodificada == null || dataCodificada.equals("")) {
			return null;
		} else if (dataCodificada.equalsIgnoreCase("-21_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, -21);
		} else if (dataCodificada.equalsIgnoreCase("-14_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, -14);
		} else if (dataCodificada.equalsIgnoreCase("-7_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, -7);
		} else if (dataCodificada.equalsIgnoreCase("+7_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, 7);
		} else if (dataCodificada.equalsIgnoreCase("+14_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, 14);
		} else if (dataCodificada.equalsIgnoreCase("+21_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, 21);
		} else {
			// AGORA novamente
		}
		return calendar.getTime();
	}

	public String translateDirectory(String dirCodificado, Assignment rot) {
		String result;
		if (dirCodificado == null) {
			return null;
		} else if (dirCodificado.equals("TESTES_DIFF_DEFAULT")) {
			result = "/periodo" + rot.getPeriod().toString()
					+ "/testes/roteiroID/" + rot.getId() + "/";
		} else if (dirCodificado.equals("TESTES_DEFAULT_OK")) {
			result = "/periodo" + rot.getPeriod().toString() + "/testes/"
					+ rot.getName() + "/";
		} else if (dirCodificado.equals("NOVO_TESTES_DEFAULT_OK")) {
			result = "/periodo" + rot.getPeriod().toString() + "/testes/"
					+ rot.getId() + "/";
		} else if (dirCodificado.equals("INTERFACE_DIFF_DEFAULT")) {
			result = "/periodo" + rot.getPeriod().toString()
					+ "/INTERFACE/roteiroID_" + rot.getId() + "/";
		} else if (dirCodificado.equals("INTERFACE_DEFAULT_OK")) {
			result = "/periodo" + rot.getPeriod().toString() + "/interface/"
					+ rot.getName() + "/";
		} else if (dirCodificado.equals("NOVA_INTERFACE_DEFAULT_OK")) {
			result = "/periodo" + rot.getPeriod().toString() + "/interface/"
					+ rot.getId() + "/";
		} else {
			result = "";
		}
		return result;

	}
}
