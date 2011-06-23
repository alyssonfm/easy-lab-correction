import Scripts.ModulesController;
import bean.Utility.Util;
import events.*;
import flash.net.SharedObject;
import flash.system.Capabilities;
import modules.System.Home;
import mx.collections.*;
import mx.containers.ViewStack;
import mx.controls.Label;
import mx.controls.LinkBar;
import mx.controls.LinkButton;
import mx.controls.TabBar;
import mx.events.MenuEvent;
import mx.rpc.events.FaultEvent;
			
[Bindable]
private var subMenusSistemas:Object = null;
private var stackTelas:ViewStack;
private var stackHome:ViewStack = new ViewStack();
private var stackMenuSistemas:ViewStack;
private var home:Home;
private var tabBar:TabBar;
private var user: *;

[Embed(source='/image/porta.png')]
private var icone:Class;

[Embed(source='/image/esquerda.png')]
private var iconeEnviar:Class;

[Embed(source='/image/help.png')]
private var iconeHelp:Class;

[Bindable]
private var loggedUser:Label;

[Bindable]
private var userLabel:Label;

[Bindable]
private var lbar:LinkBar;

[Bindable]
public var menuBarCollection:XMLListCollection;

private var menubarXML:XMLList = new XMLList();
private var urlExp: String = "";
private var attribute: String = "";
private var value: String = "";
private var confirmed:Boolean = false;


private function inicializeMenus():void {
	
	menuBarCollection = new XMLListCollection(menubarXML);
	urlExp = ExternalInterface.call("window.location.toString").split("?")[1];
	try{
		attribute = urlExp.split("=")[0];	
		value = urlExp.split("=")[1];
	}catch(e:Error){}
	
}

private function listenToEvents():void {
	menuBar.addEventListener(MenuEvent.ITEM_CLICK, PressedMenuItem);
	this.systemManager.addEventListener(EventLogResponse.ResponseLogin_Event, mountXMLMenus);
	this.systemManager.addEventListener(UserEvent.UserEventConst, saveCookie);
    this.systemManager.addEventListener(ClosePopupEvent.ClosePopup_Event, closeWindow);
}

/**
 *	Gerador do tabBar dos Sistema associados.
 */
private function geraTabBar(): TabBar {

	//Array que será destrinchado.
	tabBar = new TabBar();
	userLabel = new Label();
	userLabel.id = "labelUsuario";
	userLabel.x = 170;
	userLabel.y = 10;
	userLabel.text = "Bem vindo(a) ";
	this.addChild(userLabel);

	loggedUser = new Label();
	loggedUser.x = userLabel.x + 100;
	loggedUser.y = 10;
	loggedUser.styleName = "valorLabel";
	this.addChild(loggedUser);

	tabBar.x = 263;
	tabBar.y = 56;
	tabBar.width = 620;
	tabBar.height = 30;
	return tabBar;
}

private function addComponents(): void{
	
	this.addChild(imageELC);
	this.addChild(menuBar);
	this.addChild(mainView);
	this.addChild(hr);
	this.addChild(vr);
}

/**
 * Dispatcher.
 */
private function dispatcher(event:MenuEvent): void{
	dispatchEvent(new SubMenuEvent(event.item));
}

/**
 * Gera o linkBar do botãos superiores.
 */
private function generateBLinks(): void {
	lbar = new LinkBar();
	lbar.x = 900;
	lbar.y = 10;
	lbar.height = 40;

	var usuarioSetorOutros:Boolean = false;
	var sButton:LinkButton = new LinkButton();
	sButton.label = "Sair";
	sButton.setStyle("icon", icone);
	sButton.styleName = "linkColor";
	sButton.addEventListener(MouseEvent.CLICK, view);
	sButton.toolTip = "O usuário sai do sistema (realizar logout).";
	lbar.addChild(sButton);
	
	this.addChild(geraTabBar());
	this.addChild(lbar);
}

/**
 * Gera o view principal do fluxo do programa.
 */
private function view(event:MouseEvent): void {
	this.visible = false;
	this.removeAllChildren();
	addComponents();
	init();
}

private function closeWindow(event:ClosePopupEvent):void {
	ModulesController.unloadModule(event.popUp);
}

private function mountXMLMenus(event:EventLogResponse): void {
	var functionList: ArrayCollection = event.responseLogin;
	var functionListByMenu: ArrayCollection;
	var menuList: ArrayCollection = generateMenuList(functionList);
	var StrXML: String = "<> ";
	for (var i:int = 0; i < menuList.length; i++){
		functionListByMenu = new ArrayCollection();
		for (var j:int = 0; j < functionList.length; j++){
			if (menuList.getItemAt(i).menuId == functionList.getItemAt(j).menu.menuId){
				if (functionList.getItemAt(j).label != "avaliacaoProjeto" && functionList.getItemAt(j).label != "avaliacaoRelatorio"){
					functionListByMenu.addItem(functionList.getItemAt(j));
				}
			}
		}
		StrXML += fillsMenuBar(menuList.getItemAt(i), functionListByMenu);
	}
	StrXML += " </>";
	menubarXML = new XMLList(StrXML);
	menuBarCollection = new XMLListCollection(menubarXML);
	showHome();
}

private function generateMenuList(functionList: ArrayCollection): ArrayCollection {
	var menuList: ArrayCollection = new ArrayCollection();
	for (var i:int = 0; i < functionList.length; i++){
		if (Util.objectTakesPost("menuId", menuList, functionList.getItemAt(i).menu) == -1){
			menuList.addItem(functionList.getItemAt(i).menu);
		}
	}
	return menuList;
}

private function saveCookie(event:UserEvent):void{
	user = event.user;
	var cookie:SharedObject = SharedObject.getLocal("sistema", "/");
	cookie.data.idUsuario= user.userId;
	cookie.data.loginUsuario = user.login;
	cookie.data.nomeUsuario = user.name;
	loggedUser.text = "";
	loggedUser.text = user.name;
	cookie.flush();
}

private function fillsMenuBar(menu: *, functionList: ArrayCollection): String {
	var strMenu : String = ' <menuitem label="' + menu.name + '" data="' + menu.label + '"> ';
    for (var i:int = 0; i < functionList.length; i++) 
    	strMenu += ' <menuitem label="' + functionList.getItemAt(i).name + '" data="' + functionList.getItemAt(i).label + '"/> '; 
    strMenu += ' </menuitem> ';
	return strMenu;
}

private function showHome():void {
	this.visible = true;
	stackHome = new ViewStack;
	stackHome.x = 28;
	stackHome.y = 170;
	home = new Home();
	stackHome.addChild(home);
	this.addChild(stackHome);
}

private function generateLoginScreen():void {
	SharedObject.getLocal("sistema", "/").clear();
	this.visible = false;
	if (attribute != "senha" || confirmed) {
		ModulesController.createModule("modules/System/Login.swf", this, null);
	}
	else {
		ModulesController.createModule("modules/ConfirmacaoMembroCE.swf", this,
					[false, value]);	
		confirmed = true;
	}
}

//Método responsável por atender aos eventos de menu item clicados
private function PressedMenuItem(event: MenuEvent):void  {
     
     //Caso o evento tenha sido num item de menu 'Setor' -> 'Cadastrar'
     if (event.item.@data == "acesso") {
     	ModulesController.createModule("modules/AccessControl/Registration.swf", this, user);
     }
     if (event.item.@data == "defPerm") {
     	ModulesController.createModule("modules/AccessControl/Permissions.swf", this, "");
     }
     if (event.item.@data == "agendaRoteiros") {
     	ModulesController.createModule("modules/Views/SchedulingAssignment.swf", this, "");
     }
     if (event.item.@data == "penalidades") {
     	ModulesController.createModule("modules/Views/Penalidades.swf", this, "");
     }
     if (event.item.@data == "atribuicaoDeRoteiros") {
     	ModulesController.createModule("modules/Views/AllocateAssignment.swf", this, "");
     }
     if (event.item.@data == "notas") {
     	ModulesController.createModule("modules/Views/viewNotes.swf", this, "");
     }
     if (event.item.@data == "avaliacaoDeRoteiros") {
     	ModulesController.createModule("modules/Views/AssignmentAssessment.swf", this, "");
     }
     if (event.item.@data == "submissaoDeRoteiros") {
     	ModulesController.createModule("modules/Views/SubmissionOfAssignment.swf", this, "");
     }
     if (event.item.@data == "chat") {
     	ModulesController.createModule("modules/Views/Chat.swf", this, "");
     }
     if (event.item.@data == "criaEquipe") {
     	ModulesController.createModule("modules/Views/CreateTeam.swf", this, "");
     }
     if (event.item.@data == "reinicializaBD") {
     	ModulesController.createModule("modules/Views/RebootsDatabase.swf", this, "");
     }
}

/**
 * Método para iniciar a aplicação (é chamado quando a aplicação inicia no browser)
 */
private function init():void {
	inicializeMenus();
	listenToEvents();
	generateBLinks();
	generateLoginScreen();
}

private function falha(event:FaultEvent):void {
	var message:String;
	try{
		if (event.fault.rootCause.message == null) {
			message = event.fault.faultString;
		} else {
			message = event.fault.rootCause.message;
		}
	}
	catch(e:Error){
		message = "Serviço indisponível, reinicie a operação.";
	}
	ModulesController.createModule("modules/System/MessageStatus.swf", this, new Array(true, message));
}