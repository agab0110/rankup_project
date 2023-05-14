package com.ing.rankup_b.userGetPrize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGetPrizeService {
    
    @Autowired
    private UserGetPrizeRepository repository;

    public UserGetPrizeService(UserGetPrizeRepository repository) {
        this.repository = repository;
    }
}
