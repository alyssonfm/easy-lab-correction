package br.edu.les.easyCorrection.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;


public class SendMailServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	public SendMailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			String assunto = request.getParameter("assunto");
			String contato = request.getParameter("contato");
			String mensagem = request.getParameter("mensagem");
			String emailInst = "easylabcorrection.leda@gmail.com";

			HtmlEmail email = new HtmlEmail();
			email.setHostName("imap.gmail.com");
			email.setFrom(emailInst);
			email.setAuthentication("easylabcorrection.leda@gmail.com","alyaugdem");
			email.addTo(contato);
			email.setSubject(assunto);
			email.setHtmlMsg("<html>Mensagem enviada por: <b> Easy Lab Correction - LEDA</b>" +
				"<p><p><table border=1>" +	mensagem + "</table>" +
				"<p>Contato:<br><b>" + emailInst + "</b></html>");
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet(request,response);
	}
}