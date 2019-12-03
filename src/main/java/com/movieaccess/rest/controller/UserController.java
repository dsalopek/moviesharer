package com.movieaccess.rest.controller;

import com.movieaccess.rest.model.User;
import com.movieaccess.rest.model.UserSummary;
import com.movieaccess.rest.payload.UserResponse;
import com.movieaccess.rest.security.CurrentUser;
import com.movieaccess.rest.security.UserPrincipal;
import com.movieaccess.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername());
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
