package br.edu.ufcg.easyLabCorrection.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

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
	 * Method that decompresses the file received as parameter in the destination folder chosen.<br>
	 * @param destinationFolder - the destination folder of files uncompressed.<br>
	 * @param fileName - the file name.<br>
	 * @throws IOException - exception that can be caused in an attempt to decompress the file.<br>
	 */
    public static void unZip(String destinationFolder, String fileName)  throws IOException{
    	
    	File dir = new File (destinationFolder);
    	dir.delete();
    	File zipFile = new File (destinationFolder + fileName);
    	ZipFile zip = null;
    	File arquivo = null;
    	InputStream is = null;
    	OutputStream os = null;
    	byte[] buffer = new byte[1024];
 
    	try {
    		// create dir if not exist
    		if (!dir.exists()) {
    			dir.mkdirs();
    		}
    		if (!dir.exists() || !dir.isDirectory()) {
    			throw new IOException("O diretÛrio " + dir.getName() + " n„o È um diretÛrio v·lido");
    		}
 
    		zip = new ZipFile(zipFile);
    		Enumeration e = zip.entries();
    		while (e.hasMoreElements()) {
    			ZipEntry entrada = (ZipEntry) e.nextElement();
    			arquivo = new File(dir, entrada.getName());
     
    			if (entrada.isDirectory() && !arquivo.exists()) {
    				arquivo.mkdirs();
    				continue;
    			}
 
    			if (!arquivo.getParentFile().exists()) {
    				arquivo.getParentFile().mkdirs();
    			}
    			try {
    				is = zip.getInputStream(entrada);
    				os = new FileOutputStream(arquivo);
    				int bytesLidos = 0;
    				if (is == null) {
    					throw new ZipException("Erro ao ler a entrada do zip: " + entrada.getName());
    				}
    				while ((bytesLidos = is.read(buffer)) > 0) {
    					os.write(buffer, 0, bytesLidos);
    				}
    			} finally {
    				if (is != null) {
    					try {
    						is.close();
    					} catch (Exception ex) {
    					}
    				}
    				if (os != null) {
    					try {
    						os.close();
    					} catch (Exception ex) {
    					}
    				}
    			}
    		}
    	} finally {
    		if (zip != null) {
    			try {
    				zip.close();
    			} catch (Exception e) {
    			}
    		}
    	}
    	/* 
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
         }*/
    }
    
    private boolean deleteDir(File dir) {  
        if (dir.isDirectory()) {  
            String[] children = dir.list();  
            for (int i=0; i<children.length; i++) {   
               boolean success = deleteDir(new File(dir, children[i]));  
                if (!success) {  
                    return false;  
                }  
            }  
        }  
      
        return dir.delete();  
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
        // M√°ximo tamanho que ir√° ser guardado em mem√≥ria
        factory.setSizeThreshold(4096);
        
        File file = new File(uploadDir);
        deleteDir(file);
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
			unZip(uploadDir, fileName);
		}
	}
}