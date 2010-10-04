package br.edu.les.easyCorrection.gerenciadores;

import java.util.Date;
import java.util.List;

import br.edu.les.easyCorrection.DAO.hibernate.DAOFactory;
import br.edu.les.easyCorrection.exceptions.CriacaoRoteiroException;
import br.edu.les.easyCorrection.pojo.roteiros.Roteiro;
import br.edu.les.easyCorrection.pojo.sistema.Periodo;
import br.edu.les.easyCorrection.util.MsgErros;

public class GerenciadorRoteiros {

	public Periodo getPeriodo(int periodoId){
		return DAOFactory.DEFAULT.buildPeriodoDAO().getById(periodoId);
	}
	
	public Roteiro getRoteiro(int roteiroId){
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(roteiroId);
	}

	/*
	 * Duvidas rever o mais rapido possivel
	 */
	@SuppressWarnings("deprecation")
	public Roteiro cadastrarRoteiro(Roteiro roteiroTemp) throws CriacaoRoteiroException {
		if(roteiroTemp.getNome()=="" || roteiroTemp.getNome() == null){
			throw new CriacaoRoteiroException(MsgErros.NOMEVAZIO.msg("Nome da atividade invalido. O Roteiro nao pode ser criado!"));			
		}
		Date dataAtual = new Date();
		if(roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Data de Liberacao invalida. O roteiro nao pode ser ser criado!"));
		}
		Date dataAux = roteiroTemp.getDataLiberacao();
		Date dataAux2 = roteiroTemp.getDataLiberacao();
		dataAux.setDate(dataAux.getDate()+7);
		dataAux2.setDate(dataAux2.getDate()+21);
		if(roteiroTemp.getNome() != null || roteiroTemp.getNome() != "" && !roteiroTemp.getDataLiberacao().before(dataAtual) || roteiroTemp.getDataLiberacao() != null 
				&& roteiroTemp.getDataFinalEntrega() == dataAux 
				&& roteiroTemp.getDataFinalDiscussao() == dataAux2
				&& roteiroTemp.getNumeroMaximoEnvios() == 3
				&& roteiroTemp.getPorcentagemTestesAutomaticos()==1
				&& roteiroTemp.getPenalidadeDiasAtraso() == 0.5
				&& roteiroTemp.getTempoLimiteTestes() == 10000){
			System.out.println("Roteiro criado com sucesso");			
		}
		if(roteiroTemp.getDataFinalEntrega().before(roteiroTemp.getDataLiberacao()) || roteiroTemp.getDataFinalEntrega() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Data Limite para Entrega anterior a Data de Liberacao. O Roteiro nao pode ser criado/atualizado!"));
		}
		if(roteiroTemp.getDataFinalDiscussao().before(roteiroTemp.getDataFinalEntrega()) || roteiroTemp.getDataFinalDiscussao() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Data Limite para Discussao anterior a Data Limite para Entrega. O Roteiro nao pode ser criado/atualizado!"));
		}
		if(roteiroTemp.getNumeroMaximoEnvios() <= 0 || roteiroTemp.getNumeroMaximoEnvios() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("O numero maximo de integrantes deve ser sempre maior ou igual a 1 integrante. O Roteiro nao pode ser criado/atualizado!"));
		}
		if(roteiroTemp.getNumeroMaximoEnvios() <= 0 || roteiroTemp.getNumeroMaximoEnvios() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("O numero maximo de envios deve ser sempre maior ou igual a 1. Roteiro nao pode ser criado/atualizado!"));
		}
		if(roteiroTemp.getPenalidadeDiasAtraso()< 0.0 || roteiroTemp.getPenalidadeDiasAtraso() > 10.0){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("A Penalidade por dia de atraso deve ser sempre maior ou igual a 0,0 e menor ou igual a 10,0. O Roteiro nao pode ser criado/atualizado!"));
		}
		if(roteiroTemp.getTempoLimiteTestes() < 0 || roteiroTemp.getTempoLimiteTestes() == null){
			throw new CriacaoRoteiroException(MsgErros.VALORINVALIDO.msg("Time-limit de execução dos testes por método inválido, deve ser sempre >= 0. O Roteiro nao pode ser criado/atualizado!"));
		}
		
		int aux = DAOFactory.DEFAULT.buildRoteiroDAO().save(roteiroTemp);
		return DAOFactory.DEFAULT.buildRoteiroDAO().getById(aux);
	}

	public void excluirRoteiro(Roteiro roteiro){
		DAOFactory.DEFAULT.buildRoteiroDAO().delete(roteiro);
	}

	public List<Roteiro> listarRoteiros() {
		return DAOFactory.DEFAULT.buildRoteiroDAO().findAll();
	}
	
	
}
