package com.ing.rankup_b.ruleCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ruleCompletedApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class RuleCompletedController {
    
    @Autowired
    private RuleCompletedService service;

    public RuleCompletedController(RuleCompletedService service) {
        this.service = service;
    }

    @GetMapping(path = "user/ruleCompleted")
    public ResponseEntity ruleCompleted(@RequestParam("id_team") int id_team, @RequestParam("id_user") int id_user, @RequestParam("id_rule_completed") int id_rule_completed) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getRuleCompleted(id_team, id_user, id_rule_completed)
        );
    }
}
