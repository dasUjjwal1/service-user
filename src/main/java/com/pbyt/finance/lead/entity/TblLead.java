package com.pbyt.finance.lead.entity;

import com.pbyt.finance.global.enums.Gender;
import com.pbyt.finance.lead.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "tbl_lead", schema = "cdm")
public class TblLead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "lead_number", unique = true)
    private String leadNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_category_id")
    private String subCategoryId;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private LeadStatus status;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "created_on",columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private BigInteger createdBy;

    @Column(name = "modified_on",columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private BigInteger modifiedBy;

    @Column(name = "barangay_id")
    private BigInteger barangayId;

    @Column(name = "municipality_id")
    private BigInteger municipalityId;

    @Column(name = "province_id")
    private BigInteger provinceId;

    @Column(name = "allocated_to")
    private BigInteger allocatedTo;
}
