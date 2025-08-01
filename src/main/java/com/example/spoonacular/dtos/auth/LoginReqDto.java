package com.example.spoonacular.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public class LoginReqDto {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
