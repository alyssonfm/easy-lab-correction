package br.edu.les.easyCorrection.util;

import org.hibernate.proxy.HibernateProxy;

public class MyPersistenceLayer {

	public static <T> T deproxy(Object maybeProxy, Class<T> baseClass) throws ClassCastException {
		if (maybeProxy instanceof HibernateProxy)
			return baseClass.cast(((HibernateProxy) maybeProxy).getHibernateLazyInitializer().getImplementation());
		else
			return baseClass.cast(maybeProxy);
	}
}
