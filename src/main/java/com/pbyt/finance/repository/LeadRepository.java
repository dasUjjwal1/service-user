package com.pbyt.finance.repository;

import com.pbyt.finance.lead.entity.TblLead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface LeadRepository extends JpaRepository<TblLead, BigInteger> {
}
