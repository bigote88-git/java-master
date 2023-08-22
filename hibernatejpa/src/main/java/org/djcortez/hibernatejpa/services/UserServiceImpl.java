package org.djcortez.hibernatejpa.services;

import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.User;
import org.djcortez.hibernatejpa.repositories.CrudRepository;
import org.djcortez.hibernatejpa.repositories.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService{

    private EntityManager entityManager;

    private CrudRepository<User> userRepository;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        userRepository = new UserRepository(entityManager);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {

        try{
            entityManager.getTransaction().begin();
            userRepository.save(user);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try{
            entityManager.getTransaction().begin();
            User user = get(id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();

        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
