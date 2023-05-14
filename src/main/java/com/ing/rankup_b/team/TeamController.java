package com.ing.rankup_b.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teamApi")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8200", "http://localhost:4200" })

public class TeamController {

    @Autowired
    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PatchMapping(path = "/changePhoto/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePhoto(@PathVariable Long code, @RequestBody String photo) {
        return this.service.changePhoto(code, photo);
    }

    @GetMapping(path = "/researchTeam/{nameTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity researchTeams(@PathVariable String nameTeam) {
        String teams = this.service.researchTeams(nameTeam);

        if (teams.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @GetMapping(path = "/researchTeam", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity researchTeamsRand() {
        String teams = this.service.researchTeamsRand();

        if (teams.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }
}
