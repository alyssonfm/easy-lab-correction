package br.edu.ufcg.easyLabCorrection.DAO.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generated at Fri Jan 30 09:30:06 GMT-03:00 2009
 *
 * @author Salto-db Generator v1.0.16 / Pojos + Hibernate mapping + Generic DAO
 * @see http://www.hibernate.org/328.html
 */
public interface GenericDAO<T, ID extends Serializable> {

	T getById(ID id, boolean lock);

	T getById(ID id);
	
	T loadById(ID id);

	List<T> findAll();
	
	@SuppressWarnings("unchecked")
	List<T> findByCriteria(Map criterias);
	
	public List<T> findByExample(T exampleInstance, String[] excludeProperty);

	void saveLista(List <Object> entity);
	
	ID save(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	void delete(T entity);
	
	void deleteById(ID id);
	
	void merge(T entity);
}