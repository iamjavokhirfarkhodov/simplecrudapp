package com.springdatajdbcdemo.controllers;

import com.springdatajdbcdemo.util.ResultMessage;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionsController {


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> jwtExpired(JwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> argumentMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> argumentMismatch(EmptyResultDataAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not found!");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullArguments(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided null values!");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingParams(MissingServletRequestParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameters");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> incorrectUsername(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultMessage.builder()
                .message(e.getLocalizedMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .data("User not found!")
                .build());
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> emptyData(SQLException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultMessage.builder()
                .message(e.getLocalizedMessage())
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .data(null)
                .build());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> emptyData(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResultMessage.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .data("")
                .build());
    }
}
