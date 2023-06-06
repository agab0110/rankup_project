package com.ing.rankup_b.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    /**
     * Funzione per cambiare la foto del team
     * 
     * @param codiceTeam il team di cui cambiare la foto
     * @param photo      la nuova foto
     * @return (200 OK) se va tutto a buon fine, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> changePhoto(Long codiceTeam, String photo) {
        if (photo != null) {
            Team team = this.repository.findById(codiceTeam).get();
            team.setPhoto(photo);
            this.repository.save(team);
            return ResponseEntity.status(HttpStatus.OK).body(team);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore cambio foto");
        }
    }

    /**
     * Funzione per ricercare un team per nome
     * 
     * @param nameTeam il nome del team da ricercare
     * @return il team trovato
     */
    public String researchTeams(int idUser, String nameTeam) {
        return this.repository.findByName(idUser, nameTeam);
    }

    public ArrayList<Object> getUserCompletedActivities(int id_team, int id_user) {
        ArrayList<String> result_rules = this.repository.userCompletedActivitiesRuleQuery(id_team, id_user);
        ArrayList<String> result_tasks = this.repository.userCompletedActivitiesTaskQuery(id_team, id_user);

        ArrayList<String> result = new ArrayList<String>(result_rules);
        result.addAll(result_tasks);

        ArrayList<Object> activities = new ArrayList<Object>();

        for (String r : result) {
            activities.add(new Object() {
                public String name = r.split(",")[0];
                public int points = Integer.parseInt(r.split(",")[1])
                        + (r.split(",")[2].equals("null") ? 0 : Integer.parseInt(r.split(",")[2]));
            });
        }
        return activities;
    }

    public ArrayList<Object> getPendingActivities(int id_team) {
        ArrayList<String> result_task = this.repository.pendingActivitiesTaskQuery(id_team);
        ArrayList<String> result_rule = this.repository.pendingActivitiesRuleQuery(id_team);

        ArrayList<String> result = new ArrayList<String>(result_rule);
        result.addAll(result_task);

        ArrayList<Object> pending_activities = new ArrayList<Object>();

        for (String r : result) {
            pending_activities.add(new Object() {
                public String username = r.split(",")[0];
                public String activity_name = r.split(",")[1];
            });
        }

        return pending_activities;

    }

    private ArrayList<Object> orderActivities(ArrayList<String> result) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = i + 1; j < result.size(); j++) {
                try {
                    if (sdf.parse(result.get(i).split(",")[2]).compareTo(sdf.parse(result.get(j).split(",")[2])) > 0) {
                        String s = result.get(i);
                        result.set(i, result.get(j));
                        result.set(j, s);
                    }
                } catch (ParseException e) {
                }
            }
        }

        ArrayList<Object> activities = new ArrayList<Object>();
        for (String r : result) {
            activities.add(new Object() {
                public String username = r.split(",")[0];
                public String name = r.split(",")[1];
                public String date = r.split(",")[2].split(" ")[0];
                public Boolean acceptance = Boolean.parseBoolean(r.split(",")[3]);

            });
        }

        return activities;
    }

    public ResponseEntity<?> getTeam(long id){
        if(this.repository.findById(id) != null){
            return ResponseEntity.status(HttpStatus.OK).body(this.repository.findById(id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("team non esistente");
    }

    /**
     * Funzione per cambiare il nome di un team
     * 
     * @param codice  il codice del team da modificare
     * @param newName il nuovo nome
     * @return (200 OK) e il team modificato se la modifica va a buon fine, (400
     *         BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> changeName(Long codice, String newName) {
        Team team = this.repository.findById(codice).get();
        if (team == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");    
        }

        for (Team t : this.repository.findAll()) {
            if (t.getName().equals(newName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome duplicato");
            }
        }

        team.setName(newName);
        this.repository.save(team);

        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    /**
     * Funzione per eliminare un team
     * 
     * @param team il team da eliminare
     * @return (200 OK) se l'eliminazione va a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public ResponseEntity<?> deleteTeam(Long codice) {
        for (Team t : (List<Team>)this.repository.findAll()) {
            if (t.getCodice() == codice) {
                this.repository.delete(t);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
    }

    /*
     * N.26
     */
    public ResponseEntity<?> insert(Team team) {
        team.setCode(this.createCode());
        this.repository.save(team);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    private String createCode() {
        String code = Timestamp.from(Instant.now()).hashCode() + "";
        if(code.charAt(0) == '-')
            code = code.substring(1, code.length());
        return code;
    }

    /*
     * N.7
     */
    public ResponseEntity changePrivacyTeam(long idTeams, boolean privacy) {
        for(Team t : (List<Team>)this.repository.findAll()) {
            if(t.getCodice() == idTeams) {
                t.setPrivacy(privacy);
                this.repository.save(t);
                return ResponseEntity.status(HttpStatus.OK).body(t);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
    }

    /*
     * N.6
     */
    public ResponseEntity changePrivacyUser(long idTeams, boolean privacy) {
        for(Team t : (List<Team>)this.repository.findAll()) {
            if(t.getCodice() == idTeams) {
                t.setPointVisibility(privacy);
                this.repository.save(t);
                return ResponseEntity.status(HttpStatus.OK).body(t);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
    }
    
    /**
     * Funzione per ricercare i team pubblici
     * 
     * @return i team trovati
     */
    public String getAllTeams(int idUser) {
        return this.repository.findTeams(idUser);
    }

    /**
     * Funzione per annullare la creazione di un team
     * 
     * @param codice l'id del team da eliminare
     * @return (200 OK) se l'eliminazione va a buon fine, (400 BAD_REQUEST)
     *         altrimenti
     */
    public String undo(int codice) {
        return this.repository.undo(codice);
    }

    /**
     * Funzione per cercare un team tramite il codice di accesso
     * @param teamCode il codice di accesso per il team
     * @return (200 OK) con il team se il team viene trovato, (400 BAD_REQUEST) altrimenti
     */
    public ResponseEntity<?> getTeamByCode(String teamCode) {
        for (Team team : this.repository.findAll()) {
            if (team.getCode().equals(teamCode)) {
                return ResponseEntity.status(HttpStatus.OK).body(team); 
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team non trovato");
    }
}