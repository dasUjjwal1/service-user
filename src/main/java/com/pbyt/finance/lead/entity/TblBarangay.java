package com.pbyt.finance.lead.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Builder
@Table(name = "tbl_barangay",schema = "cdm")
public class TblBarangay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private BigInteger municipalityId;
}
