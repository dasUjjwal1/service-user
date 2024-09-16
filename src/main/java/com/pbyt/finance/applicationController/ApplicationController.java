package com.pbyt.finance.applicationController;

import com.pbyt.finance.applicationService.ApplicationService;
import com.pbyt.finance.global.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/api/v1/app/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/all-state")
    public ResponseEntity<?> getAllState() {
        return ResponseEntity.ok(MessageResponse.builder()
                .status(HttpStatus.OK)
                .data(applicationService.findAllState())
                .build());
    }
    @GetMapping("/all-district/{id}")
    public ResponseEntity<?> getAllDistrictByStateId(@PathVariable Optional<String> id) throws NumberFormatException {
        Integer stateId = id.map(Integer::parseInt).orElse(0);
        return ResponseEntity.ok(MessageResponse.builder()
                .status(HttpStatus.OK)
                .data(applicationService.findAllDistrictByStateId(stateId))
                .build());
    }
}
