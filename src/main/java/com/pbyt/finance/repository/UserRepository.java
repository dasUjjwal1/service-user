package com.pbyt.finance.repository;

import com.pbyt.finance.user.entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> , PagingAndSortingRepository<TblUser,Integer> {
    @Query("SELECT ta from TblUser ta WHERE mobileNumber = :mobileNumber")
    Optional<TblUser> findUserByMobileNumber(Long mobileNumber);

    @Query("SELECT EXISTS(SELECT tu.mobileNumber FROM TblUser tu WHERE mobileNumber = :mobileNumber)")
    Boolean findUserExists(Long mobileNumber);
}
