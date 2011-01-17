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
		EmailSender.enviarEmail(assunto, contato, mensagem, emailInst);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet(request,response);
	}
}