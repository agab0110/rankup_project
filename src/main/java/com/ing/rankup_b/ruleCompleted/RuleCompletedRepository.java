package com.ing.rankup_b.ruleCompleted;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RuleCompletedRepository extends JpaRepository<RuleCompleted, Integer> {

    @Query(value = "SELECT JSON_OBJECT('username',user.username,'name',rule.name,'timestamp',rule_completed.timestamp,'attached',rule_completed.attached) FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user WHERE rule_completed.id_rule_completed = ?1", nativeQuery = true)
    String findRule(int idRegolaCompletata);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('username', user.name, 'name', rule.name,'data', rule_completed.timestamp,'status', rule_completed.status)) FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user JOIN team ON team.id_team = rule.id_team WHERE team.id_team = ?1 AND user.name LIKE ?2%", nativeQuery = true)
    String findUserHistoryR(int idTeam, String nomeUser);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('username', user.name, 'name', task.name,'data', task_completed.timestamp,'status', task_completed.status)) FROM task_completed JOIN task ON task_completed.task_id_task = task.id_task JOIN user ON user.id_user = task_completed.id_user JOIN team ON team.id_team = task.id_team WHERE team.id_team = ?1 AND user.name LIKE ?2%", nativeQuery = true)
    String findUserHistoryT(int idTeam, String nomeUser);

    @Query(value = "SELECT rule.name, rule.points, rule_completed.bonus_points, rule.description, rule_completed.comment, user.username FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user WHERE rule.id_team = ?1 AND user.id_user = ?2 AND rule_completed.id_rule_completed = ?3", nativeQuery = true)
    public ArrayList<String> ruleCompletedQuery(int id_team, int id_user, int id_rule_completed);

    @Query(value = "SELECT JSON_ARRAYAGG(JSON_OBJECT('id_rule', rule_completed.id_rule_completed, 'name', rule.name, 'username', user.name, 'photo', user.photo)) FROM rule_completed JOIN rule ON rule.id_rule = rule_completed.id_rule join user on user.id_user = rule_completed.id_user WHERE rule_completed.status = 0 and rule.id_team = ?1", nativeQuery = true)
    String pending(int id_team);
}
