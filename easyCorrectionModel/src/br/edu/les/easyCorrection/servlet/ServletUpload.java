package br.edu.les.easyCorrection.servlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.edu.les.easyCorrection.exceptions.EasyCorrectionException;
import br.edu.les.easyCorrection.util.MsgErros;

/**
 * Servlet implementation class UploadServlet
 */
public class ServletUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String local = "upload_projetos_epibic"; //url do upload servidor
	//private static final String local = "/home/demetriogm/"; //url do upload local
	//public static final String local = "/home/elc" + File.separator + "LEDA" + File.separator + "Roteiros"; //url do upload local
	public static final String local = System.getProperty("catalina.base") + File.separator + "webapps" + File.separator + "LEDA" + File.separator + "Roteiros"; //url do upload local
	private static final String curriculoTemp = "/home/desenvolvimento/tomcat6/temp_curriculos/"; //url do temp de currículos servidor
	//private static final String curriculoTemp = "/home/demetriogm/"; //url do temp de currículos local
	//private static final String curriculoTemp = "D:/Projetos/"; //url do temp de currículos local
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().endsWith("PegaIdCurriculoPorNomeEDataNascimento")) {
			PegaIdCurriculoPorNomeEDataNascimento(request, response);
		}
		else{
			if (request.getRequestURI().endsWith("PegaIdCurriculoPorCPF")){
				PegaIdCurriculoPorCPF(request,response);
			}
			else{
				if (request.getRequestURI().endsWith("PegaCurriculo")){
					PegaCurriculo(request,response);
				}
				else{
					if (request.getRequestURI().endsWith("downloadArquivo")){
						downloadArquivo(request, response);
					}
					else{
						fazUpload(request, response);
					}
				}
			}
		}
	}
	
	protected void PegaIdCurriculoPorNomeEDataNascimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String data = request.getParameter("data");
		response.setContentType("text/xml");
		
		nome = URLEncoder.encode(nome, "UTF-8");
		
		URL url = new URL("http://servicosweb.cnpq.br/srvcurriculo/servlet/ServletID?nome=" + nome 
				+ "&data=" + data);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String line;
		ServletOutputStream out = response.getOutputStream();
		while ((line = br.readLine()) != null) {
			out.println(line);
		}
	}
	
	protected void PegaIdCurriculoPorCPF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		response.setContentType("text/xml");
		
		cpf = URLEncoder.encode(cpf, "UTF-8");
		
		URL url = new URL("http://servicosweb.cnpq.br/srvcurriculo/servlet/ServletID?cpf=" + cpf);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String line;
		ServletOutputStream out = response.getOutputStream();
		while ((line = br.readLine()) != null) {
			out.println(line);
		}
	}
	
	protected void PegaCurriculo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		response.setContentType("text/xml");
		
		id = URLEncoder.encode(id, "UTF-8");
		
		URL url = new URL("http://servicosweb.cnpq.br/srvcurriculo/servlet/ServletZip?id=" + id);
		
		InputStream br = url.openStream();
		FileOutputStream fos = new FileOutputStream(new File(curriculoTemp));
		int b;
		while((b = br.read()) != -1){
			fos.write(b);
		}
		fos.flush();
		fos.close();
		br.close();
		ServletOutputStream out = response.getOutputStream();
		unZip(out);
	}
	
	@SuppressWarnings("unchecked")
	protected void unZip(ServletOutputStream out){
		try {
			ZipFile zf = new ZipFile(curriculoTemp);
			Enumeration entries = zf.entries();

			if (entries.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) entries.nextElement();
				BufferedReader br = new BufferedReader(
					new InputStreamReader(zf.getInputStream(ze)));
				String line;
				while ((line = br.readLine()) != null) {
					out.println(line);
				}
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checaArquivo(String pastaDestino, String nomeArquivo)  throws IOException{
   	 try
        {
            String destinationname = pastaDestino;
            byte[] buf = new byte[1024];
            ZipInputStream zipinputstream = null;
            ZipEntry zipentry;
            zipinputstream = new ZipInputStream(
                new FileInputStream(pastaDestino + nomeArquivo));
            zipentry = zipinputstream.getNextEntry();
            while (zipentry != null){
                String entryName = zipentry.getName();
                if (!entryName.substring(entryName.length() - 4, entryName.length()).toUpperCase().equals("JAVA")){
                	zipinputstream.close();
                	return false;
                }
            }
            zipinputstream.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
   }
	
    public static void unZip(String pastaDestino, String nomeArquivo)  throws IOException{
    	 try
         {
             String destinationname = pastaDestino;
             byte[] buf = new byte[1024];
             ZipInputStream zipinputstream = null;
             ZipEntry zipentry;
             zipinputstream = new ZipInputStream(
                 new FileInputStream(pastaDestino + nomeArquivo));
             zipentry = zipinputstream.getNextEntry();
             while (zipentry != null) 
             { 
                 //for each entry to be extracted
                 String entryName = zipentry.getName();
                 int n;
                 FileOutputStream fileoutputstream;
                 File newFile = new File(entryName);
                 String directory = newFile.getParent();
                 
                 if(directory == null)
                 {
                     if(newFile.isDirectory())
                         break;
                 }
                 
                 fileoutputstream = new FileOutputStream(
                    destinationname+entryName);             

                 while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
                     fileoutputstream.write(buf, 0, n);

                 fileoutputstream.close(); 
                 zipinputstream.closeEntry();
                 zipentry = zipinputstream.getNextEntry();

             }//while

             zipinputstream.close();
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
    }
	
	
	protected void downloadArquivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeArquivo = request.getParameter("nomeArquivo");
		
		try{
			String url = nomeArquivo.replace("/", File.separator);
			String arq = local + url;
			System.out.println(arq);
			FileInputStream fis = new FileInputStream(new File(arq));
			ServletOutputStream sos = response.getOutputStream();
			int b;
			while((b = fis.read()) != -1){
				sos.write(b);
			}
			sos.flush();
			fis.close();
		}
		catch(Exception e){}
	}
	
	/*
	protected void fazUploadUnZip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fazUpload(request, response);
		unZip(out);
	}*/
	
	@SuppressWarnings("unchecked")
	protected void fazUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String uploadDir = this.getServletContext().getRealPath(local);
		String url = request.getParameter("url").replace("/", File.separator);
		String uploadDir = local + url;
		String nomeArquivo = request.getParameter("nomeArquivo");

		DiskFileItemFactory factory = new DiskFileItemFactory();
        // Máximo tamanho que irá ser guardado em memória
        factory.setSizeThreshold(4096);
        
        File file = new File(uploadDir);
        file.mkdirs();
        factory.setRepository(file);

        ServletFileUpload uploadParser = new ServletFileUpload(factory);
        uploadParser.setSizeMax(10485760); // 10MB no maximo

		try {
	        List<FileItem> fileItems = (List<FileItem>)uploadParser.parseRequest(request);
	        //Tive de iterar nos FileItem pra saber o fieldName chamado Filedata,
	        //pois existiam outros FileItem nulos
	        for (FileItem fileItem : fileItems) {
				if (fileItem.getFieldName().equals("Filedata")) {
					//filename no cliente
					//String fileName = fileItem.getName();
					//Escreve o arquivo
					fileItem.write(new File(uploadDir, nomeArquivo));
				}
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
		if(nomeArquivo.substring(nomeArquivo.length() - 3, nomeArquivo.length()).equals("zip")){
			if (checaArquivo(uploadDir, nomeArquivo)){
				unZip(uploadDir, nomeArquivo);
			}
			else{
				request.setAttribute("errorMessage", "Erro no envio! O pacote zip submetido possui arquivos que n�o s�o do tipo JAVA.");
				request.getRequestDispatcher("/erro.jsp").forward(request, response);
			}
		}
	}
}