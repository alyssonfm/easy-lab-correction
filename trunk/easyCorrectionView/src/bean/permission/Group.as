// ActionScript file
package bean.permission {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.permission.Group")]

	public class Group{

		public var groupId: int;
		
		public var name: String;
	}
}