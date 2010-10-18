// ActionScript file
package bean.roteiros{
	import bean.sistema.*;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.roteiros.Submissao")]

	public class Submissao{

		public var id:int;
		public var dataSubmissao:Date;
		public var equipeHasUsuarioHasRoteiro:EquipeHasUsuarioHasRoteiro;
		public var url:String;
		public var estado:String;
		
	}
}

	