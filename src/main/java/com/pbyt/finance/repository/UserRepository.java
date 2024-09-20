package com.pbyt.finance.repository;

import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UserResponseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {
    @Query("SELECT ta from TblUser ta WHERE mobileNumber = :mobileNumber")
    Optional<TblUser> findUserByMobileNumber(Long mobileNumber);

    @Query("SELECT EXISTS(SELECT tu.mobileNumber FROM TblUser tu WHERE mobileNumber = :mobileNumber)")
    Boolean findUserExists(Long mobileNumber);

    @Query("SELECT new com.pbyt.finance.user.model.UserResponseDetails(tu.id, tu.name, tu.mobileNumber, tu.dob," +
            " tu.email, " +
            "  tu.address, tu.authorities, " +
            "  tu.createdBy, tu.createdOn)" +
            " from TblUser tu WHERE tu.id != 552 AND tu.mobileNumber != 9647012776")
    List<UserResponseDetails> findAllUsers();

    @Query(value = "select tbsd.id,tbsd.name,tbsd.sid,tbsd.parent_id from (SELECT wa.area_id from public.tbl_work_area wa where wa.user_id = :userId) wadb\n" +
            "left outer join public.tbl_state_district tbsd on wadb.area_id = tbsd.id",nativeQuery = true)
    List<Object[]> findAreaListByUserId(@Param("userId")Integer userId);
}
