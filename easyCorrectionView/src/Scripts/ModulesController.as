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
	import mx.messaging.channels.SecureAMFChannel;
	import mx.modules.IModuleInfo;
	import mx.modules.ModuleManager;


	public class ModulesController {

		private static var info:IModuleInfo;
		private static var father:Container = null;
		private static var parameters:Array = new Array();
		private static var progress:ProgressBar;
		private static var panel:Panel;
		private static var popup:IFlexDisplayObject;

		public static function createModule(moduleName:String, fatherComponent:Container,
			param:Object):void {

			panel = new Panel();
			panel.width = 285;
			panel.height = 108;
			panel.setStyle("borderStyle", "solid");
			panel.setStyle("cornerRadius", 14);
			progress = new ProgressBar();
			progress.width = 246;
			progress.mode = "manual";
			progress.x = 13;
			progress.y = 0;
			progress.label = "CARREGANDO...";
			panel.addChild(progress);
			PopUpManager.addPopUp(panel, fatherComponent, true, PopUpManagerChildList.POPUP);
		    PopUpManager.centerPopUp(panel);

			father = fatherComponent;
			parameters.push(param);
			info = ModuleManager.getModule(moduleName + "?nocache=" + (new Date()).getTime());
    		info.addEventListener(ModuleEvent.READY, modEventHandler);
    		info.addEventListener(ModuleEvent.ERROR, modEventHandlerError);
    		info.addEventListener(ModuleEvent.PROGRESS, modEventHandlerProgress);
    		info.load(ApplicationDomain.currentDomain, SecurityDomain.currentDomain);
    		/*info.addEventListener(ModuleEvent.READY, modEventHandlerReady);
		    info.load();*/
		 } 
		  
		    

		private static function modEventHandlerError(event:ModuleEvent):void {
			Alert.show(event.errorText, "ERRO", Alert.OK, father);
		}

		private static function modEventHandlerReady(event:ModuleEvent):void {
			PopUpManager.removePopUp(panel);
		}

		private static function modEventHandlerProgress(event:ModuleEvent):void {
			progress.setProgress(event.bytesLoaded, event.bytesTotal);
		}

		private static function modEventHandler(event:ModuleEvent):void {
			popup = info.factory.create() as IFlexDisplayObject;
			unloadModule(panel);
			PopUpManager.addPopUp(popup, father, true, PopUpManagerChildList.POPUP);
		    //PopUpManager.centerPopUp(popup);
		    checkPopUpCentralization();
		}

		public static function unloadModule(popUp:IFlexDisplayObject):void {
			PopUpManager.removePopUp(popUp);
			info.unload();
		}

		public static function getParam():* {
			return parameters.pop();
		}
		
		private static function checkPopUpCentralization():void {
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

		public static function createChannel(destination:String, secureChannel:Boolean):ChannelSet {
			
			var channel:AMFChannel = null;
			
			if (secureChannel) {
				channel = new SecureAMFChannel("my-secure-amf", ExternalInterface.call('getUrlAMFCanalSeguro', destination));
			} else {
				channel = new AMFChannel("my-amf", ExternalInterface.call('getUrlAMFCanal', destination));
			}

			var channelSet:ChannelSet = new ChannelSet();
			channelSet.addChannel(channel);
			return channelSet;
		}
	}
}