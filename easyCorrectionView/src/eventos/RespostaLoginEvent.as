package eventos {
	import flash.events.Event;
	
	public class RespostaLoginEvent extends Event {
		public var respostaLogin:*;
		
		public static const RespostaLogin_Event:String = "respostaLoginEvent";
		
		public function RespostaLoginEvent(resposta:*) {
			super(RespostaLogin_Event, true, true); //bubble by default
			respostaLogin = resposta;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new RespostaLoginEvent(respostaLogin); // bubbling support inside
		}
	}
}