// ActionScript file
package eventos
{
	import flash.events.Event;
	
	public class RespostaCMEvent extends Event {
		
		public var respostaCM: *;
		
		public static const respostaCMConst:String = "RespostaCM";

		public function respostaCMEvent(respostaCM: *) {
			super(respostaCMConst, true, true);
			this.respostaCM = respostaCM;
		}

		override public function clone():Event {
			return new respostaCMEvent(respostaCM);
		}
	}
}