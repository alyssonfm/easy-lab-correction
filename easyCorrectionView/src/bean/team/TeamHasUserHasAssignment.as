// ActionScript file
package bean.team{
	import bean.assignments.Assignment;
	import bean.user.User;
	
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.team.TeamHasUserHasAssignment")]

	public class TeamHasUserHasAssignment{

		public var id:int;
		public var team:Team;
		public var assignment:Assignment;
		public var user:User;
		
	}
}

	