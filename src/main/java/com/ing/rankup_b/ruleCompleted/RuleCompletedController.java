package com.ing.rankup_b.ruleCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ruleCompletedApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class RuleCompletedController {
    
    @Autowired
    private RuleCompletedService service;

    public RuleCompletedController(RuleCompletedService service) {
        this.service = service;
    }
}
