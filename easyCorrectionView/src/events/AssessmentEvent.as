// ActionScript file
package events
{
	import flash.events.Event;
	
	public class AssessmentEvent extends Event {
		
		public var assessment: *;
		
		public static const assessmentEventConst:String = "Assessment";

		public function AssessmentEvent(Assessment: *) {
			super(assessmentEventConst, true, true);
			this.assessment = Assessment;
		}

		override public function clone():Event {
			return new AssessmentEvent(assessment);
		}
	}
}