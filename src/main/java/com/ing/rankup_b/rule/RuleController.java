package com.ing.rankup_b.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ruleApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class RuleController {
    
    @Autowired
    private RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }

    @GetMapping(path = "/rules/{id_team}")
    public ResponseEntity ListRule(@PathVariable int id_team) {
        return this.service.ListRule(id_team);
        
    }
}
