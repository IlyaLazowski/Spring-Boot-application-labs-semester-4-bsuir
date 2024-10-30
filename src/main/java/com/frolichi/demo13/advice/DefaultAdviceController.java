package com.frolichi.demo13.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultAdviceController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAdviceController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        logger.error("Неверные входные параметры: " + ex.getMessage(), ex);

        Map<String, Object> response = new HashMap<>();
        response.put("error", "Неверные входные параметры");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex, WebRequest request) {
        logger.error("Внутренняя ошибка сервера: " + ex.getMessage(), ex);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Внутренняя ошибка сервера");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

