package br.edu.ufcg.easyLabCorrection.webApp;

import java.util.Timer;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;


/**
 *  Class responsible for scheduling activities in the system.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class ELCScheduler {
	
	Timer timer;
	public static boolean start = false;
	
	/**
	 * Constructor of class that schedule activities on the system.<br>
	 * @param day The day of scheduling.<br>
	 * @param hour The hour of scheduling.<br>
	 * @param minute The minute of scheduling.<br>
	 */
	public ELCScheduler(int day, int hour, int minute) {
		
		int weekDay = 0;
		try{
			switch(day) {
			case 1:
				weekDay = TriggerUtils.SUNDAY;
				break;
			case 2:
				weekDay = TriggerUtils.MONDAY;
				break;
			case 3:
				weekDay = TriggerUtils.TUESDAY;
				break;
			case 4:
				weekDay = TriggerUtils.WEDNESDAY;
				break;
			case 5:
				weekDay = TriggerUtils.THURSDAY;
				break;
			case 6:
				weekDay = TriggerUtils.FRIDAY;
				break;
			case 7:
				weekDay = TriggerUtils.SATURDAY;
				break;
			default:
				break;
			}
				
			SchedulerFactory sf = new StdSchedulerFactory();
		    Scheduler sched = sf.getScheduler();
		    JobDetail jobDump = new JobDetail("job1","group1", DumpBD.class);
		    JobDetail jobSend = new JobDetail("job2","group2", DumpSender.class);
		    Trigger dumpBD = TriggerUtils.makeWeeklyTrigger("dump_Trigger", weekDay, hour, minute);
		    sched.scheduleJob(jobDump, dumpBD);
		    Trigger sendDump = TriggerUtils.makeWeeklyTrigger("send_Trigger", weekDay, hour, minute + 20);
		    sched.scheduleJob(jobSend,sendDump);
		    sched.start();
		    
		}catch(SchedulerException e){
			e.printStackTrace();
		}
	}
} 
