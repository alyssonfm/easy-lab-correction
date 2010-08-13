package eventos {
	import flash.events.Event;
	
	import mx.core.IFlexDisplayObject;

	public class FechaPopupEvent extends Event {
		public var popUp:IFlexDisplayObject;
		
		public static const FechaPopup_Event:String = "fechaPopupEvent";
		
		public function FechaPopupEvent(popup:IFlexDisplayObject) {
			super(FechaPopup_Event, true, true); //bubble by default
			popUp = popup;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new FechaPopupEvent(popUp); // bubbling support inside
		}
	}
}