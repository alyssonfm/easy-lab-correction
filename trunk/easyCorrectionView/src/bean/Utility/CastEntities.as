// ActionScript file
package bean.Utility {
	import bean.assessments.Assessment;
	import bean.assignments.Assignment;
	import bean.assignments.AssignmentType;
	import bean.assignments.Submission;
	import bean.permission.Group;
	import bean.permission.Menu;
	import bean.permission.MenuFunction;
	import bean.permission.Permission;
	import bean.system.SystemStage;
	import bean.team.Team;
	import bean.team.TeamHasUserHasAssignment;
	import bean.user.User;
	import bean.user.UserGroup;
	


	public class CastEntities {				
		
		public static function castFunction(object: *): MenuFunction{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var f:MenuFunction = new MenuFunction();
					f.functionId = object.functionId;
					f.menu = castMenu(object.menu);
					f.name = object.name;
					f.label = object.label;
					f.selected = object.selected;
					return f;
				}
			}
			return object;
		}
		
		public static function castMenu(object: *):Menu{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var m:Menu = new Menu();
					m.menuId = object.menuId;
					m.name = object.name;
					m.label = object.label;
					return m;
				}
			}
			return object;
		}
		
		public static function castGroup(object: *):Group{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var g:Group = new Group();
					g.groupId = object.groupId;
					g.name = object.name;
					return g;
				}
			}
			return object;
		}
		
		
		public static function castPermission(object: *):Permission{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var p:Permission = new Permission();
					p.permissionId = object.permissionId;
					p.group = castGroup(object.group);
					p.menuFunction = castFunction(object.menuFunction);
					return p;
				}
			}
			return object;
		}
		
		public static function castStage(object: *):SystemStage{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var s:SystemStage = new SystemStage();
					s.id = object.id;
					s.semester = object.semester;
					s.course = object.course;
					s.courseClass = object.courseClass;  
					return s;
				}
			}
			return object;
		}
		
		
		public static function castUser(object: *):User{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var u:User = new User();
					u.email = object.email;
					u.login = object.login;
					u.name = object.name;
					u.password = object.password;
					u.period = castStage(object.period);
					u.userId = object.userId;
					return u;
				}
			}
			return object;
		}
		
		public static function castUserGroup(object: *):UserGroup{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var ug:UserGroup = new UserGroup();
					ug.group = castGroup(object.group);
					ug.user = castUser(object.user);
					ug.userGroupId = object.userGroupId;
					return ug;
				}
			}
			return object;
		}
		
		public static function castTeam(object: *):Team{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var t:Team = new Team();
					t.id = object.id;
					t.name = object.name;
					return t;
				}
			}
			return object;
		}
		
		
		public static function castTeamHasUserHasAssignment(object: *):TeamHasUserHasAssignment{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var tu:TeamHasUserHasAssignment = new TeamHasUserHasAssignment();
					tu.id = object.id;
					tu.team = object.team;
					tu.assignment = castAssignment(object.assignment);
					tu.user = castUser(object.user);
					return tu;
				}
			}
			return object;
		}
		
		public static function castAssignment(object: *):Assignment{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var a:Assignment = new Assignment();
					a.assignmentType = castAssignmentType(object.assignmentType);
					a.automaticTestsPercentage = object.automaticTestsPercentage;
					a.deliveryDate = object.deliveryDate;
					a.description = object.description;
					a.discussionDate = object.discussionDate;
					a.id = object.id;
					a.interfaceDirectory = object.interfaceDirectory;
					a.participantsMaxNumber = object.participantsMaxNumber;
					a.penaltyPerDaysLate = object.penaltyPerDaysLate;
					a.releaseDate = object.releaseDate;
					a.sendMaxNumber = object.sendMaxNumber;
					a.stage = castStage(object.stage);
					a.testsDirectory = object.testsDirectory;
					a.testTimeLimit = object.testTimeLimit;
					a.name = object.name;
					return a;
				}
			}
			return object;
		}
		
		
		public static function castAssignmentType(object: *):AssignmentType{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var at:AssignmentType = new AssignmentType();
					at.id = object.id;
					at.name = object.name;
					return at;
				}
			}
			return object;
		}
		
		
		public static function castSubmission(object: *):Submission{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var s:Submission = new Submission();
					s.id = object.id;
					s.submissionDate = object.submissionDate;
					s.teamHasUserHasAssignment = castTeamHasUserHasAssignment(object.teamHasUserHasAssignment);
					s.url = object.url;
					return s;
				}
			}
			return object;
		}
		
		
		public static function castAssessment(object: *):Assessment{
			if (object != null) {
				if(object == undefined){
					object = null;
				}else{
					var a:Assessment = new Assessment();
					a.assessmentDate = object.assessmentDate;
					a.automaticGrade = object.automaticGrade;
					a.correctionGrade = object.correctionGrade;
					a.corrector = castUser(object.corrector);
					a.id = object.id;
					a.penalty = object.penalty;
					a.submission = castSubmission(object.submission);
					a.testsExecutionResult = object.testsExecutionResult;
					return a;
				}
			}
			return object;
		}
	}
}