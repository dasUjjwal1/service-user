package com.pbyt.finance.agent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class AgentTestController {
    @GetMapping("/")
    public ResponseEntity<?> testFilter(){
        return ResponseEntity.ok("Success");
    }
}
