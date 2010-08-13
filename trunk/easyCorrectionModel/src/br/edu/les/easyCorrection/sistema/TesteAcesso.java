package br.edu.les.easyCorrection.sistema;



public class TesteAcesso {

	
	
	public static void main(String[] args) throws Throwable {
		Facade facade = new Facade();
	//@SuppressWarnings("unused")
	//	GerenciadorCoordenacaoPibic g = new GerenciadorCoordenacaoPibic();
	//    System.out.println(DAOFactory.DEFAULT.buildPermissaoDAO().findByGrupoAndFuncao(2, 2));
	
	    /*MenuPibic m = new MenuPibic();
		m.setNome("Outros");
		m.setRotulo("Outros");
		*/
		
		/*Usuario u = facade.getUsuario(22);
		u.setSenha("123");
		List <Funcao> lista = facade.validaUsuario(u);
		System.out.println(lista);
	*/
		/*List <Agenda> a = g.verificaAgenda(54);
		System.out.println(a);*/
		
		//List <ComiteInstitucional> lista = facade.consultarComiteInstitucionalPorCota(1);
	//	System.out.println(lista);
		
		
/*		Funcao f = new Funcao();
		MenuPibic m = facade.getMenu(2);
		f.setMenu(m);
		f.setNome("cadastroProfessor2");
		f.setRotulo("Cadastro de Professor2");
		f.setIdFuncao(56);
		f = facade.cadastrarFuncao(f);
*/	
		
/*		Grupo g = new Grupo();
		g.setNome("Alunos");
		g = facade.cadastrarGrupo(g);
*/
		
/*		Usuario u = new Usuario();
		u.setLogin("p");
		u.setNome("Palloma Alencar");
		u.setSenha("123");
		u = facade.cadastrarUsuario(u);
		
/*		GrupoUsuario gU = new GrupoUsuario();
		Grupo g = facade.getGrupo(1);
		Usuario u = facade.getUsuario(1);
		System.out.println(g.getId());
		System.out.println(u.getId());
		gU.setGrupo(g);
		gU.setUsuario(u);
		gU = facade.cadastrarGrupoUsuario(gU);
*/

		/*
		Permissao p = new Permissao();
		Funcao f = facade.getFuncao(1);
		Grupo g = facade.getGrupo(1);
		p.setFuncao(f);
		p.setGrupo(g);
		p = facade.cadastrarPermissao(p);*/
		
		/*FuncaoEtapasCotas fEC = new FuncaoEtapasCotas();
		Funcao f = facade.getFuncao(1);
		EtapasCotas eC = facade.getEtapasCotas(3);
		fEC.setEtapaCota(eC);
		fEC.setFuncao(f);
		fEC = facade.cadastrarFuncaoEtapasCotas(fEC);
	
		*/
		/*EtapasCotas eC = new EtapasCotas();
		Agenda a = facade.getAgenda(1);
		Cota c = facade.getCota("2009/2010");
		eC.setAgenda(a);
		eC.setCota(c);
		eC.setNome("Avaliação");
		eC = facade.cadastrarEtapasCotas(eC);
		
		*/
/*		Cota cota = new Cota();
		cota.setPeriodoCota("2011/2012");
		cota = facade.cadastrarCota(cota);
		
		//Valida usuário
		Usuario u = facade.getUsuario(4);
		u.setSenha("123456");
		List <Funcao> lista = facade.validaUsuario(u);
		if(!lista.isEmpty()){
			System.out.println(lista.get(0).getNome());
			System.out.println(lista.get(0).getMenu().getRotulo());
		}
		//Lista período da agenda
	/*	Agenda a = facade.verificaAgenda(1);
		System.out.println(a.getEtapasCotas());*/
		
		/*List <GrupoUsuario> g = facade.listarGrupoUsuarios();
		System.out.println(g.get(0).getGrupo());*/
	}
}
