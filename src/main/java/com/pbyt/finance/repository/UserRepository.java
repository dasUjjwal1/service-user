package com.pbyt.finance.repository;

import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UserResponseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> , PagingAndSortingRepository<TblUser,Integer> {
    @Query("SELECT ta from TblUser ta WHERE mobileNumber = :mobileNumber")
    Optional<TblUser> findUserByMobileNumber(Long mobileNumber);

    @Query("SELECT EXISTS(SELECT tu.mobileNumber FROM TblUser tu WHERE mobileNumber = :mobileNumber)")
    Boolean findUserExists(Long mobileNumber);

    @Query("SELECT new com.pbyt.finance.user.model.UserResponseDetails(tu.id, tu.name, tu.mobileNumber, tu.dob," +
            " tu.email, " +
            "  tu.address, tu.authorities, tu.workingArea, " +
            "  tu.createdBy, tu.createdOn)" +
            " from TblUser tu WHERE tu.id != 552 AND tu.mobileNumber != 9647012776")
    List<UserResponseDetails> findAllUsers();
}
