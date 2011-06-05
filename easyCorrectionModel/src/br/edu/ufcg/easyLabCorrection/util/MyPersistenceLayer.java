package br.edu.ufcg.easyLabCorrection.util;

import org.hibernate.proxy.HibernateProxy;

/**
 * Class responsible by the persistence layer in the system ELC.<br>
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 14 of May of 2011.<br>
 */
public class MyPersistenceLayer {

	/**
	 * Function that performs communication with hibernate.<br>
	 */
	public static <T> T deproxy(Object maybeProxy, Class<T> baseClass) throws ClassCastException {
		if (maybeProxy instanceof HibernateProxy)
			return baseClass.cast(((HibernateProxy) maybeProxy).getHibernateLazyInitializer().getImplementation());
		else
			return baseClass.cast(maybeProxy);
	}
}
