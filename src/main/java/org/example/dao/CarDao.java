package org.example.dao;

import org.example.model.User;

import java.util.Optional;

public interface CarDao {
    User save(User car);
    User get(Long id);
    Optional<User> findById(Long id);
    User update(User car);
    boolean delete(User car);
}
