// ActionScript file
package bean.roteiros{
	import bean.sistema.*;
	

	[Bindable]
	[RemoteClass(alias="br.edu.les.easyCorrection.pojo.roteiros.Roteiro")]

	public class Roteiro{
		
		public var id: int;
		public var periodo: Periodo;
		public var nome: String;
		public var descricao: String;
		public var dataLiberacao: Date;
		public var dataFinalEntrega: Date;
		public var dataFinalDiscussao: Date; 
		public var numeroMaximoParticipantes: int;
		public var numeroMaximoEnvios: int;
		public var penalidadeDiasAtraso: Number;
		public var porcentagemTestesAutomaticos: Number;
		public var tempoLimiteTestes: int;
		public var diretorioInterface: String;
		public var diretorioTestes: String;
		public var bloqueado:Boolean;				
	}
}

	