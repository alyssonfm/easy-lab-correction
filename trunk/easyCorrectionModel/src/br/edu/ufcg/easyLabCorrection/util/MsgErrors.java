package br.edu.ufcg.easyLabCorrection.util;

/**
 * Enumeration of error messages.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public enum MsgErrors {

	/**
	 * ERROR MESSAGES RELATING TO EMPTY FIELDS AND / OR NULL
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
			return "Atributo nao existe ("+args[0]+")";
		}
	},
	
	OBJ_NOT_FOUND{
		public String msg(String... args){
			return args[0]+" nao foi encontrado(a).";
		}

	},
	USUARIO_ALOCADO{
		public String msg(String... args){
			return "Exclus�o n�o realizada. Este Usu�rio j� est� alocado a uma das equipe.";
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
			return "Est� equipe j� cont�m o n�mero m�ximo de participantes. Por favor cadastre-se em outra equipe.";
		}
	},
	TEMPO_MAXIMO_SUBMISSOES_EXCEDIDO{
		public String msg(String... args) {
			return "Erro no envio! Submiss�o fora do hor�rio de libera��o para submiss�es do " + args[0];
		}
	},
	NUMERO_MAXIMO_SUBMISSOES_EXCEDIDO{
		public String msg(String... args) {
			return "Erro no envio! A sua equipe j� alcan�ou o limite de submiss�es do " + args[0];
		}
	},
	ERRO_COMPILACAO_SEM_CLASSE{
		public String msg(String... args) {
			return "ERRO DE COMPILA��O! Sua solu��o n�o possui tem a classe que implementa a interface na raiz do .zip";
		}
	},
	ERRO_COMPILACAO_SINTAXE{
		public String msg(String... args) {
			return "ERRO DE COMPILA��O! Sua solu��o possui algum erro sint�tico.";
		}
	},
	TEMPO_LIMITE_EXCEDIDO{
		public String msg(String... args) {
			return "TEMPO LIMITE EXCEDIDO! Sua solu��o n�o obedeceu ao limite de tempo de execu��o.";
		}
	},	
	ROTEIRO_INEXISTENTE{
		public String msg(String... args) {
			return "N�o h� equipes alocadas pois o roteiro ainda n�o foi criado!";
		}
	},
	ID_ROTEIRO_INEXISTENTE{
		public String msg(String... args) {
			return "N�o foi poss�vel terminar a consulta! Roteiro inexistente.";
		}
	},
	ROTEIRO_NAO_LIBERADO{
		public String msg(String... args) {
			return "N�o h� equipes alocadas pois o Roteiro " +args[0]+ " ainda n�o foi liberado!";
		}
	},
	ROTEIRO_INDIVIDUAL{
		public String msg(String... args) {
			return "Nenhuma equipe pode ser modificada. O Roteiro � individual!";
		}
	},
	EQUIPE_HAS_ROTEIRO_COMPLETA{
		public String msg(String... args) {
			return "N�o foi poss�vel mudar de equipe! Limite de integrantes da " + args[0] + " j� alcan�ado (m�ximo de " + args[1] + " integrante(s) por equipe).";
		}
	},
	ARQUIVO_MAL_FORMADO{
		public String msg(String... args) {
			return "Erro no envio! O pacote zip submetido possui arquivos que n�o s�o do tipo JAVA.";
		}
	},
	ALUNO_INEXISTENTE{
		public String msg(String... args) {
			return "Mudan�a de equipe n�o realizada! Aluno inexistente.";
		}
	},
	EQUIPE_INEXISTENTE{
		public String msg(String... args) {
			return "Mudan�a n�o realizada! Equipe inexistente.";
		}
	},
	EMAIL{
		public String msg(String... args) {
			return "N�o � poss�vel cadastrar o usu�rio, este e-mail j� existe.";
		}
	},
	LOGIN{
		public String msg(String... args) {
			return "N�o � poss�vel cadastrar o usu�rio, este login j� existe.";
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
