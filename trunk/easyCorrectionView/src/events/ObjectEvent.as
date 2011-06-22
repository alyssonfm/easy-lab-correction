// ActionScript file
package events
{
	import flash.events.Event;
	
	public class ObjectEvent extends Event {
		
		public var object: *;
		
		public static const ObjetoEventConst:String = "Object";

		public function ObjectEvent(object: *) {
			super(ObjetoEventConst, true, true);
			this.object = object;
		}

		override public function clone():Event {
			return new ObjectEvent(object);
		}
	}
}