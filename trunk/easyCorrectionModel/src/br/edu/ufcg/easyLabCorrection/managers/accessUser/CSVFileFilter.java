package br.edu.ufcg.easyLabCorrection.managers.accessUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionException;
import br.edu.ufcg.easyLabCorrection.util.ErrorMsgs;

/**
 * Class responsible for checking the consistency of CSV files.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * 
 */

public class CSVFileFilter {

	private FileReader reader;
	private BufferedReader br;
	private String lineSeparator = ",";
	private int lineNumber;

	/**
	 * Constructor default of the class, creates a new object CSVFileFilter.<br>
	 */
	public CSVFileFilter() {
		this.lineNumber = 0;
	}

	/**
	 * The function responsible for checking of CSV file.<br>
	 * 
	 * @param fileName
	 *            The CSV file.<br>
	 * @return The List of List of the Strings corresponding at the content of
	 *         CSV file.<br>
	 * @throws IOException
	 *             The exception that can be launched in an attempt to open the
	 *             file passed as parameter.<br>
	 * @throws EmptyFieldException
	 *             The exception that can be launched if the CSV file is not in
	 *             accordance with the standard adopted.<br>
	 */
	public ArrayList<ArrayList<String>> checkCsvFile(String fileName)
			throws IOException, EasyCorrectionException {
		ArrayList<String> listLines = new ArrayList<String>();
		ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();
		String line;
		String[] linhas;
		try {
			this.reader = new FileReader(fileName);
			br = new BufferedReader(reader);
			while (br.ready()) {
				line = br.readLine();
				linhas = line.split(lineSeparator);
				lineNumber++;
				if (linhas.length != 3) {
					throw new EasyCorrectionException(
							ErrorMsgs.INVALID_FIELDS_NUMBER.msg(String
									.valueOf(lineNumber)));
				} else {
					for (int i = 0; i < linhas.length; i++) {
						listLines.add(linhas[i]);
					}
					listAll.add(new ArrayList<String>(listLines));
					listLines.clear();
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listAll;
	}
}
