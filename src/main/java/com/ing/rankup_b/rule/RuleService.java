package com.ing.rankup_b.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    
    @Autowired
    private RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity createRule(Rule rule){
        this.repository.save(rule);
        return ResponseEntity.status(HttpStatus.OK).body(rule);
    }
}
