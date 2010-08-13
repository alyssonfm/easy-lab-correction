// ActionScript file
package bean.Utilitarios {

	import bean.acesso.*;

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
	}
}
	