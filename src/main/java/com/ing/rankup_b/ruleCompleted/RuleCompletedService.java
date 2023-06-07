package com.ing.rankup_b.ruleCompleted;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ing.rankup_b.ruleCompleted.RuleCompleted.Status;
import com.ing.rankup_b.userJoinsTeam.UserJoinsTeam;
import com.ing.rankup_b.userJoinsTeam.UserJoinsTeamRepository;

import jakarta.validation.Valid;

@Service
public class RuleCompletedService {

    @Autowired
    private RuleCompletedRepository repository;

    @Autowired
    private UserJoinsTeamRepository userJoinsTeamRepository;

    public RuleCompletedService(RuleCompletedRepository repository, UserJoinsTeamRepository userJoinsTeamRepository) {
        this.repository = repository;
        this.userJoinsTeamRepository = userJoinsTeamRepository;
    }

    /**
     * Funzione per prendere le regole accettate in un team
     * @param teamCode il codice del team da cui prendere la lista
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> getRulesAccepted(int teamCode){
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

    /**
     * Funzione per prendere le regole rifiutate in un team
     * @param teamCode il codice del team da cui prendere la lista
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> getRulesRejected(int teamCode){
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
    public ResponseEntity<?> getRuleCompletedDetails(int idRuleCompleted) {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findById(idRuleCompleted));
    }

    /**
     * Funzione per ricercare tutte le regole completate da un utente in un determinato team
     * @param idTeam il team in cui si deve effettuare la ricerca
     * @param idUser l'utente per cui si deve effettuare la ricerca
     * @return (400 BAD_REQUEST) se non viene trovato nulla <br>(200 OK) con la lista delle regole altrimenti
     */
    public ResponseEntity<?> getRuleForASpecificUser(long idTeam, int idUser) {
        List<RuleCompleted> rules = new ArrayList<>();

        for (RuleCompleted r : this.repository.findAll()) {
            if (r.getRule().getTeam().getCodice() == idTeam && r.getUser().getId() == idUser && r.getStatus() == Status.Accettato) {
                rules.add(r);
            }
        }

        if (rules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessuna regola trovata per questo utente");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(rules);
        }
    }

    /**
     * Funzione per accettare o rifiutare una regola completata
     * @param idRuleCompleted l'id della regola completata 
     * @param status accettazione o rifiuto
     * @param ruleCompleted il body della regola
     * @return la regola completata accettata o rifiutata
     */
    public ResponseEntity<?> ruleCompletedAcceptance(int idRuleCompleted, int status, RuleCompleted ruleCompleted) {
        if (ruleCompleted == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Regola vuota");
        }

        RuleCompleted r = this.repository.findById(idRuleCompleted).get();
        r.setComment(ruleCompleted.getComment());
        r.setBonus(ruleCompleted.getBonus());

        for (UserJoinsTeam userJoinsTeam : userJoinsTeamRepository.findAll()) {
            if (userJoinsTeam.getUser().getId() == r.getUser().getId() && userJoinsTeam.getTeam().getCodice() == r.getRule().getTeam().getCodice()) {
                if(status == 1)
                    userJoinsTeam.setPoints(userJoinsTeam.getPoints() + r.getRule().getPoints() + r.getBonus());
                else
                    userJoinsTeam.setPoints(userJoinsTeam.getPoints() + 0 + r.getBonus());
            }
        }        

        if (status == 1) {
            r.setStatus(Status.Accettato);
        } else {
            r.setStatus(Status.Rifiutato);
        }
        Date revisionDate = new Date();

        r.setRevisionDate(revisionDate);
        this.repository.save(r);

        return ResponseEntity.status(HttpStatus.OK).body(r);
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
