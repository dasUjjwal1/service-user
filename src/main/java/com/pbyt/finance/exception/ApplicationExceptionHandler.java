package com.pbyt.finance.exception;


import com.pbyt.finance.global.model.MessageResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<MessageResponse> handleNUmberFormat(NumberFormatException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
    @ExceptionHandler(InvalidCredential.class)
    public ResponseEntity<MessageResponse> invalidCredential(InvalidCredential invalidCredential) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, invalidCredential.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> genericHandler(Exception ex) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> runtimeHandler(RuntimeException invalidCredential) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, invalidCredential.getMessage());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessageResponse> authHandler(UsernameNotFoundException invalidCredential) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, invalidCredential.getMessage());
    }
    @ExceptionHandler(TokenNull.class)
    public ResponseEntity<MessageResponse> tokenNull(TokenNull invalidCredential) {
        return createHttpResponse(HttpStatus.FORBIDDEN, invalidCredential.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageResponse> handleAccessDeniedException(AccessDeniedException e) {
        return createHttpResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalException(AccessDeniedException e) {
        return createHttpResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }
    @ExceptionHandler(BadRequestHandler.class)
    public ResponseEntity<MessageResponse> handleIllegalException(BadRequestHandler e) {
        return createHttpResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }
    public ResponseEntity<MessageResponse> createHttpResponse(HttpStatus status, String message) {
        MessageResponse response = MessageResponse.builder()
                .message(message)
                .status(status).build();
        return new ResponseEntity<>(response, status);
    }
}
