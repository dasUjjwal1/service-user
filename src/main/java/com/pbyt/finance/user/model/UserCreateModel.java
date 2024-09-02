package com.pbyt.finance.user.model;

import com.pbyt.finance.entity.Address;
import com.pbyt.finance.entity.TblWorkArea;
import com.pbyt.finance.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
@Data
public class UserCreateModel {
    @NotNull
    @Min(1)
    private Long mobileNumber;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Date dob;

    private Address address;
    @NotNull
    private TblWorkArea workingArea;
    @NotNull
    @NotEmpty(message = "Input authorities list cannot be empty.")
    private Collection<RoleEnum> authorities;
}
