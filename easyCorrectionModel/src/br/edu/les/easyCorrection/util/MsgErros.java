package br.edu.les.easyCorrection.util;

public enum MsgErros {

	/*
	 * MENSAGENS DE ERROS REFERENTES A CAMPOS VAZIOS E/OU NULOS
	 */
	CAMPOVAZIO {
		public String msg(String... args) {
			return "O campo \"" + args[0] + "\" nao pode ser vazio.";
		}
	},
	NOMEVAZIO {
		public String msg(String... args) {
			return ""+args[0];
		}
	},
	VALORINVALIDO {
		public String msg(String... args) {
			return ""+args[0];
		}
	},
	VALOR_DE_ATRIBUTO_INVALIDO{
		public String msg(String... args){
			return "O valor \""+args[0]+"\" e invalido para o campo \""+args[1]+"\".";
		}
	},
	ERRO_REMOCAO{
		public String msg(String... args){
			return "Impossivel realizar remocao: "+args[0];
		}
	},
	ERRO_ALTERACAO{
		public String msg(String... args){
			return "Impossivel realizar alteracao: "+args[0];
		}
	}
	,
	AUTENTICACAO{
		public String msg(String... args) {
				return "Senha ou login invalido";
			}
	},
	VALOR_DUPLICADO{
		public String msg(String... args) {
				return "Valor duplicado:" +" "+ args[0] + ".";
			}
	},
	
	ERRO_ACESSO_ARQUIVO{
		public String msg(String... args){
			return "Erro no acesso ao arquivo "+args[0]+".";
		}
	}
	,
	ATRIBUTO_INVALIDO{
		public String msg(String... args){
			return "Atributo \"" + args[0] + "\" nao existe. ("+args[1]+")";
		}
	},
	
	OBJ_NOT_FOUND{
		public String msg(String... args){
			return args[0]+" nao foi encontrado(a).";
		}

	},
	ERRO_AUTENTICACAO{
		public String msg(String... args){
			return "\""+args[0]+".";
		}
	} ,
	OPER_NAO_REALIZADA{
		public String msg(String... args){
			return "Operacao de \""+args[0]+"\"nao realizada.";
		}
	},

	VIOLACAO_CONSTRAINT{
		public String msg(String... args) {
			return "\""+args[0]+"\" duplicado(a).";
		}
	};

	
	public String msg(String... args) {
		return "ERRO 499";
	}


}
