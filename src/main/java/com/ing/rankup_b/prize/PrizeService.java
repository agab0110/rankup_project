package com.ing.rankup_b.prize;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    
    public ResponseEntity createPrize(Prize prize){
        this.repository.save(prize);
        return ResponseEntity.status(HttpStatus.OK).body(prize)
    }
}
