package com.ing.rankup_b.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity changePhoto(Long codiceTeam, String photo) {
        if (photo != null) {
            Team team = this.repository.findById(codiceTeam).get();
            team.setPhoto(photo);
            this.repository.save(team);
            return ResponseEntity.status(HttpStatus.OK).body(team);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore cambio foto");
        }
    }

    public ResponseEntity getTeam(long id){
        if(this.repository.findById(id) != null){
            return ResponseEntity.status(HttpStatus.OK).body(this.repository.findById(id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("team non esistente");
    }

        /**
     * Funzione per cambiare il nome di un team
     * 
     * @param codice il codice del team da modificare
     * @param newName il nuovo nome
     * @return (200 OK) e il team modificato se la modifica va a buon fine, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity changeName(Long codice, String newName) {
        for (Team t : (List<Team>)this.repository.findAll()) {
            if (t.getCodice() == codice) {
                t.setName(newName);
                this.repository.save(t);
                return ResponseEntity.status(HttpStatus.OK).body(t);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
    }

    /**
     * Funzione per eliminare un team
     * 
     * @param team il team da eliminare
     * @return (200 OK) se l'eliminazione va a buon fine, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity deleteTeam(Long codice) {
        for (Team t : (List<Team>)this.repository.findAll()) {
            if (t.getCodice() == codice) {
                this.repository.delete(t);
                return ResponseEntity.status(HttpStatus.OK).body("Team eliminato");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
    }
}
