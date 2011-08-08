// ActionScript file
package events
{
	import flash.events.Event;
	
	public class AssignmentEvent extends Event {
		
		public var assignment: *;
		
		public static const AssignmentEventConst:String = "Assignment";

		public function AssignmentEvent(assignment: *) {
			super(AssignmentEventConst, true, true);
			this.assignment = assignment;
		}

		override public function clone():Event {
			return new AssignmentEvent(assignment);
		}
	}
}