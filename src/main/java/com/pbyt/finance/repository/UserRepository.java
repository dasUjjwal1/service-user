package com.pbyt.finance.repository;

import com.pbyt.finance.user.entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TblUser, BigInteger> {
    @Query("SELECT ta from TblUser ta WHERE mobileNumber = :mobileNumber")
    Optional<TblUser> findAgentUser(String mobileNumber);
}
