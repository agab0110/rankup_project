package com.ing.rankup_b.userJoinsTeam;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/userJoinsTeamApi")
@CrossOrigin

public class UserJoinsTeamController {

    @Autowired
    private UserJoinsTeamService service;

    public UserJoinsTeamController(UserJoinsTeamService service) {
        this.service = service;
    }

    /**
     * N.15
     */
    @DeleteMapping(path = "/deleteRequest/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id) {
        return this.service.deleteUserRequest(id);
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

    @PatchMapping(path = "subtractUserPoints/{idTeam}/{idUser}/{idPrize}")
    public ResponseEntity userSubtractPoints(@PathVariable int idTeam, @PathVariable int idUser, @PathVariable int idPrize) {
        return this.service.userSubtractPoints(idTeam, idUser, idPrize);
    }
    
    /*
     * N.14
     */
    @GetMapping(path = "/requests/{idTeam}")
    public ResponseEntity<?> getRequests(@PathVariable long idTeam) {
        return this.service.getRequests(idTeam);
    }
    
    /*
     * N.12
     */
    @PostMapping(path = "/addUser")
    public ResponseEntity<?> addUser(@RequestParam(value = "idTeam", required = true) long idTeam, @RequestParam(value = "idUser", required = true) int idUser) {
        return service.addUser(idTeam,idUser);
    }

    @PostMapping(path = "/addUserByCode")
    public ResponseEntity<?> addUserByCode(@RequestParam(value = "codeTeam", required = true) String codeTeam, @RequestParam(value = "idUser", required = true) int idUser) {
        return service.addUserByCode(codeTeam,idUser);
    }

    /**
     * N.41
     */
    @GetMapping(path = "/teams/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTeams(@PathVariable int idUser) {
        return this.service.findTeams(idUser);
    }
    
    /*
     * N.16
     */
    @PatchMapping(path = "/manageRequest/{idTeam}/{idUser}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> manageRequest(@PathVariable long idTeam, @PathVariable int idUser, @RequestBody String status) {
        int s = Integer.parseInt(status);
        return this.service.manageRequest(idTeam, idUser, s);
    }

    /**
     * N.69
     */
    @GetMapping(path = "/list/userjoinsteamsearch/{idTeam}")
    public ResponseEntity<?> userJoinsTeamSearch(@PathVariable int idTeam, @RequestParam("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.userJoinsTeamSearch(
                        idTeam, username
                )
        );
    }

    @DeleteMapping(path = "/leaveTeam/{idTeam}/{idUser}")
    public ResponseEntity<?> leaveTeam(@PathVariable int idTeam, @PathVariable int idUser) {
        return this.service.leaveTeam(idTeam, idUser);
    }
}