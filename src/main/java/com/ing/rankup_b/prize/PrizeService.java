package com.ing.rankup_b.prize;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;

@Service
public class PrizeService {

    @Autowired
    private PrizeRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    public PrizeService(PrizeRepository repository,TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }    
  
    /**
     * Funzione per prendere i premi relativi ad un team
     * @param codice il codice del team
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> listPrize(int codice){
        List <Prize> prizes = new ArrayList<>();

        for (Prize prize : this.repository.findAll()) {
            if (prize.getBeloggingTeam().getCodice() == codice) {
                prizes.add(prize);
            }
        }
        
        if (prizes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non ci sono premi per questo team");
        }
        return ResponseEntity.status(HttpStatus.OK).body(prizes);
    }
    
    /**
     * Funzione per creare un premio
     * @param prize il premio da creare
     * @param name il nome del premio che verrà controllato se è già esistente
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> createPrize(Prize prize,String name){
        for (Team t : this.teamRepository.findAll()) {
            if (prize.getBeloggingTeam().getCodice() == t.getCodice()) {
              for (Prize r : t.getPrizes()) {
                  if (r.getName().equals(name)) {
                      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome duplicato");
                  }
              }
            }
          }
        this.repository.save(prize);
        return ResponseEntity.status(HttpStatus.OK).body(prize);
    }
    
    /**
     * Funzione per rimuovere un premio da un team
     * @param idTeam l'id del team da cui si vuole rimuovere il premio
     * @param idPrize l'id del premio da rimuovere
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> removePrize(long idTeam, int idPrize){
        for (Prize u : this.repository.findAll()) {
            if (u.getBeloggingTeam().getCodice() == idTeam && u.getId() == idPrize) {
                this.repository.delete(u);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Premio non presente nel team");
    }
}
