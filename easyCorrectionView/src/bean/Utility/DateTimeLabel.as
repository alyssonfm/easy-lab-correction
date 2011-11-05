package bean.Utility{
	
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.controls.Label;
	import mx.events.FlexEvent;
       	
	 public class DateTimeLabel extends Label  {
	 	
           private var dtNow:Date = new Date();
           private var timer:Timer;
           
           public function DateTimeLabel()  {
               super();
               this.addEventListener(FlexEvent.CREATION_COMPLETE,startTimer);
           }
           
           public function updateTime(dateNow: Date): void{
           	   this.dtNow = dateNow;
           }
           
           private function startTimer(e:FlexEvent):void{
               timer = new Timer(1000);
               timer.addEventListener("timer", updateHour);
               timer.start();
           }
           
           private function updateHour(event:TimerEvent):void{
               var now:Date = new Date();
               var sMonth:Array = new Array(
                   "January",
                   "February",
                   "March",
                   "April",
                   "May",
                   "June",
                   "July",
                   "August",
                   "September",
                   "October",
                   "November",
                   "December");
               var sWeekDay:Array = new Array(
                   "Sunday",
                   "Monday",
                   "Tuesday",
                   "Wednesday",
                   "Thursday",
                   "Friday",
                   "Saturday");
               this.text = (dtNow.dateUTC < 10 ? sMonth[now.getMonth()] + " " +
                   "0" + dtNow.dateUTC:dtNow.dateUTC) +", "+
                   now.getFullYear() + ". " + String(sWeekDay[now.dayUTC] + " – "+
                   (now.getHours()   < 10 ? "0" + now.getHours()   :now.getHours()) + ":" +
                   (now.getMinutes() < 10 ? "0" + now.getMinutes() : now.getMinutes()) + ":" +
                   (now.getSeconds() < 10 ? "0" + now.getSeconds() :now.getSeconds())
               );
           }
       }
	
	
}