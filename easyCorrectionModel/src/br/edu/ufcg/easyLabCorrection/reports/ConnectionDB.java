package br.edu.ufcg.easyLabCorrection.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class which realizes the connection with database of system Easy Lab Correction.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class ConnectionDB {
	/**
	 * The host name.<br>
	 */
	private String host;
	
	/**
	 * The database schema.<br>
	 */
	private String database;
	
	/**
	 * The user's name.<br>
	 */
	private String username;
	
	/**
	 * The user's password.<br>
	 */
	private String password;
	
	/**
	 * The connection class.<br>
	 */
	private Connection connection;
	
	
	/**
	 * Constructs a connection from the information necessary: host, database, username, password.<br>
	 * @param host The host used.<br>
	 * @param database The database which will be used to connect.<br>
	 * @param username The name of user of database.<br>
	 * @param password The password of database user.<br> 
	 */
	public ConnectionDB(String host, String database, String username, String password) {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;

		try {        	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao registrar driver.");
		}

		try {
			this.connection =  DriverManager.getConnection("jdbc:mysql://" + this.host + 
				"/" + this.database /*+ "?useOldUTF8Behavior=true&characterEncoding=utf8"*/, this.username, this.password);
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao obter conex�o.");
		}
	}

	/**
	 * Function which returns the connection generated.<br>
	 * @return The connection generated.<br>
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Procedure which close the connection.<br>
	 */
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar conex�o.");
		}
	}
}
