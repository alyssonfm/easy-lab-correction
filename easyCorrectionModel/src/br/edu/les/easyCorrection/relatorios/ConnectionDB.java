package br.edu.les.easyCorrection.relatorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alysson, Augusto e Demetrio.
 *
 */
public class ConnectionDB {
	/**
	 * O nome do host
	 */
	private String host;
	
	/**
	 * O schema do banco de dados
	 */
	private String database;
	
	/**
	 * O nome do usuario
	 */
	private String username;
	
	/**
	 * A senha do usuário
	 */
	private String password;
	
	/**
	 * A classe de conexão
	 */
	private Connection connection;
	
	
	/**
	 * Constrói uma conexão a partir das informações necessárias: host, database, username, password
	 * 
	 * @param host
	 * @param database
	 * @param username
	 * @param password
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
	 * @return A conexão gerada
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Fecha a conexão
	 */
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar conex�o.");
		}
	}
}
