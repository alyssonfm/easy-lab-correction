// ActionScript file
package bean.Utilitarios {

	import bean.acesso.*;
	import bean.avaliacoes.*;
	import bean.roteiros.*;
	import bean.sistema.*;

	public class CastDeEntidades {				
		
		public static function castFuncao(objeto: *):Funcao{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var funcao:Funcao = new Funcao();
					funcao.idFuncao = objeto.idFuncao;
					funcao.nome = objeto.nome;
					funcao.rotulo = objeto.rotulo;
					funcao.menu = CastDeEntidades.castMenu(objeto.menu);
					funcao.selecionado = objeto.selecionado;
					return funcao;
				}
			}
			return objeto;
		}
		
		public static function castMenu(objeto: *):Menu{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var menu:Menu = new Menu();
					menu.idMenu = objeto.idMenu;
					menu.nome = objeto.nome;
					menu.rotulo = objeto.rotulo;
					return menu;
				}
			}
			return objeto;
		}
		
		public static function castGrupo(objeto: *):Grupo{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var grupo:Grupo= new Grupo();
					grupo.idGrupo = objeto.idGrupo;
					grupo.nome = objeto.nome;
					return grupo;
				}
			}
			return objeto;
		}
		
		public static function castUsuario(objeto: *):Usuario{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var usuario:Usuario = new Usuario();
					usuario.idUsuario = objeto.idUsuario;
					usuario.login = objeto.login;
					usuario.senha = objeto.senha;
					usuario.nome = objeto.nome;
					usuario.email = objeto.email;
					usuario.periodo = castPeriodo(objeto.periodo);
					return usuario;
				}
			}
			return objeto;
		}
		
		public static function castGrupoUsuario(objeto: *):GrupoUsuario{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var gU:GrupoUsuario = new GrupoUsuario();
					gU.grupo = CastDeEntidades.castGrupo(objeto.grupo);
					gU.idGrupoUsuario = objeto.idGrupoUsuario;
					gU.usuario = CastDeEntidades.castUsuario(objeto.usuario);
					return gU;
				}
			}
			return objeto;
		}
		
		public static function castPermissao(objeto: *):Permissao{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var p:Permissao= new Permissao();
					p.funcao = CastDeEntidades.castFuncao(objeto.funcao);
					p.grupo = CastDeEntidades.castGrupo(objeto.grupo);
					p.idPermissao = objeto.idPermissao;
					return p;
				}
			}
			return objeto;
		}		
		
		public static function castPeriodo(objeto: *):Periodo{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var p:Periodo = new Periodo();
					p.id = objeto.id;
					p.semestre = objeto.semestre;
					return p;
				}
			}
			return objeto;
		}
		
		public static function castEquipe(objeto: *):Equipe{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var e:Equipe = new Equipe();
					e.id = objeto.id;
					e.nome = objeto.nome;
					return e;
				}
			}
			return objeto;
		}	
		
		public static function castEquipeHasUsuarioHasRoteiro(objeto: *):EquipeHasUsuarioHasRoteiro{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var e:EquipeHasUsuarioHasRoteiro = new EquipeHasUsuarioHasRoteiro();
					e.id = objeto.id;
					e.equipe = castEquipe(objeto.equipe);
					e.roteiro = castRoteiro(objeto.roteiro);
					e.usuario = castUsuario(objeto.usuario);
					return e;
				}
			}
			return objeto;
		}	
		
		public static function castSubmissao(objeto: *):Submissao{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var s:Submissao = new Submissao();
					s.id = objeto.id;
					s.dataSubmissao = objeto.dataSubmissao;
					s.equipeHasUsuarioHasRoteiro = castEquipeHasUsuarioHasRoteiro(objeto.equipeHasUsuarioHasRoteiro);
					s.estado = objeto.estado;
					s.url = objeto.url;
					return s;
				}
			}
			return objeto;
		}		
			
		
		
		
		public static function castRoteiro(objeto: *):Roteiro{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var r:Roteiro = new Roteiro();
					r.id = objeto.id;
					r.dataFinalDiscussao = objeto.dataFinalDiscussao;
					r.dataFinalEntrega = objeto.dataFinalEntrega;
					r.dataLiberacao = objeto.dataLiberacao;
					r.descricao = objeto.descricao;
					r.diretorioInterface = objeto.diretorioInterface;
					r.diretorioTestes = objeto.diretorioTestes;
					r.nome = objeto.nome;
					r.numeroMaximoEnvios = objeto.numeroMaximoEnvios;
					r.numeroMaximoParticipantes = objeto.numeroMaximoParticipantes;
					r.penalidadeDiasAtraso = objeto.penalidadeDiasAtraso;
					r.periodo = castPeriodo(objeto.periodo);
					r.porcentagemTestesAutomaticos = objeto.porcentagemTestesAutomaticos;
					r.tempoLimiteTestes = objeto.tempoLimiteTestes;
					return r;
				}
			}
			return objeto;
		}	
		
		public static function castAvaliacao(objeto: *):Avaliacao{
			if (objeto != null) {
				if(objeto == undefined){
					objeto = null;
				}else{
					var a:Avaliacao = new Avaliacao();
					a.id = objeto.id;
					a.submissao = castSubmissao(objeto.submissao);
					a.notaAutomatica = objeto.notaAutomatica;
					a.notaCorrecao = objeto.notaCorrecao;
					a.resultadoExecucaoTestes = objeto.resultadoExecucaoTestes;
					a.penalidade = objeto.penalidade;
					a.dataAvaliacao = objeto.dataAvaliacao;
					a.corrigido = objeto.corrigido; 
					try{
						a.corretor = castUsuario(objeto.usuario);
					}catch(e: Error){
						a.corretor = null;	
					}
					return a;
				}
			}
			return objeto;
		}		
	}	
}
	