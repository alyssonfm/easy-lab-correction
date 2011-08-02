package br.edu.ufcg.easyLabCorrection.util;

/**
 * This class contains all the error messages that are sent from the system to
 * the user, that we call the external error messages. All the messages are kept
 * here divided by manager to improve the maintainability.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 1-August-2011.<br>
 */
public enum ExternalErrorMsgs {

	/*
	 * Access Permission Manager
	 */

	/*
	 * Access User Manager
	 */
	INVALID_FIELDS_NUMBER {
		public String msg(String... args) {
			return "The line " + args[0]
					+ " contains an invalid number of colunms!";
		}
	},
	CSV_INVALID_USER {
		public String msg(String... args) {
			return "Invalid User at the line " + args[0] + "!";
		}
	},
	INVALID_AUTHENTICATION {
		public String msg(String... args) {
			return "Invalid Authentication, the login and password do not match!";
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
	INVALID_DEADLINE_DATE {
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
	INVALID_PARTICIPANTS_MAX_NUMBER {
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

	;

	/*
	 * Compilation Manager
	 */

	/*
	 * Submission Manager
	 */

	/*
	 * Team Manager
	 */

	/*
	 * Automated Correction Manager
	 */

	public String msg(String... args) {
		return "ERRO 499";
	}
}
