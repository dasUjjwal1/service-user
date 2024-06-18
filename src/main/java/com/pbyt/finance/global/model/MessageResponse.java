package com.pbyt.finance.global.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class MessageResponse {
    private HttpStatus status;
    private String message;
    private Object object;
}
