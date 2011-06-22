// ActionScript file
package bean.permission {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.permission.Menu")]

	public class Menu{

		public var menuId: int;
		
		public var name: String;
		
		public var label: String;
		
	}
}