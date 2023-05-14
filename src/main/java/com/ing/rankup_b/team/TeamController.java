package com.ing.rankup_b.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teamApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

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
}
