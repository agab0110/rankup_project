package com.ing.rankup_b.ruleCompleted;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.ruleCompleted.RuleCompleted.Status;

@Service
public class RuleCompletedService {
    
    @Autowired
    private RuleCompletedRepository repository;

    public RuleCompletedService(RuleCompletedRepository repository) {
        this.repository = repository;
    }
    public ResponseEntity ruleAccepted(int Codice){
        List<RuleCompleted> acceptedrule = new ArrayList<>();

        for (RuleCompleted rule : (List<RuleCompleted>)this.repository.findAll()) {
            if (rule.getRule().getTeam().getCodice() == Codice) {
                if (rule.getStatus() == Status.Accettato) {
                    acceptedrule.add(rule);
                }
            }
            
        }
        
        if(acceptedrule.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nessuna regola completato");
        }
        return ResponseEntity.status(HttpStatus.OK).body(acceptedrule);
    }

    public ResponseEntity Rulerejected(int Codice){
        List<RuleCompleted> Rejectedrule = new ArrayList<>();

        for (RuleCompleted rule : (List<RuleCompleted>)this.repository.findAll()) {
            if (rule.getRule().getTeam().getCodice() == Codice) {
                if (rule.getStatus() == Status.Rifiutato) {
                    Rejectedrule.add(rule);
                }
            }
            
        }
        
        if(Rejectedrule.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nessuna regola completato");
        }
        return ResponseEntity.status(HttpStatus.OK).body(Rejectedrule);
    }
}
