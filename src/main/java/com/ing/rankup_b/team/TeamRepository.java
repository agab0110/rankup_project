package com.ing.rankup_b.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idTeam', team.id_team, 'name', team.name, 'photo', team.photo)) FROM team WHERE team.privacy = 1 AND team.name LIKE ?1%", nativeQuery = true)
    String findByName(String title);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('idTeam', team.id_team, 'name:', team.name, 'photo', team.photo)) FROM team WHERE team.privacy = 1 ORDER BY RAND() LIMIT 20;", nativeQuery = true)
    String rand();

    @Query(value = "SELECT rule.name, rule.points, rule_completed.bonus_points FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user JOIN team ON team.id_team = rule.team WHERE rule_completed.status = 1 AND rule.team = ?1 AND rule_completed.id_user = ?2", nativeQuery = true)
    public ArrayList<String> userCompletedActivitiesRuleQuery(int id_team, int id_user);

    @Query(value = "SELECT task.name, task.points, task_completed.bonus_points FROM task_completed JOIN task ON task_completed.task_id_task = task.id_task JOIN user ON user.id_user = task_completed.id_user JOIN team ON team.id_team = task.team WHERE task_completed.status = 1 AND task.team = ?1 AND task_completed.id_user = ?2", nativeQuery = true)
    public ArrayList<String> userCompletedActivitiesTaskQuery(int id_team, int id_user);

    @Query(value = "SELECT user.username, rule.name FROM user JOIN rule_completed ON user.id_user = rule_completed.id_user JOIN rule ON rule_completed.id_rule = rule.id_rule WHERE rule_completed.status = 0 AND rule.team = ?1 ", nativeQuery = true)
    public ArrayList<String> pendingActivitiesRuleQuery(int id_team);

    @Query(value = "SELECT user.username, task.name FROM user JOIN task_completed ON user.id_user = task_completed.id_user JOIN task ON task_completed.task_id_task = task.id_task WHERE task_completed.status = 0 AND task.team = ?1 ", nativeQuery = true)
    public ArrayList<String> pendingActivitiesTaskQuery(int id_team);
}
