package com.ing.rankup_b.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prizeApi")
@CrossOrigin

public class PrizeController {
    
    @Autowired
    private PrizeService service;

    public PrizeController(PrizeService service) {
        this.service = service;
    }    

    /**
     * N.29
     * N.53
     */
    @GetMapping(path = "/prize/{idTeam}")
    public ResponseEntity<?> listPrize(@PathVariable int idTeam) {
       return this.service.listPrize(idTeam);
    }
    
    /**
     * N.9
     */
    @PostMapping(path = "/createPrize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPrize(@Valid @RequestBody Prize prize,@RequestParam("name")String name) {
        return this.service.createPrize(prize,name);
    }


    /** 
     * N.73
    */
    @DeleteMapping(path = "/removePrize/{idTeam}/{idPrize}")
    public ResponseEntity<?> removePrize(@PathVariable int idTeam, @PathVariable int idPrize) {
        return this.service.removePrize(idTeam, idPrize);
    }
}
