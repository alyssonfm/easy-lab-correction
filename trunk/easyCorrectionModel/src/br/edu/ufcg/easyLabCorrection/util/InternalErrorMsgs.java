package br.edu.ufcg.easyLabCorrection.util;

/**
 * Enumeration of internal error messages.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 31-May-2011.<br>
 */
public enum InternalErrorMsgs {

	//TODO Unused 
	CAMPOVAZIO {
		public String msg(String... args) {
			return "O campo \"" + args[0] + "\" nao pode ser vazio.";
		}
	},
	//TODO Unused 
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
	//TODO Unused 
	VALOR_DE_ATRIBUTO_INVALIDO{
		public String msg(String... args){
			return "O valor \""+args[0]+"\" e invalido para o campo \""+args[1]+"\".";
		}
	},
	//TODO Unused 
	ERRO_REMOCAO{
		public String msg(String... args){
			return "Impossivel realizar remocao: "+args[0];
		}
	},
	//TODO Unused 
	ERRO_ALTERACAO{
		public String msg(String... args){
			return "Impossivel realizar alteracao: "+args[0];
		}
	},
	VALOR_DUPLICADO{
		public String msg(String... args) {
				return "Valor duplicado:" +" "+ args[0] + ".";
			}
	},
	//TODO Unused 	
	ERRO_ACESSO_ARQUIVO{
		public String msg(String... args){
			return "Erro no acesso ao arquivo "+args[0]+".";
		}
	},
	ATRIBUTO_INVALIDO{
		public String msg(String... args){
			return "Atributo nao existe ("+args[0]+")";
		}
	},
	OBJ_NOT_FOUND{
		public String msg(String... args){
			return args[0]+" nao foi encontrado(a).";
		}

	},
	//TODO Unused 	
	ERRO_AUTENTICACAO{
		public String msg(String... args){
			return "\""+args[0]+".";
		}
	} ,
	//TODO Unused 	
	OPER_NAO_REALIZADA{
		public String msg(String... args){
			return "Operacao de \""+args[0]+"\"nao realizada.";
		}
	},
	//TODO Unused 	
	VIOLACAO_CONSTRAINT{
		public String msg(String... args) {
			return "\""+args[0]+"\" duplicado(a).";
		}
	},
	//TODO Unused
	ERRO_COMPILACAO_SEM_CLASSE{
		public String msg(String... args) {
			return "ERRO DE COMPILACAO! Sua solucao nao possui tem a classe que implementa a interface na raiz do .zip";
		}
	},
	//TODO Unused
	ERRO_COMPILACAO_SINTAXE{
		public String msg(String... args) {
			return "ERRO DE COMPILA��O! Sua solu��o possui algum erro sint�tico.";
		}
	},
	//TODO Unused
	TEMPO_LIMITE_EXCEDIDO{
		public String msg(String... args) {
			return "TEMPO LIMITE EXCEDIDO! Sua solu��o n�o obedeceu ao limite de tempo de execu��o.";
		}
	},	
	ID_ROTEIRO_INEXISTENTE{
		public String msg(String... args) {
			return "Nao foi possivel terminar a consulta! Roteiro inexistente.";
		}
	},
	//TODO Unused
	ROTEIRO_NAO_LIBERADO{
		public String msg(String... args) {
			return "N�o h� equipes alocadas pois o Roteiro " +args[0]+ " ainda n�o foi liberado!";
		}
	},
	//TODO Unused
	ARQUIVO_MAL_FORMADO{
		public String msg(String... args) {
			return "Erro no envio! O pacote zip submetido possui arquivos que n�o s�o do tipo JAVA.";
		}
	},
	//TODO Unused
	EQUIPE_INEXISTENTE{
		public String msg(String... args) {
			return "Mudanca nao realizada! Equipe inexistente.";
		}
	},
	AVALIACAO{
		public String msg(String... args) {
			return "N�o � poss�vel salvar a avalia��o.";
		}
	};
	
	public String msg(String... args) {
		return "ERRO 499";
	}
}
