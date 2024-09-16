package com.pbyt.finance.applicationEntity;

import com.pbyt.finance.user.entity.TblAgent;
import com.pbyt.finance.user.entity.TblUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class TblWorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private TblUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent.id")
    private TblAgent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateDistrict.id")
    private TblStateDistrict stateDistrict;
}
