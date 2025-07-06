package com.example.spoonacular.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spoonacular.dtos.auth.LoginReqDto;
import com.example.spoonacular.dtos.auth.LoginResDto;
import com.example.spoonacular.dtos.auth.RegisterReqDto;
import com.example.spoonacular.dtos.auth.RegisterResDto;
import com.example.spoonacular.exceptions.CustomValidationExceptionHandler;
import com.example.spoonacular.models.User;
import com.example.spoonacular.repositories.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResDto signup(RegisterReqDto input) {
        if (userRepository.existsByUsername(input.getUsername())) {
            throw new CustomValidationExceptionHandler(HttpStatus.CONFLICT, "Username already exists");
        }

        RegisterResDto result = null;

        User user = new User();
        user.setUsername(input.getUsername());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        user = userRepository.save(user);

        result = new RegisterResDto();
        result.setUsername(user.getUsername());
        result.setFullName(user.getFullName());
        result.setEmail(user.getEmail());

        return result;
    }

    public LoginResDto authenticate(LoginReqDto input) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()));

        User user = userRepository.findById(input.getUsername())
                .orElseThrow();

        LoginResDto result = new LoginResDto();
        result.setToken(jwtService.generateToken(user));
        result.setExpiresIn(jwtService.getExpirationTime());

        return result;
    }
}