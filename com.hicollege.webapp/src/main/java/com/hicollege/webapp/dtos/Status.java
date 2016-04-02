package com.hicollege.webapp.dtos;

public class Status {
    
    public enum StatusCode {OK, ERROR} 
    
    private StatusCode statusCode;
    private String message;

    public Status(StatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
