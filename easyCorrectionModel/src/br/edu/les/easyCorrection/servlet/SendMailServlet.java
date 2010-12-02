package br.edu.les.easyCorrection.servlet;

import java.io.IOException;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendMailServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	public SendMailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String assunto = request.getParameter("assunto");
		String contato = request.getParameter("contato");
		String mensagem = request.getParameter("mensagem");
		String emailInst = "leda@dsc.ufcg.edu.br";
		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet(request,response);
	}
}