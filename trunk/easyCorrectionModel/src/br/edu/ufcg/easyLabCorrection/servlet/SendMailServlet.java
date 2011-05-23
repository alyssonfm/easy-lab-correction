package br.edu.ufcg.easyLabCorrection.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsible for sending the email. Uses the EmailSender class in the send.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class SendMailServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor default to class. Creates a new object SendMailServlet.<br>
	 */
	public SendMailServlet() {
		super();
	}

	/**
	 * Method that realizes the send of mail, for way of a action doGet.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String assunto = request.getParameter("assunto");
		String contato = request.getParameter("contato");
		String mensagem = request.getParameter("mensagem");
		String emailInst = "leda@dsc.ufcg.edu.br";
		EmailSender.sendMail(assunto, contato, mensagem, emailInst);
	}

	/**
	 * Method that realizes the send of mail, for way of a action doPost.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet(request,response);
	}
}