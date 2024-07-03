package com.pbyt.finance.lead.service;

import com.pbyt.finance.global.model.MessageResponse;
import com.pbyt.finance.lead.entity.TblLead;
import com.pbyt.finance.lead.model.LeadCreateModel;
import com.pbyt.finance.repository.LeadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class LeadServiceImpl implements LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public MessageResponse createLead(LeadCreateModel leadCreateModel, BigInteger createdBy) throws Exception {
        leadCreateModel.setCreatedBy(createdBy);
        UUID uuid = UUID.randomUUID();
        String leadNUmber =  String.format("%06d", uuid.hashCode());
        TblLead lead = TblLead.builder()
                .name(leadCreateModel.getName())
                .mobileNumber(leadCreateModel.getMobileNumber())
                .dob(leadCreateModel.getDob())
                .barangayId(leadCreateModel.getBarangayId())
                .municipalityId(leadCreateModel.getMunicipalityId())
                .provinceId(leadCreateModel.getProvinceId())
                .createdBy(createdBy)
                .leadNumber("LEAD"+leadNUmber)
                .gender(leadCreateModel.getGender())
                .build();
        leadRepository.save(lead);

        return MessageResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Created Successfully")
                .build();
    }
}
