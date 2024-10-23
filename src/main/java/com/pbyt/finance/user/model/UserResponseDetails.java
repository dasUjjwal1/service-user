package com.pbyt.finance.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.applicationModel.WorkArea;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.AuthoritiesConverter;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDetails {
    private Integer id;
    private String name;
    private Long mobileNumber;
    private Date dob;
    private String email;
    @Convert(converter = AddressConverter.class)
    private Address address;
    @Convert(converter = AuthoritiesConverter.class)
    private Collection<Integer> authorities;
    @JsonProperty("workArea")
    private List<WorkArea> workArea;
    private Integer createdBy;
    private LocalDateTime createdOn;
}
