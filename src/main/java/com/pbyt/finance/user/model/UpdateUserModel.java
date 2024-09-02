package com.pbyt.finance.user.model;

import com.pbyt.finance.entity.Address;
import com.pbyt.finance.entity.TblWorkArea;
import com.pbyt.finance.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class UpdateUserModel {
    @NotNull
    @Min(1)
    private Integer id;
    @Email
    private String email;
    private String name;
    private Date dob;
    private Address address;
    private TblWorkArea workingArea;
    @NotEmpty(message = "Input authorities list cannot be empty.")
    private Collection<RoleEnum> authorities;
}
