// ActionScript file
package bean.permission {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.permission.Permission")]

	public class Permission{
		
		public var permissionId:int;
	
		public var group:Group;
	
		public var menuFunction:MenuFunction;
	}
}