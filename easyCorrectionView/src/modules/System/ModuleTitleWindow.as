////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2003-2006 Adobe Macromedia Software LLC and its licensors.
//  All Rights Reserved. The following is Source Code and is subject to all
//  restrictions on such code as contained in the End User License Agreement
//  accompanying this product.
//
////////////////////////////////////////////////////////////////////////////////

package modules.System {

	import mx.containers.TitleWindow;
	
	[Frame(factoryClass="mx.core.FlexModuleFactory")]
	
	/**
	 *  The base class for MXML-based dynamically-loadable modules. You extend this
	 *  class by using the <code>&lt;mx:Module&gt;</code> tag in an MXML file, as the
	 *  following example shows:
	 *  
	 *  <PRE>
	 *  &lt;?xml version="1.0"?&gt;
	 *  &lt;!-- This module loads an image. --&gt;
	 *  &lt;mx:Module  width="100%" height="100%" xmlns:mx="http://www.adobe.com/2006/mxml"&gt;
	 *  
	 *    &lt;mx:Image source="trinity.gif"/&gt;
	 *  
	 *  &lt;/mx:Module&gt;
	 *  </PRE>
	 *  
	 *  <p>If you want to create an ActionScript-based module, extend the ModuleBase class instead.</p>
	 *  
	 *  @see mx.modules.ModuleBase
	 */
	public class ModuleTitleWindow extends TitleWindow {
	    /**
	     *  Constructor.
	     */
	    public function ModuleTitleWindow() {
	        super();
	        toolTip = "Segure e arraste a janela pela barra de títulos (onde encontra-se o nome da janela).";
	    }
	}
}
