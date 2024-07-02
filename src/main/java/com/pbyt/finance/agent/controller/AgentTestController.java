package com.pbyt.finance.agent.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class AgentTestController {
    @GetMapping("/")
    public ResponseEntity<?> testFilter(HttpServletRequest request){


        var a = request.getAttribute("userId");
        log.info("data{}",a);
        return ResponseEntity.ok("Success");
    }
}
