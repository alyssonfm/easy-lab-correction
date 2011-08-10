package br.edu.ufcg.easyLabCorrection.util;

/**
 * This class contains all the error messages that are sent from the system to
 * the user, that we call the external error messages. All the messages are kept
 * here divided by manager to improve the maintainability.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 1-August-2011.<br>
 */
public enum ErrorMsgs {

	/*
	 * INTERNAL ERRORS
	 */
	
	INVALID_VALUE {
		public String msg(String... args) {
			return "The value " + args[0] + " is invalid!";
		}
	},
	DUPLICATED_VALUE {
		public String msg(String... args) {
			return "Duplicated value: " + args[0] + ".";
		}
	},
	INEXISTENT_ATTRIBUTE {
		public String msg(String... args) {
			return "Inexistent attribute: " + args[0];
		}
	},
	OBJ_NOT_FOUND {
		public String msg(String... args) {
			return "The object " + args[0] + " was not found!";
		}
	},
	NULL_OBJECT {
		public String msg(String... args) {
			return "The object " + args[0] + " is null!";
		}
	},
	EMPTY_QUERY_RESULT {
		public String msg(String... args) {
			return "There is no " + args[0] + " in the database!";
		}
	},
	DEVELOPMENT_ERROR{
		public String msg(String... args) {
			return "There is no " + args[0] + " in the database!";
		}
	},

	/*
	 * System
	 */

	REPEATED_USER_LOGIN{
		public String msg(String... args) {
			return "It is not possible to register the User, this login already exists.";
		}
	},
	
	REPEATED_USER_EMAIL{
		public String msg(String... args) {
			return "It is not possible to register the User, this e-mail already exists.";
		}
	},
	
	/*
	 * Access Permission Manager
	 */

	/*
	 * Access User Manager
	 */
	INVALID_FIELDS_NUMBER {
		public String msg(String... args) {
			return "The line " + args[0]
					+ " contains an invalid number of columns!";
		}
	},
	CSV_INVALID_USER {
		public String msg(String... args) {
			return "Invalid User at the line " + args[0] + "!";
		}
	},
	INVALID_AUTHENTICATION {
		public String msg(String... args) {
			return "Invalid Authentication, the login and the password do not match!";
		}
	},
	INVALID_PASSWORD_CHANGE {
		public String msg(String... args) {
			return "The login and password do not match, the update was aborted!";
		}
	},

	/*
	 * Assessment Manager
	 */

	/*
	 * Assignment Manager
	 */

	ASSIGNMENT_CREATION_ERROR {
		public String msg(String... args) {
			return "The Assignment could not be created!";
		}
	},
	UNKNOWN_ASSIGNMENT_STATE {
		public String msg(String... args) {
			return "The Assignment state is unknown!";
		}
	},
	INVALID_RELEASE_DATE {
		public String msg(String... args) {
			return "Invalid release date. The Assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_DELIVERY_DEADLINE_DATE {
		public String msg(String... args) {
			return "Invalid deadline date. The Assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_DISCUSSION_DATE {
		public String msg(String... args) {
			return "Invalid discussion date. The Assignment could not be "
					+ args[0] + "!";
		}
	},
	WRONG_SERVER_TEST_DIR_HIERARCHY {
		public String msg(String... args) {
			return "The server directory test hierarchy does not match the pattern: '/periodo<periodo>/testes/<assignment_id>/'."
					+ "The assignment could not be " + args[0] + "!";
		}
	},
	WRONG_SERVER_INTERFACE_DIR_HIERARCHY {
		public String msg(String... args) {
			return "The server directory interface hierarchy does not match the pattern: '/periodo<periodo>/environment/<assignment_id>/'."
					+ "The assignment could not be " + args[0] + "!";
		}
	},
	RELEASED_ASSIGNMENT_FIELDS_CHANGED {
		public String msg(String... args) {
			return "The assignment "
					+ args[0]
					+ " cannot be updated because of changes in its release characteristics!";
		}
	},
	INVALID_ASSIGNMENT_NAME {
		public String msg(String... args) {
			return "The name is empty. The assignment could not be " + args[0]
					+ "!";
		}
	},
	INVALID_TEAM_MEMBERS_MAX_NUMBER {
		public String msg(String... args) {
			return "The participants max number should be greater than -1. The assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_SUBMISSION_MAX_NUMBER {
		public String msg(String... args) {
			return "The submission max number should be greater than -1. The assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_PENALTY_PER_DAY_LATE {
		public String msg(String... args) {
			return "The penalty per day late should be >= 0,0 and <= 10,0. The assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_AUTOMATIC_ASSESSMENT_PERCENTAGE {
		public String msg(String... args) {
			return "The automatic assessment percentage should be >= 0 and <= 100. The assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_EXECUTION_TIME_LIMIT_NOT_ZERO {
		public String msg(String... args) {
			return "The execution time-limit should be > 0 if there is automatic assessment. The assignment could not be "
					+ args[0] + "!";
		}
	},
	INVALID_EXECUTION_TIME_LIMIT_ZERO {
		public String msg(String... args) {
			return "The execution time-limit should be 0 if there is no automatic assessment. The assignment could not be "
					+ args[0] + "!";
		}
	},

	/*
	 * Compilation Manager
	 */

	INVALID_TEST_SUITE_NAME {
		public String msg(String... args) {
			return "The test suite name should be MainTest.java";
		}
	},

	/*
	 * Submission Manager
	 */
	SUBMISSION_LIMIT_ALREADY_REACHED {
		public String msg(String... args) {
			return "The team has already reached the submission limit of the assignment. The submission was aborted!";
		}
	},
	SUBMISSION_DEADLINE_ALREADY_FINISHED {
		public String msg(String... args) {
			return "The submission deadline of the assignment has already passed. The submission was aborted!";
		}
	},

	/*
	 * Team Manager
	 */
	TEAM_MEMBERS_QUANTITY_IS_FULL {
		public String msg(String... args) {
			return "This team (" + args[0]
					+ ") already reached the max number of members(" + args[1]
					+ "). Please register in another team!";
		}
	},
	TEAM_FOR_INDIVIDUAL_ASSIGNMENTS {
		public String msg(String... args) {
			return "The teams cannot be changed. The assignment is individual!";
		}
	};

	/*
	 * Automated Correction Manager
	 */

	public String msg(String... args) {
		return "ERRO 499";
	}
}
