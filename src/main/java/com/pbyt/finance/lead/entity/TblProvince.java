package com.pbyt.finance.lead.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Builder
@Table(name = "tbl_province",schema = "cdm")
public class TblProvince {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private BigInteger regionId;
}
