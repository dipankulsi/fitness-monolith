package com.project.fitness.dto;

import com.project.fitness.model.UserRoles;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class RegisterResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
   // private String password;
    private UserRoles userRoles;
    private LocalDateTime createdAt;
   // private LocalDateTime updatedAt;

}
