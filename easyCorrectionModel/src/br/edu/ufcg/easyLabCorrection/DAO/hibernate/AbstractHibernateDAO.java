package br.edu.ufcg.easyLabCorrection.DAO.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import br.edu.ufcg.easyLabCorrection.exceptions.EasyCorrectionRunTimeException;

/**
 * 
 * @author Demetrio
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractHibernateDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	private Session session;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractHibernateDAO(Session s) {
		session = s;
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		ID id = null;
		try {
			HibernateUtil.beginTransaction();
			id = (ID) getSession().save(entity);
		} catch (Exception e) {
			throw new EasyCorrectionRunTimeException(e.getMessage());
		} finally {
			HibernateUtil.commitTransactionCloseSession();
		}
		return id;
	}

	public void saveLista(List<Object> lista) {
		HibernateUtil.beginTransaction();
		for (Object object : lista) {
			getSession().save(object);
		}
		HibernateUtil.commitTransactionCloseSession();
	}

	public void update(T entity) {
		try {
			HibernateUtil.beginTransaction();
			getSession().update(entity);
		} catch (Exception e) {
			throw new EasyCorrectionRunTimeException(e.getMessage());
		} finally {
			HibernateUtil.commitTransactionCloseSession();
		}
	}

	public void merge(T entity) {
		try {
			HibernateUtil.beginTransaction();
			getSession().merge(entity);
		} catch (Exception e) {
			throw new EasyCorrectionRunTimeException(e.getMessage());
		} finally {
			HibernateUtil.commitTransactionCloseSession();
		}
	}

	public void saveOrUpdate(T entity) {
		try {
			HibernateUtil.beginTransaction();
			getSession().saveOrUpdate(entity);
		} catch (Exception e) {
			throw new EasyCorrectionRunTimeException(e.getMessage());
		} finally {
			HibernateUtil.commitTransactionCloseSession();
		}
	}

	public void delete(T entity) {
		try {
			HibernateUtil.beginTransaction();
			getSession().delete(entity);
		} catch (Exception e) {
			throw new EasyCorrectionRunTimeException(e.getMessage());
		} finally {
			HibernateUtil.commitTransactionCloseSession();
		}
	}

	public void delete(T entity, Boolean doItNow) {
		HibernateUtil.beginTransaction();
		if (doItNow) {
			getSession().setFlushMode(FlushMode.ALWAYS);
		}
		delete(entity);
		HibernateUtil.commitTransactionCloseSession();
	}

	/**
	 * This method will execute an HQL query and return the number of affected
	 * entities. Já realiza o commit e fecha a sessao
	 */
	protected int executeQuery(String query, String[] namedParams,
			Object[] params) {
		Query q = getSession().createQuery(query);
		if (namedParams != null) {
			for (int i = 0; i < namedParams.length; i++) {
				q.setParameter(namedParams[i], params[i]);
			}
		}
		int retorno = q.executeUpdate();
		HibernateUtil.closeSession();
		return retorno;
	}

	/**
	 * Executa a query e reliza o commit.
	 * 
	 * @param query
	 * @return
	 */
	protected int executeQuery(String query) {
		HibernateUtil.beginTransaction();
		int retorno = executeQuery(query, null, null);
		HibernateUtil.commitTransactionCloseSession();
		return retorno;
	}

	/**
	 * This method will execute a Named HQL query and return the number of
	 * affected entities.
	 */
	protected int executeNamedQuery(String namedQuery, String[] namedParams,
			Object[] params) {
		Query q = getSession().getNamedQuery(namedQuery);
		if (namedParams != null) {
			for (int i = 0; i < namedParams.length; i++) {
				q.setParameter(namedParams[i], params[i]);
			}
		}
		int retorno = q.executeUpdate();
		HibernateUtil.closeSession();
		return retorno;
	}

	protected int executeNamedQuery(String namedQuery) {
		return executeNamedQuery(namedQuery, null, null);
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id) {

		T t = (T) getSession().get(getPersistentClass(), id);
		if (t != null) {
			List<T> l = new LinkedList<T>();
			l.add(t);
			instantiatesList(l);
			return l.get(0);
		} else {
			return t;
		}

	}

	@SuppressWarnings("unchecked")
	public T getById(ID id, boolean lock) {
		if (lock) {
			return (T) getSession().get(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else
			return getById(id);
	}

	@SuppressWarnings("unchecked")
	public T loadById(ID id) {
		return (T) getSession().load(getPersistentClass(), id);
	}

	public void deleteById(ID id) {
		getSession().delete(loadById(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		crit.setMaxResults(5000);// FIXME limitar a consulta
		List<T> lista = crit.list();
		instantiatesList(lista);
		return lista;
	}

	/**
	 * Find by criteria.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Map criterias) {

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.allEq(criterias));
		List<T> lista = criteria.list();
		instantiatesList(lista);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).excludeZeroes()
				.enableLike().ignoreCase();
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}

		crit.add(example);
		List<T> lista = crit.list();
		instantiatesList(lista);
		return lista;
	}

	public abstract List<T> instantiatesList(List<T> lista);

}