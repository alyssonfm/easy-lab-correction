// ActionScript file
package eventos
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	public class FuncaoEvent extends Event {
		
		public var funcoes:ArrayCollection;
		
		public static const FuncaoEventConst:String = "funcao";

		public function FuncaoEvent(funcoes:ArrayCollection) {
			super(FuncaoEventConst, true, true);
			this.funcoes = funcoes;
		}

		override public function clone():Event {
			return new FuncaoEvent(funcoes);
		}
	}
}
