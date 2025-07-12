package com.example.spoonacular.dtos.auth;

public class TokenValidationResDto {
    private boolean isValid;
    private String username;
    private String fullName;

    public TokenValidationResDto() {
    }

    // Getters and setters
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}