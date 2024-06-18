package com.pbyt.finance.repository;

import com.pbyt.finance.user.entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<TblUser, BigInteger> {
}
