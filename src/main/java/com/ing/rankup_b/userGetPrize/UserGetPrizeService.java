package com.ing.rankup_b.userGetPrize;

import com.ing.rankup_b.prize.Prize;
import com.ing.rankup_b.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
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
     * @return (400 BAD_REQUEST) nel caso in cui la lista dei premi trovata sia vuota, <br>(200 OK) con la lista di premi altrimenti
     */
    public ResponseEntity<?> getUserPrize(long idTeam, int idUser) {
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

    /**
     * Fuzione per aggiungere un premio ad un utente
     * @param idPrize l'id del premio da aggiungere
     * @param idUser l'id dell'utente a cui viene aggiunto il premio
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public UserGetPrize addUserPrize(int idPrize, int idUser) {
        UserGetPrize userGetPrize = new UserGetPrize();
        Prize prize = new Prize();
        prize.setId(idPrize);
        User user = new User();
        user.setId(idUser);

        userGetPrize.setUser(user);
        userGetPrize.setPrize(prize);

        userGetPrize.setDate(Timestamp.from(Instant.now()));
    
        return this.repository.save(userGetPrize);
    }

    /**
     * Funzione per prendere tutti i premi da un team
     * @param idTeam l'id del team da cui prendere i premi
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> getTeamPrizes(long idTeam) {
        List<UserGetPrize> prizes = new ArrayList<>();

        for (UserGetPrize userGetPrize : this.repository.findAll()) {
            if (userGetPrize.getPrize().getBeloggingTeam().getCodice() == idTeam) {
                prizes.add(userGetPrize);
            }
        }

        if (prizes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premi non trovati");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(prizes);
        }
    }
}
