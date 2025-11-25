package com.example.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthResponse {
    private String token;

    // No-arg constructor (needed by Jackson)
    public AuthResponse() {
    }

    // Our custom constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter & Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
