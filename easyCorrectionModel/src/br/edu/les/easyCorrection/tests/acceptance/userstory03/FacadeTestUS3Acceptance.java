package br.edu.les.easyCorrection.tests.acceptance.userstory03;

import java.util.List;

import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.sistema.Facade;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class FacadeTestUS3Acceptance {
	
	private Facade facadeSistema;

	public FacadeTestUS3Acceptance() {
		facadeSistema = new Facade();
	}

// ******************************************* Cria��o de Roteiros *****************************************
	
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
	
	//EasyAcceptOK
	public int cadastrarRoteiro(int periodoId, String nome, String login, String senha, String email) throws Throwable{
		Roteiro roteiroTemp = new Roteiro();
		roteiroTemp.setPeriodo(facadeSistema.getPeriodo(periodoId));
		Periodo periodoTemp = new Periodo();
		periodoTemp.setId(0);
		periodoTemp.setSemestre("2010.2");
		Roteiro roteiroCriado = facadeSistema.cadastrarRoteiro(roteiroTemp);
		return roteiroCriado.getId();
	}

	//EasyAcceptOK
	public int editarRoteiro(int roteiroId, 
								int periodoId,
								String nome, 
								String login, 
								String senha,
								String email) throws Throwable {
		
		Roteiro roteiroTemp = facadeSistema.getRoteiro(roteiroId);
		roteiroTemp.setId(roteiroId);
		roteiroTemp.setPeriodo(facadeSistema.getPeriodo(periodoId));
		roteiroTemp.setNome(nome);
		roteiroTemp.setDiretorioTestes(login);
		roteiroTemp.setDiretorioInterface(senha);
		Roteiro rotAtualizado = facadeSistema.cadastrarRoteiro(roteiroTemp);
		return rotAtualizado.getId();
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
	
}

