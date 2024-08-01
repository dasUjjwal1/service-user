package com.pbyt.finance.user.entity;

import com.pbyt.finance.entity.Address;
import com.pbyt.finance.entity.WorkArea;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.AuthoritiesConverter;
import com.pbyt.finance.util.WorkAreaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class TblUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String password;
    private String name;
    @Column(unique = true)
    private Long mobileNumber;
    private Date dob;
    private String email;
    @Convert(converter = AddressConverter.class)
    @Column(name = "address", length = 500)
    private Address address;
    @Convert(converter = AuthoritiesConverter.class)
    @Column(name = "authorities", length = 500)
    private Collection<? extends GrantedAuthority> authorities;
    @Convert(converter = WorkAreaConverter.class)
    @Column(name = "work_area", length = 500)
    private WorkArea workingArea;
    private Integer createdBy;
    @Column(name = "created_on", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime createdOn;
    private Integer modifiedBy;
    @Column(name = "modified_on", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime modifiedOn;
    @Column(columnDefinition = "boolean default false")
    private Boolean isSuperAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mobileNumber.toString();
    }
}
