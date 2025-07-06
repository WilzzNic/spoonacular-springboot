package com.example.spoonacular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spoonacular.dtos.ResponseDto;
import com.example.spoonacular.dtos.auth.LoginReqDto;
import com.example.spoonacular.dtos.auth.LoginResDto;
import com.example.spoonacular.dtos.auth.RegisterReqDto;
import com.example.spoonacular.dtos.auth.RegisterResDto;
import com.example.spoonacular.models.User;
import com.example.spoonacular.services.AuthenticationService;
import com.example.spoonacular.services.JwtService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<RegisterResDto>> register(@Valid @RequestBody RegisterReqDto registerUserDto) {
        RegisterResDto user = authenticationService.signup(registerUserDto);

        ResponseDto<RegisterResDto> value = ResponseDto.success(user, null);

        return ResponseEntity.ok(value);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResDto>> authenticate(@RequestBody LoginReqDto loginUserDto) {
        LoginResDto value = authenticationService.authenticate(loginUserDto);

        ResponseDto<LoginResDto> apiResponse = ResponseDto.success(value, null);

        return ResponseEntity.ok(apiResponse);
    }

}
