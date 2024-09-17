package com.pbyt.finance.applicationEntity;

import com.pbyt.finance.user.entity.TblAgent;
import com.pbyt.finance.user.entity.TblUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "tbl_state_district")
@NoArgsConstructor
@AllArgsConstructor
public class TblStateDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String sid;
    @Column(name = "parent_id")
    private Integer parentId;

}
