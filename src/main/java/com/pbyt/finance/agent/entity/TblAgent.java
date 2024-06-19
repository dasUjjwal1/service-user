package com.pbyt.finance.agent.entity;


import com.pbyt.finance.agent.enums.AgentType;
import com.pbyt.finance.agent.enums.IdType;
import com.pbyt.finance.agent.enums.KycStatus;
import com.pbyt.finance.global.enums.Gender;
import com.pbyt.finance.global.enums.RelationShip;
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
@Table(name = "tbl_agent", schema = "cdm")
public class TblAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "province_id")
    private BigInteger provinceId;

    @Column(name = "municipality_id")
    private BigInteger municipalityId;

    @Column(name = "barangay_id")
    private BigInteger barangayId;

    @Column(name = "zip_code")
    private BigInteger zipCode;

    @Column(name = "tin_number")
    private String tinNumber;

    @Column(name = "dl_name")
    private String dlName;

    @Column(name = "dl_dob")
    private Date dlDateOfBirth;

    @Column(name = "dl_address")
    private String dlAddress;

    @Column(name = "passport_name")
    private String passportName;

    @Column(name = "passport_dob")
    private Date passportDateOfBirth;

    @Column(name = "passport_address")
    private String passportAddress;

    @Column(name = "dl_number")
    private String dlNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "nominee_name")
    private String nomineeName;

    @Column(name = "nominee_address")
    private String nomineeAddress;

    @Column(name = "nominee_dob")
    private Date nomineeDateOfBirth;

    @Column(name = "nominee_gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender nomineeGender;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "nominee_relationship")
    private RelationShip nomineeRelationship;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "bank_id")
    private BigInteger bankId;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "micr_number")
    private String micrNumber;

    @Column(name = "user_photo")
    private String userPhoto;

    @Column(name = "user_generated_photo")
    private String userGeneratedPhoto;

    @Column(name = "is_kyc")
    private boolean isKyc;

    @Column(name = "score")
    private Integer score;

    @Column(name = "created_on",columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private BigInteger createdBy;

    @Column(name = "modified_on",columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private BigInteger modifiedBy;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "agent_type")
    private AgentType agentType;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_type")
    private IdType idType;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "kyc_id_type")
    private String kycIdType;

    @Column(name = "passport_date_of_issue")
    private Date passportDateOfIssue;

    @Column(name = "passport_date_of_expiry")
    private Date passportDateOfExpiry;

    @Column(name = "transaction_id")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_status")
    private KycStatus kycStatus;
}
