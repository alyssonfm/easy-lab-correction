// ActionScript file
package bean.acesso {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.acesso.Permissao")]

	public class Permissao{

		public var idPermissao: int;
		
		public var funcao: Funcao;
		
		public var grupo: Grupo;
		
	}
}