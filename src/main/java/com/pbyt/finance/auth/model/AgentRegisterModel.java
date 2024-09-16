package com.pbyt.finance.auth.model;

import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.global.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class AgentRegisterModel {
    @NotNull
    @NotBlank
    private Gender gender;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private Date dob;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Long mobileNumber;

    private Address address;

    private Set<Integer> workArea;

    private Long whatsappNumber;

    private String workExperienceAsFinance;

    private String qualification;

    private String previousWorkExperience;

    private String productWorkEarlier;

}
