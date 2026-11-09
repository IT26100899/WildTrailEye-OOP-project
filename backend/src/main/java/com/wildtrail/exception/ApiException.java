package com.wildtrail.exception;

import org.springframework.http.HttpStatus;

// ApiException: Java class for the WildTrail application.
public class ApiException
extends RuntimeException {
    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    // Returns an existing value or related record.
    public HttpStatus getStatus() {
        return this.status;
    }

    // Handles bad request using the existing project logic.
    public static ApiException badRequest(String message) {
        return new ApiException(HttpStatus.BAD_REQUEST, message);
    }

    // Handles not found using the existing project logic.
    public static ApiException notFound(String message) {
        return new ApiException(HttpStatus.NOT_FOUND, message);
    }

    // Handles conflict using the existing project logic.
    public static ApiException conflict(String message) {
        return new ApiException(HttpStatus.CONFLICT, message);
    }

    // Handles unauthorized using the existing project logic.
    public static ApiException unauthorized(String message) {
        return new ApiException(HttpStatus.UNAUTHORIZED, message);
    }

    // Handles forbidden using the existing project logic.
    public static ApiException forbidden(String message) {
        return new ApiException(HttpStatus.FORBIDDEN, message);
    }
}
