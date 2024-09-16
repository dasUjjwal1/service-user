package com.pbyt.finance.applicationService;

import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.repository.StateDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private StateDistrictRepository repository;
    @Override
    public List<TblStateDistrict> findAllState() {
        return repository.findAllState();
    }

    @Override
    public List<TblStateDistrict> findAllDistrictByStateId(Integer stateId) {
        return repository.findAllDistrictByStateId(stateId);
    }
}
