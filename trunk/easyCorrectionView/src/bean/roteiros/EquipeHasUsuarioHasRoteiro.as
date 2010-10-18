// ActionScript file
package bean.roteiros{
	import bean.acesso.Usuario;
	import bean.sistema.*;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.roteiros.EquipeHasUsuarioHasRoteiro")]

	public class EquipeHasUsuarioHasRoteiro{

		public var id:int;
		public var  equipe:Equipe;
		public var roteiro:Roteiro;
		public var usuario:Usuario;
		
	}
}

	