// ActionScript file
package bean.assignments{
	import bean.system.SystemStage;
	

	[Bindable]
	[RemoteClass(alias="br.edu.ufcg.easyLabCorrection.pojo.assignments.Assignment")]

	public class Assignment{

		public var id:int;
		public var stage:SystemStage;
		public var name:String;
		public var description:String;
		public var releaseDate:Date;
		public var deliveryDate:Date;
		public var discussionDate:Date;
		public var participantsMaxNumber:int;
		public var sendMaxNumber:int;
		public var penaltyPerDaysLate:Number;
		public var automaticTestsPercentage:Number;
		public var testTimeLimit:int;
		public var interfaceDirectory:String;
		public var testsDirectory:String;
		public var assignmentType:AssignmentType;
		
	}
}
