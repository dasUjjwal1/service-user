package com.pbyt.finance.repository;

import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.applicationEntity.TblWorkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface WorkAreaRepository extends JpaRepository<TblWorkArea,Integer> {
//    @Query(nativeQuery = true, value = "SELECT tbsd.name,tbsd.id,tbsd.parentId," +
//            "tbsd.sid from TblStateDistrict tbsd RIGHT OUTER JOIN (SELECT wa.areaId from TblWorkArea wa where wa.userId = :userId ) wadb " +
//            "ON tbsd.id = wadb.areaId")
//    Set<TblStateDistrict> findAreaListByUserId(Integer userId);
}
