package com.pbyt.finance.auth.model;

import com.pbyt.finance.entity.TblWorkArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentLoginResponse {
    private String token;
    private String name;
    private Long mobileNumber;
    private TblWorkArea tblWorkArea;
    private Integer id;
}
