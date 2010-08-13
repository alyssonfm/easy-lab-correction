package br.edu.les.easyCorrection.sistema;


public class Teste2 {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Throwable {

		Facade facade = new Facade();
		/*
		
		Cota cota = facade.getCota("2010/2011");
		Professor p =facade.getProfessor("0.00000-1");

    	AreaConhecimento ac = new AreaConhecimento("00-11-0-1.3", "Ciencias Exatas");
    //	ac = facade.cadastrarAreaConhecimento(ac);
    	ac = facade.getAreaConhecimentoPorCodigo(ac.getCodigoArea());
	   	Projeto projeto2 = new Projeto(cota,p,"05",ac,"Sistema de Pibic 2");
	    //facade.cadastrarProjeto(projeto2, ".pdf");
		
		Alunos aluno = facade.getAluno(new Integer("001021028"));
		EnderecoContato end = new EnderecoContato("rua","bairro","cep","cidade","es");
		end.setNumero("151");
		AlunoProjeto ap = new AlunoProjeto(aluno,projeto2,"0123456789","julho de 2008",end);
		ap.setDataInicio(EPibicUtil.getDataNow());
	//	ap = facade.CadastrarAlunoProjeto(ap);

        Avaliador avaliador = facade.getAvaliadorPorUsuario(24);
        
        CoordenadorProjetoFomento cpp = new CoordenadorProjetoFomento(p,"Petrobras");
     	cpp.setEdital("XX");
     	cpp.setResumoProjeto("Gas natural");
     	cpp.setTituloProjeto("titualo qualquer");
     	cpp.setValorFinanciado(new Double(5.0000));
     	cpp.setInicioVigenciaProjeto(EPibicUtil.getDataNow());
     	cpp.setOrgaoFomento("CAPES");
     	cpp.setProfessor(p);
     
    //	cpp = facade.cadastrarCoordenacaoProjetoFormento(cpp);
     	
    	GrupoUsuario gu = facade.getGrupoUsuario(20);
    	ComiteExterno cExterno = new ComiteExterno(gu.getUsuario(),"externo@","area","instituicao","inst","titulo");
		cExterno.setIdAvaliador(0);
		cExterno.setCota(cota);
		List<Avaliador> avalsExterno = new LinkedList<Avaliador>();
		avalsExterno.add(cExterno);
		avalsExterno =  facade.cadastrarAvaliador(avalsExterno,gu.getGrupo());
    	Avaliacao ae = new Avaliacao(projeto2,cExterno);
    	ae.setParecer("qualquer");
    	ae = facade.cadastrarAvaliacao(ae);
    	*/

    	
	}
}
