package com.movieaccess.rest.controller;

import com.movieaccess.rest.model.User;
import com.movieaccess.rest.model.UserSummary;
import com.movieaccess.rest.security.CurrentUser;
import com.movieaccess.rest.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername());
    }
}
