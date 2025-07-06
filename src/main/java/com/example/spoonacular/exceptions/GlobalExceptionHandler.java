package com.example.spoonacular.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spoonacular.dtos.ResponseDto;
import com.example.spoonacular.dtos.auth.RegisterResDto;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> handleValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(422);
        response.setMessage("Validation failed");
        response.setErrors(errors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto<Object>> handleConstraintViolation(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(422);
        response.setMessage("Validation failed");
        response.setErrors(errors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    // ...existing handlers...

    @ExceptionHandler(CustomValidationExceptionHandler.class)
    public ResponseEntity<ResponseDto<Object>> handleCustomValidation(CustomValidationExceptionHandler exception) {

        ResponseDto<Object> response = new ResponseDto<>();
        response.setStatus(exception.getStatus().value());
        response.setMessage(exception.getMessage());
        response.setErrors(exception.getErrors());

        return ResponseEntity.status(exception.getStatus()).body(response);
    }
}
