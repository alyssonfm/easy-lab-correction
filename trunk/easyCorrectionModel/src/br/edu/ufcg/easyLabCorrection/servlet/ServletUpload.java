package br.edu.ufcg.easyLabCorrection.servlet;
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

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.util.MsgErros;

/**
 * Servlet implementation class UploadServlet
 */
public class ServletUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String local = "upload_projetos_epibic"; //url do upload servidor
	//private static final String local = "/home/demetriogm/"; //url do upload local
	//public static final String local = "/home/elc" + File.separator + "LEDA" + File.separator + "Roteiros"; //url do upload local
	public static final String local = System.getProperty("catalina.base") + File.separator + "webapps" + File.separator + "LEDA" + File.separator + "Roteiros"; //url do upload local
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().endsWith("downloadArquivo")){
			downloadArquivo(request, response);
		}
		else{
			fazUpload(request, response);
		}
	}
	
	public static boolean checaArquivo(String pastaDestino, String nomeArquivo)  throws IOException{
   	 	try{
   	 		
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
                zipentry = zipinputstream.getNextEntry();
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
        // M√°ximo tamanho que ir√° ser guardado em mem√≥ria
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
				request.setAttribute("errorMessage", "Erro no envio! O pacote zip submetido possui arquivos que n„o s„o do tipo JAVA.");
				request.getRequestDispatcher("/erro.jsp").forward(request, response);
			}
		}
	}
}