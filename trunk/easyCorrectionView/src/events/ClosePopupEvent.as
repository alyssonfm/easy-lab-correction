package events {
	import flash.events.Event;
	
	import mx.core.IFlexDisplayObject;

	public class ClosePopupEvent extends Event {
		public var popUp:IFlexDisplayObject;
		
		public static const ClosePopup_Event:String = "closePopupEvent";
		
		public function ClosePopupEvent(popup:IFlexDisplayObject) {
			super(ClosePopup_Event, true, true); //bubble by default
			popUp = popup;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new ClosePopupEvent(popUp); // bubbling support inside
		}
	}
}