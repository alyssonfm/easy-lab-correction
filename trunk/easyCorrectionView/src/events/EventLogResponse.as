package events {
	import flash.events.Event;
	
	public class EventLogResponse extends Event {
		public var responseLogin:*;
		
		public static const ResponseLogin_Event:String = "ResponseLoginEvent";
		
		public function EventLogResponse(response:*) {
			super(ResponseLogin_Event, true, true); //bubble by default
			responseLogin = response;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new EventLogResponse(responseLogin); // bubbling support inside
		}
	}
}