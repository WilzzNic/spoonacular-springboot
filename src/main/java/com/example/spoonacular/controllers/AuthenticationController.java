package com.example.spoonacular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spoonacular.dtos.dto.ResponseDto;
import com.example.spoonacular.dtos.dto.auth.LoginReqDto;
import com.example.spoonacular.dtos.dto.auth.LoginResDto;
import com.example.spoonacular.dtos.dto.auth.RegisterReqDto;
import com.example.spoonacular.models.User;
import com.example.spoonacular.services.AuthenticationService;
import com.example.spoonacular.services.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterReqDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResDto>> authenticate(@RequestBody LoginReqDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResDto loginResponse = new LoginResDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        ResponseDto<LoginResDto> apiResponse = ResponseDto.success(loginResponse, null);

        return ResponseEntity.ok(apiResponse);
    }

}
