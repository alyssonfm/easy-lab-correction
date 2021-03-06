package br.edu.ufcg.easyLabCorrection.DAO.hibernate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.edu.ufcg.easyLabCorrection.util.Constants;
import flex.messaging.FlexContext;
import flex.messaging.FlexSession;

/**
 * Generated at Wed Apr 30 16:34:10 GMT-03:00 2008
 * 
 * @author Salto-db Eclipse v1.0.15 / Pojos + Hibernate mapping + Generic DAO
 */
public final class HibernateUtil {

	private static SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	private static final ThreadLocal threadSession = new ThreadLocal();

	@SuppressWarnings("unchecked")
	private static final ThreadLocal threadTransaction = new ThreadLocal();

	static {
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex.getCause());
		}
	}

	private HibernateUtil() {

	}

	/**
	 * Returns the SessionFactory used for this static class.
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Retrieves the current Session local to the thread.
	 * <p/>
	 * If no Session is open, opens a new Session for the running thread.
	 * 
	 * @return Session
	 */
	@SuppressWarnings("unchecked")
	public static Session getSession() {
		Session s = (Session) threadSession.get();

		if (s == null) {

			s = getSessionFactory().openSession();
			threadSession.set(s);
			s.setFlushMode(FlushMode.ALWAYS);
			// beginTransaction();
		}

		return s;
	}

	/**
	 * Closes the Session local to the thread.
	 */
	@SuppressWarnings("unchecked")
	public static void closeSession() {

		Session s = (Session) threadSession.get();
		threadSession.set(null);
		if (s != null && s.isOpen()) {
			s.close();
		}

	}

	/**
	 * Start a new database transaction.
	 */
	@SuppressWarnings("unchecked")
	public static void beginTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();

		if (tx == null) {

			tx = getSession().beginTransaction();
			threadTransaction.set(tx);
		}

	}

	/**
	 * Commit the database transaction.
	 */
	@SuppressWarnings("unchecked")
	public static void commitTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();

		if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {

			tx.commit();
		}
		threadTransaction.set(null);

	}

	/**
	 * comando para efetuar a transação fechando a sessão.
	 */
	@SuppressWarnings("unchecked")
	public static void commitTransactionCloseSession() {
		Transaction tx = (Transaction) threadTransaction.get();

		if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {

			try {
				tx.commit();
			} catch (HibernateException he) {
				tx.rollback();
				he.printStackTrace();
				throw he;
			} finally {
				closeSession();
				threadTransaction.set(null);
			}
		}

	}

	/**
	 * Rollback the database transaction.
	 */
	@SuppressWarnings("unchecked")
	public static void rollbackTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {

				tx.rollback();
			}

		} finally {
			closeSession();
		}
	}

	public static void executeSQL(String sqlScript) throws IOException {

		FileReader reader = new FileReader(sqlScript);
		BufferedReader leitor = new BufferedReader(reader);
		String linha = null;
		Vector<String> v = new Vector<String>();
		while ((linha = leitor.readLine()) != null) {
			v.add(linha);
		}

		Transaction t = getSession().beginTransaction();

		try {
			for (int i = 0; i < v.size(); i++) {
				String st = v.get(i).trim();
				if (!st.equals("") && !st.substring(0,2).equals("--") && !st.substring(0,2).equals("/*")) {
					Query q = getSession().createSQLQuery(st);
		            q.executeUpdate();
				}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		try {
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}

	public static void main(String[] args) throws IOException {
		HibernateUtil.executeSQL(Constants.bdTestBackupFile);
	}
	
	public static void setCurrentStageId(int systemStage){ 
		FlexSession session = FlexContext.getFlexSession();
		session.setAttribute("stageId", systemStage);
	}
	
	public static int getCurrentStageId(){
		try{
			FlexSession session = FlexContext.getFlexSession();
			return (Integer) session.getAttribute("stageId");
		} catch(NullPointerException ne){
			return 1;
		}
	}

}
