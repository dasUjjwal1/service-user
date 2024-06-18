package com.pbyt.finance.repository;

import com.pbyt.finance.agent.entity.TblAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<TblAgent, BigInteger> {
    @Query("SELECT EXISTS(SELECT ta.mobileNumber from TblAgent ta WHERE mobileNumber = :mobileNumber)")
    Boolean findAgentByMobile(String mobileNumber);
    @Query("SELECT ta.mobileNumber from TblAgent ta WHERE mobileNumber = :mobileNumber")
    Optional<TblAgent> findAgent(String mobileNumber);
}
