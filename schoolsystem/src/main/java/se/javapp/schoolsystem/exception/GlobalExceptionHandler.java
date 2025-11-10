package se.javapp.schoolsystem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, Object> body = new HashMap<>();

        body.put("error", ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> error.put(err.getField(), err.getDefaultMessage()));

        body.put("errors", error);

        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = new HashMap<>();

        body.put("error", "Unexpected error: " + ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(body);
    }
}
