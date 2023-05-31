package com.ing.rankup_b.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teamApi")
@CrossOrigin

public class TeamController {

    @Autowired
    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    /**
     * N.5
     */
    @PatchMapping(path = "/changePhoto/{code}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePhoto(@PathVariable Long code, @RequestBody String photo) {
        return this.service.changePhoto(code, photo);
    }

    /**
     * N.34 P1
     */
    @GetMapping(path = "/researchTeam/{nameTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> researchTeams(@PathVariable String nameTeam) {
        String teams = this.service.researchTeams(nameTeam);

        if (teams == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
        }

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    /**
     * N.2
     * N.54
     */
    @GetMapping(path = "/getTeam/{idTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTeam(@PathVariable Long idTeam){
        return this.service.getTeam(idTeam);
    }

    /**
     * N.3
     */
    @PatchMapping(path = "/changeName/{codice}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeName(@PathVariable Long codice, @RequestBody String newName) {
        return this.service.changeName(codice, newName);
    }

    /**
     * N.8
     */
    @DeleteMapping(path = "/deleteTeam/{codice}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTeam(@PathVariable Long codice) {
        return this.service.deleteTeam(codice);
    }

    /*
     * N.26 P1
     */
    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Team newTeam(@RequestBody Team team) {
        return this.service.insert(team);
    }

    /*
     * N.6
     */

    @PatchMapping(path = "/changePrivacyUser/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePrivacyUser(@PathVariable Long code, @RequestParam("privacy") boolean privacy) {
        return this.service.changePrivacyUser(code, privacy);
    }

    /*
     * N.7
     */
    @PatchMapping(path = "/changePrivacyTeam/{codeTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePrivacyTeam(@PathVariable Long codeTeam, @RequestParam("privacy") boolean privacy) {
        return this.service.changePrivacyTeam(codeTeam, privacy);
    }
    
    /**
     * N.34 P2
     */
    @GetMapping(path = "/getAllTeams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllTeams() {
        String teams = this.service.getAllTeams();

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    /**
     * N.26 P2
     */
    @DeleteMapping(path = "/undo/{codice}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity undo(@PathVariable int codice) {
        String teams = this.service.undo(codice);

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }
}
