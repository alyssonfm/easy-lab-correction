package br.edu.les.easyCorrection.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.File;
import java.io.IOException;

public class DumpBD implements Job {
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		File diretorio = new File(Constantes.diretorioBackupBancoDeDados);  
		File bck = new File(Constantes.diretorioBackupBancoDeDados + Constantes.arquivoBackupBancoDeDados);  
		// os zeros é para diferenciar um backup do outro  
		// Cria diretório 
		if(!diretorio.isDirectory()) {  
			new File(Constantes.diretorioBackupBancoDeDados).mkdir();  
		} else {  
		
		}  
		
		// Cria Arquivo de Backup  
		try {  
			if(!bck.isFile()) {
				String temp = Constantes.caminhoComandoDump + 
					Constantes.diretorioBackupBancoDeDados + 
					Constantes.arquivoBackupBancoDeDados + 
					" " + Constantes.nomeEsquemaBanco;
				Runtime.getRuntime().exec(temp);  
			} else {  
				int numerodobackup = 0;
				while(bck.isFile()) {  
					numerodobackup++;  
					bck = new File(Constantes.diretorioBackupBancoDeDados + 
							"backup" +
							Constantes.nomeEsquemaBanco + 
							"00"+ numerodobackup + ".sql");  
				}
				Runtime.getRuntime().exec(Constantes.caminhoComandoDump + bck + " " + Constantes.nomeEsquemaBanco);  
			}  
		} catch (IOException ex) {  
			ex.printStackTrace();  
		}
	}
}
