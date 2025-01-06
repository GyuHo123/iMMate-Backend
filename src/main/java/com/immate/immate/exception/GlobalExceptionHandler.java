package com.immate.immate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Invalid Input",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalStateException(IllegalStateException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.CONFLICT,
            "Invalid State",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidInvestmentStateException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidInvestmentStateException(InvalidInvestmentStateException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.CONFLICT,
            "Invalid Investment State",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidVoteException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidVoteException(InvalidVoteException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Invalid Vote",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTradingPlanException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTradingPlanException(InvalidTradingPlanException e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Invalid Trading Plan",
            e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        Map<String, Object> body = createErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            "An unexpected error occurred"
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> createErrorResponse(
            HttpStatus status,
            String error,
            String message
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return body;
    }
} 