package com.youcode.gameyou.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiRequestHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<HashMap<String, Object>> handleApiRequestException(ApiException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", apiException.getMessage());
        response.put("status", apiException.getHttpStatus());
        return new ResponseEntity<>(response, apiException.getHttpStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> handleApiRequestException(NotFoundException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", apiException.getMessage());
        response.put("status", apiException.getHttpStatus());
        return new ResponseEntity<>(response, apiException.getHttpStatus());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<HashMap<String, Object>> handleBadRequestException(BadRequestException e) {
        // Customise the exception body
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", apiException.getMessage());
        response.put("status", apiException.getHttpStatus());
        // Return the exception response
        return new ResponseEntity<>(response, apiException.getHttpStatus());
    }
}
