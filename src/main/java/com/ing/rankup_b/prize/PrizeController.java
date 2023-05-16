package com.ing.rankup_b.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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

    @GetMapping(path = "user")
    public ResponseEntity user(@RequestParam("id_team") int id_team, @RequestParam("id_user") int id_user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                //this.service.getUserPrize(id_team, id_user)
                ""
        );
    }
    
    @PostMapping(path = "/createPrize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPrize(@Valid @RequestBody Prize prize){
        return this.service.createPrize(prize);
    }
}
