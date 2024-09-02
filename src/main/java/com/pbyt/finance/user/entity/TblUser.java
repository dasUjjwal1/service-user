package com.pbyt.finance.user.entity;

import com.pbyt.finance.entity.Address;
import com.pbyt.finance.entity.TblWorkArea;
import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.AuthoritiesConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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
    private Collection<Integer> authorities;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_work",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "work_area_id")})
    @Column(name = "work_area", length = 500)
    private Set<TblWorkArea> workingArea = HashSet.newHashSet(0);
    private Integer createdBy;
    @Column(name = "created_on", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime createdOn;
    private Integer modifiedBy;
    @Column(name = "modified_on", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private LocalDateTime modifiedOn;
    @Column(columnDefinition = "boolean default false")
    private Boolean isSuperAdmin;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(it -> {
            String role = switch (it) {
                case 0 -> RoleEnum.ADMIN.name();
                case 1 -> RoleEnum.ZM.name();
                case 2 -> RoleEnum.RSM.name();
                case 3 -> RoleEnum.RM.name();
                default -> RoleEnum.USER.name();
            };
            return new SimpleGrantedAuthority("ROLE_" + role);
        }).toList();
    }

    public Collection<Integer> getRoles() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return mobileNumber.toString();
    }

}
