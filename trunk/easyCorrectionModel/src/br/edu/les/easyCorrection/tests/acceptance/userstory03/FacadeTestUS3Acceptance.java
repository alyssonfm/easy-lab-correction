package br.edu.les.easyCorrection.tests.acceptance.userstory03;

import java.util.Calendar;
import java.util.List;

import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class FacadeTestUS3Acceptance {
	
	private Facade facadeSistema;

	public FacadeTestUS3Acceptance() {
		facadeSistema = new Facade();
	}

// ******************************************* Criação de Roteiros *****************************************
	
	//EasyAcceptOK
	public Object getAtributoRoteiro(int id, String nomeAtributo) throws Throwable{
		Roteiro objRoteiro = getRoteiro(id);
		if (nomeAtributo.equals("periodo")){
			return easyCorrectionUtil.getAtributo(objRoteiro.getPeriodo(), nomeAtributo, false);
		}
		else{
			return easyCorrectionUtil.getAtributo(objRoteiro, nomeAtributo, false);
		}
	}
	
	public int cadastrarRoteiro(int periodoId, 
								String nome, 
								String descricao,
								String dataLiberacao,
								String dataFinalEntrega,
								String dataFinalDiscussao,
								int numeroMaximoEnvios,
								double penalidadeDiasAtraso,
								double porcentagemTestesAutomaticos,
								int tempoLimiteTestes,
								String diretorioInterface,
								String diretorioTestes,
								int numeroMaximoParticipantes,
								boolean bloqueado) throws Throwable{
			
			Roteiro roteiroTemp = new Roteiro();
			roteiroTemp.setPeriodo(facadeSistema.getPeriodo(periodoId));
			roteiroTemp.setId(0);
			roteiroTemp.setNome(nome); 
			roteiroTemp.setDescricao(descricao);
			roteiroTemp.setDataLiberacao(easyCorrectionUtil.formataData(dataLiberacao));
			roteiroTemp.setDataFinalEntrega(easyCorrectionUtil.formataData(dataFinalEntrega));
			roteiroTemp.setDataFinalDiscussao(easyCorrectionUtil.formataData(dataFinalDiscussao));
			roteiroTemp.setDiretorioInterface(diretorioInterface);
			roteiroTemp.setDiretorioTestes(diretorioTestes);
			roteiroTemp.setBloqueado(bloqueado);
			
			// doubleOnes
			roteiroTemp.setPenalidadeDiasAtraso(penalidadeDiasAtraso);
			roteiroTemp.setPorcentagemTestesAutomaticos(porcentagemTestesAutomaticos);
			
			// convencoes
			if (numeroMaximoEnvios == -1){
				roteiroTemp.setNumeroMaximoEnvios(null);
			}else{
				roteiroTemp.setNumeroMaximoEnvios(Integer.valueOf(numeroMaximoEnvios));
			}
			if (tempoLimiteTestes == -1){
				roteiroTemp.setTempoLimiteTestes(null);
			}else{
				roteiroTemp.setTempoLimiteTestes(Integer.valueOf(tempoLimiteTestes));
			}
			if (numeroMaximoParticipantes == -1){
				roteiroTemp.setNumeroMaximoParticipantes(null);
			}else{
				roteiroTemp.setNumeroMaximoParticipantes(Integer.valueOf(numeroMaximoParticipantes));
			}
			
			Roteiro roteiroCriado = facadeSistema.cadastrarRoteiro(roteiroTemp);
			return roteiroCriado.getId();
	}

	//EasyAcceptOK
	public int editarRoteiro(int roteiroId, 
							 int periodoId, 
							 String nome, 
							 String descricao,
							 String dataLiberacao,
							 String dataFinalEntrega,
							 String dataFinalDiscussao,
							 int numeroMaximoEnvios,
							 double penalidadeDiasAtraso,
							 double porcentagemTestesAutomaticos,
							 int tempoLimiteTestes,
							 String diretorioInterface,
							 String diretorioTestes,
							 int numeroMaximoParticipantes,
							 boolean isBloqueado) throws Throwable {
		
		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
		roteiroTemp.setId(roteiroId);
		roteiroTemp.setPeriodo(facadeSistema.getPeriodo(periodoId));
		roteiroTemp.setNome(nome);
		roteiroTemp.setDescricao(descricao);
		roteiroTemp.setDataLiberacao(easyCorrectionUtil.formataData(dataLiberacao));
		roteiroTemp.setDataFinalEntrega(easyCorrectionUtil.formataData(dataFinalEntrega));
		roteiroTemp.setDataFinalDiscussao(easyCorrectionUtil.formataData(dataFinalDiscussao));
		
		roteiroTemp.setDiretorioInterface(diretorioInterface);
		roteiroTemp.setDiretorioTestes(diretorioTestes);
		roteiroTemp.setBloqueado(isBloqueado);

		// doubleOnes
		roteiroTemp.setPenalidadeDiasAtraso(penalidadeDiasAtraso);
		roteiroTemp.setPorcentagemTestesAutomaticos(porcentagemTestesAutomaticos);
		
		// convencoes
		if (numeroMaximoEnvios == -1){
			roteiroTemp.setNumeroMaximoEnvios(null);
		}else{
			roteiroTemp.setNumeroMaximoEnvios(Integer.valueOf(numeroMaximoEnvios));
		}
		if (tempoLimiteTestes == -1){
			roteiroTemp.setTempoLimiteTestes(null);
		}else{
			roteiroTemp.setTempoLimiteTestes(Integer.valueOf(tempoLimiteTestes));
		}
		if (numeroMaximoParticipantes == -1){
			roteiroTemp.setNumeroMaximoParticipantes(null);
		}else{
			roteiroTemp.setNumeroMaximoParticipantes(Integer.valueOf(numeroMaximoParticipantes));
		}
		
		
		Roteiro rotAtualizado = facadeSistema.editarRoteiro(roteiroTemp);
		return rotAtualizado.getId();
	}
	
	//EasyAcceptOK
	public void liberarRoteiro(int roteiroId) throws Throwable {
		facadeSistema.liberarRoteiro(facadeSistema.getRoteiro(roteiroId));
	}
	
	//EasyAcceptOK
	public void bloquearRoteiro(int roteiroId) throws Throwable {
		facadeSistema.bloquearRoteiro(facadeSistema.getRoteiro(roteiroId));
	}

	//EasyAcceptOK
	public void excluirRoteiro(int idRoteiro) throws Throwable {
		facadeSistema.excluirRoteiro(facadeSistema.getRoteiro(idRoteiro));
	}
	
	//EasyAcceptOK
	public Object getAtributoListaRoteiro(int id, String nomeAtributo) throws Throwable{
		List<Roteiro> listaRoteiros = listarRoteiros();
		for(Roteiro Roteiro: listaRoteiros){
			if(Roteiro.getId() == id){
				return easyCorrectionUtil.getAtributo(Roteiro, nomeAtributo, false); 
			}
		}
		return "Roteiro Inexistente.";
	}

	//EasyAcceptOK
	public List<Roteiro> listarRoteiros() throws Throwable{
		List<Roteiro> listarRoteiros = facadeSistema.listarRoteiros();
		return listarRoteiros;
	}
	
	//EasyAcceptOK
	public Roteiro getRoteiro(int idRoteiro) throws Throwable {
		return facadeSistema.getRoteiro(idRoteiro);
	}
	
	
	// UTIL
	public String getDataAtual() {
		return (Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/" + (Calendar.YEAR + 1900));
	}

	// TODO
	public String getDiretorioTestes(){
		return "OPS";
	}
	
	// TODO
	public String getDiretorioTestesDiff(){
		return "OPS";
	}
	
	// TODO
	public String getPathInterfaceFileNotJava(){
		return "OPS";
	}
	
	// TODO
	public String getPathInterfaceFile(){
		return "OPS";
	}
}


