package com.example.demo.user.controller.advice;

import com.example.demo.user.exception.UserTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserTaskException.class)
    public ResponseEntity<?> handleUserTaskException(UserTaskException e) {
        return ResponseEntity.status(e.getCode()).body(Map.of("error", e.getMsg()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Set<String> errorMessages = new HashSet<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            errorMessages.add(message);
        });

        Map<String, String> response = new HashMap<>();
        response.put("error", String.join(", ", errorMessages));  // 에러 메시지들을 조인하여 반환

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
