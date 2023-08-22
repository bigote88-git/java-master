package org.djcortez.hibernatejpa.repositories;

import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.User;

import java.util.List;

public class UserRepository implements CrudRepository<User> {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User entity) {
        if (entity != null && (entity.getId() != null && entity.getId() > 0))
            entityManager.merge(entity);
        else
            entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        User userToDelete = findById(id);
        if (userToDelete != null)
            entityManager.remove(userToDelete);
    }
}
