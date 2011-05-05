package br.edu.les.easyCorrection.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;


public class Agendador {
	
	Timer timer;
	public static boolean comecou = false;
	
	public Agendador() {
		
		try{
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			 
			JobDetail job = new JobDetail("dispara_email", "grupo", DumpJob.class);
			Trigger semanal = TriggerUtils.makeWeeklyTrigger("weekly", TriggerUtils.SUNDAY, 12, 10); 
			sched.scheduleJob(job, semanal);
			
			sched.start();
		}catch(SchedulerException e){}
	}
	
	public class DumpJob implements Job{
	    public void execute(JobExecutionContext arg0) throws JobExecutionException{
	        System.out.println("Trabalhando com  o Quartz: " + new Date());
	    }
	}
	
	class RemindTask extends TimerTask {
	
		public void run() {
			/*
			Facade facade = new Facade();
			try {
				System.out.println("Executando...");
				List<GrupoUsuario> listaGUs = facade.listarGrupoUsuarios();
				String listaEmails = "";
				for (GrupoUsuario grupoUsuario : listaGUs) {
					if(grupoUsuario.getGrupo().getNome().toUpperCase().equals("ALUNO")){
						listaEmails += grupoUsuario.getUsuario().getEmail() + ", ";
					}
				}
				listaEmails.substring(0, listaEmails.length() - 2);
				List<Roteiro> listaRL = facade.getRoteirosLiberados();
				Roteiro rot = null;
				for (Roteiro roteiro : listaRL) {
					if(roteiro.getDataLiberacao().equals(facade.getDataNow())){
						rot = roteiro;
					}
				}
				if(rot != null){
					String assunto = "[LEDA] Roteiro Liberado!";
					String contato = listaEmails;
					String mensagem = "O roteiro " + rot.getNome() + " está liberado.";
					String email = "leda@dsc.ufcg.edu.br";
					EmailSender.enviarEmail(assunto, contato, mensagem, email);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}*/
			
			System.out.println("Executou!");
			//timer.cancel(); //Fecha a thread timer
		}
	}
} 
