package com.example.spoonacular.dtos.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto<T> {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<String> errors = new ArrayList<>();

    @JsonProperty("val")
    private T val;

    // Constructors
    public ResponseDto() {
    }

    public ResponseDto(int status, String message, T val, List<String> errors) {
        this.status = status;
        this.message = message;
        this.val = val;

        if (errors == null) {
            errors = new ArrayList<>();
        }
        
        this.errors = errors;
    }

    // Static factory methods for success responses
    public static <T> ResponseDto<T> success(T data, List<String> errors) {
        return new ResponseDto<>(200, "Success", data, errors);
    }

    public static <T> ResponseDto<T> success(T data, String message, List<String> errors) {
        return new ResponseDto<>(200, message, data, errors);
    }

    public static <T> ResponseDto<T> created(T data, List<String> errors) {
        return new ResponseDto<>(201, "Resource created successfully", data, errors);
    }

    public static <T> ResponseDto<T> noContent(List<String> errors) {
        return new ResponseDto<>(204, "No content", null, errors);
    }

    // Static factory methods for error responses
    public static <T> ResponseDto<T> error(int status, String message, List<String> errors) {
        return new ResponseDto<>(status, message, null, errors);
    }

    public static <T> ResponseDto<T> error(int status, String message, T errorDetails, List<String> errors) {
        return new ResponseDto<>(status, message, errorDetails, errors);
    }

    public static <T> ResponseDto<T> badRequest(String message, List<String> errors) {
        return new ResponseDto<>(400, message, null, errors);
    }

    public static <T> ResponseDto<T> unauthorized(String message, List<String> errors) {
        return new ResponseDto<>(401, message, null, errors);
    }

    public static <T> ResponseDto<T> forbidden(String message, List<String> errors) {
        return new ResponseDto<>(403, message, null, errors);
    }

    public static <T> ResponseDto<T> notFound(String message, List<String> errors) {
        return new ResponseDto<>(404, message, null, errors);
    }

    public static <T> ResponseDto<T> internalServerError(String message, List<String> errors) {
        return new ResponseDto<>(500, message, null, errors);
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
