package com.ing.rankup_b.userGetPrize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userGetPrizeApi")
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8200", "http://localhost:4200" })

public class UserGetPrizeController {

    @Autowired
    private UserGetPrizeService service;

    public UserGetPrizeController(UserGetPrizeService service) {
        this.service = service;
    }

    /**
     * N.23
     * N.37
     */
    @GetMapping(path = "/getPrizes/{idTeam}/{idUser}")
    public ResponseEntity<?> getUserPrize(@PathVariable long idTeam, @PathVariable int idUser) {
        return this.service.getUserPrize(idTeam, idUser);
    }

    /**
     * New
     */
    @GetMapping(path = "/addUserPrizes/{idPrize}/{idUser}")
    public ResponseEntity<?> addUserPrize(@PathVariable int idPrize, @PathVariable int idUser) {
        UserGetPrize userGetPrize = this.service.addUserPrize(idPrize, idUser);
        return ResponseEntity.status(HttpStatus.OK).body(userGetPrize);
    }
}
