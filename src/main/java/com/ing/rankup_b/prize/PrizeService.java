package com.ing.rankup_b.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PrizeService {

    @Autowired
    private PrizeRepository repository;

    public PrizeService(PrizeRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Object> getUserPrize(int id_team, int id_user) {
        ArrayList<String> result = this.repository.userPrizeQuery(id_team, id_user);

        ArrayList<Object> prizes = new ArrayList<Object>();

        for (String r: result) {
            prizes.add(new Object() {
                public String name = r.split(",")[0];
                public int price = Integer.parseInt(r.split(",")[1]);
            });
        }

        return prizes;
    }
}
