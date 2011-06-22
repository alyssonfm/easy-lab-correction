// ActionScript file
package bean.assignments{
	import bean.team.TeamHasUserHasAssignment;
	
	
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.assignments.Submission")]

	public class Submission{

		public var id:int;
		public var submissionDate:Date;
		public var teamHasUserHasAssignment:TeamHasUserHasAssignment;
		public var url:String;
		public var status:String;
		
	}
}

	