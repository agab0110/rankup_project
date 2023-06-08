package com.ing.rankup_b.userJoinsTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ing.rankup_b.adminManageTeam.AdminManageTeam;
import com.ing.rankup_b.adminManageTeam.AdminManageTeamRepository;
import com.ing.rankup_b.prize.Prize;
import com.ing.rankup_b.team.Team;
import com.ing.rankup_b.team.TeamRepository;
import com.ing.rankup_b.user.User;
import com.ing.rankup_b.userJoinsTeam.UserJoinsTeam.Status;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserJoinsTeamService {
    
    @Autowired
    private UserJoinsTeamRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AdminManageTeamRepository adminRepository;

    public UserJoinsTeamService(UserJoinsTeamRepository repository, TeamRepository teamRepository, AdminManageTeamRepository adminRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * Funzione per l'eliminazione di una richiesta d'accesso in un team
     * @param id l'id della richiesta
     * @return (200 OK) con la conferma dell'eliminazione, errore altrimenti
     */
    public ResponseEntity<?> deleteUserRequest(int id) {
        this.repository.delete(this.repository.findById(id).get());
        return ResponseEntity.status(HttpStatus.OK).body(null);
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
    public ResponseEntity<?> findPartecipantsPoints(long idTeam) {
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
    public ResponseEntity<?> findPartecipants(long idTeam) {
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
     * Funzione per modificare il punteggio di un utente quando riscuote un premio
     * @param idTeam
     * @param idUser
     * @param idPrize
     */
    public ResponseEntity<?> userSubtractPoints(long idTeam, int idUser , int idPrize) {
        for (UserJoinsTeam u : this.repository.findAll()) {
            if(u.getUser().getId() == idUser && u.getTeam().getCodice() == idTeam){
                for(Prize p : u.getTeam().getPrizes()) {
                    if (u.getPoints() >= p.getPrice() && p.getId() == idPrize) {
                        u.setPoints(u.getPoints()- p.getPrice());
                        this.repository.save(u);
                    }
                }
                if(u.getPoints() >= 0) {
                    return ResponseEntity.status(HttpStatus.OK).body(u);
                }
            }  
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Punti non sufficienti o utente non partecipa al Team");
    }

    /**
     * funzione per prendere le richieste di un utente che vuole entrare nel team
     * @param idTeam e il parametro per dire in quale team vuole entrare
     * @return 200 ok o 400 bad request
     */
    public ResponseEntity<?> getRequests(long idTeam){
        List<UserJoinsTeam> requests = new ArrayList<>();
        for (UserJoinsTeam u : this.repository.findAll()) {
            if(u.getTeam().getCodice() == idTeam) {
                if (u.getStatus() == Status.Sospeso) {
                    requests.add(u);
                }
            }
        }
        if(requests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nessuna richiesta trovata");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(requests);
        }
    }

    /**
     * funzione per prendere un utente e inserirlo come player nel team
     * @param idTeam serve per dire il team in cui va inserito l'utente
     * @param idUser serve per dire quale utente va inserito nel team
     * @return ritorna 200 ok o 400 bad request
     */
    public ResponseEntity<?> addUser(long idTeam, int idUser) {
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.addUserQuery(0,1,idTeam,idUser));
    }

    /**
     * Funzione per aggiungere un utente ad un team tramite codice
     * @param codeTeam il codice del team
     * @param idUser l'id dell'utente da aggiungere
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> addUserByCode(String codeTeam, int idUser) {
        for (Team team : this.teamRepository.findAll()) {
            if(team.getCode().equals(codeTeam)) {
                long idTeam = team.getCodice();
                for (UserJoinsTeam userJoinsTeam : repository.findAll()) {
                    if(userJoinsTeam.getUser().getId() == idUser && userJoinsTeam.getTeam().getCodice() == idTeam) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente già presente nel team");
                    }
                }
                for (AdminManageTeam adminManageTeam : adminRepository.findAll()) {
                    if(adminManageTeam.getUser().getId() == idUser && adminManageTeam.getTeam().getCodice() == idTeam) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente già presente nel team");
                    }                    
                }
                if(team.getPrivacy() == true)
                    this.repository.addUserQuery(0,1,idTeam,idUser);
                else
                    this.repository.addUserQuery(0,0,idTeam,idUser);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Codice team non valido");

    }

    /**
     * Funzione per prendere la lista di team in cui è un utente
     * 
     * @param idUser l'utente per cui si vuole la lista dei team
     * @return (200 OK) con la lista dei team se c'è almeno un elemento nella lista,<br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> findTeams(int idUser) {
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

     /**
      * Funzione per accettare una richiesta di accesso ad un team
      * @param idTeam il team per cui si è fatta richiesta
      * @param idUser l'utente che ha fatto richiesta
      * @param status l'accettazione della richiesta
      * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
      */
    public ResponseEntity<?> manageRequest(long idTeam, int idUser, int status) {
        for (UserJoinsTeam u : this.repository.findAll()) {
            if (u.getTeam().getCodice() == idTeam && u.getUser().getId() == idUser && u.getStatus() == Status.Sospeso) {
                if (status == 1) {
                    u.setStatus(Status.Accettato);
                    return ResponseEntity.status(HttpStatus.OK).body(this.repository.save(u));
                } else {
                    u.setStatus(Status.Rifiutato);
                    return ResponseEntity.status(HttpStatus.OK).body(this.repository.save(u));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore");
    }

    public List<Object> userJoinsTeamSearch(int idTeam, String username) {

        if (username.isBlank()) {
            return new ArrayList<Object>();
        }

        ArrayList<String> result = this.repository.userJoinsTeamSearch(idTeam, username + '%');
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

    /**
     * Funzione per rimuovere un utente da un team
     * @param idTeam l'id del team da cui deve essere rimosso l'utente
     * @param idUser l'utente da rimuovere
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> removeUserFromTeam(long idTeam, int idUser){
        for (UserJoinsTeam u : this.repository.findAll()) {
            if (u.getTeam().getCodice() == idTeam && u.getUser().getId() == idUser) {
                this.repository.delete(u);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utente non presente nel team");
    }

    /**
     * Funzione per abbandonare il team
     * @param idTeam l'id del team che va abbandonato
     * @param idUser l'id dell'utente che vuole abbandonare il team
     * @return (200 OK) se i controlli vanno a buon fine, <br>(400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> leaveTeam(long idTeam, int idUser){
        for (UserJoinsTeam u : this.repository.findAll()) {
            if (u.getTeam().getCodice() == idTeam && u.getUser().getId() == idUser) {
                this.repository.delete(u);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("utente non è presente nel team");
    }
}
