package com.pbyt.finance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pbyt.finance.user.entity.TblUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_work_area")
public class TblWorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String areaName;

    private Integer parentId;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    @JsonIgnore
    private Set<TblUser> users = new HashSet<>();
}
