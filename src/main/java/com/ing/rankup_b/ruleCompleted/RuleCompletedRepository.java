package com.ing.rankup_b.ruleCompleted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface RuleCompletedRepository extends JpaRepository<RuleCompleted, Integer>{
    @Query(value = "SELECT rule.name, rule.points, rule_completed.bonus_points, rule.description, rule_completed.comment, user.username FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user WHERE rule.team = ?1 AND user.id_user = ?2 AND rule_completed.id_rule_completed = ?3", nativeQuery = true)
    public ArrayList<String> ruleCompletedQuery(int id_team, int id_user, int id_rule_completed);
}
