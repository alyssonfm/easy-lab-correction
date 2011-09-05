package br.edu.ufcg.easyLabCorrection.managers.automatedCorrection;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import br.edu.ufcg.easyLabCorrection.util.Constants;

public class TestExecutionFileFilter {
	
	private ArrayList<String> sourcePaths;
	
	public TestExecutionFileFilter() {
		sourcePaths = new ArrayList<String>();
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
		return sourcePaths;
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
				return name.endsWith(".class");
			}
		};

		children = dir.list(filter);
		try {
			for (int i = 0; i < children.length; i++) {
				String filePath = dir.getPath() + File.separator + children[i];
				sourcePaths.add(filePath);
			}
		} catch (Exception e){}
	}
	
	public String findMainTest(){
		for (int i = 0; i < sourcePaths.size(); i++) {
			if (sourcePaths.get(i).endsWith(Constants.mainTest + ".class")){
				return sourcePaths.get(i);
			}
		}
		return "";
	}
	
	public String findMainClass(){
		for (int i = 0; i < sourcePaths.size(); i++) {
			if (sourcePaths.get(i).endsWith(Constants.mainClass + ".class")){
				return sourcePaths.get(i);
			}
		}
		return "";
	}
	
}
