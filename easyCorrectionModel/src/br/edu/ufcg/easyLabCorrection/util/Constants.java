package br.edu.ufcg.easyLabCorrection.util;

/**
 * Class used for the main constants in the system ELC.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.
 */
public class Constants {

	// Demetrio's directory
	// public static final String arquivoBackupBancoDeDados =
	// "C:/Graduacao/LES/workspace/easyCorrectionModel/etc/backupEasyCorrection.sql";
	// public static final String arquivoBackupBancoDeDados =
	// "C:/Graduacao/LES/workspace/easyCorrectionModel/etc/backupEasyCorrection.sql";

	// Demetrio's directory
	public static final String bdSchemaName = "easyLabCorrection";
	// public static final String caminhoComandoDump =
	// "mysqldump -uelc -p123 -r";
	public static final String dumpCommandPath = "C:/xampp/mysql/bin/mysqldump.exe -uelc -p123 -r";
	// public static final String diretorioBackupBancoDeDados = "/home/elc/";
	public static final String bdBackupDirectory = "D:/Gradua��o/";
	public static final String bdBackupFile = "backupEasyLabCorrection.sql";

	public static final String bdTestBackupFile = "etc/backupeasylabcorrection_test.sql";

	public static final String mainTest = "MainTest";
	public static final String mainClass = "Main";

	public static final String notAllowedWords = "System.exit";

	// Server's directory
	// public static final String nomeEsquemaBanco = "easyLabCorrection";
	// public static final String caminhoComandoDump =
	// "mysqldump -uelc -p123 -r";
	// public static final String diretorioBackupBancoDeDados =
	// ServletUpload.local;
	// public static final String arquivoBackupBancoDeDados = "backup" +
	// nomeEsquemaBanco + "0000.sql";

	/*
	 * OUTPUT COMPARISON CONSTANTS
	 */

	public static final String TEST_CASE_SEPARATOR = System
			.getProperty("line.separator");
	public static final String TEST_DATA_SEPARATOR = " ";
	public static final String EXPECTED_OUTPUT_SEPARATOR = "@"
			+ System.getProperty("line.separator");

}
