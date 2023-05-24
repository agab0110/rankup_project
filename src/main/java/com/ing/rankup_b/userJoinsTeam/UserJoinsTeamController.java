package com.ing.rankup_b.userJoinsTeam;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userJoinsTeamApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class UserJoinsTeamController {

    @Autowired
    private UserJoinsTeamService service;

    public UserJoinsTeamController(UserJoinsTeamService service) {
        this.service = service;
    }

    /**
     * N.15
     */
    @DeleteMapping(path = "/deleteRequest/{codeTeam}/{userId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long codeTeam, @PathVariable int userId) {
        return this.service.deleteUserRequest(codeTeam, userId);
    }

    /**
     * N.11
     */
    @GetMapping(path = "/list/userSearch")
    public ResponseEntity<?> listUserSearch(@RequestParam("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.getListUserSearch(
                        username
                )
        );
    }
    
    /**
     * N.18
     * N.44
     */
    @GetMapping(path = "/partecipantsPoints/{idTeam}")
    public ResponseEntity<?> getPartecipantsPoints(@PathVariable long idTeam) {
        return this.service.findPartecipantsPoints(idTeam);
    }

    /**
     * N.19
     * N.43
     */
    @GetMapping(path = "/partecipants/{idTeam}")
    public ResponseEntity<?> getPartecipants(@PathVariable long idTeam) {
        return this.service.findPartecipants(idTeam);
    }

    /**
     * N.16
     */
    @PatchMapping(path = "/manageRequest/{idTeam}/{idUser}")
    public ResponseEntity<?> manageRequest(@PathVariable long idTeam, @PathVariable int idUser, @RequestParam("status") int status) {
        return this.service.manageRequest(idTeam, idUser, status);
    }
}