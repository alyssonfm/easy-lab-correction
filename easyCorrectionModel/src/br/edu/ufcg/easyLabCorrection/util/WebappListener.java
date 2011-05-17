package br.edu.ufcg.easyLabCorrection.util;

import java.sql.Driver;

import java.sql.DriverManager;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.LogManager;

public class WebappListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Iniciando context listener "
				+ sce.getServletContext().getContextPath() + "...");
		
		if (sce.getServletContext().getContextPath().equals(
				"/easyCorrectionModel")) {
			//1 - Sunday, 2 - Monday, 3 - Tuesday, 4 - Wednesday, 5 - Thusday, 6 - Friday, 7 - Saturday  
			new ELCScheduler(6, 23, 0);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		try {
			// desliga todos os logger's da aplicação e bibliotecas utilizadas
			LogManager.shutdown();
			// desliga os possíveis drivers JDBC utilizados
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
