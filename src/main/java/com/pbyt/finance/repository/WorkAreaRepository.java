package com.pbyt.finance.repository;

import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.applicationEntity.TblWorkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface WorkAreaRepository extends JpaRepository<TblWorkArea,Integer> {
    @Query(nativeQuery = true, value = "select statedb.\"name\",statedb.id,statedb.parent_id,statedb.sid from (select area_id from public.tbl_work_area wa where wa.user_id = 52 ) as workdb\n" +
            "left outer join public.tbl_state_district statedb on statedb.id = workdb.area_id")
    Set<TblStateDistrict> findAreaListByUserId(Integer userId);
}
