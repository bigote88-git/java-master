package org.djcortez.hibernatejpa.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> getAll();
    T findById(Long id);
    void save(T entity);
    void delete(Long id);
}
