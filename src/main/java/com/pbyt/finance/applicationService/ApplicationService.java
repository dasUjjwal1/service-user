package com.pbyt.finance.applicationService;

import com.pbyt.finance.applicationEntity.TblStateDistrict;

import java.util.List;

public interface ApplicationService {
    List<TblStateDistrict> findAllState();
    List<TblStateDistrict> findAllDistrictByStateId(Integer stateId);
}
