package br.edu.ufcg.easyLabCorrection.DAO.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T, ID extends Serializable> {

	T getById(ID id, boolean lock);

	T getById(ID id);

	T loadById(ID id);

	List<T> findAll();

	@SuppressWarnings("unchecked")
	List<T> findByCriteria(Map criterias);

	List<T> findByExample(T exampleInstance, String[] excludeProperty);

	void saveLista(List<Object> entity);

	ID save(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	void delete(T entity);

	void deleteById(ID id);

	void merge(T entity);
}