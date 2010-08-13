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
			return "Operacao de \""+args[0]+"\"nao realizada. "+args[1];
		}
	},
	DATA_INVALIDA{
		public String msg(String... args){
			return "Data invalida para o campo \""+args[0]+"\".";
		}
		public String toString(){
			return "Data invalida";
		}
	},
	VIOLACAO_CONSTRAINT{
		public String msg(String... args) {
			return "\""+args[0]+"\" duplicado(a).";
		}
	},
	ESTOQUE_INSUFICIENTE{
		public String msg(String... args) {
				return "Quantidade insuficiente em estoque. Quantidade disponivel: "+args[0]+".";
			}
	},
	DAR_BAIXA{
		public String msg(String... args) {
				return "Produto ja atendido para a solicitacao: "+args[0]+".";
			}
	}, 
	
	ESTOQUE_MINIMO{
		public String msg(String... args) {
				return "Produto atingiu o estoque minimo";
			}
	},
	
	SOLIC_NAO_AUTORIZADA{
		public String msg(String... args) {
			return "Solicita��o ainda n�o foi autorizada.";
		}
	},
	
	TAXA_BANCADA{
		public String msg(String... args) {
			return "O projeto já possui duas taxas de bancada.";
		}
	},
	NUM_PLANOS_PROJETO{
		public String msg(String... args) {
			return "O projeto não pode ter duas taxas de bancada. Número de planos de trabalho inferior a três.";
		}
	},
	ALUNOS_PROJETO{
		public String msg(String... args) {
			return "Não é possível cadastrar este aluno para este projeto. O número máximo de alunos por projeto foi excedido.";
		}
	},
	ALUNO_CADASTRADO{
		public String msg(String... args) {
			return "O aluno já participa de algum projeto nessa cota.";
		}
	},
	ALUNO_CRE{
		public String msg(String... args) {
			return "O aluno possui CRE inferior a 7.0.";
		}
	},
	ALUNO_MATRICULADO{
		public String msg(String... args) {
			return "O aluno não matriculado na instituição.";
		}
	},
	BOLSISTA{
		public String msg(String... args) {
			return "Esse projeto já possui um bolsista.";
		}
	};
	
	public String msg(String... args) {
		return "ERRO 499";
	}


}
