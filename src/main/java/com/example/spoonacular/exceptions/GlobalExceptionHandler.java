package com.example.spoonacular.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spoonacular.dtos.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDto<Object>> handleBadCredentials(BadCredentialsException exception) {
        exception.printStackTrace();

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(401);
        response.setMessage("The username or password is incorrect");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ResponseDto<Object>> handleAccountStatus(AccountStatusException exception) {
        exception.printStackTrace();

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(403);
        response.setMessage("The account is locked");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto<Object>> handleAccessDenied(AccessDeniedException exception) {
        exception.printStackTrace();

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(403);
        response.setMessage("You are not authorized to access this resource");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseDto<Object>> handleSignature(SignatureException exception) {
        exception.printStackTrace();

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(403);
        response.setMessage("The JWT signature is invalid");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseDto<Object>> handleExpiredJwt(ExpiredJwtException exception) {
        exception.printStackTrace();

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(403);
        response.setMessage("The JWT token has expired");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.example.spoonacular.dtos.dto.ResponseDto<Object>> handleGeneral(
            Exception exception) {
        exception.printStackTrace();

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(500);
        response.setMessage("Unknown internal server error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}