package com.ing.rankup_b.rule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;

@Service
public class RuleService {

    @Autowired
    private RuleRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    

    public RuleService(RuleRepository repository,TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }
  
    /**
     * Funzione per prendere la lista di regole per il team
     * @param teamcode il codice del team di cui si vogliono avere le regole
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> listRule(int teamcode){
        List<Rule> rules = new ArrayList<>();
        
        for (Rule rule : this.repository.findAll()) {
            if (rule.getTeam().getCodice() == teamcode) {
                rules.add(rule);
            }
        }
        if (rules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono regole per questo team");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rules);
    }

    /**
     * Funzione per creare una regola per un team
     * @param rule la regola da creare
     * @param name il nome della regola che verrà controllato se è già esistente
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> createRule(Rule rule,String name) {
        for (Team t : this.teamRepository.findAll()) {
        if (rule.getTeam().getCodice() == t.getCodice()) {
            for (Rule r : t.getRules()) {
                if (r.getName().equals(name)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome duplicato");
                }
            }
        }
        }
       
        this.repository.save(rule);
        return ResponseEntity.status(HttpStatus.OK).body(rule);
    }

    /*
     * N.58
     */
    public String getRule(int idRule) {
        return this.repository.findRule(idRule);
    }

    /**
     * Funzione per l'eliminazione di una regola da un team
     * @param idRule l'id della regola da rimuovere
     * @param idTeam l'id del team da cui rimuovere la regola
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> deleteRule(int idRule,long idTeam) {
        for (Rule t : this.repository.findAll()) {
            if (t.getId() == idRule) {
                if (t.getTeam().getCodice() == idTeam) {
                    this.repository.delete(t);
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("regola non trovato");
    }
}
