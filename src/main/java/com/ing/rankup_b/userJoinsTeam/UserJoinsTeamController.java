package com.ing.rankup_b.userJoinsTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("userJoinsTeamApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class UserJoinsTeamController {

    @Autowired
    private UserJoinsTeamService service;

    public UserJoinsTeamController(UserJoinsTeamService service) {
        this.service = service;
    }

    @DeleteMapping(path = "/deleteRequest/{codeTeam}/{userId}")
    public ResponseEntity deleteRequest(@PathVariable Long codeTeam, @PathVariable int userId) {
        return this.service.deleteUserRequest(codeTeam, userId);
    }

    @PostMapping(path = "addMember", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMember(@RequestBody Map<String,Object> body) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.addMember(
                        Integer.parseInt(body.get("id_team").toString()), Integer.parseInt(body.get("id_user").toString())
                )
        );// TODO: SEGNALARE
    }

    @GetMapping(path = "list/userSearch")
    public ResponseEntity listUserSearch(@RequestParam("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getListUserSearch(
                        username
                )
        );
    }

    @GetMapping(path = "list/pendingRequests")
    public ResponseEntity listPendingRequests(@RequestParam("id_team") int id_team) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getListPendingRequests(
                        id_team
                )
        );
    }
}
