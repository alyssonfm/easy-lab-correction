package br.edu.ufcg.easyLabCorrection.managers.compilation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import br.edu.ufcg.easyLabCorrection.util.Constants;

/**
 * Class responsible for performing validation of files during the submission.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 * 
 */
public class CompilationFileFilter {

	private ArrayList<String> forbiddenWords;
	private ArrayList<String> sourcePaths;
	private ArrayList<String> libPaths;
	private String forbiddenWordFound;
	private String forbiddenWordFoundClass;

	/**
	 * Constructor default of class.<br>
	 */
	public CompilationFileFilter() {
		forbiddenWords = new ArrayList<String>();
		sourcePaths = new ArrayList<String>();
		libPaths = new ArrayList<String>();
		forbiddenWordFound = "";
		forbiddenWordFoundClass = "";
		buildFWList();
	}

	/**
	 * Procedure that constructs the list of forbidden words used in the system.<br>
	 */
	private void buildFWList() {
		StringTokenizer st = new StringTokenizer(Constants.notAllowedWords, ",");
		while (st.hasMoreTokens()) {
			forbiddenWords.add(st.nextToken());
		}
	}

	/**
	 * Function that search forbidden words in files of the system.<br>
	 * 
	 * @param sc
	 *            The scanner used in search.<br>
	 * @return The forbidden word if already, empty otherwise.<br>
	 */
	private String findFW(Scanner sc) {
		for (int i = 0; i < forbiddenWords.size(); i++) {
			if (sc.findInLine(forbiddenWords.get(i)) != null) {
				return forbiddenWords.get(i);
			}
		}
		return "";
	}

	/**
	 * Function that verify a file passed as parameter.<br>
	 * 
	 * @param file
	 *            The file to be verified.<br>
	 * @return A forbidden word was found in the file.<br>
	 * @throws FileNotFoundException
	 *             Exception launched if the file does not exist in the system.<br>
	 */
	private String verify(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String term = findFW(sc);
			if (!term.equals(""))
				return term;
			else
				sc.nextLine();
		}
		return "OK!";
	}

	/**
	 * Procedure used to scan all directories and all files.<br>
	 * 
	 * @param dir
	 *            The directy which will be verified.<br>
	 */
	public ArrayList<String> visitAllDirsAndFiles(File dir) {
		processDirectory(dir);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFiles(new File(dir, children[i]));
			}
		}
		if(!forbiddenWordFound.equals("")){
			ArrayList<String> forbs = new ArrayList<String>();
			forbs.add("forbidden");
			forbs.add(forbiddenWordFound);
			forbs.add(forbiddenWordFoundClass);
			return forbs;
		}
		else{
			return sourcePaths;
		}
	}

	/**
	 * Procedure used to process a directory in system.<br>
	 * 
	 * @param dir
	 *            The directory to be processed.<br>
	 */
	private void processDirectory(File dir) {
		String[] children = dir.list();

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		};

		children = dir.list(filter);
		try {
			for (int i = 0; i < children.length; i++) {
				String filePath = dir.getPath() + File.separator + children[i];
				File f = new File(filePath);
				String verifiedClass = verify(f);
				if(verifiedClass.equals("OK!")){
					sourcePaths.add(filePath);
				}
				else{
					forbiddenWordFound = verifiedClass;
					forbiddenWordFoundClass = filePath;
				}
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * Procedure used to scan all directories and all files.<br>
	 * 
	 * @param dir
	 *            The directory which will be verified.<br>
	 */
	public ArrayList<String> visitAllDirsAndFilesFindingJars(File dir) {
		processDirectoryFindingJars(dir);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFilesFindingJars(new File(dir, children[i]));
			}
		}
		return libPaths;
	}
	
	private void processDirectoryFindingJars(File dir) {
		String[] children = dir.list();

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		};

		children = dir.list(filter);
		try {
			for (int i = 0; i < children.length; i++) {
				String filePath = dir.getPath() + File.separator + children[i];
				libPaths.add(filePath);
			}
		} catch (Exception e) {
		}
	}
	
}
