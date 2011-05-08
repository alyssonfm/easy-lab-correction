package br.edu.les.easyCorrection.util;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DumpSender implements Job {
	
	private static String assunto = "[ELC] Backup Bando de Dados ELC";
	private static String contato = "demetriogm@gmail.com";
	private static String mensagem = "Segue BD do ELC.";
	private static String emailInst = "leda@dsc.ufcg.edu.br";

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.dsc.ufcg.edu.br");
		props.put("mail.smtp.socketFactory.port", "25");
		props.put("mail.from","leda@dsc.ufcg.edu.br");
		props.put("mail.smtp.auth", "true");
	
		Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{ return new PasswordAuthentication("leda@dsc.ufcg.edu.br", "lda!2010");	}
		});		
	
		try {
	
	        Message message = new MimeMessage(session);
	        MimeBodyPart mbp = new MimeBodyPart();
	        
	        //Adicionando Anexo
	        DataSource fds = new FileDataSource(new File(Constantes.diretorioBackupBancoDeDados + Constantes.arquivoBackupBancoDeDados));  
	        mbp.setDisposition(Part.ATTACHMENT);  
	        mbp.setDataHandler(new DataHandler(fds));  
	        mbp.setFileName(fds.getName());
	        
	        //Construindo o multipart
	        Multipart mp = new MimeMultipart();     
	        mp.addBodyPart(mbp);  
	        message.setContent(mp);
	        
	        
		    message.setFrom(new InternetAddress("leda@dsc.ufcg.edu.br"));
		    message.setRecipients(Message.RecipientType.TO, 
	                        InternetAddress.parse(contato));
		    message.setSubject(assunto);
		    message.setContent("<html>Mensagem enviada por: <b> Easy Lab Correction - LEDA</b>" +
					"<p><p><table border=1>" +	mensagem + "</table>" +
					"<p>Contato:<br><b>" + emailInst + "</b></html>", "text/html");
		    
		    Transport.send(message);
		    
		} catch (MessagingException e) {
		    System.out.println("");
		}
	}
	
}
