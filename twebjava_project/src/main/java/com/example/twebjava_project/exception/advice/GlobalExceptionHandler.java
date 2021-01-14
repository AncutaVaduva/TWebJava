package com.example.twebjava_project.exception.advice;

import com.example.twebjava_project.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RallyNotFoundException.class,
            EditionNotFoundException.class,
            TeamNotFoundException.class,
            UpdateFailedException.class,
            TeamMemberNotFoundException.class,
            ParticipationAlreadyExistException.class,
            ParticipationNotFoundException.class,
            StageNotFoundException.class,
            ResultAlreadyRegisteredException.class
    })
    public ResponseEntity<String> handle(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body("Invalid value : " + e.getFieldError().getRejectedValue() +
                        " for field " + e.getFieldError().getField() +
                        " with message " + e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest()
                .body(e.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handle(SQLIntegrityConstraintViolationException  e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

}
