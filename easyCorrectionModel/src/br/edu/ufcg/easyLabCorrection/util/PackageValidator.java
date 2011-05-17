package br.edu.ufcg.easyLabCorrection.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PackageValidator {
	
	private ArrayList<String> forbiddenWords;
	
	public PackageValidator(){
		forbiddenWords = new ArrayList<String>();
		buildFWList();
	}
	
	private void buildFWList(){
		StringTokenizer st = new StringTokenizer(Constants.notAllowedWords, ",");
		while (st.hasMoreTokens()){
			forbiddenWords.add(st.nextToken());
		}
	}
	
	private String findFW(Scanner sc){
		for(int i = 0; i < forbiddenWords.size(); i++){
			if (sc.findInLine(forbiddenWords.get(i)) != null){
				return forbiddenWords.get(i);
			}
		}
		return "";
	}
	
	private String verify(File file) throws FileNotFoundException{
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()){
			String term = findFW(sc);
			if (!term.equals("")) return term;
			else sc.nextLine();
		}
		return "OK!";
	}
	
	public void visitAllDirsAndFiles(File dir) {
	    process(dir);
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i < children.length; i++) {
	            visitAllDirsAndFiles(new File(dir, children[i]));
	        }
	    }
	}

	private void process(File dir){
		String[] children = dir.list();
		
		if (children != null) {
		    for (int i = 0; i < children.length; i++) {
		        String filename = children[i];
		    }
		}
		
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".java");
		    }
		};
		
		children = dir.list(filter);
		try{
			for (int i = 0; i < children.length; i++){
				String filePath = dir.getPath() + File.separator + children[i];
				File f = new File(filePath);
				System.out.println(filePath + "-->" + verify(f));
			}
		}
		catch (Exception e) {
		}
	}

	public static void main(String[] args) throws FileNotFoundException{
		PackageValidator pv = new PackageValidator();
		pv.visitAllDirsAndFiles(new File("D:/Gradua��o/"));
	}
	
}
