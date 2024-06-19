package com.pbyt.finance.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user", schema = "cdm")
public class TblUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;
    private String userName;
    private String password;
    private String name;
    private String mobileNumber;

    private BigInteger agentId;
    private Date dob;
    private String email;
    private String address;
    private BigInteger barangayId;
    private BigInteger municipalityId;
    private BigInteger provinceId;
    private BigInteger zipCode;
    private BigInteger createdBy;
    @Column(name = "created_on",columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime createdOn;
    private BigInteger modifiedBy;
    @Column(name = "modified_on",columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime modifiedOn;
    @Column(columnDefinition = "boolean default false")
    private Boolean isSuperAdmin;
}
