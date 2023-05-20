package com.ing.rankup_b.ruleCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruleCompletedApi")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8200", "http://localhost:4200" })

public class RuleCompletedController {

    @Autowired
    private RuleCompletedService service;

    public RuleCompletedController(RuleCompletedService service) {
        this.service = service;
    }

    @GetMapping(path = "/ruleAccepted/{idTeam}")
    public ResponseEntity ruleCompleted(@PathVariable int idTeam) {
        return this.service.getRulesAccepted(idTeam);
    }

    @GetMapping(path = "/ruleRejected/{idTeam}")
    public ResponseEntity ruleRejected(@PathVariable int idTeam) {
        return this.service.getRulesRejected(idTeam);
    }

    @GetMapping(path = "/request/{idRegolaCompletata}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRuleDelivered(@PathVariable int idRegolaCompletata) {
        String ruleCompleted = this.service.researchRule(idRegolaCompletata);

        if (ruleCompleted == null) {
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
    public ResponseEntity ruleCompleted(@RequestParam("idTeam") int idTeam, @RequestParam("idUser") int idUser,
            @RequestParam("idRuleCompleted") int idRuleCompleted) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getRuleCompleted(idTeam, idUser, idRuleCompleted));
    }

    /*
     * N.25
     */
    @GetMapping(path = "/pending/{id_team}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPending(@PathVariable int id_team) {
        return this.service.getPending(id_team);
    }

    /*
     * N.59
     */
    @PostMapping(path = "/ruleCompleted", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RuleCompleted insert(@RequestBody RuleCompleted ruleCompleted) {
        return this.service.insert(ruleCompleted);
  
    @GetMapping(path = "/user/ruleCompletedDetails/{idRuleCompleted}")
    public ResponseEntity ruleCompletedDetails(@PathVariable int idRuleCompleted) {
        return this.service.getRuleCompletedDetails(idRuleCompleted);
    }

    @GetMapping(path = "/getRuleForSpecificUser/{idTeam}/{idUser}")
    public ResponseEntity getRuleForSpecificUser(@PathVariable long idTeam, @PathVariable int idUser) {
        return this.service.getRuleForASpecificUser(idTeam, idUser);
    }

    @PatchMapping(path = "/acceptance/{idRuleCompleted}")
    public ResponseEntity acceptance(@PathVariable int idRuleCompleted, @RequestParam String comment, @RequestParam int bonusPoints, @RequestParam int status) {
        return this.service.ruleCompletedAcceptance(idRuleCompleted, comment, bonusPoints, status);
    } // TODO: da far funzionare in frontend, pagina: task-confirmation, servizio: rule completed service
}
