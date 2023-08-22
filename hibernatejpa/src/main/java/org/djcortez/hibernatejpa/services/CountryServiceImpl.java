package org.djcortez.hibernatejpa.services;

import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Country;
import org.djcortez.hibernatejpa.repositories.CountryRepository;
import org.djcortez.hibernatejpa.repositories.CrudRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService{

    EntityManager entityManager;
    CrudRepository<Country> countryCrudRepository;

    public CountryServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        countryCrudRepository = new CountryRepository(entityManager);
    }

    @Override
    public List<Country> getAll() {
        return countryCrudRepository.getAll();
    }

    @Override
    public Optional<Country> get(Long id) {
        return Optional.ofNullable(countryCrudRepository.findById(id));
    }

    @Override
    public void save(Country country) {
        try{
            entityManager.getTransaction().begin();
            countryCrudRepository.save(country);
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
            countryCrudRepository.delete(id);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
