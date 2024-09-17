package com.pbyt.finance.user.entity;

import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.global.enums.Gender;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.AuthoritiesConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_agent")
public class TblAgent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String name;
    @Column(unique = true)
    private Long mobileNumber;
    private Date dob;
    private String password;
    @Convert(converter = AddressConverter.class)
    @Column(name = "address", length = 500)
    private Address address;
    @Convert(converter = AuthoritiesConverter.class)
    @Column(name = "authorities", length = 500)
    private Collection<Integer> authorities;

    private String status;

    private boolean kycVerified;

    private boolean whatsappUpdate;

    private boolean tncAccepted;

    private boolean registered;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime createdOn;

    @Column(name = "modified_on", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime modifiedOn;

    private Integer createdBy;

    private Integer updatedBy;

    private Long whatsappNumber;

    private String employeeCode;

    private String agentCode;

    private String notes;

    private String workExperienceAsFinance;

    private String qualification;

    private String previousWorkExperience;

    private String productWorkEarlier;

    private LocalDateTime lastCalled;

    private String deviceToken;

}
