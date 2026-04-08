package com.project.fitness.dto;

import com.project.fitness.model.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    private String firstName;
    private String lastName;
    @NotBlank(message = "Email cant be blank")
    @Email(message = "Invalid Email" )
    private String email;
    @NotBlank(message = "Password not provided")
    private String password;
    private UserRoles userRoles;

}
