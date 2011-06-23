package br.edu.ufcg.easyLabCorrection.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Class responsible for realization of uploads in the system Easy Lab Correction. <br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class ServletUpload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String local = System.getProperty("catalina.base") + File.separator + "webapps" + File.separator + "LEDA" + File.separator + "Roteiros"; //url do upload local
	
	/**
	 * Method that realizes upload, for way of a action doGet.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Method that realizes upload, for way of a action doPost.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().endsWith("downloadArquivo")){
			downloadArchive(request, response);
		}
		else{
			makesUpload(request, response);
		}
	}
	
	/**
	 * Method that verify the validity of the file.<br>
	 * @param destinationFolder - the destination folder of the file<br> 
	 * @param fileName - the name of file.<br>
	 * @return a boolean value indicating if the file is OK or no.<br>
	 * @throws IOException - exception that can be caused in an attempt to check the file.<br>
	 */
	public static boolean checkFile(String destinationFolder, String fileName)  throws IOException{
   	 	try{
			// String destinationname = destinationFolder;
			// byte[] buf = new byte[1024];
            ZipInputStream zipinputstream = null;
            ZipEntry zipentry;
            zipinputstream = new ZipInputStream(
                new FileInputStream(destinationFolder + fileName));
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
	
	/**
	 * Method that decompresses the file received as parameter in the destination folder chosen.<br>
	 * @param destinationFolder - the destination folder of files uncompressed.<br>
	 * @param fileName - the file name.<br>
	 * @throws IOException - exception that can be caused in an attempt to decompress the file.<br>
	 */
    public static void unZip(String destinationFolder, String fileName)  throws IOException{
    	 try
         {
             String destinationname = destinationFolder;
             byte[] buf = new byte[1024];
             ZipInputStream zipinputstream = null;
             ZipEntry zipentry;
             zipinputstream = new ZipInputStream(
                 new FileInputStream(destinationFolder + fileName));
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
	
	/**
	 * Method used to download files in system ELC.<br> 
	 * @param request - the request used to download files.<br>
	 * @param response - the response of the request.<br>
	 * @throws ServletException - exceptions caused in the use of the servlet.<br>
	 * @throws IOException - exceptions caused for problems of the input and/or output.<br> 
	 */
	protected void downloadArchive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = request.getParameter("fileName");
		
		try{
			String url = fileName.replace("/", File.separator);
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
	
	/**
	 * Method used to creates uploads in the system ELC.<br>
	 */
	@SuppressWarnings("unchecked")
	protected void makesUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String uploadDir = this.getServletContext().getRealPath(local);
		String url = request.getParameter("url").replace("/", File.separator);
		String uploadDir = local + url;
		String fileName = request.getParameter("fileName");

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
					fileItem.write(new File(uploadDir, fileName));
				}
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
		if(fileName.substring(fileName.length() - 3, fileName.length()).equals("zip")){
			if (checkFile(uploadDir, fileName)){
				unZip(uploadDir, fileName);
			}
			else{
				request.setAttribute("errorMessage", "Erro no envio! O pacote zip submetido possui arquivos que n�o s�o do tipo JAVA.");
				request.getRequestDispatcher("/erro.jsp").forward(request, response);
			}
		}
	}
}