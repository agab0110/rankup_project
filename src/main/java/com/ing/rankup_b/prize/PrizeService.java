package com.ing.rankup_b.prize;

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
    
    public ResponseEntity createPrize(Prize prize){
        this.repository.save(prize);
        return ResponseEntity.status(HttpStatus.OK).body(prize);
    }
}
