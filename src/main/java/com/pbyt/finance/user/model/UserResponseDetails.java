package com.pbyt.finance.user.model;

import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.applicationEntity.TblWorkArea;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.AuthoritiesConverter;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
//    private Set<TblWorkArea> workArea;
    private Integer createdBy;
    private LocalDateTime createdOn;
}
