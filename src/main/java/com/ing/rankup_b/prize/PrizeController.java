package com.ing.rankup_b.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prizeApi")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8200", "http://localhost:4200"})

public class PrizeController {
    
    @Autowired
    private PrizeService service;

    public PrizeController(PrizeService service) {
        this.service = service;
    }    

    @GetMapping(path = "/prize/{id_team}")
    public ResponseEntity Listprize(@PathVariable int id_team) {
       return this.service.Listprize(id_team);
    }
}
