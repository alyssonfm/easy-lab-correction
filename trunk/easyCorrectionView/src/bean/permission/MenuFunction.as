// ActionScript file
package bean.permission {
	import mx.collections.ArrayCollection;
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.permission.Function")]

	public class MenuFunction{

		public var functionId: int;
		
		public var menu: Menu;
		
		public var name: String;
		
		public var label: String;
		
		[Transient]
		public var selected:Boolean = false;
		
	}
}