package com.ing.rankup_b.userGetPrize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userGetPrizeApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class UserGetPrizeController {

    @Autowired
    private UserGetPrizeService service;

    public UserGetPrizeController(UserGetPrizeService service) {
        this.service = service;
    }
}
