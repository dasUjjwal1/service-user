package com.pbyt.finance.repository;

import com.pbyt.finance.applicationEntity.TblStateDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateDistrictRepository extends JpaRepository<TblStateDistrict,Integer> {
    @Query("SELECT tsd FROM TblStateDistrict tsd WHERE tsd.parentId IS NULL")
    List<TblStateDistrict> findAllState();

    @Query("SELECT tsd FROM TblStateDistrict tsd WHERE tsd.parentId = :stateId")
    List<TblStateDistrict> findAllDistrictByStateId(Integer stateId);
}
