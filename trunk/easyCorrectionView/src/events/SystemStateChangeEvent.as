package events {
	import flash.events.Event;
	
	public class SystemStateChangeEvent extends Event {
		public var systemState:*;
		
		public static const systemStateChangeEventConst:String = "systemStateChangeConst";
		
		public function SystemStateChangeEvent(systemState:*) {
			super(systemStateChangeEventConst, true, true); //bubble by default
			this.systemState = systemState;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new SystemStateChangeEvent(systemState); // bubbling support inside
		}
	}
}