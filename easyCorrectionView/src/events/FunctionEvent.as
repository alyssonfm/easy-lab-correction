// ActionScript file
package events
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	public class FunctionEvent extends Event {
		
		public var functions:ArrayCollection;
		
		public static const FunctionEventConst:String = "funcao";

		public function FunctionEvent(functions:ArrayCollection) {
			super(FunctionEventConst, true, true);
			this.functions = functions;
		}

		override public function clone():Event {
			return new FunctionEvent(functions);
		}
	}
}
