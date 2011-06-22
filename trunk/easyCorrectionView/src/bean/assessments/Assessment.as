// ActionScript file
package bean.assessments{
	import bean.assignments.Submission;
	import bean.user.User;
	
	
	
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.assessments.Assessment")]

	public class Assessment{

		public var id:int;
		public var submission:Submission;
		public var automaticGrade:Number;
		public var correctionGrade:Number;
		public var testsExecutionResult:String;
		public var penalty:Number;
		public var assessmentDate:Date;
		public var corrector:User;
		public var corrected:Boolean;
		
	}
}

	