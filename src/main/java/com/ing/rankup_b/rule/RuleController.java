package com.ing.rankup_b.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("ruleApi")
@CrossOrigin

public class RuleController {

    @Autowired
    private RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }
  
    /**
     * N.27
     * N.55
     */
    @GetMapping(path = "/rules/{idTeam}")
    public ResponseEntity<?> listRule(@PathVariable int idTeam) {
        return this.service.listRule(idTeam);
    }

    /**
     * N.1
     */
    @PostMapping(path = "/createRule", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRule(@Valid @RequestBody Rule rule) {
        return this.service.createRule(rule);
    }

    /*
     * N.58
     */
    @GetMapping(path = "/rule/{idRule}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRule(@PathVariable int idRule) {
        String rule = this.service.getRule(idRule);
        
        if (rule == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Regola non trovata");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getRule(idRule));
    }
}
