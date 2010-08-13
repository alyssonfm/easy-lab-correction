// ActionScript file
package bean.acesso {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.acesso.GrupoUsuario")]

	public class GrupoUsuario{

		public var idGrupoUsuario: int;
		
		public var grupo: Grupo;
		
		public var usuario: Usuario;
		
	}
}