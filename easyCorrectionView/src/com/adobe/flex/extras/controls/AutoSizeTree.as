// ActionScript file
package com.adobe.flex.extras.controls
{
	import flash.events.Event;
	import mx.controls.Tree;
	import mx.core.mx_internal;
	import mx.core.ScrollPolicy;
	import mx.events.TreeEvent;
	 
	public class AutoSizeTree extends Tree
	{
	     public function AutoSizeTree(){
	          super();
	          horizontalScrollPolicy = ScrollPolicy.AUTO;
	     }
	 
	     // we need to override maxHorizontalScrollPosition because setting
	     // Tree's maxHorizontalScrollPosition adds an indent value to it,
	     // which we don't need as measureWidthOfItems seems to return exactly
	     // what we need.  Not only that, but getIndent() seems to be broken
	     // anyways (SDK-12578).
	 
	     // I hate using mx_internal stuff, but we can't do
	     // super.super.maxHorizontalScrollPosition in AS 3, so we have to
	     // emulate it.
	     override public function get maxHorizontalScrollPosition():Number
	     {
	          if (isNaN(mx_internal::_maxHorizontalScrollPosition))
	              return 0;
	 
	          return mx_internal::_maxHorizontalScrollPosition;
	     }
	 
	     override public function set maxHorizontalScrollPosition(value:Number):void
	     {
	          mx_internal::_maxHorizontalScrollPosition = value;
	          dispatchEvent(new Event("maxHorizontalScrollPositionChanged"));
	 
	          scrollAreaChanged = true;
	          invalidateDisplayList();
	     }
	 
	     override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
	     {
	          // we call measureWidthOfItems to get the max width of the item renderers.
	          // then we see how much space we need to scroll, setting maxHorizontalScrollPosition appropriately
	          var diffWidth:Number = measureWidthOfItems(0,0) - (unscaledWidth - viewMetrics.left - viewMetrics.right);
	 
	          if (diffWidth <= 0)
	              maxHorizontalScrollPosition = NaN;
	          else
	              maxHorizontalScrollPosition = diffWidth;
	 
	          super.updateDisplayList(unscaledWidth, unscaledHeight);
	     }
	}
}