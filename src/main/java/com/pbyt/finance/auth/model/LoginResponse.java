package com.pbyt.finance.auth.model;

import com.pbyt.finance.entity.WorkArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String name;
    private Long mobileNumber;
    private WorkArea workArea;
    private Integer id;
}
