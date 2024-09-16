package com.pbyt.finance.applicationEntity;

import com.pbyt.finance.user.entity.TblAgent;
import com.pbyt.finance.user.entity.TblUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "tbl_state_district")
public class TblStateDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String sid;
    private Integer parentId;

}
