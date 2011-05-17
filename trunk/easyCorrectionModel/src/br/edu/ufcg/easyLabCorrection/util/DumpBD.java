package br.edu.ufcg.easyLabCorrection.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.File;
import java.io.IOException;

public class DumpBD implements Job {
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		File directory = new File(Constants.bdBackupDirectory);  
		File bck = new File(Constants.bdBackupDirectory + Constants.bdBackupFile);
		
		if(!directory.isDirectory()) {  
			new File(Constants.bdBackupDirectory).mkdir();  
		}  
		
		// Cria Arquivo de Backup  
		try {  
			if(!bck.isFile()) {
				String temp = Constants.dumpCommandPath + 
					Constants.bdBackupDirectory + 
					Constants.bdBackupFile + 
					" " + Constants.bdSchemaName;
				Runtime.getRuntime().exec(temp);  
			} else {  
				int numerodobackup = 0;
				while(bck.isFile()) {  
					numerodobackup++;  
					bck = new File(Constants.bdBackupDirectory + 
							"backup" +
							Constants.bdSchemaName + 
							"00"+ numerodobackup + ".sql");  
				}
				Runtime.getRuntime().exec(Constants.dumpCommandPath + bck + " " + Constants.bdSchemaName);  
			}  
		} catch (IOException ex) {  
			ex.printStackTrace();  
		}
	}
}
