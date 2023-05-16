package com.ing.rankup_b.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idTeam', team.id_team, 'name', team.name, 'photo', team.photo)) FROM team WHERE team.privacy = 1 AND team.name LIKE ?1%", nativeQuery = true)
    String findByName(String title);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idTeam', team.id_team, 'name:', team.name, 'photo', team.photo)) FROM team WHERE team.privacy = 1 ORDER BY RAND() LIMIT 20;", nativeQuery = true)
    String rand();

    @Query(value = "SELECT user.username, rule.name, rule_completed.timestamp, rule_completed.status FROM rule_completed JOIN user ON rule_completed.id_user = user.id_user JOIN rule ON rule.id_rule = rule_completed.id_rule WHERE rule.team = ?1 AND rule.name LIKE ?2", nativeQuery = true)
    public ArrayList<String> requestHistoryActivityRuleQuery(int id_team, String activity);

    @Query(value = "SELECT user.username, task.name, task_completed.timestamp, task_completed.status FROM task_completed JOIN user ON task_completed.id_user = user.id_user JOIN task ON task.id_task = task_completed.task_id_task WHERE task.team = ?1 AND task.name LIKE ?2", nativeQuery = true)
    public ArrayList<String> requestHistoryActivityTaskQuery(int id_team, String activity);

    @Query(value = "SELECT user.username, rule.name, rule_completed.timestamp, rule_completed.status FROM rule_completed JOIN user ON rule_completed.id_user = user.id_user JOIN rule ON rule.id_rule = rule_completed.id_rule WHERE rule.team = ?1 AND rule_completed.revision_date LIKE ?2", nativeQuery = true)
    public ArrayList<String> requestHistoryDateRuleQuery(int id_team, String date);

    @Query(value = "SELECT user.username, task.name, task_completed.timestamp, task_completed.status FROM task_completed JOIN user ON task_completed.id_user = user.id_user JOIN task ON task.id_task = task_completed.task_id_task WHERE task.team = ?1 AND task_completed.revision_date LIKE ?2", nativeQuery = true)
    public ArrayList<String> requestHistoryDateTaskQuery(int id_team, String date);


    @Query(value = "SELECT rule.name, rule.points, rule_completed.bonus_points FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user JOIN team ON team.id_team = rule.team WHERE rule_completed.status = 1 AND rule.team = ?1 AND rule_completed.id_user = ?2", nativeQuery = true)
    public ArrayList<String> userCompletedActivitiesRuleQuery(int id_team, int id_user);

    @Query(value = "SELECT task.name, task.points, task_completed.bonus_points FROM task_completed JOIN task ON task_completed.task_id_task = task.id_task JOIN user ON user.id_user = task_completed.id_user JOIN team ON team.id_team = task.team WHERE task_completed.status = 1 AND task.team = ?1 AND task_completed.id_user = ?2", nativeQuery = true)
    public ArrayList<String> userCompletedActivitiesTaskQuery(int id_team, int id_user);


    @Query(value = "SELECT prize.id_prize, prize.name, prize.price FROM prize JOIN user_get_prize ON prize.id_prize = user_get_prize.id_prize WHERE prize.id_team = ?1 AND user_get_prize.id_user = ?2", nativeQuery = true)
    public ArrayList<String> userPrizesQuery(int id_team, int id_user);


    @Query(value = "SELECT user.username, rule.name FROM user JOIN rule_completed ON user.id_user = rule_completed.id_user JOIN rule ON rule_completed.id_rule = rule.id_rule WHERE rule_completed.status = 0 AND rule.team = ?1 ", nativeQuery = true)
    public ArrayList<String> pendingActivitiesRuleQuery(int id_team);

    @Query(value = "SELECT user.username, task.name FROM user JOIN task_completed ON user.id_user = task_completed.id_user JOIN task ON task_completed.task_id_task = task.id_task WHERE task_completed.status = 0 AND task.team = ?1 ", nativeQuery = true)
    public ArrayList<String> pendingActivitiesTaskQuery(int id_team);

    @Query(value = "DELETE FROM team WHERE team.id_team = ?1", nativeQuery = true)
    public void deleteByCodiceTeam(Long codice);
}
