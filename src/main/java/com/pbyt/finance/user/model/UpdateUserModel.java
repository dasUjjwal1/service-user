package com.pbyt.finance.user.model;

import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
    private Set<TblStateDistrict> workArea;
    @NotEmpty(message = "Input authorities list cannot be empty.")
    private Collection<RoleEnum> authorities;
}
