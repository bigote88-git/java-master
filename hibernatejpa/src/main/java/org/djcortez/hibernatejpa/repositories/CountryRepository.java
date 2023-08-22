package org.djcortez.hibernatejpa.repositories;

import jakarta.persistence.EntityManager;
import org.djcortez.hibernatejpa.entities.Country;

import java.util.List;

public class CountryRepository implements CrudRepository<Country> {

    private EntityManager entityManager;

    public CountryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Country> getAll() {
        return entityManager.createQuery("select c from Country c", Country.class).getResultList();
    }

    @Override
    public Country findById(Long id) {
        return entityManager.find(Country.class, id);
    }

    @Override
    public void save(Country entity) {
        if (entity != null && (entity.getId() != null && entity.getId() > 0))
            entityManager.merge(entity);
        else
            entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        Country countryToDelete = findById(id);
        if (countryToDelete != null)
            entityManager.remove(countryToDelete);

    }
}
