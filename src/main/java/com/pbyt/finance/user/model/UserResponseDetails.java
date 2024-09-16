package com.pbyt.finance.user.model;

import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.AuthoritiesConverter;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

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
//    private Set<TblStateDistrict> workArea;
    private Integer createdBy;
    private LocalDateTime createdOn;
    public Collection<String> getAuthorities() {
        return authorities.stream().map(it -> {
            return switch (it) {
                case 0 -> RoleEnum.ADMIN.name();
                case 1 -> RoleEnum.ZM.name();
                case 2 -> RoleEnum.RSM.name();
                case 3 -> RoleEnum.RM.name();
                default -> RoleEnum.USER.name();
            };
        }).toList();
    }
}
