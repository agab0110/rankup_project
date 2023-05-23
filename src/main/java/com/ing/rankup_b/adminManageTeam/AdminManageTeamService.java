package com.ing.rankup_b.adminManageTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminManageTeamService {
    
    @Autowired
    private AdminManageTeamRepository repository;
    


    public AdminManageTeamService(AdminManageTeamRepository repository) {
        this.repository = repository;
    }

    /**
     * funzione per aggiungere menbri al team come admin
     * @param idTeam serve per dire in quale team va l'utente come admin
     * @param idUser serve epr dire quale utente va nel team come admin
     * @return 200 ok o 400 bad request
     */
    public ResponseEntity addAdmin(long idTeam, int idUser) {
        this.repository.addAdminQuery(idTeam, idUser);
        return ResponseEntity.status(HttpStatus.OK).body("funziona tutto");
        
    }

}
