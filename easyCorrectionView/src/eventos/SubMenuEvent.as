package eventos {
	import flash.events.Event;

	/**
	 * Classe que representa um evento que pode ser lançado pelo
	 * componente SubMenuSistema
	 * @author Jackson Lima
	 */
	public class SubMenuEvent extends Event {
		/*
		 *	Referência ao objeto clicado no menu de subsistema
		 *	(item do menu)
		 */
		public var menuItem:Object;
		
		/*
		 *	Nome do evento de menu
		 */
		public static const SUB_MENU_EVENT:String = "SubMenuEvent";
		
		/**
		 * Construtor para a esta classe de evento
		 */
		public function SubMenuEvent(menuItem:Object) {
			super(SUB_MENU_EVENT, true, true); //Passa o evento para os componentes pai
			this.menuItem = menuItem;
		}

		/*
		 *	Método responsável por clonar esse objeto de evento
		 */
		override public function clone():Event {
			return new SubMenuEvent(this.menuItem);
		}
	}
}