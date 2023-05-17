package com.ing.rankup_b.ruleCompleted;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ing.rankup_b.ruleCompleted.RuleCompleted.Status;

import jakarta.validation.Valid;

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

    public ArrayList<Object> getRuleCompleted(int id_team, int id_user, int id_rule_completed) {
        ArrayList<String> result = this.repository.ruleCompletedQuery(id_team, id_user, id_rule_completed);

        ArrayList<Object> rulesCompleted = new ArrayList<Object>();

        for (String r : result) {
            rulesCompleted.add(new Object() {
                public String nome = r.split(",")[0];
                public int points = Integer.parseInt(r.split(",")[1])
                        + (r.split(",")[2].equals("null") ? 0 : Integer.parseInt(r.split(",")[2]));
                public String description = r.split(",")[3];
                public String comment = r.split(",")[4];
                public String username = r.split(",")[5];

            });
        }
        return rulesCompleted;
    }

    /*
     * N.59
     */
    public RuleCompleted insert(@Valid RuleCompleted ruleCompleted) {
        return this.repository.save(ruleCompleted);
    }
}
