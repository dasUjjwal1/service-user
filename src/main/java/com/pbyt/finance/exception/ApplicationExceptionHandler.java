package com.pbyt.finance.exception;


import com.pbyt.finance.global.model.MessageResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler implements ErrorController {
    @ExceptionHandler(AlreadyPresent.class)
    public ResponseEntity<MessageResponse> alreadyPresent(AlreadyPresent alreadyPresent) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, "Already Present");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse>  handleInvalidRequestData(MethodArgumentNotValidException exception) {
        String data = exception.getBindingResult().getFieldErrors().stream()
                .map(it->it.getField() +" "+ it.getDefaultMessage()).collect(Collectors.joining(","));
        MessageResponse response = MessageResponse.builder()
                .message(data)
                .status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<MessageResponse> handleNotFound(NotFound notFound) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, notFound.getMessage());
    }
    @ExceptionHandler(InvalidCredential.class)
    public ResponseEntity<MessageResponse> invalidCredential(InvalidCredential invalidCredential) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, invalidCredential.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> genericHandler(Exception invalidCredential) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, invalidCredential.getMessage());
    }

    public ResponseEntity<MessageResponse> createHttpResponse(HttpStatus status, String message) {
        MessageResponse response = MessageResponse.builder()
                .message(message)
                .status(status).build();
        return new ResponseEntity<>(response, status);
    }
}
