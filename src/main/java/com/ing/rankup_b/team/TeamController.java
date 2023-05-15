package com.ing.rankup_b.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PatchMapping(path = "/changePhoto/{code}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePhoto(@PathVariable Long code, @RequestBody String photo) {
        return this.service.changePhoto(code, photo);
    }

    @GetMapping(path = "/researchTeam/{nameTeam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity researchTeams(@PathVariable String nameTeam) {
        String teams = this.service.researchTeams(nameTeam);

        if (teams == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
        }

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @GetMapping(path = "/researchTeam", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity researchTeamsRand() {
        String teams = this.service.researchTeamsRand();

        if (teams == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
        }

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @GetMapping(path = "history/request/date")
    public ResponseEntity historyRequestDate(@RequestParam("id_team") int id_team, @RequestParam("date") String date) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getRequestHistoryDate(id_team, date)
        );
    }

    @GetMapping(path = "history/request/activity")
    public ResponseEntity historyRequestActivity(@RequestParam("id_team") int id_team, @RequestParam("activity") String activity) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getRequestHistoryActivity(id_team, activity + '%')
        );
    }

    @GetMapping(path = "user/completedActivities")
    public ResponseEntity userCompletedActivities(@RequestParam("id_team") int id_team, @RequestParam("id_user") int id_user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getUserCompletedActivities(id_team, id_user)
        );
    }

    @GetMapping(path = "admin/completedActivities")
    public ResponseEntity adminCompletedActivities(@RequestParam("id_team") int id_team, @RequestParam("id_user") int id_user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getUserCompletedActivities(id_team, id_user)
        );
    }

    @GetMapping(path = "pendingActivities")
    public ResponseEntity pendingActivities(@RequestParam("id_team") int id_team) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getPendingActivities(id_team)
        );
    }

    @GetMapping(path = "user/prizes")
    public ResponseEntity userPrizes(@RequestParam("id_team") int id_team, @RequestParam("id_user") int id_user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getUserPrizes(id_team, id_user)
        );
    }
    
    @GetMapping(path = "/getTeam/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTeam(@PathVariable Long id){
        return this.service.getTeam(id);
    }

    
    @PatchMapping(path = "/changeName/{codice}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeName(@PathVariable Long codice, @RequestBody String newName) {
        return this.service.changeName(codice, newName);
    }

    @DeleteMapping(path = "/deleteTeam/{codice}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTeam(@PathVariable Long codice) {
        return this.service.deleteTeam(codice);
    }
}
