// ActionScript file
package bean.assignments{

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.assignments.AssignmentType")]

	public class AssignmentType{

		public var id:int;
		public var name:String;
		public var compilation: Boolean;
		public var testExecution: Boolean;
		public var outputComparison: Boolean;
		
	}
}
