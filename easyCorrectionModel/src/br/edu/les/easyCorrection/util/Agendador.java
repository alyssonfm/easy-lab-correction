package br.edu.les.easyCorrection.util;

import java.util.Timer;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;


public class Agendador {
	
	Timer timer;
	public static boolean comecou = false;
	
	public Agendador(int dia, int hora, int minuto) {
		
		int diaDaSemana = 0;
		try{
			switch(dia) {
			case 1:
				diaDaSemana = TriggerUtils.SUNDAY;
				break;
			case 2:
				diaDaSemana = TriggerUtils.MONDAY;
				break;
			case 3:
				diaDaSemana = TriggerUtils.TUESDAY;
				break;
			case 4:
				diaDaSemana = TriggerUtils.WEDNESDAY;
				break;
			case 5:
				diaDaSemana = TriggerUtils.THURSDAY;
				break;
			case 6:
				diaDaSemana = TriggerUtils.FRIDAY;
				break;
			case 7:
				diaDaSemana = TriggerUtils.SATURDAY;
				break;
			default:
				break;
			}
				
			SchedulerFactory sf=new StdSchedulerFactory();
		    Scheduler sched=sf.getScheduler();
		    JobDetail jobDump = new JobDetail("job1","group1", DumpBD.class);
		    JobDetail jobSend = new JobDetail("job2","group2", DumpSender.class);
		    Trigger dumpBD = TriggerUtils.makeWeeklyTrigger("dump_Trigger", diaDaSemana, hora, minuto);
		    sched.scheduleJob(jobDump, dumpBD);
		    Trigger enviarDump = TriggerUtils.makeWeeklyTrigger("send_Trigger", diaDaSemana, hora, minuto + 20);
		    sched.scheduleJob(jobSend,enviarDump);
		    sched.start();
		    
		}catch(SchedulerException e){
			e.printStackTrace();
		}
	}
} 
