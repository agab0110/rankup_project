package com.ing.rankup_b.userGetPrize;

import com.ing.rankup_b.prize.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGetPrizeService {
    
    @Autowired
    private UserGetPrizeRepository repository;

    public UserGetPrizeService(UserGetPrizeRepository repository) {
        this.repository = repository;
    }

    /**
     * Funzione per prendere i premi acquistati da un utente in un determinato team
     * @param idTeam il codice del team in cui si acquista il premio
     * @param idUser l'id dell'utente che ha acquistato il premio
     * @return (400 BAD_REQUEST) nel caso in cui la lista dei premi trovata sia vuota, (200 OK) altrimenti
     */
    public ResponseEntity getUserPrize(long idTeam, int idUser) {
        List<Prize> prizes = new ArrayList<>();

        for (UserGetPrize userGetPrize : this.repository.findAll()) {
            if (userGetPrize.getPrize().getBeloggingTeam().getCodice() == idTeam && userGetPrize.getUser().getId() == idUser) {
                prizes.add(userGetPrize.getPrize());
            }
        }

        if (prizes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premi non trovati");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(prizes);
        }
    }
}
