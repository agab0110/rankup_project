package com.ing.rankup_b.userGetPrize;

import com.ing.rankup_b.prize.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserGetPrizeService {
    
    @Autowired
    private UserGetPrizeRepository repository;

    public UserGetPrizeService(UserGetPrizeRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Prize> getUserPrize(int id_team, int id_user) {
        ArrayList<String> result = this.repository.userPrize(id_team, id_user);
        ArrayList<Prize> prizes = new ArrayList<Prize>();

        for (String r: result) {
            Prize prize = new Prize();

            prize.setId(Integer.parseInt(r.split(",")[0]));
            prize.setName(r.split(",")[1]);
            prize.setPrice(Integer.parseInt(r.split(",")[2]));

            prizes.add(prize);
        }
        return prizes;
    }
}
