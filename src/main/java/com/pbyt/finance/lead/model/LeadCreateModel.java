package com.pbyt.finance.lead.model;

import com.pbyt.finance.global.enums.Gender;
import com.pbyt.finance.lead.enums.LeadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class LeadCreateModel {
    private BigInteger id;
    private String leadNumber;
    @NotNull
    @NotBlank
    private String name;
    @NotBlank
    @NotNull
    private String subCategoryId;
    private String categoryName;
    private Gender gender;
    @NotNull
    @NotBlank
    private String mobileNumber;
    private LeadStatus status;
    @NotBlank
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;
    @NotBlank
    @NotNull
    private BigInteger barangayId;
    private String barangayName;
    @NotBlank
    @NotNull
    private BigInteger municipalityId;
    private String municipalityName;
    @NotBlank
    @NotNull
    private BigInteger provinceId;
    private String provinceName;
}
