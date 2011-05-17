package br.edu.ufcg.easyLabCorrection.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

public class EmailSender {
	
	public static void enviarEmail(String assunto, String contato, String mensagem, String email)
		throws ServletException, IOException {
		
		String emailInst = email;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.dsc.ufcg.edu.br");
		props.put("mail.smtp.socketFactory.port", "25");
		props.put("mail.from","leda@dsc.ufcg.edu.br");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		//props.put("mail.smtp.port", "25");
		//props.put("mail.smtp.socketFactory.fallback", "false");
		//props.put("mail.smtp.starttls.enable","true");
	
	
		Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{ return new PasswordAuthentication("leda@dsc.ufcg.edu.br", "lda!2010");	}
		});		
	
		try {
	
	        Message message = new MimeMessage(session);
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
