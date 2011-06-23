// ActionScript file
package bean.user {
	import bean.system.SystemStage;
	
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.user.User")]

	public class User{
		
		public var userId:int;
	
		public var login:String;
	
		public var name:String;
	
		public var password:String;
	
		public var email:String;
	
		public var period:SystemStage;
	}
}