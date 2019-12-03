package com.movieaccess.rest.service;

import com.movieaccess.rest.model.User;
import com.movieaccess.rest.payload.UserResponse;
import com.movieaccess.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail())).collect(Collectors.toList());
    }
}
