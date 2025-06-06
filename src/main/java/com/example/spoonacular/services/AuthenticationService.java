package com.example.spoonacular.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spoonacular.dtos.LoginUserDto;
import com.example.spoonacular.dtos.RegisterUserDto;
import com.example.spoonacular.models.User;
import com.example.spoonacular.repositories.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setFullName(input.getFullName());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()));

        return userRepository.findById(input.getUsername())
                .orElseThrow();
    }
}