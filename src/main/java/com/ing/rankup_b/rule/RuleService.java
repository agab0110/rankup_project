package com.ing.rankup_b.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    
    @Autowired
    private RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }
}
