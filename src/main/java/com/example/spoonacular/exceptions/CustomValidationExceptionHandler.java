package com.example.spoonacular.exceptions;

import java.util.List;

import org.springframework.http.HttpStatusCode;

public class CustomValidationExceptionHandler extends RuntimeException {
    private HttpStatusCode status;
    private final List<String> errors;

    public CustomValidationExceptionHandler(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
        this.errors = List.of(message);
    }

    // Add missing constructors and getter
    public CustomValidationExceptionHandler(HttpStatusCode status, List<String> errors) {
        super("Validation failed");
        this.status = status;
        this.errors = errors;
    }

    public CustomValidationExceptionHandler(HttpStatusCode status, String message, List<String> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }
}
