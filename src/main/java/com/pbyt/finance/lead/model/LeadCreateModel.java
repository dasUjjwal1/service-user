package com.pbyt.finance.lead.model;

import com.pbyt.finance.global.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadCreateModel {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String subCategoryId;
    @NotNull
    private Gender gender;

    @NotBlank
    @NotNull
    private String mobileNumber;
    @NotNull
    private Date dob;
    @NotNull
    private BigInteger barangayId;
    @NotNull
    private BigInteger municipalityId;
    @NotNull
    private BigInteger provinceId;

    private BigInteger createdBy;
}
