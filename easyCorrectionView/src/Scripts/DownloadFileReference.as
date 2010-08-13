package Scripts{
	
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.net.FileReference;
	import flash.net.URLRequest;
	
	public class DownloadFileReference {
		
		private var fileRef:FileReference;
		
		private const FILE_UPLOAD_URL:String = "http://servicosweb.cnpq.br/srvcurriculo/servlet/ServletID?nome=Gabriela Maria Cavalcanti Costa&data=09/07/1974";
		
		public function init():void {
		    fileRef = new FileReference();
		    fileRef.addEventListener(Event.SELECT, fileRef_select);
		    //fileRef.addEventListener(ProgressEvent.PROGRESS, fileRef_progress);
		    //fileRef.addEventListener(Event.COMPLETE, fileRef_complete);
		    download();
		    //browseAndUpload();
		}
		
		public function browseAndUpload():void {
		    fileRef.browse();
		}
		
		public function fileRef_select(evt:Event):void {
		    try {
		        var request:URLRequest = new URLRequest(FILE_UPLOAD_URL);
		        fileRef.upload(request);
		    } catch (err:Error) {
		    }
		}
		
		public function download():void {
			try {
		        var request:URLRequest = new URLRequest(FILE_UPLOAD_URL);
		        fileRef.download(request);
		    } catch (err:Error) {
		    }
		}
		
		public function fileRef_progress(evt:ProgressEvent):void {
		    //progressBar.visible = true;
		}
		
		public function fileRef_complete(evt:Event):void {
		    //message.text += " (complete)";
		    //progressBar.visible = false;
		}
	}
}