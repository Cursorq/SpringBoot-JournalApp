package com.JournalAPP.Journalapp.Controller;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.GetMapping;
@RestController
public class HealthCheck {
    @GetMapping("/health-check")
    public String checkHealth(){
        return "OK";
    }
}
