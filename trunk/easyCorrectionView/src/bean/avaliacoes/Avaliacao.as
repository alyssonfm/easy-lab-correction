// ActionScript file
package bean.avaliacoes{
	import bean.acesso.Usuario;
	import bean.roteiros.Submissao;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.avaliacoes.Avaliacao")]

	public class Avaliacao{

		public var id:int;
		public var submissao:Submissao;
		public var notaAutomatica:Number;
		public var notaCorrecao:Number;
		public var resultadoExecucaoTestes:String;
		public var penalidade:Number;
		public var dataAvaliacao:Date;
		public var corretor: Usuario;
		public var corrigida: Boolean;
	}
}

	