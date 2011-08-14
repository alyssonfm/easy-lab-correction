// ActionScript file
package bean.user {
	import bean.permission.Group;
	import bean.system.SystemStage;

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup")]

	public class UserGroup{

		public var userGroupId: int;
		
		public var group: Group;
		
		public var user: User;
		
		public var systemStage: SystemStage;
		
	}
}