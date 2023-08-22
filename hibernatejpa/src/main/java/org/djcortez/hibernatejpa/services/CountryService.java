package org.djcortez.hibernatejpa.services;

import org.djcortez.hibernatejpa.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> getAll();
    Optional<Country> get(Long id);
    void save(Country country);
    void delete(Long id);
}
