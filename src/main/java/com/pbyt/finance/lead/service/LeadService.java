package com.pbyt.finance.lead.service;

import com.pbyt.finance.global.model.MessageResponse;
import com.pbyt.finance.lead.model.LeadCreateModel;

import java.math.BigInteger;

public interface LeadService {
    MessageResponse createLead(LeadCreateModel leadCreateModel, BigInteger createdBy) throws Exception;
//    void allocateLead()
}
