package com.pbyt.finance.auth.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginModel {
    @NotNull
    @Min(value = 1)
    private Long mobileNumber;

    @NotBlank
    @NotNull
    @Size(min = 3)
    private String password;
}
