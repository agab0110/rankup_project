package com.ing.rankup_b.ruleCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path ="/ruleAccepted/{id_team}")
    public ResponseEntity ruleCompleted (@PathVariable int id_team){
        return this.service.ruleAccepted(id_team);
    }
    
    @GetMapping(path ="/rulerejected/{id_team}")
    public ResponseEntity ruleRejected (@PathVariable int id_team){
        return this.service.Rulerejected(id_team);
    }
}
