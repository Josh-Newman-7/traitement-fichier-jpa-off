package DAO;

import java.util.List;

public interface GenericDAO<T, K> {
	//Méthodes du CRUD
    T create(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(K id);
    List<T> findAll();
}
