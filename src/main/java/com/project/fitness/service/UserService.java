package com.project.fitness.service;

import com.project.fitness.dto.LoginRequest;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.RegisterResponse;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRoles;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse createUser(RegisterRequest request) {

        UserRoles role= request.getUserRoles() !=null ? request.getUserRoles(): UserRoles.ROLE_USER;
        System.out.println("login " +request.toString());
        User user = User.builder().firstName(request.getFirstName()).
                lastName(request.getLastName()).roles(role).
                email(request.getEmail()).
                password(passwordEncoder.encode(request.getPassword()))
                .build();

        System.out.println("User "+user.toString());
        System.out.println("login " +request.toString());
        User response = userRepository.save(user);

        return mapResponse(response);
    }

    public RegisterResponse mapResponse(User response) {

        return RegisterResponse.builder().id(response.getId()).
                email(response.getEmail()).createdAt(response.getCreatedAt()).
                userRoles(response.getRoles()).
                firstName(response.getFirstName()).
                lastName(response.getLastName()).
                build();
    }

    public User getUserByEmail(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail());

        if(user == null){
            throw  new RuntimeException("User not found");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw  new RuntimeException("Incorrect credentials");
        }
        return user;
    }
}
