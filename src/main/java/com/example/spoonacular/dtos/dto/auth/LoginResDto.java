package com.example.spoonacular.dtos.dto.auth;

public class LoginResDto {
    private String token;

    private long expiresIn;

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

}