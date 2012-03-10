package bean.Utility{
	
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.controls.Label;
	import mx.events.FlexEvent;
       	
	 public class DateTimeLabel extends Label  {
	 	
           private var dtNow:Date = new Date();
           private var now:Date = new Date();
           private var timer:Timer;
           private var timeDifference: Number;
           private var sMonth:Array = new Array(
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
           private var sWeekDay:Array = new Array(
                   "Sunday",
                   "Monday",
                   "Tuesday",
                   "Wednesday",
                   "Thursday",
                   "Friday",
                   "Saturday");
           
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
           	   dtNow.setTime(dtNow.getTime() + 1000);
               this.text = sMonth[dtNow.getMonth()] + " " + (dtNow.dateUTC < 10 ? "0" + dtNow.dateUTC:dtNow.dateUTC) + ", " +
                   dtNow.getFullYear() + ". " + String(sWeekDay[dtNow.dayUTC] + " – " +
                   (dtNow.getHours()   < 10 ? "0" + dtNow.getHours()   : dtNow.getHours()) + ":" +
                   (dtNow.getMinutes() < 10 ? "0" + dtNow.getMinutes() : dtNow.getMinutes()) + ":" +
                   (dtNow.getSeconds() < 10 ? "0" + dtNow.getSeconds() : dtNow.getSeconds())
               );
           }
       }
	
	
}