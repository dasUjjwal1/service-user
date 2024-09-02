package com.pbyt.finance.repository;

import com.pbyt.finance.user.entity.TblAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<TblAgent,Integer> {
    @Query("SELECT ta from TblAgent ta WHERE mobileNumber = :mobileNumber")
    Optional<TblAgent> findAgentByMobileNumber(Long mobileNumber);

    @Query("SELECT EXISTS(SELECT tu.mobileNumber FROM TblAgent tu WHERE mobileNumber = :mobileNumber)")
    Boolean findAgentExists(Long mobileNumber);
}
