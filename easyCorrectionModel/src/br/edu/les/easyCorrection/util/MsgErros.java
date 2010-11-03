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
			return "Atributo nao existe. ("+args[0]+")";
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
	},
	NUMERO_MAXIMO_PARTICIPANTES{
		public String msg(String... args) {
			return "Está equipe já contém o número máximo de participantes. Por favor cadastre-se em outra equipe.";
		}
	},
	NUMERO_MAXIMO_SUBMISSOES_EXCEDIDO{
		public String msg(String... args) {
			return "Erro no envio! A sua equipe já alcançou o limite de submissões do " + args[0];
		}
	},
	ROTEIRO_INEXISTENTE{
		public String msg(String... args) {
			return "Não foi possível terminar a consulta! Roteiro inexistente.";
		}
	},
	ROTEIRO_NAO_LIBERADO{
		public String msg(String... args) {
			return "Não há equipes alocadas pois o Roteiro " +args[0]+ " ainda não foi liberado!";
		}
	},
	EMAIL{
		public String msg(String... args) {
			return "Não é possível cadastrar o usuário, este e-mail já existe.";
		}
	},
	LOGIN{
		public String msg(String... args) {
			return "Não é possível cadastrar o usuário, este login já existe.";
		}
	};
	
	
	public String msg(String... args) {
		return "ERRO 499";
	}


}
