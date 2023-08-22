package org.djcortez.hibernatejpa.services;

import org.djcortez.hibernatejpa.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User get(Long id);
    void save(User user);
    void delete(Long id);
}
