package com.ing.rankup_b.userJoinsTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
