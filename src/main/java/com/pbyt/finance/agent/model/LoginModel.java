package com.pbyt.finance.agent.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginModel {
    @NotNull
    @NotBlank
    private String mobileNumber;
    @NotNull
    @NotBlank
    private String password;
}
