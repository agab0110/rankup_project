package com.ing.rankup_b.adminManageTeam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;

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
        for (AdminManageTeam admin : this.repository.findAll()) {
            if(admin.getUser().getId() == idUser){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("utente gia inserito");
            }
        }
        this.repository.addAdminQuery(idTeam, idUser);
        return ResponseEntity.status(HttpStatus.OK).body("funziona tutto");
        
    }

    /**
     * Funzione per prendere la lista di team in cui è un utente
     * 
     * @param idUser l'utente per cui si vuole la lista dei team
     * @return (200 OK) con la lista dei team se c'è almeno un elemento nella lista,<br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity findTeams(int idUser) {
        List<Team> teams = new ArrayList<>();
        for (AdminManageTeam u : this.repository.findAll()) {
            if(u.getUser().getId() == idUser) {
                teams.add(u.getTeam());
            }
        }

        if(teams.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun team trovato");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(teams);
        }
    }

    public ResponseEntity getAdmin(long idTeam, int idUser) {
        List<AdminManageTeam> admins = this.repository.findAll();
        AdminManageTeam admin = null;

        for (AdminManageTeam a : admins) {
            if(a.getTeam().getCodice() == idTeam && a.getUser().getId() == idUser) {
                admin = a;
                break;
            }
        }

        if(admin == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non è un admin");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        }
    }
}
