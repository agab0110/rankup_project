package com.ing.rankup_b.ruleCompleted;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ruleCompletedApi")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8200", "http://localhost:4200" })

public class RuleCompletedController {

    @Autowired
    private RuleCompletedService service;

    public RuleCompletedController(RuleCompletedService service) {
        this.service = service;
    }

    @GetMapping(path ="/ruleAccepted/{idTeam}")
    public ResponseEntity ruleCompleted (@PathVariable int idTeam){
        return this.service.getRulesAccepted(idTeam);
    }
    
    @GetMapping(path ="/rulerejected/{idTeam}")
    public ResponseEntity ruleRejected (@PathVariable int idTeam){
        return this.service.getRulesRejected(idTeam);
    }
  
    @GetMapping(path = "/request/{idRegolaCompletata}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRuleDelivered(@PathVariable int idRegolaCompletata) {
        String ruleCompleted = this.service.researchRule(idRegolaCompletata);

        if (ruleCompleted == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Regola non trovata");
        }

        return ResponseEntity.status(HttpStatus.OK).body(ruleCompleted);
    }

    @GetMapping(path = "/history/{idTeam}/{nomeTask}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity researchUserHistory(@PathVariable int idTeam, @PathVariable String nomeTask) {
        String history = this.service.researchUserHistory(idTeam, nomeTask);

        if (history == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Attività non trovata");

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
  
    @GetMapping(path = "/user/ruleCompleted")
    public ResponseEntity ruleCompleted(@RequestParam("idTeam") int idTeam, @RequestParam("idUser") int idUser, @RequestParam("idRuleCompleted") int idRuleCompleted) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getRuleCompleted(idTeam, idUser, idRuleCompleted)
        );
    }
}
