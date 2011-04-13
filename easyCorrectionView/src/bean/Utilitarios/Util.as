// ActionScript file
package bean.Utilitarios {
	import com.adobe.utils.StringUtil;
	
	import flash.net.URLRequest;
	import flash.net.URLVariables;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.formatters.DateFormatter;
	
	public class Util{
	
		public static function formataValor(quantidade: String): String{
			var st:Array = quantidade.split(".");
			if (st.length == 1) return st[0] + ",00";
			else return st[0] + "," + st[1];
		}
		
		public static function concat(vec:Array): String{
			var str: String = "";
			for(var i:int = 0; i < vec.length; i++){
				str += vec[i];
			}
			return str;
		}
		
		public static function formataTextValorToNumber(quantidade: String): Number{
			var valorIntermediario: String = concat(quantidade.split("."));
			return Number(valorIntermediario.replace(",", "."));		
		}
		
		public static function formataTextValorToNumber2(quantidade: String): Number{
			try{
				var valorIntermediario: String = concat(quantidade.split("_"));
				var num: Number = new Number(valorIntermediario.replace(",", "."))
				num.toPrecision(2);
			}
			catch(e: Error){
				num = 0;
			}
			return num;	
		}
		
		public static function formataData(data: Date): String{
			var df:DateFormatter = new DateFormatter();
			df.formatString = "DD/MM/YYYY";
			return df.format(data);
		}
		
		public static function pegaPosObjeto(atributoComp: String, lista: ArrayCollection, objeto: *): int{
			for(var i:int = 0; i < lista.length; i++){
				if (objeto[atributoComp] == lista.getItemAt(i)[atributoComp]){
					return i;
				}
			}
			return -1;
		}
		
		public static function gerarRelatorio(parametros:URLVariables, request:URLRequest): void {
			request.data = parametros;
			request.method = "POST";
			navigateToURL(request);
		}
		
		public static function geraCodigosHTML(mensagem: String): String {
			
			mensagem = StringUtil.replace(mensagem, "á", "&aacute;"); 
			mensagem = StringUtil.replace(mensagem, "â", "&acirc;");
			mensagem = StringUtil.replace(mensagem, "à", "&agrave;");
			mensagem = StringUtil.replace(mensagem, "ã", "&atilde;");
			mensagem = StringUtil.replace(mensagem, "ç", "&ccedil;");
			mensagem = StringUtil.replace(mensagem, "é", "&eacute;");
			mensagem = StringUtil.replace(mensagem, "ê", "&ecirc;");
			mensagem = StringUtil.replace(mensagem, "í", "&iacute;");
			mensagem = StringUtil.replace(mensagem, "ó", "&oacute;");
			mensagem = StringUtil.replace(mensagem, "ô", "&ocirc;");
			mensagem = StringUtil.replace(mensagem, "õ", "&otilde;");
			mensagem = StringUtil.replace(mensagem, "ú", "&uacute;");
			mensagem = StringUtil.replace(mensagem, "Á", "&Aacute;");
			mensagem = StringUtil.replace(mensagem, "Â", "&Acirc;");
			mensagem = StringUtil.replace(mensagem, "À", "&Agrave;");
			mensagem = StringUtil.replace(mensagem, "Ã", "&Atilde;");
			mensagem = StringUtil.replace(mensagem, "Ç", "&Ccedil;");
			mensagem = StringUtil.replace(mensagem, "É", "&Eacute;");
			mensagem = StringUtil.replace(mensagem, "Ê", "&Ecirc;");
			mensagem = StringUtil.replace(mensagem, "Í", "&Iacute;");
			mensagem = StringUtil.replace(mensagem, "Ó", "&Oacute;");
			mensagem = StringUtil.replace(mensagem, "Ô", "&Ocirc;");
			mensagem = StringUtil.replace(mensagem, "Õ", "&Otilde;");
			mensagem = StringUtil.replace(mensagem, "Ú", "&Uacute;");
			return mensagem;
			
		}
		
		public static function formataDataStringEmDate(data:String):Date{
			var dat:Date = new Date();
			var datStr:String = "";
			var arr:Array = new Array();
			arr = data.split("/");
			datStr = arr[2]+"/"+arr[1]+"/"+arr[0];
			dat = new Date(Date.parse(datStr));
			dat.setHours(12,0,0,0);
			return  dat;
		}
		
		public static function gerarSenha(tamanho: int): String{
			var randTexto: String = "";
			var chars: String = "1234567890" + "abcdefghijklmnopqrstuvywxz";
			for(var i: int = 0; i < tamanho; i++){
				randTexto += chars.substr((Math.floor(Math.random()*(chars.length-1))+1),1);
			}
			return randTexto;
		}
		
		public static function organizaUnidadeTempo(unidade: String): String{
			if(unidade.length == 1){
				return "0" + unidade;
			}
			return unidade;
		}
	}
}