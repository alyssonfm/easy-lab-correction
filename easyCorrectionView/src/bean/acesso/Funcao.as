// ActionScript file
package bean.acesso {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.acesso.Funcao")]

	public class Funcao{

		public var idFuncao: int;
		
		public var menu: Menu;
		
		public var nome: String;
		
		public var rotulo: String;
		
		[Transient]
		public var selecionado:Boolean = false;
		
	}
}