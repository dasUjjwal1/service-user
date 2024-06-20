package com.pbyt.finance.lead.service;

import com.pbyt.finance.global.model.MessageResponse;
import com.pbyt.finance.lead.entity.TblLead;
import com.pbyt.finance.lead.model.LeadCreateModel;
import com.pbyt.finance.repository.LeadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

public class LeadServiceImpl implements LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public MessageResponse createLead(LeadCreateModel leadCreateModel, BigInteger createdBy) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        leadCreateModel.setCreatedBy(createdBy);
        TblLead lead = modelMapper.map(leadCreateModel, TblLead.class);
        leadRepository.save(lead);

        return null;
    }
}
