package br.edu.les.easyCorrection.tests.acceptance.userstory03;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.tests.acceptance.userstory02.FacadeAcceptanceTestUS02;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class FacadeTestUS3Acceptance extends FacadeAcceptanceTestUS02 {

	private Facade facadeSistema;

	public FacadeTestUS3Acceptance() {
		facadeSistema = new Facade();
	}

	// ******************************************* Criação de Roteiros
	// *****************************************

	public int cadastrarRoteiro(int periodoId, String nome, String descricao,
			String dataLiberacao, String dataFinalEntrega,
			String dataFinalDiscussao, int numeroMaximoEnvios,
			double penalidadeDiasAtraso, double porcentagemTestesAutomaticos,
			int tempoLimiteTestes, int numeroMaximoParticipantes,
			boolean bloqueado) throws Throwable {

		Roteiro roteiroTemp = new Roteiro();
		roteiroTemp.setPeriodo(facadeSistema.getPeriodo(periodoId));
		roteiroTemp.setId(0);
		roteiroTemp.setNome(nome);
		roteiroTemp.setDescricao(descricao);
		roteiroTemp.setDataLiberacao(translateData(dataLiberacao));
		roteiroTemp.setDataFinalEntrega(translateData(dataFinalEntrega));
		roteiroTemp.setDataFinalDiscussao(translateData(dataFinalDiscussao));

		roteiroTemp.setBloqueado(bloqueado);

		// doubleOnes
		roteiroTemp.setPenalidadeDiasAtraso(penalidadeDiasAtraso);
		roteiroTemp
				.setPorcentagemTestesAutomaticos(porcentagemTestesAutomaticos);

		// convencoes
		if (numeroMaximoEnvios == -1) {
			roteiroTemp.setNumeroMaximoEnvios(null);
		} else {
			roteiroTemp.setNumeroMaximoEnvios(Integer
					.valueOf(numeroMaximoEnvios));
		}
		if (tempoLimiteTestes == -1) {
			roteiroTemp.setTempoLimiteTestes(null);
		} else {
			roteiroTemp
					.setTempoLimiteTestes(Integer.valueOf(tempoLimiteTestes));
		}
		if (numeroMaximoParticipantes == -1) {
			roteiroTemp.setNumeroMaximoParticipantes(null);
		} else {
			roteiroTemp.setNumeroMaximoParticipantes(Integer
					.valueOf(numeroMaximoParticipantes));
		}

		Roteiro roteiroCriado = facadeSistema.cadastrarRoteiro(roteiroTemp);
		return roteiroCriado.getId();
	}

	// ******************************************* Edição de Roteiros
	// *****************************************

	// EasyAcceptOK
	public int editarRoteiro(int roteiroId, int periodoId, String nome,
			String descricao, String dataLiberacao, String dataFinalEntrega,
			String dataFinalDiscussao, int numeroMaximoEnvios,
			double penalidadeDiasAtraso, double porcentagemTestesAutomaticos,
			int tempoLimiteTestes, String diretorioInterface,
			String diretorioTestes, int numeroMaximoParticipantes,
			boolean isBloqueado) throws Throwable {

		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
		roteiroTemp.setId(roteiroId);
		roteiroTemp.setPeriodo(facadeSistema.getPeriodo(periodoId));
		roteiroTemp.setNome(nome);
		roteiroTemp.setDescricao(descricao);
		roteiroTemp.setDataLiberacao(translateData(dataLiberacao));
		roteiroTemp.setDataFinalEntrega(translateData(dataFinalEntrega));
		roteiroTemp.setDataFinalDiscussao(translateData(dataFinalDiscussao));

		roteiroTemp.setDiretorioInterface(translateDirectory(
				diretorioInterface, roteiroTemp));
		roteiroTemp.setDiretorioTestes(translateDirectory(diretorioTestes,
				roteiroTemp));
		roteiroTemp.setBloqueado(isBloqueado);

		// doubleOnes
		roteiroTemp.setPenalidadeDiasAtraso(penalidadeDiasAtraso);
		roteiroTemp
				.setPorcentagemTestesAutomaticos(porcentagemTestesAutomaticos);

		// convencoes
		if (numeroMaximoEnvios == -1) {
			roteiroTemp.setNumeroMaximoEnvios(null);
		} else {
			roteiroTemp.setNumeroMaximoEnvios(Integer
					.valueOf(numeroMaximoEnvios));
		}
		if (tempoLimiteTestes == -1) {
			roteiroTemp.setTempoLimiteTestes(null);
		} else {
			roteiroTemp
					.setTempoLimiteTestes(Integer.valueOf(tempoLimiteTestes));
		}
		if (numeroMaximoParticipantes == -1) {
			roteiroTemp.setNumeroMaximoParticipantes(null);
		} else {
			roteiroTemp.setNumeroMaximoParticipantes(Integer
					.valueOf(numeroMaximoParticipantes));
		}

		Roteiro rotAtualizado = facadeSistema.editarRoteiro(roteiroTemp);
		return rotAtualizado.getId();
	}

	// EasyAcceptOK
	public int liberarRoteiro(int idRoteiro) throws Throwable {
		Roteiro roteiroTemp = facadeSistema.getRoteiro(idRoteiro);

		Roteiro rotLiberado = facadeSistema.liberarRoteiro(roteiroTemp);
		return rotLiberado.getId();
	}

	// EasyAcceptOK
	public int bloquearRoteiro(int roteiroId) throws Throwable {
		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
		Roteiro rotBloqueado = facadeSistema.bloquearRoteiro(roteiroTemp, true);
		return rotBloqueado.getId();
	}

	// EasyAcceptOK
	public int desbloquearRoteiro(int roteiroId) throws Throwable {
		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
		Roteiro rotDesbloqueado = facadeSistema.bloquearRoteiro(roteiroTemp, false);
		return rotDesbloqueado.getId();
	}

	// EasyAcceptOK
	public void excluirRoteiro(int idRoteiro) throws Throwable {
		facadeSistema.excluirRoteiro(facadeSistema.getRoteiro(idRoteiro));
	}

	// ******************************************* UTIL
	// *****************************************

	// EasyAcceptOK
	public Object getAtributoRoteiro(int id, String nomeAtributo)
			throws Throwable {
		Roteiro objRoteiro = getRoteiro(id);
		if (nomeAtributo.equals("periodo")) {
			return easyCorrectionUtil.getAtributo(objRoteiro.getPeriodo(),
					nomeAtributo, false);
		} else {
			return easyCorrectionUtil.getAtributo(objRoteiro, nomeAtributo,
					false);
		}
	}

	// EasyAcceptOK
	public Object getAtributoListaRoteiro(int id, String nomeAtributo)
			throws Throwable {
		List<Roteiro> listaRoteiros = listarRoteiros();
		for (Roteiro Roteiro : listaRoteiros) {
			if (Roteiro.getId() == id) {
				return easyCorrectionUtil.getAtributo(Roteiro, nomeAtributo,
						false);
			}
		}
		return "Roteiro Inexistente.";
	}

	// EasyAcceptOK
	public List<Roteiro> listarRoteiros() throws Throwable {
		List<Roteiro> listarRoteiros = facadeSistema.listarRoteiros();
		return listarRoteiros;
	}

	// EasyAcceptOK
	public Roteiro getRoteiro(int idRoteiro) throws Throwable {
		return facadeSistema.getRoteiro(idRoteiro);
	}

	public Date translateData(String dataCodificada) {

		Calendar calendar = Calendar.getInstance();

		if (dataCodificada == null || dataCodificada.equals("")) {
			return null;
		} else if (dataCodificada.equalsIgnoreCase("-21_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, -7);
		} else if (dataCodificada.equalsIgnoreCase("-14_DIAS")) {
			calendar.add(Calendar.DAY_OF_MONTH, -7);
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

	public String translateDirectory(String dirCodificado, Roteiro rot) {
		String result;
		if (dirCodificado == null) {
			return null;
		} else if (dirCodificado.equals("TESTES_DIFF_DEFAULT")) {
			result = "periodo" + rot.getPeriodo().toString()
					+ "/testes/roteiroID/" + rot.getId() + "/";
		} else if (dirCodificado.equals("TESTES_DEFAULT_OK")) {
			result = "periodo" + rot.getPeriodo().toString()
					+ "/testes/roteiroID_" + rot.getId() + "/";
		} else if (dirCodificado.equals("INTERFACE_DIFF_DEFAULT")) {
			result = "periodo" + rot.getPeriodo().toString()
					+ "/INTERFACE/roteiroID_" + rot.getId() + "/";
		} else if (dirCodificado.equals("INTERFACE_DEFAULT_OK")) {
			result = "periodo" + rot.getPeriodo().toString()
					+ "/interface/roteiroID_" + rot.getId() + "/";
		} else {
			result = "";
		}
		return result;

	}
}
