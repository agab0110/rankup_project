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

    public ResponseEntity getRulesAccepted(int teamCode){
        List<RuleCompleted> acceptedRules = new ArrayList<>();

        for (RuleCompleted rule : (List<RuleCompleted>)this.repository.findAll()) {
            if (rule.getRule().getTeam().getCodice() == teamCode) {
                if (rule.getStatus() == Status.Accettato) {
                    acceptedRules.add(rule);
                }
            }
            
        }
        
        if(acceptedRules.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nessuna regola completato");
        }
        return ResponseEntity.status(HttpStatus.OK).body(acceptedRules);
    }

    public ResponseEntity getRulesRejected(int teamCode){
        List<RuleCompleted> rejectedRules = new ArrayList<>();

        for (RuleCompleted rule : (List<RuleCompleted>)this.repository.findAll()) {
            if (rule.getRule().getTeam().getCodice() == teamCode) {
                if (rule.getStatus() == Status.Rifiutato) {
                    rejectedRules.add(rule);
                }
            }
            
        }
        
        if(rejectedRules.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nessuna regola completato");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rejectedRules);
    }

    public String researchRule(int idRegolaCompletata) {
        return this.repository.findRule(idRegolaCompletata);
    }

    public String researchUserHistory(int idTeam, String nomeTask) {
        return this.repository.findUserHistoryT(idTeam, nomeTask);
    }

    /**
     * Funzione per prendere i dettagli di una regola completata
     * @param idRuleCompleted l'id della regola completata
     * @return (200 OK) con la regola completata nel body
     */
    public ResponseEntity getRuleCompletedDetails(int idRuleCompleted) {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findById(idRuleCompleted));
    }
}
