package com.ing.rankup_b.ruleCompleted;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ing.rankup_b.rule.Rule;
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

    /**
     * Funzione per prendere i dettagli di una regola completata
     * @param idRuleCompleted l'id della regola completata
     * @return (200 OK) con la regola completata nel body
     */
    public ResponseEntity getRuleCompletedDetails(int idRuleCompleted) {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findById(idRuleCompleted));
    }

    /**
     * Funzione per ricercare tutte le regole completate da un utente in un determinato team
     * @param idTeam il team in cui si deve effettuare la ricerca
     * @param idUser l'utente per cui si deve effettuare la ricerca
     * @return (400 BAD_REQUEST) se non viene trovato nulla <br>(200 OK) con la lista delle regole altrimenti
     */
    public ResponseEntity getRuleForASpecificUser(long idTeam, int idUser) {
        List<Rule> rules = new ArrayList<>();

        for (RuleCompleted r : this.repository.findAll()) {
            if (r.getRule().getTeam().getCodice() == idTeam && r.getUser().getId() == idUser && r.getStatus() == Status.Accettato) {
                rules.add(r.getRule());
            }
        }

        if (rules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun task trovato per questo utente");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(rules);
        }
    }

    public ResponseEntity ruleCompletedAcceptance(int idRuleCompleted, String comment, int bonusPoints, int status) {
        RuleCompleted ruleCompleted = this.repository.findById(idRuleCompleted).get();
        ruleCompleted.setBonus(bonusPoints);
        ruleCompleted.setComment(comment);

        Date revisionDate = new Date();

        ruleCompleted.setRevisionDate(revisionDate);

        if (status == 1) {
            ruleCompleted.setStatus(Status.Accettato);
        } else {
            ruleCompleted.setStatus(Status.Rifiutato);
        }
        
        this.repository.save(ruleCompleted);

        return ResponseEntity.status(HttpStatus.OK).body(ruleCompleted);
    }

    /*
     * N.25
     */
    public String getPending(int id_team) {
        return this.repository.pending(id_team);
    }

    /*
     * N.59
     */
    public RuleCompleted insert(@Valid RuleCompleted ruleCompleted) {
        ruleCompleted.setTimestamp(Timestamp.from(Instant.now()));
        return this.repository.save(ruleCompleted);
    }
}
