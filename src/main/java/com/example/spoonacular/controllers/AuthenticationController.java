package com.example.spoonacular.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spoonacular.dtos.ResponseDto;
import com.example.spoonacular.dtos.auth.LoginReqDto;
import com.example.spoonacular.dtos.auth.LoginResDto;
import com.example.spoonacular.dtos.auth.RegisterReqDto;
import com.example.spoonacular.dtos.auth.RegisterResDto;
import com.example.spoonacular.dtos.auth.TokenValidationResDto;
import com.example.spoonacular.services.AuthenticationService;

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

    // Alternative: Using Authorization header
    @PostMapping("/validate")
    public ResponseEntity<ResponseDto<TokenValidationResDto>> validateTokenFromHeader(
            @RequestHeader("Authorization") String authorizationHeader) {

        TokenValidationResDto value = authenticationService.validateToken(authorizationHeader);

        HttpStatus httpStatus;
        ResponseDto<TokenValidationResDto> response;
        String message;
        if (value.isValid()) {
            message = "Token is valid for user: " + value.getUsername();
            response = ResponseDto.success(value, message);
            httpStatus = HttpStatus.OK;

            return ResponseEntity.ok(response);
        } else {
            message = "Token is invalid or expired";
            response = ResponseDto.unauthorized(message);
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return ResponseEntity.status(httpStatus).body(response);
    }
}
