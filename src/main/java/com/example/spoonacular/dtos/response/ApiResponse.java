package com.example.spoonacular.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {
    
    @JsonProperty("status")
    private int status;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("val")
    private T val;
    
    @JsonProperty("endpoint")
    private String endpoint;
    
    // Constructors
    public ApiResponse() {}
    
    public ApiResponse(int status, String message, T val, String endpoint) {
        this.status = status;
        this.message = message;
        this.val = val;
        this.endpoint = endpoint;
    }
    
    // Static factory methods for success responses
    public static <T> ApiResponse<T> success(T data, String endpoint) {
        return new ApiResponse<>(200, "Success", data, endpoint);
    }
    
    public static <T> ApiResponse<T> success(T data, String message, String endpoint) {
        return new ApiResponse<>(200, message, data, endpoint);
    }
    
    public static <T> ApiResponse<T> created(T data, String endpoint) {
        return new ApiResponse<>(201, "Resource created successfully", data, endpoint);
    }
    
    public static <T> ApiResponse<T> noContent(String endpoint) {
        return new ApiResponse<>(204, "No content", null, endpoint);
    }
    
    // Static factory methods for error responses
    public static <T> ApiResponse<T> error(int status, String message, String endpoint) {
        return new ApiResponse<>(status, message, null, endpoint);
    }
    
    public static <T> ApiResponse<T> error(int status, String message, T errorDetails, String endpoint) {
        return new ApiResponse<>(status, message, errorDetails, endpoint);
    }
    
    public static <T> ApiResponse<T> badRequest(String message, String endpoint) {
        return new ApiResponse<>(400, message, null, endpoint);
    }
    
    public static <T> ApiResponse<T> unauthorized(String message, String endpoint) {
        return new ApiResponse<>(401, message, null, endpoint);
    }
    
    public static <T> ApiResponse<T> forbidden(String message, String endpoint) {
        return new ApiResponse<>(403, message, null, endpoint);
    }
    
    public static <T> ApiResponse<T> notFound(String message, String endpoint) {
        return new ApiResponse<>(404, message, null, endpoint);
    }
    
    public static <T> ApiResponse<T> internalServerError(String message, String endpoint) {
        return new ApiResponse<>(500, message, null, endpoint);
    }
    
    // Getters and Setters
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public T getVal() { return val; }
    public void setVal(T val) { this.val = val; }
    
    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
}
