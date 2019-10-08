package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findByUsername(String username);
    //Optional<User> findByEmail(String email);
    Optional<User> findById(long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User save(User user);
}
