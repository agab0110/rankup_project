package com.ing.rankup_b.userJoinsTeam;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.user.User;
import com.ing.rankup_b.userJoinsTeam.UserJoinsTeam.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserJoinsTeamService {
    
    @Autowired
    private UserJoinsTeamRepository repository;

    public UserJoinsTeamService(UserJoinsTeamRepository repository) {
        this.repository = repository;
    }

    /**
     * Funzione per eliminare una richiesta d'accesso nel team
     * @param teamCode il team a cui è stato richiesto l'accesso
     * @param userId l'utente che ha fatto la richiesta
     * @return (200 OK) se viene eliminata correttamente la richiesta, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity deleteUserRequest(Long teamCode, int userId) {
        for (UserJoinsTeam userJoinsTeam : this.repository.findAll()) {
            if (userJoinsTeam.getTeam().getCodice() == teamCode) {
                if (userJoinsTeam.getUser().getId() == userId) {
                    this.repository.delete(userJoinsTeam);
                    return ResponseEntity.status(HttpStatus.OK).body("Richiesta eliminata");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erorre imprevisto");
    }

    public List<Object> getListUserSearch(String username) {    //GIACENTO

        if (username.isBlank()) {
            return new ArrayList<Object>();
        }

        ArrayList<String> result = this.repository.listUserSearchQuery(username + '%');
        ArrayList<Object> users = new ArrayList<Object>();

        int i = 0;

        for (String r: result) {
            if (i == 20)
                break;

            users.add(new Object() {
                public String id_user = r.split(",")[0];
                public String username = r.split(",")[1];
                public String photo = r.split(",")[2];
            });

            i ++;
        }

        return users;

    }

    /**
     * Funzione per prendere la lista dei partecipanti ad un team con il punteggio
     * @param idTeam il team per i partecipanti
     * @return (200 OK) con la lista dei partecipanti se c'è almeno un elemento nella lista,<br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity findPartecipantsPoints(long idTeam) {
        List<UserJoinsTeam> partecipants = new ArrayList<>();
        for (UserJoinsTeam u : this.repository.findAll()) {
            if(u.getTeam().getCodice() == idTeam) {
                if (u.getStatus() == Status.Accettato) {
                    partecipants.add(u);
                }
            }
        }
        if(partecipants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun utente trovato");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(partecipants);
        }
    }

    /**
     * Funzione per prendere la lista dei partecipanti ad un team senza punteggio
     * @param idTeam il team per i partecipanti
     * @return (200 OK) con la lista dei partecipanti se c'è almeno un elemento nella lista,<br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity findPartecipants(long idTeam) {
        List<User> partecipants = new ArrayList<>();
        for (UserJoinsTeam u : this.repository.findAll()) {
            if(u.getTeam().getCodice() == idTeam) {
                if (u.getStatus() == Status.Accettato) {
                    partecipants.add(u.getUser());
                }
            }
        }

        if(partecipants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun utente trovato");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(partecipants);
        }
    }

    /**
     * Funzione per prendere la lista di team in cui è un utente
     * 
     * @param idUser l'utente per cui si vuole la lista dei team
     * @return (200 OK) con la lista dei team se c'è almeno un elemento nella lista,<br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity findTeams(int idUser) {
        List<Team> teams = new ArrayList<>();
        for (UserJoinsTeam u : this.repository.findAll()) {
            if(u.getUser().getId() == idUser) {
                if (u.getStatus() == Status.Accettato) {
                    teams.add(u.getTeam());
                }
            }
        }

        if(teams.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessun team trovato");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(teams);
        }
    }

    public List<Object> userJoinsTeamSearch(String username) {    //GIACENTO

        if (username.isBlank()) {
            return new ArrayList<Object>();
        }

        ArrayList<String> result = this.repository.userJoinsTeamSearch(username + '%');
        ArrayList<Object> users = new ArrayList<Object>();

        int i = 0;

        for (String r: result) {
            if (i == 20)
                break;

            users.add(new Object() {
                public String id_user = r.split(",")[0];
                public String username = r.split(",")[1];
                public String photo = r.split(",")[2];
                public String points = r.split(",")[3];
            });

            i ++;
        }

        return users;

    }
}
