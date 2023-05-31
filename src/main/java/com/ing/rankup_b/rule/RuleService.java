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
  
    public ResponseEntity<?> listRule(int teamcode){
        List<Rule> rules = new ArrayList<>();
        
        for (Rule rule : (List<Rule>)this.repository.findAll()) {
            if (rule.getTeam().getCodice() == teamcode) {
                rules.add(rule);
            }
        }
        if (rules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono regole per questo team");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rules);
    }

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
}
