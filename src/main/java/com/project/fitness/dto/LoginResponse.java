package com.project.fitness.dto;

import com.project.fitness.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String message;
    private RegisterResponse user;

    public LoginResponse(String message) {
        this.message = message;
    }
}
