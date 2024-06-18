package com.pbyt.finance.exception;


import com.pbyt.finance.global.model.MessageResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler implements ErrorController {
    @ExceptionHandler(AlreadyPresent.class)
    public ResponseEntity<MessageResponse> alreadyPresent(AlreadyPresent alreadyPresent) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, "Already Present");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleInvalidRequestData(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
    }

    public ResponseEntity<MessageResponse> createHttpResponse(HttpStatus status, String message) {
        MessageResponse response = MessageResponse.builder()
                .message(message)
                .status(status).build();
        return new ResponseEntity<>(response, status);
    }
}
