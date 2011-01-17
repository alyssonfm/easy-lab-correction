package br.edu.les.easyCorrection.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Agendador {
	
	Timer timer;
	public static boolean comecou = false;
	
	public Agendador() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 02);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();
		
		timer = new Timer();
		//timer.schedule(new RemindTask(), time);
		timer.scheduleAtFixedRate(new RemindTask(), time, 86400000);
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
