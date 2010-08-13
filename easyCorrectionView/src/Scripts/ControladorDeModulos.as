package Scripts {
	import flash.external.ExternalInterface;
	import flash.system.ApplicationDomain;
	import flash.system.Capabilities;
	import flash.system.SecurityDomain;
	
	import mx.containers.Panel;
	import mx.controls.Alert;
	import mx.controls.ProgressBar;
	import mx.core.Container;
	import mx.core.IFlexDisplayObject;
	import mx.events.ModuleEvent;
	import mx.managers.PopUpManager;
	import mx.managers.PopUpManagerChildList;
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.modules.IModuleInfo;
	import mx.modules.ModuleManager;


	public class ControladorDeModulos {

		private static var info:IModuleInfo;
		private static var pai:Container = null;
		private static var parametros:Array = new Array();
		private static var progresso:ProgressBar;
		private static var panel:Panel;
		private static var popup:IFlexDisplayObject;

		public static function criaModulo(nomeModulo:String, componentePai:Container,
			param:Object):void {

			panel = new Panel();
			panel.width = 285;
			panel.height = 108;
			panel.setStyle("borderStyle", "solid");
			panel.setStyle("cornerRadius", 14);
			progresso = new ProgressBar();
			progresso.width = 246;
			progresso.mode = "manual";
			progresso.x = 13;
			progresso.y = 0;
			progresso.label = "CARREGANDO...";
			panel.addChild(progresso);
			PopUpManager.addPopUp(panel, componentePai, true, PopUpManagerChildList.POPUP);
		    PopUpManager.centerPopUp(panel);

			pai = componentePai;
			parametros.push(param);
			info = ModuleManager.getModule(nomeModulo + "?nocache=" + (new Date()).getTime());
    		info.addEventListener(ModuleEvent.READY, modEventHandler);
    		info.addEventListener(ModuleEvent.ERROR, modEventHandlerError);
    		info.addEventListener(ModuleEvent.PROGRESS, modEventHandlerProgress);
    		info.load(ApplicationDomain.currentDomain, SecurityDomain.currentDomain);
    		/*info.addEventListener(ModuleEvent.READY, modEventHandlerReady);
		    info.load();*/
		 } 
		  
		    

		private static function modEventHandlerError(event:ModuleEvent):void {
			Alert.show(event.errorText, "ERRO", Alert.OK, pai);
		}

		private static function modEventHandlerReady(event:ModuleEvent):void {
			PopUpManager.removePopUp(panel);
		}

		private static function modEventHandlerProgress(event:ModuleEvent):void {
			progresso.setProgress(event.bytesLoaded, event.bytesTotal);
		}

		private static function modEventHandler(event:ModuleEvent):void {
			popup = info.factory.create() as IFlexDisplayObject;
			descarregaModulo(panel);
			PopUpManager.addPopUp(popup, pai, true, PopUpManagerChildList.POPUP);
		    //PopUpManager.centerPopUp(popup);
		    verificaCentralizacaoPopup();
		}

		public static function descarregaModulo(popUp:IFlexDisplayObject):void {
			PopUpManager.removePopUp(popUp);
			info.unload();
		}

		public static function getParam():* {
			return parametros.pop();
		}
		
		private static function verificaCentralizacaoPopup():void {
			var sizeHeight:Number = Capabilities.screenResolutionY - 183;
			if (popup.width > Capabilities.screenResolutionX) {
				popup.width = Capabilities.screenResolutionX;
			}
			
			if (popup.height > sizeHeight) {
				popup.height = sizeHeight;
			}
	
			popup.x = (Capabilities.screenResolutionX - popup.width) / 2;
			popup.y = (sizeHeight - popup.height) / 2;
		}

		public static function criaCanal():ChannelSet {
			var channel:AMFChannel =
				new AMFChannel("my-amf", ExternalInterface.call('getUrlAMFCanal'));

			var channelSet:ChannelSet = new ChannelSet();
			channelSet.addChannel(channel);
			return channelSet;
		}
	}
}