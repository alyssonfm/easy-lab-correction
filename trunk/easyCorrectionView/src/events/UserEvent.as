package events {
	import bean.user.User;
	
	import flash.events.Event;
	
	public class UserEvent extends Event {
		public var user:*;
		
		public static const UserEventConst:String = "userConst";
		
		public function UserEvent(user:*) {
			super(UserEventConst, true, true); //bubble by default
			this.user = user;
		}

		/**
		 *	Método responsável por clonar este objeto de evento
		 */
		override public function clone():Event {
			return new UserEvent(user); // bubbling support inside
		}
	}
}