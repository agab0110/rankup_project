package com.ing.rankup_b.team;

import org.springframework.beans.factory.annotation.Autowired;
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

    
    @GetMapping(path = "/getTeam", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTeam(long id){
        return this.service.getTeam(id);
    }
}
