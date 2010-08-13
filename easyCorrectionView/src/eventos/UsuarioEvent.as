package eventos {
	import bean.acesso.Usuario;
	import flash.events.Event;
	
	public class UsuarioEvent extends Event {
		public var usuario:*;
		
		public static const UsuarioEventConst:String = "usuarioConst";
		
		public function UsuarioEvent(usuario:*) {
			super(UsuarioEventConst, true, true); //bubble by default
			this.usuario = usuario;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new UsuarioEvent(usuario); // bubbling support inside
		}
	}
}