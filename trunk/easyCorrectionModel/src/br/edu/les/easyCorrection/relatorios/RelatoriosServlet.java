package br.edu.les.easyCorrection.relatorios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class for Servlet: RelatoriosProdutosServlet
 */
public class RelatoriosServlet extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger("relatorio");

	private Properties config;
	protected Connection conexao;
	private String pathRelatorio;

	public RelatoriosServlet() throws IOException {
		super();
	}

	private void abreConexao() throws Throwable {
		try {
			config = loadProperties();
			ConnectionDB connector = new ConnectionDB(config
					.getProperty("servidor"), config.getProperty("banco"),
					config.getProperty("usuario"), config.getProperty("senha"));
			conexao = connector.getConnection();
		} catch (Throwable e) {
			log.error("abreConexao()", e);
			throw e;
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		processRequest(req, res);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		try {
			abreConexao();

			pathRelatorio = this.getServletContext().getRealPath("")
					+ System.getProperty("file.separator") + "relatorios"
					+ System.getProperty("file.separator");

			if (req.getRequestURI().endsWith(
					"gerarRelatoriosAvaliadosDetalhes")) {
				gerarRelatoriosAvaliadosDetalhes(req, res);
			}

		} catch (Throwable e) {
			log.error("processRequest()", e);
			throw new IOException(e);
		} finally {
			try {
				if (!conexao.isClosed()) {
					conexao.close();
				}
			} catch (SQLException e) {
				log.error("processRequest() - fecharConexao", e);
				throw new IOException(e);
			}
		}
	}

	private void gerarRelatoriosAvaliadosDetalhes(HttpServletRequest req,
			HttpServletResponse res) throws Throwable {
		try {
			String idCota = req.getParameter("idCota");
			String idRelatorios = req.getParameter("idRelatorios");
			
			boolean todosRelatorios = idRelatorios==null ? true : idRelatorios.trim().isEmpty();
			if(todosRelatorios){
				 idRelatorios = "-1";
			}
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("path", pathRelatorio);
			parametros.put("idCota", new Integer(idCota));
			parametros.put("idRelatorios", idRelatorios);
			parametros.put("todosRelatorios", new Boolean(todosRelatorios));
			OutputStream out = res.getOutputStream();
			geraRelatorioJASPER(parametros, pathRelatorio + 
				"Relatorio_aval_detalhes2.jasper", out, conexao);
			res.setContentType("application/pdf");
			out.close();
		} catch (Throwable e) {
			log.error("gerarAlunosInscritos()", e);
			throw e;
		}
	}

	/**
	 * Método que gera o comprovante dos produtos que foram dados baixa
	 * 
	 * @param idMovimentacoes
	 *            Os ids das movimentações geradas para cada produto
	 * @param parametros
	 *            Os parametros do relatório
	 * @param pathRelatorio
	 *            O caminho do arquivo do relatório
	 * @param out
	 *            O stream de saída para o relatório
	 * @throws JRException
	 */
	private void geraRelatorio(Map<String, Object> parametros,
			String pathRelatorio, OutputStream out) throws Throwable {

		try {
			JasperDesign design = JRXmlLoader.load(pathRelatorio);
			if (conexao.isClosed()) {
				throw new JRException("A conexao esta fechada!!!");
			}
			JasperReport report = JasperCompileManager.compileReport(design);
			JasperPrint printer = JasperFillManager.fillReport(report,
					parametros, conexao);
			JasperExportManager.exportReportToPdfStream(printer, out);
		} catch (Throwable e) {
			log.error("geraRelatorioProduto()", e);
			throw e;
		}
	}

	public static void geraRelatorioJASPER(Map<String, Object> parametros, 
			String pathRelatorio, OutputStream out, Connection conexao) throws Throwable {
			try {
				if (conexao.isClosed()) {
					throw new JRException("A conexão está fechada!!!");
				}
				JasperPrint printer = JasperFillManager.fillReport(pathRelatorio, parametros, 
					conexao);
				JasperExportManager.exportReportToPdfStream(printer, out);
			} catch (Throwable e) {
				throw e;
			}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		doGet(req, res);
	}

	/**
	 * Carrega as informações de conexão com o BD a partir de um arquivo de
	 * propriedades.
	 * 
	 * @return Uma classe Properties com as informações de conexão com o BD.
	 * @throws IOException
	 */
	private Properties loadProperties() throws Throwable {
		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream(
					"conexao.properties");
			props.load(is);
			return props;
		} catch (Throwable e) {
			log.error("loadProperties()", e);
			throw e;
		}
	}	
	
	public static void geraRelatorioJRXML(HttpServletRequest req,
			HttpServletResponse res, Map<String, Object> parametros,
			String pathRelatorio, Connection conexao) throws Throwable {
		try {
			OutputStream out = res.getOutputStream();
			res.setContentType("application/pdf");
			JasperDesign design = JRXmlLoader.load(pathRelatorio);
			JasperReport report = JasperCompileManager.compileReport(design);
			JasperPrint printer = JasperFillManager.fillReport(report,
					parametros, conexao);
			JasperExportManager.exportReportToPdfStream(printer, out);
			out.close();
		} catch (Throwable e) {
			log.error("geraRelatorio", e);
			throw e;
		}
	}
	
	public static void geraRelatorioJASPER(HttpServletRequest req,
			HttpServletResponse res, Map<String, Object> parametros,
			String pathRelatorio, Connection conexao) throws Throwable {
			try {
				OutputStream out = res.getOutputStream();
				JasperPrint printer = JasperFillManager.fillReport(pathRelatorio, parametros, 
					conexao);
				JasperExportManager.exportReportToPdfStream(printer, out);
				out.close();
			} catch (Throwable e) {
				throw e;
			}
	}

}