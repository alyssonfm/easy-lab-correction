// ActionScript file
package eventos
{
	import flash.events.Event;
	
	public class ObjetoEvent extends Event {
		
		public var objeto: *;
		
		public static const ObjetoEventConst:String = "Objeto";

		public function ObjetoEvent(objeto: *) {
			super(ObjetoEventConst, true, true);
			this.objeto = objeto;
		}

		override public function clone():Event {
			return new ObjetoEvent(objeto);
		}
	}
}