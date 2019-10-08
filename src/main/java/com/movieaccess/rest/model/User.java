package com.movieaccess.rest.model;

import java.util.Set;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Set<Role> role;
    private boolean active;

    public User() {
    }

    public User(String username, String email, String password, Set<Role> roles, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = roles;
        this.active = active;
    }

    public User(int id, String username, String email, String password, Set<Role> roles, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = roles;
        this.active = active;
    }

    public User(String username, String email, String password, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return role;
    }

    public void setRoles(Set<Role> role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + role +
                ", active=" + active +
                '}';
    }
}
