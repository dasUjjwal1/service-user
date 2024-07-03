package com.pbyt.finance.lead.controller;

import com.pbyt.finance.lead.model.LeadCreateModel;
import com.pbyt.finance.lead.service.LeadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/v1/app/lead")
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PostMapping("/create-lead")
    public ResponseEntity<?> createLead(@RequestBody @Validated LeadCreateModel leadCreateModel, HttpServletRequest request) throws Exception {
        BigInteger userId = new BigInteger(request.getAttribute("userId").toString()) ;
        return ResponseEntity.ok(leadService.createLead(leadCreateModel, userId));
    }
}
