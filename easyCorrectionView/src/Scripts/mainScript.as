import Scripts.ControladorDeModulos;
import bean.Utilitarios.Util;
import eventos.FechaPopupEvent;
import eventos.ObjetoEvent;
import eventos.RespostaLoginEvent;
import eventos.SubMenuEvent;
import eventos.UsuarioEvent;
import flash.net.SharedObject;
import flash.system.Capabilities;
import modulos.sistema.Home;

import mx.collections.*;
import mx.containers.ViewStack;
import mx.controls.Label;
import mx.controls.LinkBar;
import mx.controls.LinkButton;
import mx.controls.TabBar;
import mx.events.MenuEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;


public var largura: int = Capabilities.screenResolutionX;
public var altura: int = Capabilities.screenResolutionY;
			
[Bindable]
private var subMenusSistemas:Object = null;
private var stackTelas:ViewStack;
private var stackHome:ViewStack = new ViewStack();
private var stackMenuSistemas:ViewStack;
private var home:Home;
private var tabBar:TabBar;
private var setoresUsuario:Array;
private var usuario: *;

[Embed(source='/image/porta.png')]
private var icone:Class;

[Embed(source='/image/esquerda.png')]
private var iconeEnviar:Class;

[Embed(source='/image/help.png')]
private var iconeHelp:Class;

[Bindable]
private var usuarioLogado:Label;

[Bindable]
private var labelUsuario:Label;

[Bindable]
private var lbar:LinkBar;

[Bindable]
public var menuBarCollection:XMLListCollection;

private var menubarXML:XMLList = new XMLList();
private var urlExp: String = "";
private var atributo: String = "";
private var valor: String = "";
private var confirmado:Boolean = false;


private function inicializaMenus():void {
	
	menuBarCollection = new XMLListCollection(menubarXML);
	urlExp = ExternalInterface.call("window.location.toString").split("?")[1];
	try{
		atributo = urlExp.split("=")[0];	
		valor = urlExp.split("=")[1];
	}catch(e:Error){}
	
}

private function escutaEventos():void {
	menuBar.addEventListener(MenuEvent.ITEM_CLICK, menuItemPressionado);
	this.systemManager.addEventListener(RespostaLoginEvent.RespostaLogin_Event, montaXMLMenus);
	this.systemManager.addEventListener(UsuarioEvent.UsuarioEventConst, salvaCookie);
    this.systemManager.addEventListener(FechaPopupEvent.FechaPopup_Event, fechaJanela);
}

/**
 *	Gerador do tabBar dos Sistema associados.
 */
private function geraTabBar(): TabBar {

	//Array que será destrinchado.
	tabBar = new TabBar();
	labelUsuario = new Label();
	labelUsuario.id = "labelUsuario";
	labelUsuario.x = 170;
	labelUsuario.y = 10;
	labelUsuario.text = "Bem vindo(a) ";
	this.addChild(labelUsuario);

	usuarioLogado = new Label();
	usuarioLogado.x = labelUsuario.x + 100;
	usuarioLogado.y = 10;
	usuarioLogado.styleName = "valorLabel";
	this.addChild(usuarioLogado);

	tabBar.x = 263;
	tabBar.y = 56;
	tabBar.width = 620;
	tabBar.height = 30;
	return tabBar;
}

private function adicionaComponentes(): void{
	
	this.addChild(imagemELC);
	this.addChild(menuBar);
	this.addChild(viewPrincipal);
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
private function geraBLinks(): void {
	lbar = new LinkBar();
	lbar.x = 670;
	lbar.y = 10;
	lbar.height = 40;

	var usuarioSetorOutros:Boolean = false;
	//Verifica se o usuário é do setor outros, caso seja não exibimos o menu fale conosco
	/*
	for each (var idSetor:String in setoresUsuario) {
		if (idSetor === "184") {
			usuarioSetorOutros = true;
		}
	}*/
	var eButton:LinkButton = new LinkButton();
	eButton.label = "Fale Conosco!";
	eButton.setStyle("icon", iconeEnviar);
	eButton.styleName = "linkColor";
	eButton.toolTip = "Ver README.pdf";
	lbar.addChild(eButton);
	var hButton:LinkButton = new LinkButton();
	hButton.label = "Ajuda";
	hButton.setStyle("icon", iconeHelp);
	hButton.styleName = "linkColor";
	hButton.toolTip = "Baixar um arquivo tutorial (README.pdf)";
	lbar.addChild(hButton);
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
	adicionaComponentes();
	init();
}

private function fechaJanela(event:FechaPopupEvent):void {
	ControladorDeModulos.descarregaModulo(event.popUp);
}

private function montaXMLMenus(event:RespostaLoginEvent): void {
	var listaFuncoes: ArrayCollection = event.respostaLogin;
	var listaFuncoesPorMenu: ArrayCollection;
	var listaMenus: ArrayCollection = geraListaMenus(listaFuncoes);
	var StrXML: String = "<> ";
	for (var i:int = 0; i < listaMenus.length; i++){
		listaFuncoesPorMenu = new ArrayCollection();
		for (var j:int = 0; j < listaFuncoes.length; j++){
			if (listaMenus.getItemAt(i).idMenu == listaFuncoes.getItemAt(j).menu.idMenu){
				if (listaFuncoes.getItemAt(j).rotulo != "avaliacaoProjeto" && listaFuncoes.getItemAt(j).rotulo != "avaliacaoRelatorio"){
					listaFuncoesPorMenu.addItem(listaFuncoes.getItemAt(j));
				}
			}
		}
		StrXML += povoaMenuBar(listaMenus.getItemAt(i), listaFuncoesPorMenu);
	}
	StrXML += " </>";
	menubarXML = new XMLList(StrXML);
	menuBarCollection = new XMLListCollection(menubarXML);
	showHome();
}

private function geraListaMenus(listaFuncoes: ArrayCollection): ArrayCollection {
	var listaMenus: ArrayCollection = new ArrayCollection();
	for (var i:int = 0; i < listaFuncoes.length; i++){
		if (Util.pegaPosObjeto("idMenu",listaMenus, listaFuncoes.getItemAt(i).menu) == -1){
			listaMenus.addItem(listaFuncoes.getItemAt(i).menu);
		}
	}
	return listaMenus;
}

private function salvaCookie(event:UsuarioEvent):void{
	usuario = event.usuario;
	var cookie:SharedObject = SharedObject.getLocal("sistema", "/");
	cookie.data.idUsuario= usuario.idUsuario;
	cookie.data.loginUsuario = usuario.login;
	cookie.data.nomeUsuario = usuario.nome;
	usuarioLogado.text = "";
	usuarioLogado.text = usuario.nome;
	cookie.flush();
}

private function povoaMenuBar(menu: *, listaFuncoes: ArrayCollection): String {
	var strMenu : String = ' <menuitem label="' + menu.nome + '" data="' + menu.rotulo + '"> ';
    for (var i:int = 0; i < listaFuncoes.length; i++) 
    	strMenu += ' <menuitem label="' + listaFuncoes.getItemAt(i).nome + '" data="' + listaFuncoes.getItemAt(i).rotulo + '"/> '; 
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

private function geraTelaLogin():void {
	SharedObject.getLocal("sistema", "/").clear();
	this.visible = false;
	if (atributo != "senha" || confirmado) {
		ControladorDeModulos.criaModulo("modulos/sistema/Login.swf", this, null);
	}
	else {
		ControladorDeModulos.criaModulo("modulos/ConfirmacaoMembroCE.swf", this,
					[false, valor]);	
		confirmado = true;
	}
}

//Método responsável por atender aos eventos de menu item clicados
private function menuItemPressionado(event: MenuEvent):void  {
     
     //Caso o evento tenha sido num item de menu 'Setor' -> 'Cadastrar'
     if (event.item.@data == "acesso") {
     	ControladorDeModulos.criaModulo("modulos/ControleDeAcesso/Cadastros.swf", this, usuario);
     }
     if (event.item.@data == "defPerm") {
     	ControladorDeModulos.criaModulo("modulos/ControleDeAcesso/DefinirFuncoes.swf", this, "");
     }
     if (event.item.@data == "agendaRoteiros") {
     	ControladorDeModulos.criaModulo("modulos/Views/AgendamentoRoteiros.swf", this, "");
     }
     if (event.item.@data == "penalidades") {
     	ControladorDeModulos.criaModulo("modulos/Views/Penalidades.swf", this, "");
     }
     if (event.item.@data == "atribuicaoDeRoteiros") {
     	ControladorDeModulos.criaModulo("modulos/Views/AtribuicaoDeRoteiros.swf", this, "");
     }
     if (event.item.@data == "notas") {
     	ControladorDeModulos.criaModulo("modulos/Views/VisualizacaoDeNotas.swf", this, "");
     }
     if (event.item.@data == "avaliacaoDeRoteiros") {
     	ControladorDeModulos.criaModulo("modulos/Views/AvaliacaoDeRoteiros.swf", this, "");
     }
     if (event.item.@data == "submissaoDeRoteiros") {
     	ControladorDeModulos.criaModulo("modulos/Views/SubmissaoDeRoteiros.swf", this, "");
     }
     if (event.item.@data == "chat") {
     	ControladorDeModulos.criaModulo("modulos/Views/Chat.swf", this, "");
     }
     if (event.item.@data == "criaEquipe") {
     	ControladorDeModulos.criaModulo("modulos/Views/CriaEquipe.swf", this, "");
     }
     if (event.item.@data == "reinicializaBD") {
     	ControladorDeModulos.criaModulo("modulos/Views/ReinicializaBancoDeDados.swf", this, "");
     }
}

/**
 * Método para iniciar a aplicação (é chamado quando a aplicação inicia no browser)
 */
private function init():void {
	facade.channelSet = ControladorDeModulos.criaCanal();
	inicializaMenus();
	escutaEventos();
	geraBLinks();
	geraTelaLogin();
}

private function falha(event:FaultEvent):void {
	var mensagem:String;
	try{
		if (event.fault.rootCause.message == null) {
			mensagem = event.fault.faultString;
		} else {
			mensagem = event.fault.rootCause.message;
		}
	}
	catch(e:Error){
		mensagem = "Serviço indisponível, reinicie a operação.";
	}
	ControladorDeModulos.criaModulo("modulos/sistema/MensagemStatus.swf", this, new Array(true, mensagem));
}