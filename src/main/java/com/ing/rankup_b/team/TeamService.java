package com.ing.rankup_b.team;

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
}
