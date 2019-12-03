package com.movieaccess.rest.payload;

public class UserResponse {
    private long userId;
    private String username;
    private String email;

    public UserResponse(long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
