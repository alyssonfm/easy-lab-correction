// ActionScript file
package bean.Utility {
	import com.adobe.utils.StringUtil;
	
	import flash.net.URLRequest;
	import flash.net.URLVariables;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.formatters.DateFormatter;
	
	public class Util{
	
		public static function formatsValue(amount: String): String{
			var st:Array = amount.split(".");
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
		
		public static function formatsTextValueToNumber(amount: String): Number{
			var intermediateValue: String = concat(amount.split("."));
			return Number(intermediateValue.replace(",", "."));		
		}
		
		public static function formatsTextValueToNumber2(amount: String): Number{
			try{
				var intermediateValue: String = concat(amount.split("_"));
				var num: Number = new Number(intermediateValue.replace(",", "."))
				num.toPrecision(2);
			}
			catch(e: Error){
				num = 0;
			}
			return num;	
		}
		
		public static function formatsDate(data: Date): String{
			var df:DateFormatter = new DateFormatter();
			df.formatString = "DD/MM/YYYY";
			return df.format(data);
		}
		
		public static function objectTakesPost(attributeComp: String, list: ArrayCollection, object: *): int{
			for(var i:int = 0; i < list.length; i++){
				if (object[attributeComp] == list.getItemAt(i)[attributeComp]){
					return i;
				}
			}
			return -1;
		}
		
		public static function generateReport(parameters:URLVariables, request:URLRequest): void {
			request.data = parameters;
			request.method = "POST";
			navigateToURL(request);
		}
		
		public static function generateHTMLCode(message: String): String {
			
			message = StringUtil.replace(message, "á", "&aacute;"); 
			message = StringUtil.replace(message, "â", "&acirc;");
			message = StringUtil.replace(message, "à", "&agrave;");
			message = StringUtil.replace(message, "ã", "&atilde;");
			message = StringUtil.replace(message, "ç", "&ccedil;");
			message = StringUtil.replace(message, "é", "&eacute;");
			message = StringUtil.replace(message, "ê", "&ecirc;");
			message = StringUtil.replace(message, "í", "&iacute;");
			message = StringUtil.replace(message, "ó", "&oacute;");
			message = StringUtil.replace(message, "ô", "&ocirc;");
			message = StringUtil.replace(message, "õ", "&otilde;");
			message = StringUtil.replace(message, "ú", "&uacute;");
			message = StringUtil.replace(message, "Á", "&Aacute;");
			message = StringUtil.replace(message, "Â", "&Acirc;");
			message = StringUtil.replace(message, "À", "&Agrave;");
			message = StringUtil.replace(message, "Ã", "&Atilde;");
			message = StringUtil.replace(message, "Ç", "&Ccedil;");
			message = StringUtil.replace(message, "É", "&Eacute;");
			message = StringUtil.replace(message, "Ê", "&Ecirc;");
			message = StringUtil.replace(message, "Í", "&Iacute;");
			message = StringUtil.replace(message, "Ó", "&Oacute;");
			message = StringUtil.replace(message, "Ô", "&Ocirc;");
			message = StringUtil.replace(message, "Õ", "&Otilde;");
			message = StringUtil.replace(message, "Ú", "&Uacute;");
			return message;
			
		}
		
		public static function formatsDateStringEmDate(data:String):Date{
			var dat:Date = new Date();
			var datStr:String = "";
			var arr:Array = new Array();
			arr = data.split("/");
			datStr = arr[2]+"/"+arr[1]+"/"+arr[0];
			dat = new Date(Date.parse(datStr));
			dat.setHours(12,0,0,0);
			return  dat;
		}
		
		public static function generatePassword(size: int): String{
			var randText: String = "";
			var chars: String = "1234567890" + "abcdefghijklmnopqrstuvywxz";
			for(var i: int = 0; i < size; i++){
				randText += chars.substr((Math.floor(Math.random()*(chars.length-1))+1),1);
			}
			return randText;
		}
		
		public static function unitOrganizesTime(unit: String): String{
			if(unit.length == 1){
				return "0" + unit;
			}
			return unit;
		}
	}
}