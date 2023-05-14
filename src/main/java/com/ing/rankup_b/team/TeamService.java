package com.ing.rankup_b.team;

import com.ing.rankup_b.prize.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

}
