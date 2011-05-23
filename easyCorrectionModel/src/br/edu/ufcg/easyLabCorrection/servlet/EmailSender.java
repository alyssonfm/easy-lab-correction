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

/**
 * Class responsible for send emails in system Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class EmailSender {	
	
	/**
	 * Method sendMail is the method that send emails in system ELC. It receives four arguments as parameter: subject - the subject of mail; contact - the destination of mail; message - the message of mail and email - the mail of sender. <br>
	 * @param subject - subject of the email being sent.<br>
	 * @param contact - the address of destination message.<br>
	 * @param messages - the message of mail.<br>
	 * @param email - the address sender.<br>
	 * @throws ServletException - exception for problems in servlet of send emails.<br>
	 * @throws IOException - exception for problems of input or output.<br>
	 */
	public static void sendMail(String subject, String contact, String message, String email)
		throws ServletException, IOException {
		
		String emailInst = email;
		
		/*
		 * Define the properties of mail being sent.<br>
		 */
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.dsc.ufcg.edu.br");
		props.put("mail.smtp.socketFactory.port", "25");
		props.put("mail.from","leda@dsc.ufcg.edu.br");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		//props.put("mail.smtp.port", "25");
		//props.put("mail.smtp.socketFactory.fallback", "false");
		//props.put("mail.smtp.starttls.enable","true");
	
		/*
		 * Creates the session to send mail.<br>
		 */
		Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{ return new PasswordAuthentication("leda@dsc.ufcg.edu.br", "lda!2010");	}
		});		
	
		try {
	
	        Message messages = new MimeMessage(session);
		    messages.setFrom(new InternetAddress("leda@dsc.ufcg.edu.br"));
		    messages.setRecipients(Message.RecipientType.TO, 
	                        InternetAddress.parse(contact));
		    messages.setSubject(subject);
		    messages.setContent("<html>Mensagem enviada por: <b> Easy Lab Correction - LEDA</b>" +
					"<p><p><table border=1>" +	message + "</table>" +
					"<p>Contato:<br><b>" + emailInst + "</b></html>", "text/html");
		    
		    Transport.send(messages);
		    
		} catch (MessagingException e) {
		    System.out.println("");
		}
	}
}
