package com.ing.rankup_b.team;

import com.ing.rankup_b.prize.Prize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    /**
     * Funzione per ricercare un team per nome
     * @param nameTeam il nome del team da ricercare
     * @return il team trovato
     */
    public String researchTeams(String nameTeam) {
        return this.repository.findByName(nameTeam);
    }

    public String researchTeamsRand() {
        return this.repository.rand();
    }

    public List<Object> getRequestHistoryActivity(int id_team, String activity) {
        ArrayList<String> result_rules = this.repository.requestHistoryActivityRuleQuery(id_team, activity);
        ArrayList<String> result_tasks = this.repository.requestHistoryActivityTaskQuery(id_team, activity);

        ArrayList<String> result = new ArrayList<String>(result_rules);
        result.addAll(result_tasks);

        return this.orderActivities(result);

    }


    public List<Object> getRequestHistoryDate(int id_team, String date) {
        ArrayList<String> result_rules = this.repository.requestHistoryDateRuleQuery(id_team, date);
        ArrayList<String> result_tasks = this.repository.requestHistoryDateTaskQuery(id_team, date);

        ArrayList<String> result = new ArrayList<String>(result_rules);
        result.addAll(result_tasks);

        return this.orderActivities(result);


    }

    public ArrayList<Prize> getUserPrizes(int id_team, int id_user) {
        ArrayList<String> result = this.repository.userPrizesQuery(id_team, id_user);

        ArrayList<Prize> prizes = new ArrayList<Prize>();

        for (String r: result) {
            Prize prize = new Prize();
            prize.setId(Integer.parseInt(r.split(",")[0]));
            prize.setName(r.split(",")[1]);
            prize.setPrice(Integer.parseInt(r.split(",")[2]));
            prizes.add(prize);
        }
        return prizes;
    }


    public ArrayList<Object> getUserCompletedActivities(int id_team, int id_user) {
        ArrayList<String> result_rules = this.repository.userCompletedActivitiesRuleQuery(id_team, id_user);
        ArrayList<String> result_tasks = this.repository.userCompletedActivitiesTaskQuery(id_team, id_user);

        ArrayList<String> result = new ArrayList<String>(result_rules);
        result.addAll(result_tasks);


        ArrayList<Object> activities = new ArrayList<Object>();

        for (String r: result) {
            activities.add(new Object() {
                public String name = r.split(",")[0];
                public int points = Integer.parseInt(r.split(",")[1]) + (r.split(",")[2].equals("null") ? 0 : Integer.parseInt(r.split(",")[2]));
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

        for (int i = 0; i < result.size() - 1; i ++) {
            for (int j = i + 1; j < result.size(); j ++) {
                try {
                    if (sdf.parse(result.get(i).split(",")[2]).compareTo(sdf.parse(result.get(j).split(",")[2])) > 0) {
                        String s = result.get(i);
                        result.set(i, result.get(j));
                        result.set(j, s);
                    }
                } catch (ParseException e) {}
            }
        }

        ArrayList<Object> activities = new ArrayList<Object>();
        for (String r: result) {
            activities.add(new Object() {
                public String username = r.split(",")[0];
                public String name = r.split(",")[1];
                public String date = r.split(",")[2].split(" ")[0];
                public Boolean acceptance = Boolean.parseBoolean(r.split(",")[3]);

            });
        }

        return activities;
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
