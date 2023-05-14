package com.ing.rankup_b.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrizeService {

    @Autowired
    private PrizeRepository repository;

    public PrizeService(PrizeRepository repository) {
        this.repository = repository;
    }    
}
