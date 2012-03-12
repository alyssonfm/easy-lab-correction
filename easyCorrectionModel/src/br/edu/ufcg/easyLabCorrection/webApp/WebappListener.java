package br.edu.ufcg.easyLabCorrection.webApp;

import java.sql.Driver;

import java.sql.DriverManager;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.LogManager;


/**
 * Class responsible for realizes the web communication.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class WebappListener implements ServletContextListener {

	/**
	 * Procedure that initializes the context listener, receives a 
	 * ServletContextEvent as parameter.<br> 
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Iniciando context listener "
				+ sce.getServletContext().getContextPath() + "...");
		
		if (sce.getServletContext().getContextPath().equals(
				"/easyCorrection")) {
			  
			new ELCScheduler(6, 23, 0);
		}
	}

	/**
	 * Procedure that destroys the context listener, receives a 
	 * ServletContextEvent as parameter.<br>
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			// shuts down all the application's logger and libraries used
			LogManager.shutdown();
			// JDBC drivers off the possible use
			Enumeration<Driver> enumer = DriverManager.getDrivers();
			while (enumer.hasMoreElements()) {
				DriverManager.deregisterDriver(enumer.nextElement());
			}
			System.gc();
			System.out.println("Finalizando context listener "
					+ sce.getServletContext().getContextPath() + ".");
		} catch (java.sql.SQLException se) {
			se.printStackTrace();
		}
	}
}
