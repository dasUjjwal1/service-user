package com.pbyt.finance.lead.service;

import com.pbyt.finance.global.model.MessageResponse;
import com.pbyt.finance.lead.model.LeadCreateModel;

public interface LeadService {
    MessageResponse createLead(LeadCreateModel leadCreateModel) throws Exception;
}
