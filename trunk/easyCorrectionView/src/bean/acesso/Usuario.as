// ActionScript file
package bean.acesso {
	import bean.sistema.Periodo;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.acesso.Usuario")]

	public class Usuario{

		public var idUsuario: int;
		
		public var nome: String;
		
		public var login: String;
		
		public var senha: String;
		
		public var email: String;
		
		public var periodo:Periodo;
	}
}