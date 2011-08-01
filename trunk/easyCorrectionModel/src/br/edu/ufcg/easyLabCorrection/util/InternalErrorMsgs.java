package br.edu.ufcg.easyLabCorrection.util;

/**
 * Enumeration of internal error messages.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 1-August-2011.<br>
 */
public enum InternalErrorMsgs {
	
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
	EMPTY_QUERY_RESULT {
		public String msg(String... args) {
			return "There is no " + args[0] + " in the database!";
		}
	};

	public String msg(String... args) {
		return "ERRO 499";
	}
}
