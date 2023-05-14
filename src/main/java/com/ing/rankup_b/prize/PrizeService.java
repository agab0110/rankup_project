package com.ing.rankup_b.prize;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PrizeService {

    @Autowired
    private PrizeRepository repository;

    public PrizeService(PrizeRepository repository) {
        this.repository = repository;
    }    
    public ResponseEntity  Listprize(int codice){
        List <Prize> prizes = new ArrayList<>();

        for (Prize prize : (List<Prize>)this.repository.findAll()) {
            if (prize.getBeloggingTeam().getCodice() == codice) {
                prizes.add(prize);
            }
        }
        
        if (prizes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono premi per questo team");
        }
        return ResponseEntity.status(HttpStatus.OK).body(prizes);
    }
}
